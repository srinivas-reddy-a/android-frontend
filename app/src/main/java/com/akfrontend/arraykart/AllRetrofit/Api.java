package com.akfrontend.arraykart.AllRetrofit;

import com.akfrontend.arraykart.AllApiModels.AddressDeleteRespones;
import com.akfrontend.arraykart.AllApiModels.AddressUpdateRespones;
import com.akfrontend.arraykart.AllApiModels.AuthRespones;
import com.akfrontend.arraykart.AllApiModels.BrandRespones;
import com.akfrontend.arraykart.AllApiModels.CartAddRespones;
import com.akfrontend.arraykart.AllApiModels.CartUPdateRespones;
import com.akfrontend.arraykart.AllApiModels.CategoryIdRespones;
import com.akfrontend.arraykart.AllApiModels.GetAddressRespones;
import com.akfrontend.arraykart.AllApiModels.GetCartRespones;
import com.akfrontend.arraykart.AllApiModels.LogInIdRespones;
import com.akfrontend.arraykart.AllApiModels.LogInOtpRespones;
import com.akfrontend.arraykart.AllApiModels.LogInRespones;
import com.akfrontend.arraykart.AllApiModels.LogOutRespones;
import com.akfrontend.arraykart.AllApiModels.ProductDetailPageRespones;
import com.akfrontend.arraykart.AllApiModels.ProductsCategoryRespones;
import com.akfrontend.arraykart.AllApiModels.ProductsRespones;
import com.akfrontend.arraykart.AllApiModels.SearchProducRespones;
import com.akfrontend.arraykart.AllApiModels.SignUpRespones;
import com.akfrontend.arraykart.AllApiModels.SignUpTopRespones;
import com.akfrontend.arraykart.AllApiModels.UserUpdateResponse;
import com.akfrontend.arraykart.AllApiModels.AddressFormRespones;
import com.akfrontend.arraykart.AllApiModels.WishListAddRespones;
import com.akfrontend.arraykart.AllApiModels.deleteWishListRespones;
import com.akfrontend.arraykart.AllApiModels.getOrderRespones;
import com.akfrontend.arraykart.AllApiModels.getProductsRespones;
import com.akfrontend.arraykart.AllApiModels.getSelectedAddressRespones;
import com.akfrontend.arraykart.AllApiModels.getWishListRespones;
import com.akfrontend.arraykart.AllApiModels.nestedCategoryRespones;

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

    @FormUrlEncoded
    @POST("/api/user/register/otp/resend/")
    Call<ResponseBody>registerOtpResend(@Field("phoneNumber") String userNumber);

    @GET("/api/user/auth/")
    Call<AuthRespones>auth(@Header("Authorization") String Authorization);


    @GET("/api/user/community/register/")
    Call<CartUPdateRespones> getPradhaan(@Header("Authorization") String Authorization);
    @FormUrlEncoded
    @PUT("/api/user/community/register/")
    Call<CartUPdateRespones> addCommunity(@Header("Authorization") String Authorization,
                                          @Field("aadhaar") String aadhaar,
                                          @Field("name") String name);

    @FormUrlEncoded
    @POST("/api/user/login/")
    Call<LogInRespones>login(@Field("phoneNumber") String userNumber);

    @FormUrlEncoded
    @POST("/api/user/login/otp")
    Call<LogInOtpRespones>loginOtp(@Field("id") String id,
                                   @Field("otp") String otp,
                                   @Field("phoneNumber") String phoneNumber
    );

    @FormUrlEncoded
    @POST("/api/user/login/otp/resend/")
    Call<ResponseBody>loginOtpresend(@Field("phoneNumber") String phoneNumber);

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


    @GET("/api/user/address/{id}")
    Call<getSelectedAddressRespones> getAddressId(@Header("Authorization") String Authorization,
                                                  @Path("id") String id);

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

    @GET("/api/product/{id}/")
    Call<getProductsRespones>productId(@Header("Authorization") String Authorization,
                                       @Path("id") String id);

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
    Call<deleteWishListRespones>orderAdd( @Header("Authorization") String Authorization,
                                @Field("total") String total,
                                @Field("address_id") String address_id
    );

    @FormUrlEncoded
    @POST("/api/order/detail/")
    Call<ResponseBody>OrderDetail(@Field("order_id") String order_id,
                                  @Field("product_id") String product_id,
                                  @Field("quantity") String quantity,
                                  @Field("volume") String volume
    );

    @GET("/api/order/")
    Call<getOrderRespones>getOrder(@Header("Authorization") String Authorization);

    @FormUrlEncoded
    @PUT("/api/order/cancel/")
    Call<CartUPdateRespones>cancelOrder(@Header("Authorization") String Authorization,
                                        @Field("order_id") String order_id,
                                        @Field("reason_cancel") String reason_cancel);

    @GET("/api/order/:id/")
    Call<ResponseBody>orderId();

    @GET("/api/order/detail/:id/")
    Call<ResponseBody>orderDetailId();

        ///cart api
    @FormUrlEncoded
    @POST("/api/cart/")
    Call<CartAddRespones>addToCart(@Header("Authorization") String Authorization,
                                   @Field("product_id") String product_id,
                                   @Field("quantity") String quantity,
                                   @Field("volume") String volume
    );

    @FormUrlEncoded
    @POST("/api/cart/status/{id}/")
    Call<CartUPdateRespones> getStatusCart(@Header("Authorization") String Authorization,
                                           @Path("id") String product_id,
                                           @Field("volume") String volume
    );

    @GET("/api/cart/")
    Call<GetCartRespones>getCartItem(@Header("Authorization") String Authorization);

    @FormUrlEncoded
    @PUT("/api/cart/")
    Call<CartUPdateRespones>updateCart(@Header("Authorization") String Authorization,
                                       @Field("product_id") String product_id,
                                       @Field("quantity") String quantity,
                                       @Field("currentVolume") String currentVolume,
                                       @Field("updatedVolume") String updatedVolume
    );

    @FormUrlEncoded
    @PUT("/api/cart/")
    Call<CartUPdateRespones>volumeUpdate(@Header("Authorization") String Authorization,
                                         @Field("product_id") String product_id,
                                         @Field("quantity") String quantity,
                                         @Field("currentVolume") String currentVolume,
                                         @Field("updatedVolume") String updatedVolume
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
