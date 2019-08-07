package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.utils.UserUtils;

public class MeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    private void initView(){
        initNavbar(true, "My Account",false);
    }

    /**
     * change password onclick
     */
    public void onChangeClick(View v){
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    /**
     * logout onclick
     */
    public void onLogoutClick(View v) {
        UserUtils.logout(this);
    }
}
