package com.example.tryandroidmusicplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.tryandroidmusicplayer.Activities.LoginActivity;
import com.example.tryandroidmusicplayer.R;
import com.example.tryandroidmusicplayer.helps.RealmHelp;
import com.example.tryandroidmusicplayer.helps.UserHelp;
import com.example.tryandroidmusicplayer.models.UserModel;

import java.util.List;

public class UserUtils {

    /**
     * Authenticate user
     */
    public static boolean validateLogin (Context context, String phone, String password){
//        RegexUtils.isMobileSimple(phone);
       if (!phone.matches("^[+]?[0-9]{10,13}$")){
//        if (!android.util.Patterns.PHONE.matcher(phone).matches()){
            Toast.makeText(context,"Invalid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show();
            return false;
        }

        /**
         * 1.check if user input phone number has been registered
         * 2.check if user input phone and password combination matches
         */
        if(!UserUtils.userExistBasedOnPhone(phone)){
            Toast.makeText(context, "The phone number is not registered", Toast.LENGTH_SHORT).show();
            return false;
        }

        RealmHelp realmHelp = new RealmHelp();
        boolean valid = realmHelp.validateUser(phone, EncryptUtils.encryptMD5ToString(password));
//        realmHelp.close();

        if (!valid){
            Toast.makeText(context, "Incorrect phone number or password", Toast.LENGTH_SHORT).show();
            return false;
        }

//        save user login user mark
        boolean isSaved = SharedPreferencesUtils.saveUser(context,phone);
        if (!isSaved){
            Toast.makeText(context, "System failure, please try again later", Toast.LENGTH_SHORT).show();
            return false;
        }

//        Save user mark into Singleton pattern
        UserHelp.getInstance().setPhone(phone);

//        save music source data
        realmHelp.setMusicSource(context);

        realmHelp.close();

        return true;
    }

    /**
     * Log out
     */
    public static void logout(Context context) {
//        delete user mark inside SharedPreferences
        boolean isRemoved = SharedPreferencesUtils.removeUser(context);

        if (!isRemoved){
            Toast.makeText(context, "System failure, please try again later", Toast.LENGTH_SHORT).show();
            return;
        }

//        delete music data source
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.removeMusicSource();
        realmHelp.close();

        Intent intent = new Intent(context, LoginActivity.class);
//        add intent flag, clear task stack, and generate a task stack
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
//        Define activity redirect animation
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }

    /**
     * User register
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */
    public static boolean registerUser(Context context, String phone, String password, String passwordConfirm){

        if (!phone.matches("^[+]?[0-9]{10,13}$")){
            Toast.makeText(context,"Invalid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (StringUtils.isEmpty(password)||!password.equals(passwordConfirm)){
            Toast.makeText(context,"Passwords are not identical", Toast.LENGTH_SHORT).show();
            return false;
        }

//        check if phone number has been registered
        /**
         * 1.using Realm to get all registered user
         * 2.check if user input phone number matches any registered phone number
         */
        if (UserUtils.userExistBasedOnPhone(phone)){
            Toast.makeText(context,"This phone number has already been registered", Toast.LENGTH_SHORT).show();
            return false;
        }


        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));

        saveUser(userModel);

        return true;
    }

    /**
     * Save user to database
     * @param userModel
     */
    public static void saveUser (UserModel userModel){
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.saveUser(userModel);
        realmHelp.close();
    }

    /**
     * check if the user exist
     */
    public static boolean userExistBasedOnPhone (String phone){
        boolean isExist = false;

        RealmHelp realmHelp = new RealmHelp();
        List<UserModel> allUser = realmHelp.getAllUser();

        for (UserModel userModel : allUser) {
            if(userModel.getPhone().equals(phone)){
//                this phone number is already exist
                isExist = true;
                break;
            }
        }

        realmHelp.close();

        return isExist;
    }

    /**
     * check if exist logined user
     */
    public static boolean validateUserLogin(Context context){
        return SharedPreferencesUtils.isLoginUser(context);
    }

    /**
     * change password
     * 1.validate user input
     *      a.old password entered
     *      b.new password entered and identical to confirm password
     *      c.old password is correct
     *          - get current user User Model in Realm
     *          - check if old password is identical to password stored in user model
     * 2.change password by using Realm model automatically update
     */
    public static boolean changePassword(Context context, String oldPassword, String password, String passwordConfirm) {

        if (TextUtils.isEmpty(oldPassword)){
            Toast.makeText(context,"Please enter your old password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password) || !password.equals(passwordConfirm)){
            Toast.makeText(context,"Please confirm your new password", Toast.LENGTH_SHORT).show();
            return false;
        }

//        check if old password correct
        RealmHelp realmHelp = new RealmHelp();
        UserModel userModel = realmHelp.getUser();

        if (!EncryptUtils.encryptMD5File2String(oldPassword).equals(userModel.getPassword())){
            Toast.makeText(context,"Incorrect old password", Toast.LENGTH_SHORT).show();
            return false;
        }

        realmHelp.changePassword(EncryptUtils.encryptMD5ToString(password));

        realmHelp.close();

        return  true;

    }
}
