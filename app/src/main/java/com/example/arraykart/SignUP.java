package com.example.arraykart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignUP extends AppCompatActivity {
    private ImageView imageView;
    private TextView signintv;
    private AppCompatButton signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        try{
            imageView = findViewById(R.id.imageView6);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }

        try {
            signintv = findViewById(R.id.textView21);
            signintv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(new Intent(SignUP.this, Signin.class));
                }
            });
        }catch (Exception e){

        }

        try {
            signupbtn = findViewById(R.id.button4);
            signupbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Signed Up!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){

        }

    }
}