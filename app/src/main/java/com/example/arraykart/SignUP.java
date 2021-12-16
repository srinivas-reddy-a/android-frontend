package com.example.arraykart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SignUP extends AppCompatActivity {

    CallbackManager callbackManager;

    private ImageView imageView;
    private TextView signintv;
    private Button signupbtn;
    private EditText signUpUserName,signUpUserEmail,signUpUserPassword,signUpUserPassw0rdConform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_sign_up);

        callbackManager =CallbackManager.Factory.create();

        signUpUserName = findViewById(R.id.signUpUserName);
        signUpUserEmail = findViewById(R.id.signUpUserEmail);
        signUpUserPassword = findViewById(R.id.signUpUserPassword);
        signUpUserPassw0rdConform = findViewById(R.id.signUpUserPasswordConform);

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

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

                }
            });
        }catch (Exception e){

        }

    }

    public void logIn(View view){
        LoginManager.getInstance().logInWithReadPermissions(this,
                Arrays.asList("email")
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    GraphRequest request1;

    private void handleFacebookToken(AccessToken token){
        Log.d("tokenfb",token.getToken());
        Profile profile = Profile.getCurrentProfile();
        if(profile!=null){
            String facebook_id= profile.getId();
            String UName = profile.getName();
            String email = profile.getLastName();

            signUpUserName.setText(UName);
            signUpUserEmail.setText(email);

            request1 = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse graphResponse) {
                    try {
                        String email_id= jsonObject.getString("email");
                        String gender = jsonObject.getString("gender");
                        String profile_name = jsonObject.getString("name");
                        String fb_id = jsonObject.getString("id"); ///we will use this for logout

                        Log.d("email_id","email_id");
                        Log.d("gender","gender");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            request1.executeAsync();
        }
    }

}