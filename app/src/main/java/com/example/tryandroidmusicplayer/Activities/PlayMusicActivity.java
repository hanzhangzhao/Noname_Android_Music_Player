package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    private ImageView myIvBg;
    private PlayMusicView myPlayMusicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

//        hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    private void initView(){

        myIvBg = findId(R.id.iv_bg);
//        glide-transformations
        Glide.with(this)
                .load("https://www.famousbirthdays.com/faces/bach-johann-image.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 2)))
                .into(myIvBg);

        myPlayMusicView = findId(R.id.play_music_view);
        myPlayMusicView.setMusicIcon("https://www.famousbirthdays.com/faces/bach-johann-image.jpg");
    }

    public void onBackClick(View view){
        onBackPressed();
    }
}
