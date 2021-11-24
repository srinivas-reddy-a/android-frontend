package com.example.arraykart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    private ImageView imageView;
    private TextView signup;
    private ConstraintLayout cl;
    private AppCompatButton signinbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
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
            signinbtn =findViewById(R.id.button3);
            signinbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Signed in!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){

        }



    }

}