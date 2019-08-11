package com.example.tryandroidmusicplayer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;

import com.example.tryandroidmusicplayer.Activities.WelcomeActivity;
import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.helps.MediaPlayerHelp;
import com.example.tryandroidmusicplayer.models.MusicModel;

/**
 * 1. use service to connect PlayMusicView and MediaPlayHelp
 * 2. PlayMusicView -- service
 *      a. play pause music
 *      b. start service, bind service, unbind service
 * 3. service -- MediaPlayHelp
 *      a. play pause music
 *      b. a listener to listen if the music is finished playing, stop service
 */
public class MusicService extends Service {

    private MediaPlayerHelp myMediaPlayerHelp;
    private MusicModel myMusicModel;

//    notification id can't be 0
    public static final int NOTIFICATION_ID = 1;

    public MusicService() {
    }

    public class MusicBind extends Binder{
        /**
         * set music (MusicModel)
         */
        public  void setMusic (MusicModel musicModel){
            myMusicModel = musicModel;

            startForeground();
        }

        /**
         * play music
         */
        public void playMusic(){
            /**
             * 1.check if current music is playing now
             * 2.if the selected music is playing, call start()
             * 3.if current playing music is not the selected one, call setPath()
             */
            if (myMediaPlayerHelp.getPath()!=null && myMediaPlayerHelp.getPath().equals(myMusicModel.getPath())){
                myMediaPlayerHelp.start();
            }else{
                myMediaPlayerHelp.setPath(myMusicModel.getPath());
                myMediaPlayerHelp.setOnMediaPlayerHelperListerner(new MediaPlayerHelp.OnMediaPlayerHelperListerner() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        myMediaPlayerHelp.start();
                    }

                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopSelf();
                    }
                });
            }
        }

        /**
         * pause music
         */
        public void stopMusic(){
            myMediaPlayerHelp.pause();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MusicBind();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myMediaPlayerHelp = MediaPlayerHelp.getInstance(this);
    }

    /**
     * system won't allow invisible background service play by default
     * need to use Notification to play music even if we killed the app
     */

    /**
     * make service visible foreground
     */
    private void startForeground(){

        /**
         * Notification click jump intent
         */
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this,0,new Intent(this, WelcomeActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);

        /**
         * create Notification
         */
        Notification notification = null;

        /**
         * android API 26 above NotificationChannel
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = createNotificationChannel();
            notification = new Notification.Builder(this, channel.getId())
                    .setContentTitle(myMusicModel.getName())
                    .setContentText(myMusicModel.getAuthor())
                    .setSmallIcon(R.mipmap.welcome)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
        else{
            notification = new Notification.Builder(this)
                    .setContentTitle(myMusicModel.getName())
                    .setContentText(myMusicModel.getAuthor())
                    .setSmallIcon(R.mipmap.welcome)
                    .setContentIntent(pendingIntent)
                    .build();
        }

        /**
         * set notification display in foreground
         */
        startForeground(NOTIFICATION_ID, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel createNotificationChannel () {
        String channelId = "nonamePlayer";
        String channelName = "NonameMusicService";
        String Description = "NonameMusicPlayer";
        NotificationChannel channel = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(Description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        channel.setShowBadge(false);

        return channel;

    }
}
