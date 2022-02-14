package com.example.arraykart.HelpCenterActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.arraykart.R;

public class HelpCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ImageView imgView = (ImageView) findViewById(R.id.backButton);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
 }

    public void onSupportNavigateUp(View view) {
        finish();
    }

    public void sendEmail(View view){
        String uriText =
                "yourfriends@arraykart.com" +
                        "?subject=" + Uri.encode("some subject text here") +
                        "&body=" + Uri.encode("some text here");


        try {
            Uri uri = Uri.parse(uriText);
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(uri);
            startActivity(Intent.createChooser(intent, "Send email through..."));
        }
        catch(Exception e){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String uriText = "tel:+919311900912";
        try {
            intent.setData(Uri.parse(uriText));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}