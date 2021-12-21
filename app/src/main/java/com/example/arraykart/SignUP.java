package com.example.arraykart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arraykart.AllApiModels.SignUpRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.UserProfile.UserProfileActivity;
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
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUP extends AppCompatActivity {

    CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;
    private static  int RC_SIGN_IN = 100;

    private ImageView imageView;
    private TextView signintv;
    private Button signupbtn;
    private EditText signUpUserName,signUpUserEmail,signUpUserPassword,signUpUserPassw0rdConform;
    private LoginButton loginButton;
    private  Button fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_sign_up);

        signUpUserName = findViewById(R.id.signUpUserName);
        signUpUserEmail = findViewById(R.id.signUpUserEmail);
        signUpUserPassword = findViewById(R.id.signUpUserPassword);
        signUpUserPassw0rdConform = findViewById(R.id.signUpUserPasswordConform);
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
                    registerUser();

                }
            });
        }catch (Exception e){

        }

        //facebook access
        callbackManager =CallbackManager.Factory.create();
        fb = (Button) findViewById(R.id.fb);
        loginButton = (LoginButton) findViewById(R.id.login_button);

        List< String > permissionNeeds = Arrays.asList("user_photos", "email",
                "user_birthday", "public_profile", "AccessToken");

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

        //google access
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }

    private void  registerUser(){
        String userName = signUpUserName.getText().toString();
        String userEmail=signUpUserEmail.getText().toString() ;
        String userPassword =signUpUserPassword.getText().toString();
        String userCP =signUpUserPassw0rdConform.getText().toString();

        if(userName.isEmpty()){
            signUpUserName.requestFocus();
            signUpUserName.setError("please enter you name");
            return;
        }
//        if(userEmail.isEmpty()){
//            signUpUserEmail.requestFocus();
//            signUpUserEmail.setError("please enter you email");
//            return;
//        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
//            signUpUserEmail.requestFocus();
//            signUpUserEmail.setError("please enter you correctEmail");
//            return;
//        }
//        if(userPassword.isEmpty()){
//            signUpUserPassword.requestFocus();
//            signUpUserPassword.setError("please enter you name");
//            return;
//        }
//        if(userPassword != userCP){
//            signUpUserPassw0rdConform.requestFocus();
//            signUpUserPassw0rdConform.setError("please enter correct password");
//            return;
//        }

        Call<SignUpRespones> call = RetrofitClient
                .getInstance()
                .getApi().signUp(userName);
        call.enqueue(new Callback<SignUpRespones>() {
            @Override
            public void onResponse(Call<SignUpRespones> call, Response<SignUpRespones> response) {
                SignUpRespones signUpRespones = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(SignUP.this, signUpRespones.getMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SignUP.this, signUpRespones.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpRespones> call, Throwable t) {
                Toast.makeText(SignUP.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    //facebook

    GraphRequest request1;

    private void handleFacebookToken(AccessToken token){
        Log.d("tokenfb",token.getToken());
        Profile profile = Profile.getCurrentProfile();
        if(profile!=null){
            //String facebook_id= profile.getId();
            String UName = profile.getName();
            String uLastName = profile.getId();

            signUpUserName.setText(UName);
            signUpUserEmail.setText(uLastName);

            request1 = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse graphResponse) {
                    try {
                        String email_id= jsonObject.getString("email");
                        String gender = jsonObject.getString("gender");
                        String profile_name = jsonObject.getString("name");
                        String fb_id = jsonObject.getString("id"); ///we will use this for logout

                        signUpUserEmail.setText(email_id);

                        Log.d("email_id","email_id");
                        Log.d("gender","gender");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            request1.executeAsync();

            // nextActivity(profile);

        }
    }
//    public void logIn(View view){
//        LoginManager.getInstance().logInWithReadPermissions(this,
//                Arrays.asList("public_profile", "email", "user_birthday")
//        );
//    }
    public void onClick(View v) {
        if (v == fb) {
            loginButton.performClick();
            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("user_photos", "email", "user_birthday", "public_profile")
            );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);

        //google

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent i = new Intent(SignUP.this, UserProfileActivity.class);
            i.putExtra("name", profile.getFirstName());
            i.putExtra("surname", profile.getLastName());
            //i.putExtra("email", profile.get());
            i.putExtra("imageUrl",
                    profile.getProfilePictureUri(200,200).toString());
            startActivity(i);
            //finish();
        }
    }

    ////google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
            }

            startActivity(new Intent(SignUP.this,UserProfileActivity.class));

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
            Log.d("message",e.toString());
        }
    }


}