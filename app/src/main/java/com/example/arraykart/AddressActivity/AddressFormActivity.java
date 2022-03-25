package com.example.arraykart.AddressActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arraykart.AllApiModels.AddressFormRespones;
import com.example.arraykart.AllApiModels.GetAddressRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.HomeNavigationActivity;
import com.example.arraykart.R;
import com.example.arraykart.SignUP;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressFormActivity extends AppCompatActivity {

    private ImageView back_addressForm_page;
    private EditText UserCity,UserAddressLine1,UserAddressLine2,UserPinCode,UserState,USerFullName,
            UserMobileNumber,UserAlternativeNumber;
    private TextView savedAddress;
    private Button UserAddAddress;

    private List<AddressModel> addressModels;

    SharedPrefManager sharedPrefManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_form);

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");
        final String[] it_default = new String[1];
//
        UserCity = findViewById(R.id.UserCity);
        UserAddressLine1 = findViewById(R.id.UserAddressLine1);
        UserAddressLine2 = findViewById(R.id.UserAddressLine2);
        UserPinCode = findViewById(R.id.UserPinCode);
        UserState = findViewById(R.id.UserState);
        USerFullName = findViewById(R.id.USerFullName);
        UserMobileNumber = findViewById(R.id.UserMobileNumber);
        UserAlternativeNumber = findViewById(R.id.UserAlternativeNumber);
        UserAddAddress = findViewById(R.id.UserAddAddress);
        savedAddress = findViewById(R.id.savedAddress);
//
//
//        String id = getIntent().getStringExtra("id");
//        String address_name = getIntent().getStringExtra("address_name");
//        String address_line1 = getIntent().getStringExtra("address_line1");
//        String address_line2 = getIntent().getStringExtra("address_line2");
//        String city= getIntent().getStringExtra("city");
//        String postal_code = getIntent().getStringExtra("postal_code");
//        String state = getIntent().getStringExtra("state");
//        String phone_number = getIntent().getStringExtra("phone_number");
//
//        UserCity.setText(city);
//        USerFullName.setText(address_name);
//        UserAddressLine1.setText(address_line1);
//        UserAddressLine2.setText(address_line2);
//        UserPinCode.setText(postal_code);
//        UserState.setText(state);
//        UserMobileNumber.setText(phone_number);


        Call<GetAddressRespones> call = RetrofitClient
                .getInstance()
                .getApi().getAddress(token);
        call.enqueue(new Callback<GetAddressRespones>() {
            @Override
            public void onResponse(Call<GetAddressRespones> call, Response<GetAddressRespones> response) {
                if(response.isSuccessful()) {
                    addressModels = response.body().getAddress();
                    if(!addressModels.isEmpty()){
                        it_default[0] ="0";
//                        savedAddress.setVisibility(View.GONE);
                    }else {
                        it_default[0] = "1";
//                        savedAddress.setVisibility(View.VISIBLE);
                    }
                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddressFormActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        Toast.makeText(AddressFormActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAddressRespones> call, Throwable t) {
                Toast.makeText(AddressFormActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



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
                if(Integer.parseInt(UserPinCode.getText().toString()) != 202001 && Integer.parseInt(UserPinCode.getText().toString()) != 202002 ){
                    alert("we are not servicing in your area we will reach you soon");
                    UserPinCode.requestFocus();
                    UserPinCode.setError("we will reach soon on this pincode");
                    return;
                }else {
                    String name = USerFullName.getText().toString();
                    String addr1 = UserAddressLine1.getText().toString();
                    String addr2 = UserAddressLine2.getText().toString();
                    String city = UserCity.getText().toString();
                    String pinCode = UserPinCode.getText().toString();
                    String state = UserState.getText().toString();
                    String number = UserMobileNumber.getText().toString();
                    String AlNumber = UserAlternativeNumber.getText().toString();

                    if(name.isEmpty()){
                        USerFullName.requestFocus();
                        USerFullName.setError("please enter you name");
                        return;
                    }
                    if(addr1.isEmpty()){
                        UserAddressLine1.requestFocus();
                        UserAddressLine1.setError("please enter you Address");
                        return;
                    }
                    if(city.isEmpty()){
                        UserCity.requestFocus();
                        UserCity.setError("please enter you city");
                        return;
                    }
                    if(pinCode.isEmpty()){
                        UserPinCode.requestFocus();
                        UserPinCode.setError("please enter you pincode");
                        return;
                    }
                    if(state.isEmpty()){
                        UserState.requestFocus();
                        UserState.setError("please enter you state");
                        return;
                    }
                    if(number.isEmpty()){
                        UserMobileNumber.requestFocus();
                        UserMobileNumber.setError("please enter you number");
                        return;
                    }


                    Call<AddressFormRespones> call = RetrofitClient
                            .getInstance()
                            .getApi().UserAddressForm(token, name, addr1, addr2, city, pinCode, state, number, AlNumber, it_default[0]);
                    call.enqueue(new Callback<AddressFormRespones>() {
                        @Override
                        public void onResponse(Call<AddressFormRespones> call, Response<AddressFormRespones> response) {
                            AddressFormRespones addressFormRespones = response.body();
                            if (response.isSuccessful()) {
                                Toast.makeText(AddressFormActivity.this, addressFormRespones.getMsg(), Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                    Toast.makeText(AddressFormActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                } catch (Exception e) {
                                    Toast.makeText(AddressFormActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AddressFormRespones> call, Throwable t) {
                            Toast.makeText(AddressFormActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }

    private void alert(String message){
        AlertDialog alg = new AlertDialog.Builder(AddressFormActivity.this)
                .setTitle("Sorry!!!!")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alg.show();
    }


}