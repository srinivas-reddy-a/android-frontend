package com.example.arraykart.MyCart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arraykart.MyOrder.MyOrderFragment;
import com.example.arraykart.MyOrder.OrderDetail;
import com.example.arraykart.R;
import com.example.arraykart.SearchPage.SearchPageActivity;

public class MYCartActivity extends AppCompatActivity {

    private ImageView back_cart_page;
    private TextView searchMyCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart_activity);


        getSupportFragmentManager().beginTransaction().add(R.id.gameview_layout,new MyCartFragment()).commit();

        back_cart_page = findViewById(R.id.back_cart_page);
        back_cart_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //searchview
        try {
            searchMyCart = findViewById(R.id.searchMyCart);
            searchMyCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MYCartActivity.this, SearchPageActivity.class));
                }
            });
        }catch (Exception e){

        }
    }
}