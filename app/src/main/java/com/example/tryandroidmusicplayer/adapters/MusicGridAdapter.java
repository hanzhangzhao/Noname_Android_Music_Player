package com.example.tryandroidmusicplayer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tryandroidmusicplayer.Activities.AlbumListActivity;
import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.models.AlbumModel;

import java.util.List;

public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {

    private Context myContext;

    private List<AlbumModel> myDataSource;

    public MusicGridAdapter(Context context, List<AlbumModel> dataSource) {
        myContext = context;
        this.myDataSource = dataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.music_item_grid, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final AlbumModel albumModel = myDataSource.get(position);

        Glide.with(myContext)
                .load(albumModel.getPoster())
                .into(viewHolder.IVIcon);

        viewHolder.myTVPlayNum.setText(albumModel.getPlayNum());
        viewHolder.myTVListName.setText(albumModel.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myContext, AlbumListActivity.class);
                intent.putExtra(AlbumListActivity.ALBUM_ID, albumModel.getAlbumId());
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDataSource.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView IVIcon;
        View itemView;
        TextView myTVPlayNum, myTVListName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            IVIcon = itemView.findViewById(R.id.iv_icon);

            myTVPlayNum = itemView.findViewById(R.id.tv_play_num);
            myTVListName = itemView.findViewById(R.id.tv_list_name);
        }
    }
}
