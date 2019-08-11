package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.helps.RealmHelp;
import com.example.tryandroidmusicplayer.models.MusicModel;
import com.example.tryandroidmusicplayer.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    public static final String MUSIC_ID = "musicId";

    private ImageView myIvBg;
    private PlayMusicView myPlayMusicView;

    private String myMusicId;
    private RealmHelp myRealHelp;
    private MusicModel myMusicModel;
    private TextView myTVName, myTVAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

//        hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initData();
        initView();
    }

    private void initData() {
        myMusicId = getIntent().getStringExtra(MUSIC_ID);
        myRealHelp = new RealmHelp();
        myMusicModel = myRealHelp.getMusic(myMusicId);
    }

    private void initView(){

        myIvBg = findId(R.id.iv_bg);
        myTVName = findId(R.id.tv_name);
        myTVAuthor = findId(R.id.tv_author);

//        glide-transformations
        Glide.with(this)
                .load(myMusicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 2)))
                .into(myIvBg);

        myTVName.setText(myMusicModel.getName());
        myTVAuthor.setText(myMusicModel.getAuthor());

        myPlayMusicView = findId(R.id.play_music_view);
//        myPlayMusicView.setMusicIcon(myMusicModel.getPoster());
        myPlayMusicView.setMusic(myMusicModel);
//        myPlayMusicView.playMusic(myMusicModel.getPath());
        myPlayMusicView.playMusic();
    }

    public void onBackClick(View view){
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPlayMusicView.destory();
        myRealHelp.close();
    }
}
