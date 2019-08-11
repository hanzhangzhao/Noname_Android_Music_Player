package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.utils.UserUtils;
import com.example.tryandroidmusicplayer.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView myInputPhone, myInputPassword, myInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        initView();
    }

    private void initView() {
        initNavbar(true,"Register",false);

        myInputPhone = findId(R.id.input_phone);
        myInputPassword = findId(R.id.input_password);
        myInputPasswordConfirm = findId(R.id.input_password_confirm);
    }

    /**
     * Register button onClick
     * 1.user input verify: a.phone number valid;
     *                      b.check if password & confirm password is entered;
     *                      c.if passwords identical;
     *                      d.if phone number has been registered
     * 2.save user input phone number and password (MD5)
     */
    public void onRegisterClick(View view){


        String phone = myInputPhone.getInputStr();
        String password = myInputPassword.getInputStr();
        String passwordConfirm = myInputPasswordConfirm.getInputStr();

        boolean isRegistered = UserUtils.registerUser(this,phone,password,passwordConfirm);

        if(!isRegistered) return;

//        back to login
        onBackPressed();
    }
}
