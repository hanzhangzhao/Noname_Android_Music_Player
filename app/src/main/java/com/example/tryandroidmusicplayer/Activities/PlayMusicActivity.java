package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tryandroidmusicplayer.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    private ImageView myIvBg;

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
                .load("https://simple.wikipedia.org/wiki/Picture#/media/File:Pictor_A_composite.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 1)))
                .into(myIvBg);
    }

    public void onBackClick(View view){
        onBackPressed();
    }
}
