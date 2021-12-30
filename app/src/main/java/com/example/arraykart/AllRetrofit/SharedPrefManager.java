package com.example.arraykart.AllRetrofit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.arraykart.AllApiModels.LogInOtpRespones;
import com.example.arraykart.AllApiModels.User;
import com.example.arraykart.AllApiModels.UserId;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME="arraykartuser"; ////name of class
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor; ///for save the user

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    //int
    public int getValue_int(String key){
        return  sharedPreferences.getInt(key,0);
    }

    public void setValue_int(String key,int value){
        editor.putInt(key,value).commit();
    }

    //string

    public String getValue_string(String key){
        return  sharedPreferences.getString(key,"");
    }

    public void setValue_string(String key,String value){
        editor.putString(key,value).commit();
    }

    //boolean

    public boolean getValue_boolean(String key){
        return  sharedPreferences.getBoolean(key,false);
    }

    public void setValue_boolean(String key,boolean value){
        editor.putBoolean(key,value).commit();
    }

    public void clear(){
        editor.clear().commit();
    }


}
