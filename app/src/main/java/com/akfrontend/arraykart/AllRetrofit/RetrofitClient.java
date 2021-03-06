package com.akfrontend.arraykart.AllRetrofit;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://3.111.180.248:3000/";
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50,TimeUnit.SECONDS)
            .build();
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
