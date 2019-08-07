package com.example.tryandroidmusicplayer.Activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;

import com.example.tryandroidmusicplayer.R;

public class BaseActivity extends Activity {

    private ImageView IVback, IVme;
    private TextView TVtitle;

    /**
     * Receive id for a resource and return T, which is a child class of a View
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findId (@IdRes int id) {
        return findViewById(id);
    }


    protected void initNavbar (boolean isShowBack, String title, boolean isShowMe){

        IVback = findId(R.id.img_back);
        IVme = findId(R.id.img_me);
        TVtitle = findId(R.id.nav_title);

        IVback.setVisibility(isShowBack ? View.VISIBLE: View.GONE);
        IVme.setVisibility(isShowMe ? View.VISIBLE: View.GONE);
        TVtitle.setText(title);

        IVback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        IVme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseActivity.this, MeActivity.class));
            }
        });

    }
}
