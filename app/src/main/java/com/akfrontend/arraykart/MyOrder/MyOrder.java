package com.akfrontend.arraykart.MyOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.akfrontend.arraykart.MyCart.MYCartActivity;
import com.akfrontend.arraykart.R;
import com.akfrontend.arraykart.SearchPage.SearchPageActivity;

public class MyOrder extends AppCompatActivity {
    private ImageView back_order_page;
    private LottieAnimationView orderPageCartIcon;
    private TextView searchMyOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        getSupportFragmentManager().beginTransaction().add(R.id.baby,new MyOrderFragment()).commit();

        try{
            back_order_page = findViewById(R.id.back_order_page);
            back_order_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }
        ////cart icon on order page
        try {
            orderPageCartIcon = findViewById(R.id.orderPageCartIcon);
            orderPageCartIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MyOrder.this, MYCartActivity.class));
                }
            });
        }catch(Exception e){

        }
        ////cart icon on order page

        //searchview
        try {
            searchMyOrder = findViewById(R.id.searchMyOrder);
            searchMyOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MyOrder.this, SearchPageActivity.class));
                }
            });
        }catch (Exception e){

        }
    }

}