package com.example.tryandroidmusicplayer.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

public class EqualHeightWidthView extends AppCompatImageView {

    public EqualHeightWidthView(Context context) {
        super(context);
    }

    public EqualHeightWidthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EqualHeightWidthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

////        get width of View
//        int width = MeasureSpec.getSize(widthMeasureSpec);
////        get mode of View: match_parent/ warp_content/ dp
//        int mode = MeasureSpec.getMode(widthMeasureSpec);
    }
}
