package com.example.tryandroidmusicplayer.helps;

/**
 * 1.User Login
 *      a.when user log in, save user mark(phone) by SharedPreferences
 *      b.use Singleton pattern UserHelp to save login user info
 *          - when user log in
 *          - when user open the app, check if phone us stored in SharedPreferences. if exist, assign
 *            the value to UserHelp, and enter main page. if not, enter login page
 * 2.User Log out
 *      a.delete user mark inside SharedPreferences, back to login page
 */
public class UserHelp {

    private static UserHelp instance;

    private UserHelp(){

    }

    public static UserHelp getInstance() {
        if(instance == null) {
            synchronized (UserHelp.class){
                if (instance == null){
                    instance = new UserHelp();
                }
            }
        }
        return instance;
    }

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
