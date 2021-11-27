package com.example.arraykart.WishList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.R;

public class WishListActivity extends AppCompatActivity {

    private ImageView back_wishList_Page;
    private LottieAnimationView wishListPageCartIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        getSupportFragmentManager().beginTransaction().add(R.id.wishListActivity,new WishListFragment()).commit();

        try {
            back_wishList_Page = findViewById(R.id.back_wishList_page);
            back_wishList_Page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }
        try{
            wishListPageCartIcon=findViewById(R.id.wishListPageCartIcon);
            wishListPageCartIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(WishListActivity.this, MYCartActivity.class));
                }
            });

        }catch (Exception e){

        }
    }
}