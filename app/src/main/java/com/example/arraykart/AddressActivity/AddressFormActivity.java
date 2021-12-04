package com.example.arraykart.AddressActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.arraykart.R;

public class AddressFormActivity extends AppCompatActivity {

    private ImageView back_addressForm_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_form);

        try{
            back_addressForm_page = findViewById(R.id.back_addressForm_page);
            back_addressForm_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }catch (Exception e){

        }
    }
}