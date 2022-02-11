package com.example.arraykart.MyOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;
import com.example.arraykart.SearchPage.SearchPageActivity;

public class OrderDetail extends AppCompatActivity {
    private ImageView backOrderDetailPage;

    private LottieAnimationView orderDetailPageCartIcon;

    private ConstraintLayout detail_container;

    private TextView searchOrderDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

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
                 startActivity(new Intent(OrderDetail.this, ProductDetailActivity.class));
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
    }
}