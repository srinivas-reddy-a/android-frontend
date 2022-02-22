package com.example.arraykart.AddressActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.R;

public class AddressEditForm extends AppCompatActivity {

    private ImageView back_addressForm_page;
    private EditText UserCity,UserAddressLine1,UserAddressLine2,UserPinCode,UserState,USerFullName,
            UserMobileNumber,UserAlternativeNumber;
    private Button UserAddAddress;

    SharedPrefManager sharedPrefManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit_form);

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);

        UserCity = findViewById(R.id.UserCity);
        UserAddressLine1 = findViewById(R.id.UserAddressLine1);
        UserAddressLine2 = findViewById(R.id.UserAddressLine2);
        UserPinCode = findViewById(R.id.UserPinCode);
        UserState = findViewById(R.id.UserState);
        USerFullName = findViewById(R.id.USerFullName);
        UserMobileNumber = findViewById(R.id.UserMobileNumber);
        UserAlternativeNumber = findViewById(R.id.UserAlternativeNumber);
        UserAddAddress = findViewById(R.id.UserAddAddress);

        String id = getIntent().getStringExtra("id");
        String address_name = getIntent().getStringExtra("address_name");
        String address_line1 = getIntent().getStringExtra("address_line1");
        String address_line2 = getIntent().getStringExtra("address_line2");
        String city= getIntent().getStringExtra("city");
        String postal_code = getIntent().getStringExtra("postal_code");
        String state = getIntent().getStringExtra("state");
        String phone_number = getIntent().getStringExtra("phone_number");

        UserCity.setText(city);
        USerFullName.setText(address_name);
        UserAddressLine1.setText(address_line1);
        UserAddressLine2.setText(address_line2);
        UserPinCode.setText(postal_code);
        UserState.setText(state);
        UserMobileNumber.setText(phone_number);

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

        try {
            UserAddAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    updateAddress(token,id);
                }
            });
        }catch (Exception e){

        }
    }
//    private void updateAddress(String token , String id){
//
//    }
}