package com.example.tryandroidmusicplayer.helps;

import android.content.Context;

import com.example.tryandroidmusicplayer.migration.Migration;
import com.example.tryandroidmusicplayer.models.AlbumModel;
import com.example.tryandroidmusicplayer.models.MusicModel;
import com.example.tryandroidmusicplayer.models.MusicSourceModel;
import com.example.tryandroidmusicplayer.models.UserModel;
import com.example.tryandroidmusicplayer.utils.DataUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {

    private Realm myRealm;

    public RealmHelp(){

        myRealm = Realm.getDefaultInstance();
    }

    /**
     * when Realm database has structure changes (add, modify, delete happen to model
     * or fields of model), we have to migrate database
     */

    /**
     * tell Realm that data needs migration, and config newest configuration for Realm
     */
    public static void migration() {
        RealmConfiguration conf = getRealmConf();

//        newest configuration for Realm
        Realm.setDefaultConfiguration(conf);
//        tell Realm data need migration
        try {
            Realm.migrateRealm(conf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * retrun RealmConfiguration
     * @return
     */
    private static RealmConfiguration getRealmConf(){
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
    }


    /**
     * close connection
     */
    public void close(){
        if(myRealm != null && !myRealm.isClosed()){
            myRealm.close();
        }
    }

    /**
     * Save user info
     */
    public void saveUser (UserModel userModel){
        myRealm.beginTransaction();
        myRealm.insert(userModel);
//        myRealm.insertOrUpdate(userModel);
        myRealm.commitTransaction();
    }

    /**
     * return all user
     */
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query = myRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    /**
     * Authenticate user info
     */
    public boolean validateUser(String phone, String password){
        boolean result = false;
        RealmQuery<UserModel> query = myRealm.where(UserModel.class);
        query = query.equalTo("phone",phone).equalTo("password",password);
        UserModel userModel = query.findFirst();

        if (userModel != null){
            result = true;
        }

        return result;
    }

    /**
     * get current user
     */
    public UserModel getUser(){
        RealmQuery<UserModel> realmQuery = myRealm.where(UserModel.class);
        UserModel userModel = realmQuery.equalTo("phone", UserHelp.getInstance().getPhone()).findFirst();
        return userModel;
    }

    /**
     * change password
     */
    public void changePassword(String password){
        UserModel userModel = getUser();

        myRealm.beginTransaction();
        userModel.setPassword(password);
        myRealm.commitTransaction();
    }

    /**
     * 1.user login, save data
     * 2.user log out, delete data
     */

    /**
     * save music data
     */
    public void setMusicSource(Context context){
//        get data inside source file
        String musicSourceJson = DataUtils.getJsonFromAssets(context,"DataSource.json");
        myRealm.beginTransaction();
        myRealm.createObjectFromJson(MusicSourceModel.class, musicSourceJson);
        myRealm.commitTransaction();
    }

    /**
     * delete music data
     * 1.RealmResult delete methods
     * 2.Realm delete to delete all data in a model
     */
    public void removeMusicSource(){
        myRealm.beginTransaction();
        myRealm.delete(MusicSourceModel.class);
        myRealm.delete(MusicModel.class);
        myRealm.delete(AlbumModel.class);
        myRealm.commitTransaction();
    }

    /**
     * return music source data
     */
    public MusicSourceModel getMusicSource() {
        return myRealm.where(MusicSourceModel.class).findFirst();
    }

    /**
     * return playlist
     */
    public AlbumModel getAlbum (String albumId){
        return myRealm.where(AlbumModel.class).equalTo("albumId",albumId).findFirst();
    }

    /**
     * return music
     */
    public MusicModel getMusic (String musicId){
        return myRealm.where(MusicModel.class).equalTo("musicId", musicId).findFirst();
    }
}
