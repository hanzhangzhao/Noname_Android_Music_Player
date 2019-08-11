package com.example.tryandroidmusicplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.tryandroidmusicplayer.constant.SharedPreferencesConstants;
import com.example.tryandroidmusicplayer.helps.UserHelp;

public class SharedPreferencesUtils {

    /**
     * when user log in, save user mark(phone) by SharedPreferences
     */
    public static boolean saveUser (Context context, String phone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesConstants.SHAREDPREFERENCES_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SharedPreferencesConstants.SHAREDPREFERENCES_KEY_PHONE, phone);
        boolean result = editor.commit();
        return result;
    }

    /**
     * Check if there exists a logined user
     */
    public static boolean isLoginUser(Context context) {
        boolean isLoginUser = false;

        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesConstants.SHAREDPREFERENCES_NAME_USER, Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString(SharedPreferencesConstants.SHAREDPREFERENCES_KEY_PHONE,"");

        if (!TextUtils.isEmpty(phone)){
            isLoginUser = true;
            UserHelp.getInstance().setPhone(phone);
        }

        return isLoginUser;
    }

    /**
     * delete user mark
     */
    public static boolean removeUser (Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesConstants.SHAREDPREFERENCES_NAME_USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(SharedPreferencesConstants.SHAREDPREFERENCES_KEY_PHONE);
        boolean result = editor.commit();
        return result;
    }

}
