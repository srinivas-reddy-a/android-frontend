package com.example.arraykart.MyOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.arraykart.AddressActivity.AddressModel;
import com.example.arraykart.AllApiModels.CartUPdateRespones;
import com.example.arraykart.AllApiModels.ProductDetailPageRespones;
import com.example.arraykart.AllApiModels.getSelectedAddressRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.ProductDetailAboutListing.ProductDetailPageModel;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;
import com.example.arraykart.SearchPage.SearchPageActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetail extends AppCompatActivity {
    private ImageView backOrderDetailPage,order_detail_productImage;

    private LottieAnimationView orderDetailPageCartIcon;

    private ConstraintLayout detail_container;

    private TextView searchOrderDetail,order_detail_productTitle,order_detail_productPrice,
            order_detail_productQuantity,shoppingDetailName,shoppingDetailAddress,textView6,total_item,total_item_price,saved_price
            ,selected_volume,cancelOrder;

    private List<AddressModel> addressModels;

    private List<ProductDetailPageModel> product;

    SharedPrefManager sharedPrefManager ;
    String si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        sharedPrefManager = new SharedPrefManager(this);
        String Token = sharedPrefManager.getValue_string("token");


        order_detail_productImage = findViewById(R.id.order_detail_productImage);
        order_detail_productTitle = findViewById(R.id.order_detail_productTitle);
        order_detail_productPrice = findViewById(R.id.order_detail_productPrice);
        order_detail_productQuantity = findViewById(R.id.order_detail_productQuantity);
        total_item_price = findViewById(R.id.total_item_price);
        total_item = findViewById(R.id.total_item);
        saved_price = findViewById(R.id.saved_price);
        selected_volume = findViewById(R.id.selected_volume);
        cancelOrder = findViewById(R.id.cancelOrder);

        String order_id = getIntent().getStringExtra("order_id");
        String addId = getIntent().getStringExtra("addId");
        String productId = getIntent().getStringExtra("productId");
        String qty = getIntent().getStringExtra("qty");
        String volume = getIntent().getStringExtra("volume");

        selected_volume.setText(volume);

        ShippingAddress(Token,addId);

        getProductDetail(productId,qty);

        try{
            backOrderDetailPage = findViewById(R.id.back_order_detail_page);
            backOrderDetailPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }

        try{
            orderDetailPageCartIcon = findViewById(R.id.orderDetailPageCartIcon);
            orderDetailPageCartIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(OrderDetail.this, MYCartActivity.class));
                }
            });
        }catch (Exception e){

        }

        try{
         detail_container = findViewById(R.id.detail_container);
         detail_container.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent in = new Intent(OrderDetail.this, ProductDetailActivity.class);
                 in.putExtra("image","https://arraykartandroid.s3.ap-south-1.amazonaws.com/"+si);
                 in.putExtra("qlt",qty);
                 in.putExtra("id",productId);
                 in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(in);
             }
         });
        }catch (Exception e){

        }

        //searchview
        try {
            searchOrderDetail = findViewById(R.id.searchOrderDetail);
            searchOrderDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(OrderDetail.this, SearchPageActivity.class));
                }
            });
        }catch (Exception e){

        }

        //cancelOrder

        try{
            cancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowDialog();
                }
            });
        }catch (Exception e){

        }



    }

    private void getProductDetail(String id ,String qty){

        String url = "/api/product/"+id;
        Call<ProductDetailPageRespones> CallDetail = RetrofitClient.getInstance().getApi().getDetail(url);
        CallDetail.enqueue(new Callback<ProductDetailPageRespones>() {
            @Override
            public void onResponse(Call<ProductDetailPageRespones> call, Response<ProductDetailPageRespones> response) {

                try {
                    product = response.body().getProduct();
                    order_detail_productTitle.setText(product.get(0).getName());
                    int t = Integer.parseInt(qty)*Integer.parseInt(product.get(0).getPrice());;
                    String total = Integer.toString(t);
                    order_detail_productQuantity.setText("QTY:"+qty);
                    total_item.setText("Price("+qty+"item)");


                    total_item_price.setText("₹ "+total+"/--");
                    saved_price.setText("₹ "+total+"/--");
                    order_detail_productPrice.setText("₹ "+total+"/---");
//                    total_item_price.setText("Price coming soon");
//                    saved_price.setText("Price coming soon");
//                    order_detail_productPrice.setText("Price coming soon");
                    String yo = product.get(0).getImage();
                    String [] i = yo.split(",");
                    si = i[0];
                    Glide.with(OrderDetail.this)
                            .load("https://arraykartandroid.s3.ap-south-1.amazonaws.com/"+si)
                            .centerCrop()
                            .into(order_detail_productImage);

                } catch (Exception e) {
                    Toast.makeText(OrderDetail.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductDetailPageRespones> call, Throwable t) {

            }

        });
    }

    private void ShippingAddress(String token,String addId){
        ///shipping address

        shoppingDetailName = findViewById(R.id.shoppingDetailName);
        shoppingDetailAddress = findViewById(R.id.shoppingDetailAddress);
        textView6 = findViewById(R.id.textView6);
        final String[] add1 = new String[1];
        final String[] add2 = new String[1];
        final String[] city = new String[1];
        final String[] state = new String[1];

        SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
        if(userToken.contains("token")) {
            Call<getSelectedAddressRespones> callSelectedAddress = RetrofitClient.getInstance().getApi().getAddressId(token,addId);
            callSelectedAddress.enqueue(new Callback<getSelectedAddressRespones>() {
                @Override
                public void onResponse(Call<getSelectedAddressRespones> call, Response<getSelectedAddressRespones> response) {
                    getSelectedAddressRespones getSelectedAddressRespones = response.body();
                    addressModels = getSelectedAddressRespones.getAddress();
                    if(!addressModels.isEmpty()) {
                        shoppingDetailName.setText(addressModels.get(0).getAddress_name());
                        add1[0] = addressModels.get(0).getAddress_line1();
                        add2[0] = addressModels.get(0).getAddress_line2();
                        state[0] = addressModels.get(0).getState();
                        city[0] = addressModels.get(0).getCity();
                        textView6.setText(addressModels.get(0).getPostal_code());
                        shoppingDetailAddress.setText(add1[0] + "," + add2[0] + "," + state[0] + "," + city[0]);
                    }

                }

                @Override
                public void onFailure(Call<getSelectedAddressRespones> call, Throwable t) {
                    Toast.makeText(OrderDetail.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
        }
    }

    private void ShowDialog(){
        Dialog dialog = new Dialog(this,R.style.DialogStyle);
        dialog.setContentView(R.layout.cancel_product_layout);
        ImageView backFeedback = dialog.findViewById(R.id.backFeedback);
        backFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView reason_cancel1 = dialog.findViewById(R.id.reason_cancel1);
        TextView reason_cancel2 = dialog.findViewById(R.id.reason_cancel2);
        TextView reason_cancel3 = dialog.findViewById(R.id.reason_cancel3);
        TextView reason_cancel4 = dialog.findViewById(R.id.reason_cancel4);
        TextView reason_cancel5 = dialog.findViewById(R.id.reason_cancel5);

        reason_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = reason_cancel1.getText().toString();
                cancelOrder(msg);
                cancelOrder.setText("your order has been Canceled");
                dialog.dismiss();
            }
        });
        reason_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = reason_cancel2.getText().toString();
                cancelOrder(msg);
                cancelOrder.setText("your order has been Canceled");
                dialog.dismiss();
            }
        });
        reason_cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = reason_cancel3.getText().toString();
                cancelOrder(msg);
                cancelOrder.setText("your order has been Canceled");
                dialog.dismiss();
            }
        });
        reason_cancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = reason_cancel4.getText().toString();
                cancelOrder(msg);
                cancelOrder.setText("your order has been Canceled");
                dialog.dismiss();
            }
        });
        reason_cancel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = reason_cancel5.getText().toString();
                cancelOrder(msg);
                cancelOrder.setText("your order has been Canceled");
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private void cancelOrder(String msg){
        sharedPrefManager = new SharedPrefManager(this);
        String Token = sharedPrefManager.getValue_string("token");
        String order_id = getIntent().getStringExtra("order_id");
        Call<CartUPdateRespones> callC = RetrofitClient.getInstance().getApi().cancelOrder(Token,order_id,msg);

        callC.enqueue(new Callback<CartUPdateRespones>() {
            @Override
            public void onResponse(Call<CartUPdateRespones> call, Response<CartUPdateRespones> response) {
                CartUPdateRespones cartUPdateRespones = response.body();
                Toast.makeText(OrderDetail.this, cartUPdateRespones.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CartUPdateRespones> call, Throwable t) {
                Toast.makeText(OrderDetail.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}