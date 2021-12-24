package com.example.arraykart.AllRetrofit;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.arraykart.AllApiModels.LogInOtpRespones;
import com.example.arraykart.AllApiModels.User;
import com.example.arraykart.AllApiModels.UserId;

public class SharedPrefManager {
    private static String SHARED_PREF_NAME="arraykartuser"; ////name of class
    private SharedPreferences sharedPreferences;
    Context context ;
    private SharedPreferences.Editor editor; ///for save the user

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(UserId user){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("phone_number", user.getPhone_number());
        editor.putString("email",user.getEmail());
        editor.putBoolean("logged",true);
        editor.apply();
    }
    public boolean isLoggedIn(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);

    }
    public UserId getUser(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,context.MODE_PRIVATE);
        return new UserId(sharedPreferences.getInt("id",-1),
                sharedPreferences.getString("name",null),
                sharedPreferences.getString("phone_number",null),
                sharedPreferences.getString("email",null));
    }

    void logged(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }



}
