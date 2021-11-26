package com.example.arraykart.MyOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.R;

public class OrderDetail extends AppCompatActivity {
    private ImageView backOrderDetailPage;

    private LottieAnimationView orderDetailPageCartIcon;

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
    }
}