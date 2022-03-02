package com.example.arraykart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signin extends AppCompatActivity {

    CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;
    private static  int RC_SIGN_IN = 100;

    private ImageView imageView;
    private TextView signup;
    private TextView Sign_in_page_otp,Sign_in_page_email;
    private Button Sign_in,Submit;

    public String UserToken;

    SharedPrefManager sharedPrefManager;

    private LoginButton loginButton;
    private  ImageView fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Sign_in_page_email = findViewById(R.id.Sign_in_page_email);
        Sign_in_page_otp = findViewById(R.id.Sign_in_page_otp);
        Submit = findViewById(R.id.Submit);
        Sign_in = findViewById(R.id.Sign_in);

        String number = getIntent().getStringExtra("number");
//        if(number != null){
//            Sign_in_page_email.setText(number);
//        }

        sharedPrefManager = new SharedPrefManager(this);
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
//        try {
//            signup = findViewById(R.id.textView17);
//            signup.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                    startActivity(new Intent(Signin.this, SignUP.class));
//                }
//            });
//        }catch (Exception e){
//
//        }



        //facebook access
        callbackManager =CallbackManager.Factory.create();
        fb = (ImageView) findViewById(R.id.fb);
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


        createGoogleRequest();

        ImageView sign_in_google = findViewById(R.id.sign_in_google);

        sign_in_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });





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
            Sign_in_page_email.setError("please enter you number");
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
                    String user_id = logInRespones.getId();
                    Sign_in_page_otp.setVisibility(View.VISIBLE);
                    Sign_in.setVisibility(View.GONE);
                    Submit.setVisibility(View.VISIBLE);
                    Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loginOtp(user_id);
                        }
                    });
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(Signin.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        finish();
                        Intent in = new Intent(Signin.this,SignUP.class);
//                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    private void loginOtp(String user_id){
        String otp = Sign_in_page_otp.getText().toString();

        if(otp.isEmpty()){
            Sign_in_page_otp.requestFocus();
            Sign_in_page_otp.setError("please enter otp first");
            return;
        }else {
            Submit.setVisibility(View.GONE);
            Sign_in.setVisibility(View.VISIBLE);
            Sign_in_page_otp.setVisibility(View.GONE);
            Call<LogInOtpRespones> call = RetrofitClient
                    .getInstance()
                    .getApi().loginOtp(user_id, otp);
            call.enqueue(new Callback<LogInOtpRespones>() {
                @Override
                public void onResponse(Call<LogInOtpRespones> call, Response<LogInOtpRespones> response) {
                    LogInOtpRespones logInOtpRespones = response.body();
                    if (response.isSuccessful()) {
                        UserToken = logInOtpRespones.getToken();
                        sharedPrefManager.setValue_string("token", UserToken);
                        Toast.makeText(Signin.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
//                    Intent in = new Intent(Signin.this, HomeNavigationActivity.class);
//                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(in);
                        finish();

                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            Toast.makeText(Signin.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
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
    }


    private void createGoogleRequest(){
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
    }
    ////google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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



    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
//                String personName = acct.getDisplayName();
//                String personGivenName = acct.getGivenName();
//                String personFamilyName = acct.getFamilyName();
//                String personEmail = acct.getEmail();
//                String personId = acct.getId();
//                Uri personPhoto = acct.getPhotoUrl();
                String token = acct.getIdToken();
                sharedPrefManager.setValue_string("token", token);

            }
            finish();

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



    /////facebook
    GraphRequest request1;

    private void handleFacebookToken(AccessToken token){
        Log.d("tokenfb",token.getToken());
        Profile profile = Profile.getCurrentProfile();
        if(profile!=null){
            //String facebook_id= profile.getId();
            String UName = profile.getName();
            String uLastName = profile.getId();

//            Sign_in_page_email.setText(UName);

            request1 = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse graphResponse) {
                    try {
                        String email_id= jsonObject.getString("email");
                        String gender = jsonObject.getString("gender");
                        String profile_name = jsonObject.getString("name");
                        String fb_id = jsonObject.getString("id"); ///we will use this for logout
                        String token = jsonObject.getString("token");

                        sharedPrefManager.setValue_string("token",token);

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
    public void logIn(View view){
        LoginManager.getInstance().logInWithReadPermissions(this,
                Arrays.asList("public_profile", "email", "user_birthday")
        );
    }
    public void onClick(View v) {
        if (v == fb) {
            loginButton.performClick();
            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("user_photos", "email", "user_birthday", "public_profile")
            );
        }
    }

//    private void nextActivity(Profile profile){
//        if(profile != null){
//            Intent i = new Intent(Signin.this, UserProfileActivity.class);
//            i.putExtra("name", profile.getFirstName());
//            i.putExtra("surname", profile.getLastName());
//            //i.putExtra("email", profile.get());
//            i.putExtra("imageUrl",
//                    profile.getProfilePictureUri(200,200).toString());
//            startActivity(i);
//            //finish();
//        }
//    }



    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
        if(userToken.contains("token")){

        }
    }
}