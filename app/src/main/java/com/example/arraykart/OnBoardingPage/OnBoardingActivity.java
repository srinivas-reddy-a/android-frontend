package com.example.arraykart.OnBoardingPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arraykart.HomeNavigationActivity;
import com.example.arraykart.R;

public class OnBoardingActivity extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String firstTime = preferences.getString("FirstTimeInstall", "");
        if(firstTime.equals("Yes")){
            Intent intent = new Intent(OnBoardingActivity.this, HomeNavigationActivity.class);
            startActivity(intent);
        }
        else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();

            String original = "You can trust us with your money, products we deal in are 100% authentic.";
            String homeDelivery = "We deliver at your door-step within 3 to 5 days, so no shopping out hustles.";
            String factory = "We deal directly with the manufacturers, ofcourse we reduce the costs.";
            TextView textView = (TextView) findViewById(R.id.textView);
            Button button = (Button) findViewById(R.id.button);
            Button skip = (Button) findViewById(R.id.skip);
            ImageView imgView1 = (ImageView) findViewById(R.id.navimage1);
            ImageView imgView2 = (ImageView) findViewById(R.id.navimage2);
            ImageView imgView3 = (ImageView) findViewById(R.id.navimage3);
            ImageView imgView4 = (ImageView) findViewById(R.id.imageView1);

            imgView4.setImageDrawable(getResources().getDrawable(R.drawable.original));
            imgView1.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
            textView.setText(original);

            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OnBoardingActivity.this, HomeNavigationActivity.class);
                    startActivity(intent);
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (textView.getText().toString().equals(original)) {
                        imgView4.setImageDrawable(getResources().getDrawable(R.drawable.homedelivery));
                        imgView2.setColorFilter(ContextCompat.getColor(OnBoardingActivity.this, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                        imgView1.setColorFilter(ContextCompat.getColor(OnBoardingActivity.this, R.color.light_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                        textView.setText(homeDelivery);
                    }

                    else if (textView.getText().toString().equals(homeDelivery)) {
                        imgView4.setImageDrawable(getResources().getDrawable(R.drawable.factory));
                        imgView3.setColorFilter(ContextCompat.getColor(OnBoardingActivity.this, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
                        imgView2.setColorFilter(ContextCompat.getColor(OnBoardingActivity.this, R.color.light_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                        textView.setText(factory);
                    }

                    else {
                        Intent intent = new Intent(OnBoardingActivity.this, HomeNavigationActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}