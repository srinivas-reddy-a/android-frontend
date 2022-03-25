package com.akfrontend.arraykart.AllRetrofit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

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

    //set string
    public Set<String> getSetString(String key){
        return  sharedPreferences.getStringSet(key,null);
    }

    public void setStringList(String key ,Set<String> value){
        editor.putStringSet(key,value).commit();
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
