package com.example.arraykart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arraykart.AllApiModels.LogInOtpRespones;
import com.example.arraykart.AllApiModels.LogInRespones;
import com.example.arraykart.AllApiModels.SignUpRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.UserProfile.UserProfileActivity;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signin extends AppCompatActivity {
    private ImageView imageView;
    private TextView signup;
    private TextView Sign_in_page_otp,Sign_in_page_email;
    private Button Sign_in,Submit;

    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Sign_in_page_email = findViewById(R.id.Sign_in_page_email);
        Sign_in_page_otp = findViewById(R.id.Sign_in_page_otp);
        Submit = findViewById(R.id.Submit);
        Sign_in = findViewById(R.id.Sign_in);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        try {
            imageView = findViewById(R.id.imageView5);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }
        try {
            signup = findViewById(R.id.textView17);
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(new Intent(Signin.this, SignUP.class));
                }
            });
        }catch (Exception e){

        }
        try {
            Sign_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        }catch (Exception e){

        }

    }

    private void login(){
        String email = Sign_in_page_email.getText().toString();


        if(email.isEmpty()){
            Sign_in_page_email.requestFocus();
            Sign_in_page_email.setError("please enter you email");
            return;
        }


        Call<LogInRespones> call = RetrofitClient
                .getInstance()
                .getApi().login(email);
        call.enqueue(new Callback<LogInRespones>() {
            @Override
            public void onResponse(Call<LogInRespones> call, Response<LogInRespones> response) {
                LogInRespones logInRespones = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(Signin.this, logInRespones.getMessage(), Toast.LENGTH_SHORT).show();
                    Sign_in_page_otp.setVisibility(View.VISIBLE);
                    Sign_in.setVisibility(View.GONE);
                    Submit.setVisibility(View.VISIBLE);
                    Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loginOtp();
                            Submit.setVisibility(View.GONE);
                            Sign_in.setVisibility(View.VISIBLE);
                            Sign_in_page_otp.setVisibility(View.GONE);
                            startActivity(new Intent(Signin.this,UserProfileActivity.class));
                        }
                    });
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(Signin.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(Signin.this,SignUP.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(in);

                    } catch (Exception e) {
                        Toast.makeText(Signin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LogInRespones> call, Throwable t) {
                Toast.makeText(Signin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loginOtp(){
        String otp = Sign_in_page_otp.getText().toString();

        if(otp.isEmpty()){
            Sign_in_page_otp.requestFocus();
            Sign_in_page_otp.setError("please enter otp first");
            return;
        }
        Call<LogInOtpRespones> call = RetrofitClient
                .getInstance()
                .getApi().loginOtp(otp);
        call.enqueue(new Callback<LogInOtpRespones>() {
            @Override
            public void onResponse(Call<LogInOtpRespones> call, Response<LogInOtpRespones> response) {
                LogInOtpRespones logInOtpRespones = response.body();
                if(response.isSuccessful()){

                    if(logInOtpRespones.equals("200")){
                        sharedPrefManager.saveUser(logInOtpRespones.getUser());
                        startActivity(new Intent(Signin.this,UserProfileActivity.class));
                        Toast.makeText(Signin.this,logInOtpRespones.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(Signin.this,logInOtpRespones.getToken(), Toast.LENGTH_SHORT).show();
                }else {
                    try {
                         JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(Signin.this,jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();

                        Intent in = new Intent(Signin.this, SignUP.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(in);

                    } catch (Exception e) {
                        Toast.makeText(Signin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LogInOtpRespones> call, Throwable t) {
                Toast.makeText(Signin.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPrefManager.isLoggedIn()){
            startActivity(new Intent(Signin.this,UserProfileActivity.class));

        }
    }
}