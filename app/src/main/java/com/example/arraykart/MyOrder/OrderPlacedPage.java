package com.example.arraykart.MyOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.arraykart.HomeNavigationActivity;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.R;

public class OrderPlacedPage extends AppCompatActivity {

    private LottieAnimationView orderPalcedPageCartIcon;
    private ImageView back_orderPlace_page,orderPlaceIamge;
    private TextView order_number,orderPlacePName,orderPlacedPrice,orderPlacedQty,ContinueShopping;
    private Button PalcedPageButton;
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed_page);
        orderPalcedPageCartIcon = findViewById(R.id.orderPalcedPageCartIcon);
        back_orderPlace_page = findViewById(R.id.back_orderPlace_page);
        orderPlaceIamge = findViewById(R.id.orderPlaceIamge);
        order_number = findViewById(R.id.order_number);
        orderPlacePName = findViewById(R.id.orderPlacePName);
        orderPlacedPrice = findViewById(R.id.orderPlacedPrice);
        orderPlacedQty = findViewById(R.id.orderPlacedQty);
        ContinueShopping = findViewById(R.id.ContinueShopping);
        PalcedPageButton = findViewById(R.id.PalcedPageButton);
        cardView = findViewById(R.id.cardView);

        String page = getIntent().getStringExtra("page");
        String Pid = getIntent().getStringExtra("id");
        String qlt = getIntent().getStringExtra("qlt");
        String image = getIntent().getStringExtra("image");
        String total = getIntent().getStringExtra("total");
        String order_id = getIntent().getStringExtra("order_id");
        String Add = getIntent().getStringExtra("Add");
        String name = getIntent().getStringExtra("name");
        String volume = getIntent().getStringExtra("volume");

        if(page.contains("cart")){
            cardView.setVisibility(View.GONE);
        }else {

            Glide.with(OrderPlacedPage.this)
                    .load(image)
                    .centerCrop()
                    .into(orderPlaceIamge);
            orderPlacePName.setText(name);
            orderPlacedPrice.setText("RS." + total + "/--");
            orderPlacedQty.setText("X" + qlt);

        }





        try{
            orderPalcedPageCartIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(OrderPlacedPage.this, MYCartActivity.class));
                    finish();
                }
            });

            back_orderPlace_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });
            ContinueShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(OrderPlacedPage.this, HomeNavigationActivity.class));
                }
            });
            PalcedPageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(OrderPlacedPage.this, OrderDetail.class);
                    in.putExtra("productId",Pid);
                    in.putExtra("qty",qlt);
                    in.putExtra("image",image);
                    in.putExtra("total",total);
                    in.putExtra("order_id",order_id);
                    in.putExtra("addId",Add);
                    in.putExtra("volume",volume);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }
}