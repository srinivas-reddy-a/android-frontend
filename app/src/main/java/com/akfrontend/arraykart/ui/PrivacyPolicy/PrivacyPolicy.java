package com.akfrontend.arraykart.ui.PrivacyPolicy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.akfrontend.arraykart.R;

public class PrivacyPolicy extends AppCompatActivity {

    ImageView imageView14 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        imageView14 = findViewById(R.id.imageView14);

        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}