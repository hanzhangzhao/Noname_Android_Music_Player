package com.example.tryandroidmusicplayer.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * 1. play music in an activity. bind music to activity, the music will stop if exit the activity
 * 2. bind music to application by a singleton, when application runs, music plays; music stop when application is killed
 * 3. use service to play music. music won't stop as long as the service remains
 */
public class MediaPlayerHelp {

    private static MediaPlayerHelp instance;

    private Context myContext;
    private MediaPlayer myMediaPlayer;
    private OnMediaPlayerHelperListerner onMediaPlayerHelperListerner;
    private String myPath;

    public void setOnMediaPlayerHelperListerner(OnMediaPlayerHelperListerner onMediaPlayerHelperListerner) {
        this.onMediaPlayerHelperListerner = onMediaPlayerHelperListerner;
    }

    public static MediaPlayerHelp getInstance(Context context){

        if(instance == null){
            synchronized (MediaPlayerHelp.class){
                if (instance == null){
                    instance = new MediaPlayerHelp(context);
                }
            }
        }

        return instance;
    }

    private MediaPlayerHelp(Context context){

        myContext = context;
        myMediaPlayer = new MediaPlayer();

    }

    /**
     * 1.setPath: location of current playing music
     * 2.start: play music
     * 3.pause: pause music
     */
    public void setPath (String path){
//        myPath = path;
        /**
         * 1.if music is playing, reset player status
         * 2.set current playing music path
         * 3.prepare to play
         */
//        if music is playing or choose another music, reset player status
        if (myMediaPlayer.isPlaying() || !path.equals(myPath)){
            myMediaPlayer.reset();
        }
        myPath = path;
//        set current playing music path
        try {
            myMediaPlayer.setDataSource(myContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        prepare to play
        myMediaPlayer.prepareAsync();
        myMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (onMediaPlayerHelperListerner != null){
                    onMediaPlayerHelperListerner.onPrepared(mediaPlayer);
                }
            }
        });

//        monitor if music is finished
        myMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(onMediaPlayerHelperListerner != null){
                    onMediaPlayerHelperListerner.onCompletion(mediaPlayer);
                }
            }
        });

    }

    /**
     * return the path of the music currently playing
     * @return
     */
    public String getPath () {
        return myPath;
    }

    /**
     * start playing music
     */
    public void start() {
        if (myMediaPlayer.isPlaying()) return;
        myMediaPlayer.start();
    }

    /**
     * pause music
     */
    public void pause() {
        myMediaPlayer.pause();
    }

    public interface OnMediaPlayerHelperListerner {
        void onPrepared(MediaPlayer mediaPlayer);

        void onCompletion (MediaPlayer mediaPlayer);
    }
}
