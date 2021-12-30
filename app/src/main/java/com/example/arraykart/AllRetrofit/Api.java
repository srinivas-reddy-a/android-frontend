package com.example.arraykart.AllRetrofit;

import com.example.arraykart.AllApiModels.AuthRespones;
import com.example.arraykart.AllApiModels.GetAddressRespones;
import com.example.arraykart.AllApiModels.LogInIdRespones;
import com.example.arraykart.AllApiModels.LogInOtpRespones;
import com.example.arraykart.AllApiModels.LogInRespones;
import com.example.arraykart.AllApiModels.ProductsCategoryRespones;
import com.example.arraykart.AllApiModels.ProductsRespones;
import com.example.arraykart.AllApiModels.SignUpRespones;
import com.example.arraykart.AllApiModels.SignUpTopRespones;
import com.example.arraykart.AllApiModels.UserId;
import com.example.arraykart.AllApiModels.UserUpdateResponse;
import com.example.arraykart.AllApiModels.AddressFormRespones;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {
     //User Api

    @FormUrlEncoded
    @POST("/api/user/register/")
    Call<SignUpRespones>signUp(@Field("phoneNumber") String userNumber);

    @FormUrlEncoded
    @POST("/api/user/register/otp")
    Call<SignUpTopRespones>registerOtp(@Field("phoneNumber") String userNumber,
                                       @Field("otp") String otp);

    @GET("/api/user/auth/")
    Call<AuthRespones>auth(@Header("Authorization") String Authorization);


    @FormUrlEncoded
    @POST("/api/user/login/")
    Call<LogInRespones>login(@Field("phoneNumber") String userNumber);

    @FormUrlEncoded
    @POST("/api/user/login/otp")
    Call<LogInOtpRespones>loginOtp(@Field("otp") String otp);

    @GET("/api/user/:id")
    Call<LogInIdRespones>loginId();

    @FormUrlEncoded
    @PUT("/api/user/")
    Call<UserUpdateResponse>updateUser(
                                       @Field("phone_number") String phone_number ,
                                       @Field(" email") String email,
                                       @Field("name") String name
    );

    @FormUrlEncoded
//    @Headers({
//            "Content-Type : application/json",
//            "Authorization : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxMDAxfSwiaWF0IjoxNjQwNTI3MjA3LCJleHAiOjE2NDA4ODcyMDd9.XFXChA4iO36elzx3naqJfImDj6S-qsQnI4XuHjA9NrI"
//    })
    @POST("/api/user/address/")
    Call<AddressFormRespones>UserAddressForm(
            @Header("Content-Type") String Content_Type,
            @Header("Authorization") String Authorization,
            @Field("address_name") String address_name,
            @Field("addressLine1") String addressLine1,
            @Field("addressLine2") String addressLine2,
            @Field("city") String city,
            @Field("postalCode") String postalCode,
            @Field("country") String country,
            @Field("phoneNumber") String phoneNumber
    );

    @GET("/api/user/address/")
    Call<GetAddressRespones>getAddress(@Header("Authorization") String Authorization);

    @GET("/api/user/address/:id/")
    Call<ResponseBody>getAddressId();

    ////products api

    @GET("/api/product/")
    Call<ProductsRespones>getProduct();

    @GET("/api/product/category/")
    Call<ProductsCategoryRespones>productCategory();

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

    ///cart api
    @FormUrlEncoded
    @POST("/api/cart/")
    Call<ResponseBody>addToCart(@Header("Authorization") String Authorization,
                                @Field("product_id") String product_id,
                                @Field("quantity") String quantity
    );

    @GET("/api/cart/")
    Call<ResponseBody>getCartItem();







}
