package com.example.tryandroidmusicplayer.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuView;

import com.bumptech.glide.Glide;
import com.example.tryandroidmusicplayer.R;

public class PlayMusicView extends FrameLayout {

    private Context myContext;
    private View myView;
    private ImageView myIVIcon;

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

        myIVIcon = myView.findViewById(R.id.circle_iv_icon);

        addView(myView);
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
