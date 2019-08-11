package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.adapters.MusicListAdapter;
import com.example.tryandroidmusicplayer.helps.RealmHelp;
import com.example.tryandroidmusicplayer.models.AlbumModel;

public class AlbumListActivity extends BaseActivity {

    public static final String ALBUM_ID = "albumId";

    private RecyclerView myRVList;
    private MusicListAdapter myAdapter;

    private String myAlbumId;
    private RealmHelp myRealmHelp;
    private AlbumModel myAlbumModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initData();
        initView();
    }

    private void initData() {
        myAlbumId = getIntent().getStringExtra(ALBUM_ID);
        myRealmHelp = new RealmHelp();
        myAlbumModel = myRealmHelp.getAlbum(myAlbumId);
    }

    private void initView(){
        initNavbar(true, "Playlist: list", false);

        myRVList = findId(R.id.rv_list);
        myRVList.setLayoutManager(new LinearLayoutManager(this));
        myRVList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        second param of MusicListAdapter is used to calculate the height of recyclerView when it is used with scrollview
        myAdapter = new MusicListAdapter(this,null, myAlbumModel.getList());
        myRVList.setAdapter(myAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRealmHelp.close();
    }
}
