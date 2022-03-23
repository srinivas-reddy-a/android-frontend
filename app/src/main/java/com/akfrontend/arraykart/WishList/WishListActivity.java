package com.akfrontend.arraykart.WishList;

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

public class WishListActivity extends AppCompatActivity {

    private ImageView back_wishList_Page;
    private LottieAnimationView wishListPageCartIcon;
    private TextView searchWishList;

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
        //searchview
        try {
            searchWishList = findViewById(R.id.searchWishList);
            searchWishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(WishListActivity.this, SearchPageActivity.class));
                }
            });
        }catch (Exception e){

        }
    }
}