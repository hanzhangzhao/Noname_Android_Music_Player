package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.adapters.MusicListAdapter;

public class AlbumListActivity extends BaseActivity {

    private RecyclerView myRVList;
    private MusicListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initView();
    }

    private void initView(){
        initNavbar(true, "Playlist: list", false);

        myRVList = findId(R.id.rv_list);
        myRVList.setLayoutManager(new LinearLayoutManager(this));
        myRVList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        second param of MusicListAdapter is used to calculate the height of recyclerView when it is used with scrollview
        myAdapter = new MusicListAdapter(this,null);
        myRVList.setAdapter(myAdapter);
    }
}
