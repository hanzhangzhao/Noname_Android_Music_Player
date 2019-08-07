package com.example.tryandroidmusicplayer.Activities;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.adapters.MusicGridAdapter;

public class MainActivity extends BaseActivity {

    private RecyclerView myRVGrid;
    private MusicGridAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        initNavbar(false, "Noname Music Player",true);

        myRVGrid = findId(R.id.rv_grid);
        myRVGrid.setLayoutManager(new GridLayoutManager(this, 3));
        myAdapter = new MusicGridAdapter(this);
        myRVGrid.setAdapter(myAdapter);
    }
}
