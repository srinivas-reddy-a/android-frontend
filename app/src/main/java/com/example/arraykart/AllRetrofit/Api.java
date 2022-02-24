package com.example.arraykart.AllRetrofit;

import com.example.arraykart.AllApiModels.AddressDeleteRespones;
import com.example.arraykart.AllApiModels.AddressUpdateRespones;
import com.example.arraykart.AllApiModels.AuthRespones;
import com.example.arraykart.AllApiModels.BrandRespones;
import com.example.arraykart.AllApiModels.CartAddRespones;
import com.example.arraykart.AllApiModels.CartUPdateRespones;
import com.example.arraykart.AllApiModels.CategoryIdRespones;
import com.example.arraykart.AllApiModels.GetAddressRespones;
import com.example.arraykart.AllApiModels.GetCartRespones;
import com.example.arraykart.AllApiModels.LogInIdRespones;
import com.example.arraykart.AllApiModels.LogInOtpRespones;
import com.example.arraykart.AllApiModels.LogInRespones;
import com.example.arraykart.AllApiModels.LogOutRespones;
import com.example.arraykart.AllApiModels.ProductDetailPageRespones;
import com.example.arraykart.AllApiModels.ProductsCategoryRespones;
import com.example.arraykart.AllApiModels.ProductsRespones;
import com.example.arraykart.AllApiModels.SearchProducRespones;
import com.example.arraykart.AllApiModels.SignUpRespones;
import com.example.arraykart.AllApiModels.SignUpTopRespones;
import com.example.arraykart.AllApiModels.UserUpdateResponse;
import com.example.arraykart.AllApiModels.AddressFormRespones;
import com.example.arraykart.AllApiModels.WishListAddRespones;
import com.example.arraykart.AllApiModels.deleteWishListRespones;
import com.example.arraykart.AllApiModels.getSelectedAddressRespones;
import com.example.arraykart.AllApiModels.getWishListRespones;
import com.example.arraykart.AllApiModels.nestedCategoryRespones;
import com.example.arraykart.SearchPage.SearchProductModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

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
    Call<LogInOtpRespones>loginOtp(@Field("id") String id,
                                   @Field("otp") String otp
    );

    @POST("/api/user/logout/")
    Call<LogOutRespones>logout(@Header("Authorization") String Authorization
    );

    @GET("/api/user/")
    Call<LogInIdRespones>loginId(@Header("Authorization") String Authorization);

    @FormUrlEncoded
    @PUT("/api/user/")
    Call<UserUpdateResponse>updateUser(@Header("Authorization") String Authorization,
                                       @Field("phone_number") String phone_number ,
                                       @Field("email") String email,
                                       @Field("name") String name
    );

    @FormUrlEncoded
    @POST("/api/user/address/")
    Call<AddressFormRespones>UserAddressForm(@Header("Authorization") String Authorization,
                                             @Field("address_name") String address_name,
                                             @Field("addressLine1") String addressLine1,
                                             @Field("addressLine2") String addressLine2,
                                             @Field("city") String city,
                                             @Field("postalCode") String postalCode,
                                             @Field("state") String country,
                                             @Field("phoneNumber") String phoneNumber,
                                             @Field("alternate_number") String alternate_number,
                                             @Field("is_default") String is_default);

    @GET("/api/user/address/")
    Call<GetAddressRespones>getAddress(@Header("Authorization") String Authorization);

    @GET("/api/user/address/default/")
    Call<getSelectedAddressRespones>getSelectedAddress(@Header("Authorization") String Authorization);

    @FormUrlEncoded
    @PUT("/api/user/address/")
    Call<AddressUpdateRespones>updateAddress(
                                             @Header("Authorization") String Authorization,
                                             @Field("id") String id,
                                             @Field("address_name") String address_name ,
                                             @Field("addressLine1") String addressLine1,
                                             @Field("addressLine2") String addressLine2,
                                             @Field("city") String city,
                                             @Field("postalCode") String postalCode,
                                             @Field("state") String country,
                                             @Field("phoneNumber") String phoneNumber,
                                             @Field("alternate_number") String alternate_number,
                                             @Field("is_default") String is_default
    );



    @DELETE("/api/user/address/{id}")
    Call<AddressDeleteRespones>deleteAddress(
                                             @Header("Authorization") String Authorization,
                                             @Path("id") String id
    );

    @FormUrlEncoded
    @PUT("/api/user/address/default/")
    Call<ResponseBody>SelectAddress(@Header("Authorization") String Authorization,
                        @Field("id") String id);

    ////products api
    @GET("/api/product/")
    Call<ProductsRespones>getProduct();

    @GET
    Call<CategoryIdRespones>getCategory(@Url String Url);

    //ProductsRespones

    @GET
    Call<ProductsRespones>getNestedCategory(@Url String Url);

    @GET
    Call<SearchProducRespones>getSearchProduct(@Url String Url);

    @GET
    Call<ProductDetailPageRespones> getDetail(@Url String Url);

    @GET("/api/product/category/")
    Call<ProductsCategoryRespones>productCategory();

    @GET("/api/product/category/")
    Call<nestedCategoryRespones>productsCategory();

    @GET("/api/product/:id/")
    Call<ResponseBody>productId();

    @GET("/api/product/category/:id/")
    Call<ResponseBody>productCategoryId();

    @POST("/api/product/:id/review/")
    Call<ResponseBody>reviewComment(@Field("rating") String rating,
                                    @Field("comment") String comment
    );


    ////Brand
    @GET("/api/brand/")
    Call<BrandRespones> getBrand();

    ///orders api
    @FormUrlEncoded
    @POST("/api/order/")
    Call<ResponseBody>OrderInsert(@Field("total") String total,
                                  @Field("address_id") String address_id
    );

    @FormUrlEncoded
    @POST("/api/order/detail/")
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
    Call<CartAddRespones>addToCart(@Header("Authorization") String Authorization,
                                   @Field("product_id") String product_id,
                                   @Field("quantity") String quantity
    );

    @POST("/api/cart/status/{id}/")
    Call<CartUPdateRespones> getStatusCart(@Header("Authorization") String Authorization,
                                     @Path("id") String product_id);

    @GET("/api/cart/")
    Call<GetCartRespones>getCartItem(@Header("Authorization") String Authorization);

    @FormUrlEncoded
    @PUT("/api/cart/")
    Call<CartUPdateRespones>updateCart(@Header("Authorization") String Authorization,
                                       @Field("product_id") String product_id,
                                       @Field("quantity") String quantity
    );

    @DELETE("/api/cart/{id}")
    Call<deleteWishListRespones>deleteCartItem(@Header("Authorization") String Authorization,
                                     @Path("id") String product_id
    );

    //wishlist api
    @FormUrlEncoded
    @POST("/api/wishlist/")
    Call<WishListAddRespones> addWishlist(@Header("Authorization") String Authorization,
                                          @Field("product_id") String product_id,
                                          @Field("quantity") String quantity
    );

    @POST("/api/wishlist/status/{id}/")
    Call<CartUPdateRespones> getStatusWishList(@Header("Authorization") String Authorization,
                                           @Path("id") String product_id);


    @GET("/api/wishlist/")
    Call<getWishListRespones> getWishList(@Header("Authorization") String Authorization);

    @PUT("/api/wishlist/")
    Call<ResponseBody> updateWishList(@Header("Authorization") String Authorization,
                                      @Field("product_id") String product_id
    );

    @DELETE("/api/wishlist/{id}/")
    Call<deleteWishListRespones> deleteWishList(@Header("Content-Type") String Content_Type,@Header("Authorization") String Authorization,
                                                @Path("id") String product_id
    );

}
