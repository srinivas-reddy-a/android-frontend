package com.example.arraykart.AllRetrofit;

import com.example.arraykart.AllApiModels.AuthRespones;
import com.example.arraykart.AllApiModels.LogInIdRespones;
import com.example.arraykart.AllApiModels.LogInOtpRespones;
import com.example.arraykart.AllApiModels.LogInRespones;
import com.example.arraykart.AllApiModels.ProductsRespones;
import com.example.arraykart.AllApiModels.SignUpRespones;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
     //User Api

    @FormUrlEncoded
    @POST("/api/user/register/")
    Call<SignUpRespones>signUp(@Field("phoneNumber") String userNumber);

    @FormUrlEncoded
    @POST("/api/user/register/otp")
    Call<ResponseBody>registerOtp(@Field("phoneNumber") String userNumber,
                                  @Field("otp") String otp);

    @GET("/api/user/auth/")
    Call<AuthRespones>auth();


    @FormUrlEncoded
    @POST("/api/user/login/")
    Call<LogInRespones>login(@Field("phoneNumber") String userNumber);

    @FormUrlEncoded
    @POST("/api/user/login/otp")
    Call<LogInOtpRespones>loginOtp(@Field("otp") String otp);

    @GET("/api/user/:id")
    Call<LogInIdRespones>loginId();

    @FormUrlEncoded
    @PUT("/api/user")
    Call<ResponseBody>updateUser(@Path("id") String id,
             @Field("phone_number") String phone_number ,
             @Field(" email") String email,
             @Field("name") String name
    );


    ////products api

    @GET("/api/product/")
    Call<ProductsRespones>getProduct();

    @GET("/api/product/category/")
    Call<ResponseBody>productCategory();

    @GET("/api/product/:id/")
    Call<ResponseBody>productId();

    @GET("/api/product/category/:id/")
    Call<ResponseBody>productCategoryId();

    @POST("/api/product/:id/review/")
    Call<ResponseBody>reviewComment(@Field("rating") String rating,
                                    @Field("comment") String comment
    );


    ///orders api

    @FormUrlEncoded
    @POST("'/api/order/")
    Call<ResponseBody>OrderInsert(@Field("total") String total,
                                  @Field("address_id") String address_id
    );

    @FormUrlEncoded
    @POST("'/api/order/detail/")
    Call<ResponseBody>OrderDetail(@Field("order_id") String order_id,
                                  @Field("product_id") String product_id,
                                  @Field("quantity") String quantity
    );

    @GET("/api/order/:id/")
    Call<ResponseBody>orderId();

    @GET("/api/order/detail/:id/")
    Call<ResponseBody>orderDetailId();


}
