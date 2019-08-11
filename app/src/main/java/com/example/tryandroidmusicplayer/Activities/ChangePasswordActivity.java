package com.example.tryandroidmusicplayer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.utils.UserUtils;
import com.example.tryandroidmusicplayer.views.InputView;

public class ChangePasswordActivity extends BaseActivity {

    private InputView myOldPassword, myPassword, myPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
    }

    private void initView() {
        initNavbar(true, "Change Password", false);

        myOldPassword = findId(R.id.input_old_password);
        myPassword = findId(R.id.input_password);
        myPasswordConfirm = findId(R.id.input_password_confirm);
    }

    public void onChangePasswordClick (View view){
        String oldPassword = myOldPassword.getInputStr();
        String password = myPassword.getInputStr();
        String passwordConfirm = myPasswordConfirm.getInputStr();

        boolean isChanged = UserUtils.changePassword(this,oldPassword,password,passwordConfirm);
        if (!isChanged){
            return;
        }

        UserUtils.logout(this);
    }

}
