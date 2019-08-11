package com.example.tryandroidmusicplayer.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuView;

import com.bumptech.glide.Glide;
import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.helps.MediaPlayerHelp;
import com.example.tryandroidmusicplayer.models.MusicModel;
import com.example.tryandroidmusicplayer.service.MusicService;

public class  PlayMusicView extends FrameLayout {

    private Context myContext;
    private View myView;
    private FrameLayout myFlPlayMusic;
    private ImageView myIVIcon, myIVArm, myIVPlay;
//    private MediaPlayerHelp myMediaPlayerHelp;

    private Animation myPlayMusicAnim, myPlayArmAnim, myStopArmAnim;

    private boolean isPlaying, isBindService;
//    private String myPath;

    private Intent myServiceIntent;
    private MusicService.MusicBind myMusicBind;
    private MusicModel myMusicModel;

    public PlayMusicView(Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        myContext = context;

        myView = LayoutInflater.from(myContext).inflate(R.layout.play_music, this, false);

        myFlPlayMusic = myView.findViewById(R.id.fl_play_music);
        myFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playPause();
            }
        });
        myIVIcon = myView.findViewById(R.id.circle_iv_icon);
        myIVArm = myView.findViewById(R.id.iv_arm);
        myIVPlay = myView.findViewById(R.id.iv_play);

        /**
         * I. 1. Disc rotate animation
         *    2. tone arm pointing to disc
         *    3. tone arm left disc
         * II. startAnimation
         */
        myPlayMusicAnim = AnimationUtils.loadAnimation(myContext, R.anim.play_music_ani);
        myPlayArmAnim = AnimationUtils.loadAnimation(myContext, R.anim.play_arm_anim);
        myStopArmAnim = AnimationUtils.loadAnimation(myContext, R.anim.stop_arm_anim);

        addView(myView);

//        myMediaPlayerHelp = MediaPlayerHelp.getInstance(myContext);
    }

    /**
     * play/pause
     */
    private void playPause() {
        if (isPlaying) {
            stopMusic();
        }else {
//            playMusic(myPath);
            playMusic();
        }
    }

//    /**
//     * Start playing music
//     */
//    public void playMusic(String path) {
//        myPath = path;
//        isPlaying = true;
//        myIVPlay.setVisibility(GONE);
//        myFlPlayMusic.startAnimation(myPlayMusicAnim);
//        myIVArm.startAnimation(myPlayArmAnim);
//
//        /**
//         * 1.check if current music is playing now
//         * 2.if the selected music is playing, call start()
//         * 3.if current playing music is not the selected one, call setPath()
//         */
//        if (myMediaPlayerHelp.getPath()!=null && myMediaPlayerHelp.getPath().equals(path)){
//            myMediaPlayerHelp.start();
//        }else{
//            myMediaPlayerHelp.setPath(path);
//            myMediaPlayerHelp.setOnMediaPlayerHelperListerner(new MediaPlayerHelp.OnMediaPlayerHelperListerner() {
//                @Override
//                public void onPrepared(MediaPlayer mediaPlayer) {
//                    myMediaPlayerHelp.start();
//                }
//            });
//        }
//    }

    public void playMusic() {
        isPlaying = true;
        myIVPlay.setVisibility(GONE);
        myFlPlayMusic.startAnimation(myPlayMusicAnim);
        myIVArm.startAnimation(myPlayArmAnim);

        startMusicService();
    }

    /**
     * Stop playing music
     */
    public void stopMusic() {
        isPlaying = false;
        myIVPlay.setVisibility(VISIBLE);
        myFlPlayMusic.clearAnimation();
        myIVArm.startAnimation(myStopArmAnim);

//        myMediaPlayerHelp.pause();
        if (myMusicBind != null)
            myMusicBind.stopMusic();
    }

    /**
     * set album surface pic inside the disc
     */
//    public void setMusicIcon(String icon){
//
//        Glide.with(myContext)
//                .load(icon)
//                .into(myIVIcon);
//    }
    public void setMusicIcon(){
        Glide.with(myContext)
                .load(myMusicModel.getPoster())
                .into(myIVIcon);
    }

    public void setMusic (MusicModel musicModel){
        myMusicModel = musicModel;
        setMusicIcon();
    }

    /**
     * start music service
     */
    private void startMusicService() {
//        start service
        if (myServiceIntent == null) {
            myServiceIntent = new Intent(myContext, MusicService.class);
            myContext.startService(myServiceIntent);
        }else {
            myMusicBind.playMusic();
        }
//        bind service, check if current is not bind
        if (!isBindService){
            isBindService = true;
            myContext.bindService(myServiceIntent,conn,Context.BIND_AUTO_CREATE);
        }
    }

    /**
     * cancel bind
     */
    public void destory(){
//        if service is already bind, cancel bind
        if(isBindService){
            isBindService = false;
            myContext.unbindService(conn);
        }
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myMusicBind = (MusicService.MusicBind) iBinder;
            myMusicBind.setMusic(myMusicModel);
            myMusicBind.playMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}
