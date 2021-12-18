package com.example.arraykart.AllRetrofit;

import com.example.arraykart.AllApiModels.SignUpRespones;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("/register/")
    Call<SignUpRespones>signUp(@Field("phone_number") String userNumber);
}
