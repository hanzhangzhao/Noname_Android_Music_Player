package com.example.tryandroidmusicplayer.views;

import android.content.Context;
import android.os.Build;
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

public class PlayMusicView extends FrameLayout {

    private Context myContext;
    private View myView;
    private FrameLayout myFlPlayMusic;
    private ImageView myIVIcon, myIVArm, myIVPlay;

    private Animation myPlayMusicAnim, myPlayArmAnim, myStopArmAnim;


    public PlayMusicView(Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context){
        this.myContext = context;

        myView = LayoutInflater.from(myContext).inflate(R.layout.play_music, this, false);

        myFlPlayMusic = myView.findViewById(R.id.fl_play_music);
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
        myIVPlay = myView.findViewById(R.id.iv_play);

        addView(myView);
    }

    /**
     * Start playing music
     */
    public void playMusic() {
        myIVPlay.setVisibility(View.GONE);
        myFlPlayMusic.startAnimation(myPlayMusicAnim);
        myIVArm.startAnimation(myPlayArmAnim);
    }

    /**
     * Stop playing music
     */
    public void StopMusic() {
        myIVPlay.setVisibility(View.VISIBLE);
        myFlPlayMusic.clearAnimation();
        myIVArm.startAnimation(myStopArmAnim);

    }

    /**
     * set album surface pic inside the disc
     */
    public void setMusicIcon(String icon){

        Glide.with(myContext.getApplicationContext())
                .load(icon)
                .into(myIVIcon);

    }
}
