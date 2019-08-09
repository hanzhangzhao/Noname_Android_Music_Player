package com.example.tryandroidmusicplayer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tryandroidmusicplayer.Activities.PlayMusicActivity;
import com.example.tryandroidmusicplayer.R;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context myContext;
    private View myItemView;
    private RecyclerView myRV;
    private boolean isCalculateRVHeight;

    public MusicListAdapter (Context context, RecyclerView recyclerView){
        myContext = context;
        myRV = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myItemView = LayoutInflater.from(myContext).inflate(R.layout.music_list_item, parent, false);
        return new ViewHolder(myItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        setRecyclerViewHeight();

        Glide.with(myContext)
                .load("https://www.famousbirthdays.com/faces/bach-johann-image.jpg")
                .into(viewHolder.IVIcon);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myContext, PlayMusicActivity.class);
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    /**
     * 1.Get ItemView height
     * 2.Get ItemView number
     * 3.RecyclerView = itemViewHeight * itemViewNum
     */
    private void setRecyclerViewHeight() {

        if (isCalculateRVHeight||myRV == null) return;

        isCalculateRVHeight = true;

//        Get ItemView height
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) myItemView.getLayoutParams();
//        Get ItemView num
        int itemCount = getItemCount();
        int recyclerViewHeight = layoutParams.height*itemCount;
//        Set recyclerView height
        LinearLayout.LayoutParams recyclerViewLayoutParams = (LinearLayout.LayoutParams) myRV.getLayoutParams();
        recyclerViewLayoutParams.height = recyclerViewHeight;
        myRV.setLayoutParams(recyclerViewLayoutParams);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView IVIcon;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            IVIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
