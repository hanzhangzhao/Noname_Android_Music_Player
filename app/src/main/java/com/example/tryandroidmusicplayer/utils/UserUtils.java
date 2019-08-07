package com.example.tryandroidmusicplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.example.tryandroidmusicplayer.Activities.LoginActivity;
import com.example.tryandroidmusicplayer.R;

public class UserUtils {

    /**
     * Authenticate user input
     */
    public static boolean validateLogin (Context context, String phone, String password){
//        RegexUtils.isMobileSimple(phone);
       if (!PhoneNumberUtils.isGlobalPhoneNumber(phone)){
           Toast.makeText(context,"Invalid phone number", Toast.LENGTH_SHORT).show();
           return false;
       }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Log out
     */
    public static void logout(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
//        add intent flag, clear task stack, and generate a task stack
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
//        Define activity redirect animation
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }
}
