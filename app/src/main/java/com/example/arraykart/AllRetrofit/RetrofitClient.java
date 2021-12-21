package com.example.arraykart.AllRetrofit;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(1500, TimeUnit.SECONDS)
            .readTimeout(1500,TimeUnit.SECONDS)
            .build();

    private static final String BASE_URL = "http://10.0.2.2:3000";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;


    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(retrofitClient==null){
            retrofitClient = new RetrofitClient();

        }
        return retrofitClient;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
