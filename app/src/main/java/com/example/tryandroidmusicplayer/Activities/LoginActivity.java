package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.utils.UserUtils;
import com.example.tryandroidmusicplayer.views.InputView;

public class LoginActivity extends BaseActivity {

    private InputView myInputPhone, myInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        initNavbar(false, "Login",false);

        myInputPhone = findId(R.id.input_phone);
        myInputPassword = findId(R.id.input_password);
    }

    public void onRegisterClick(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onLoginClick (View v){

        String phone = myInputPhone.getInputStr();
        String password = myInputPassword.getInputStr();

//        validate user input
        if(!UserUtils.validateLogin(this,phone,password)){
            return;
        }

//        redirect to main page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
