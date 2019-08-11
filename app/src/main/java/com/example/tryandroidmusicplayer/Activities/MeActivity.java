package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.helps.UserHelp;
import com.example.tryandroidmusicplayer.utils.UserUtils;

public class MeActivity extends BaseActivity {

    private TextView myUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    private void initView(){
        initNavbar(true, "My Account",false);

        myUsername = findId(R.id.username);
        myUsername.setText("Username: "+ UserHelp.getInstance().getPhone());
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
