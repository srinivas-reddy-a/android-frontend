package com.example.arraykart.MyOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.arraykart.R;

public class MyOrder extends AppCompatActivity {
    private ImageView back_order_page;

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
    }
}