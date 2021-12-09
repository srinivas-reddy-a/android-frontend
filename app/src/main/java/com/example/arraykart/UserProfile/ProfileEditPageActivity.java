package com.example.arraykart.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.arraykart.R;

public class ProfileEditPageActivity extends AppCompatActivity {
    private ImageView back_edit_profile_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_page);


        try{
            back_edit_profile_page= findViewById(R.id.back_edit_profile_page);
            back_edit_profile_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }catch (Exception e){

        }


    }
}