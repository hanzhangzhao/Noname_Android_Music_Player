package com.example.tryandroidmusicplayer.Activities;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.adapters.MusicGridAdapter;
import com.example.tryandroidmusicplayer.adapters.MusicListAdapter;
import com.example.tryandroidmusicplayer.helps.RealmHelp;
import com.example.tryandroidmusicplayer.models.MusicSourceModel;
import com.example.tryandroidmusicplayer.views.GridItemSpaceDecoration;

public class MainActivity extends BaseActivity {

    private RecyclerView myRVGrid, myRVList;
    private MusicGridAdapter myGridAdapter;
    private MusicListAdapter myListAdapter;

    private RealmHelp myRealmHelp;
    private MusicSourceModel myMusicSourceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private  void initData(){
        myRealmHelp = new RealmHelp();
        myMusicSourceModel = myRealmHelp.getMusicSource();

    }

    private void initView(){
        initNavbar(false, "Noname Music Player",true);

        myRVGrid = findId(R.id.rv_grid);
        myRVGrid.setLayoutManager(new GridLayoutManager(this, 3));
        myRVGrid.addItemDecoration(new GridItemSpaceDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize),myRVGrid));

        myRVGrid.setNestedScrollingEnabled(false);
        myGridAdapter = new MusicGridAdapter(this, myMusicSourceModel.getAlbum());
        myRVGrid.setAdapter(myGridAdapter);

        /**
         * when nest scrollview and recyclerView together, the calculation of recyclerView height will be wrong
         *
         * Solution1: Define height of recyclerView in the layout if it is already known
         * Solution2: if list height is unknown, manually calculate RecyclerView
         */
        myRVList = findId(R.id.rv_list);
        myRVList.setLayoutManager(new LinearLayoutManager(this));
        myRVList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        avoid separate scroll bar for recyclerView lists
        myRVList.setNestedScrollingEnabled(false);
        myListAdapter = new MusicListAdapter(this, myRVList,myMusicSourceModel.getHot());
        myRVList.setAdapter(myListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRealmHelp.close();
    }
}
