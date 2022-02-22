package com.example.arraykart.AddressActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.arraykart.AllApiModels.AddressUpdateRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        String altNum = getIntent().getStringExtra("alt_num");

        UserCity.setText(city);
        USerFullName.setText(address_name);
        UserAddressLine1.setText(address_line1);
        UserAddressLine2.setText(address_line2);
        UserPinCode.setText(postal_code);
        UserState.setText(state);
        UserMobileNumber.setText(phone_number);
        UserAlternativeNumber.setText(altNum);


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

        UserAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = USerFullName.getText().toString();
                String addr1 = UserAddressLine1.getText().toString();
                String addr2 = UserAddressLine2.getText().toString();
                String city = UserCity.getText().toString();
                String pinCode = UserPinCode.getText().toString();
                String state = UserState.getText().toString();
                String number =UserMobileNumber.getText().toString();
                String AlNumber = UserAlternativeNumber.getText().toString();
                Call<AddressUpdateRespones> callUpdate = RetrofitClient.getInstance().getApi().updateAddress(token,id,name,addr1,addr2,city,pinCode,state,number,AlNumber,"0");

                callUpdate.enqueue(new Callback<AddressUpdateRespones>() {
                    @Override
                    public void onResponse(Call<AddressUpdateRespones> call, Response<AddressUpdateRespones> response) {
                        Toast.makeText(AddressEditForm.this, "Edit Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<AddressUpdateRespones> call, Throwable t) {
                        Toast.makeText(AddressEditForm.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}