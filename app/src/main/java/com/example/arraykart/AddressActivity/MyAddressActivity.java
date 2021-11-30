package com.example.arraykart.AddressActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends AppCompatActivity {

    private RecyclerView addressRecyclerView;
    private LinearLayout addNewAddress;
    private ImageView back_Address_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        try {
            addressRecyclerView = findViewById(R.id.AddressPageRecyclerView);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            addressRecyclerView.setLayoutManager(layoutManager);

            List<AddressModel> addressModels = new ArrayList<>();
            addressModels.add(new AddressModel("ram Lal", "gupt nagar street 420,choro wali gali ,near bhoot ghati drna mnaa park ", "9211420"));
            addressModels.add(new AddressModel("ram Lal", "gupt nagar street 420,choro wali gali ,near bhoot ghati drna mnaa park ", "9211420"));
            addressModels.add(new AddressModel("ram Lal", "gupt nagar street 420,choro wali gali ,near bhoot ghati drna mnaa park ", "9211420"));
            addressModels.add(new AddressModel("ram Lal", "gupt nagar street 420,choro wali gali ,near bhoot ghati drna mnaa park ", "9211420"));

            AddressAdapter addressAdapter = new AddressAdapter(addressModels);
            addressRecyclerView.setAdapter(addressAdapter);

            addNewAddress = findViewById(R.id.AddNewAddress);
            addNewAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MyAddressActivity.this, AddressFormActivity.class));
                }
            });
        }catch (Exception e){

        }
        try{
            back_Address_page = findViewById(R.id.back_Address_page);
            back_Address_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }
    }
}