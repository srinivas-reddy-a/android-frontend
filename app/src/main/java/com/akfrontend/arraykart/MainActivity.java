package com.akfrontend.arraykart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.akfrontend.arraykart.OnBoardingPage.OnBoardingActivity;
import com.akfrontend.arraykart.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getPackageManager().getNameForUid(Binder.getCallingUid()).equals(BuildConfig.APPLICATION_ID)){
                    Intent i = new Intent(MainActivity.this, OnBoardingActivity.class);
                    startActivity(i);
                    finish();
                }
//                Intent i = new Intent(MainActivity.this, OnBoardingActivity.class);
//                startActivity(i);
//                Toast.makeText(MainActivity.this, getPackageManager().getNameForUid(Binder.getCallingUid()), Toast.LENGTH_SHORT).show();
//                finish();
            }
        }, 3000);

//        printKeyHash();
    }

//    private void printKeyHash() {
//        try{
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for(Signature signature:info.signatures){
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KEY_HASH", Base64.encodeToString(md.digest(),Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
}