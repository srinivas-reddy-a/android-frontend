package com.akfrontend.arraykart.DeliveryPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.akfrontend.arraykart.R;

public class DeliveryActivity extends AppCompatActivity {

    private RecyclerView deliveryPageRecyclerView;
    private ImageView back_Delivery_page;
    private Button changeAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

//        try {
//            deliveryPageRecyclerView = findViewById(R.id.deliveryPageRecyclerView);
//
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            deliveryPageRecyclerView.setLayoutManager(layoutManager);
//
////            List<CartItemModel> cartItemModelList = new ArrayList<>();
////            cartItemModelList.add(new CartItemModel(0,"1",R.drawable.img,"product name",2,"₹ ---/-","₹ ---",1,0,0));
////            cartItemModelList.add(new CartItemModel(0,"2",R.drawable.img,"product name",0,"₹ ---/-","₹ ---",2,1,0));
////            cartItemModelList.add(new CartItemModel(0,"3",R.drawable.img,"product name",1,"₹ ---/-","₹ ---",3,0,1));
////            cartItemModelList.add(new CartItemModel(1,"Price(3 item)","₹ ----","Free","₹ 342553767"));
////
////            CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
////            deliveryPageRecyclerView.setAdapter(cartAdapter);
////            cartAdapter.notifyDataSetChanged();
//
//        }catch (Exception e){
//
//        }
//        try {
//            changeAddress = findViewById(R.id.changeAddress);
//            changeAddress.setVisibility(View.VISIBLE);
//            changeAddress.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(DeliveryActivity.this, MyAddressActivity.class));
//                }
//            });
//
//        }catch (Exception e){
//
//        }
//        back_Delivery_page = findViewById(R.id.back_Delivery_page);
//        back_Delivery_page.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}