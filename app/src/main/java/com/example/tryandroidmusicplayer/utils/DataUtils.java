package com.example.tryandroidmusicplayer.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataUtils {

    /**
     * read data from source
     * @return
     */
    public static String getJsonFromAssets(Context context, String fileName) {

        /**
         * 1.StringBuilder to save data
         * 2.AssetManager Open method to open source file, an InputStream will return
         * 3.InputStreamReader, BufferedReader
         * 4.use readLine method of BufferedReader to read every lines of data, save these data into StringBuilder
         * 5.return all data
         */

//        StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
//        AssetManager
        AssetManager assetManager = context.getAssets();
//        Open method to open source file, return an InputStream
        try {
            InputStream inputStream = assetManager.open(fileName);
//            InputStreamReader
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            use readLine method of BufferedReader to read every lines of data
            String line;
            while ((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();

    }

}
