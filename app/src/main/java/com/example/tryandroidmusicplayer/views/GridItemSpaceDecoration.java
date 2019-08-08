package com.example.tryandroidmusicplayer.views;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridItemSpaceDecoration extends RecyclerView.ItemDecoration {

    private int mySpace;

    public GridItemSpaceDecoration(int space, RecyclerView parent){
        mySpace = space;
        getRecyclerViewOffsets(parent);
    }

    /**
     *
     * @param outRect rectangle out bound for item
     * @param view ItemView itself
     * @param parent RecycleView
     * @param state RecycleView states
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = mySpace;

////        check if current item is the first item in a row
//        if(parent.getChildLayoutPosition(view)%3 == 0){
//            outRect.left = 0;
//        }
//        not working

//        if margin is negative, View will expand the outbound
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mySpace;
        parent.setLayoutParams(layoutParams);
    }

    private void getRecyclerViewOffsets (RecyclerView parent) {
//        if margin is negative, View will expand the outbound
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mySpace;
        parent.setLayoutParams(layoutParams);
    }
}
