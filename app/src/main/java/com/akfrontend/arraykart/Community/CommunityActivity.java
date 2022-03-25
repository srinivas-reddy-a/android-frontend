package com.akfrontend.arraykart.Community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akfrontend.arraykart.AllApiModels.CartUPdateRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.AllRetrofit.SharedPrefManager;
import com.akfrontend.arraykart.R;
import com.akfrontend.arraykart.SignUP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityActivity extends AppCompatActivity {

    private ImageView iv;
    SharedPrefManager sharedPrefManager;
    private TextView editTextTextPersonName,editTextTextPersonName2,AlreadyRegistered,textView17;
    private Button button3;
    private LinearLayout linearLayout9,linearLayout10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        iv=findViewById(R.id.imageView14);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        AlreadyRegistered = findViewById(R.id.AlreadyRegistered);
        textView17 = findViewById(R.id.textView17);
        button3 = findViewById(R.id.button3);
        linearLayout9 = findViewById(R.id.linearLayout9);
        linearLayout10 = findViewById(R.id.linearLayout10);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");
        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);
        if(user_token.contains("token")){

            Call<CartUPdateRespones> callPradh = RetrofitClient.getInstance().getApi().getPradhaan(token);
            callPradh.enqueue(new Callback<CartUPdateRespones>() {
                @Override
                public void onResponse(Call<CartUPdateRespones> call, Response<CartUPdateRespones> response) {
                    CartUPdateRespones cartUPdateRespones = response.body();
                    String msg = cartUPdateRespones.getMessage();
                    try {
                        if (msg.contains("Already Registered!")) {
//                            linearLayout9.setVisibility(View.GONE);
//                            linearLayout10.setVisibility(View.GONE);
//                            button3.setVisibility(View.GONE);
                            AlreadyRegistered.setVisibility(View.VISIBLE);
//                            textView17.setVisibility(View.GONE);
                        } else {
                            linearLayout9.setVisibility(View.VISIBLE);
                            linearLayout10.setVisibility(View.VISIBLE);
                            button3.setVisibility(View.VISIBLE);
//                            AlreadyRegistered.setVisibility(View.GONE);
                            textView17.setVisibility(View.VISIBLE);

                        }
                    }catch (Exception e){
                        Toast.makeText(CommunityActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CartUPdateRespones> call, Throwable t) {
                    Toast.makeText(CommunityActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }else {
            startActivity(new Intent(CommunityActivity.this, SignUP.class));
        }

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Community(editTextTextPersonName.getText().toString() , editTextTextPersonName2.getText().toString());
            }
        });


    }

    private void Community(String name , String Adhaar){
        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");
        if(user_token.contains("token")){
            Call<CartUPdateRespones> callCommu = RetrofitClient.getInstance().getApi().addCommunity(token,Adhaar,name);
            callCommu.enqueue(new Callback<CartUPdateRespones>() {
                @Override
                public void onResponse(Call<CartUPdateRespones> call, Response<CartUPdateRespones> response) {
                    if(response.isSuccessful()){
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<CartUPdateRespones> call, Throwable t) {
                    Toast.makeText(CommunityActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}