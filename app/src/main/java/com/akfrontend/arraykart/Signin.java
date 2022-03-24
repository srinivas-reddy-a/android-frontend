package com.akfrontend.arraykart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akfrontend.arraykart.AllApiModels.LogInOtpRespones;
import com.akfrontend.arraykart.AllApiModels.LogInRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.AllRetrofit.SharedPrefManager;
import com.akfrontend.arraykart.R;
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
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signin extends AppCompatActivity {

    CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;
    private static  int RC_SIGN_IN = 100;

    private static final int REQ_USER_CONSENT = 200;
    OtpReeiver otpReeiver;

    private static final int CREDENTIAL_PICKER_REQUEST =120 ;

    private ImageView imageView;
    private TextView signup,resendSingIn;
    private EditText Sign_in_page_otp,Sign_in_page_email;
    private Button Sign_in,Submit;

    public String UserToken;
    private String user_id;

    SharedPrefManager sharedPrefManager;

    private LoginButton loginButton;
    private  ImageView fb;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Sign_in_page_email = findViewById(R.id.Sign_in_page_email);
        Sign_in_page_otp = findViewById(R.id.Sign_in_page_otp);
        Submit = findViewById(R.id.Submit);
        Sign_in = findViewById(R.id.Sign_in);
        resendSingIn = findViewById(R.id.resendSingIn);
        progressBar = findViewById(R.id.progressBar);

        getNumberAuto();

        OtpRequestPermissions();

//        String ot = new OtpReeiver().getOtp();
//        Sign_in_page_otp.setText(ot);

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


//        createGoogleRequest();
//
//        ImageView sign_in_google = findViewById(R.id.sign_in_google);
//
//        sign_in_google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });



        try {
            Sign_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
//                    progressBar.setVisibility(View.VISIBLE);
                }
            });
        }catch (Exception e){

        }

        resendSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginOtpResend();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    private void OtpRequestPermissions(){

        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsUserConsent(null);
    }

    private void getNumberAuto(){
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();


        PendingIntent intent = Credentials.getClient(Signin.this).getHintPickerIntent(hintRequest);
        try
        {
            startIntentSenderForResult(intent.getIntentSender(), CREDENTIAL_PICKER_REQUEST, null, 0, 0, 0,new Bundle());
        }
        catch (IntentSender.SendIntentException e)
        {
            e.printStackTrace();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == RESULT_OK)
        {
            // Obtain the phone number from the result
            Credential credentials = data.getParcelableExtra(Credential.EXTRA_KEY);
            /* EditText.setText(credentials.getId().substring(3));*/ //get the selected phone number
            //Do what ever you want to do with your selected phone number here

            Sign_in_page_email.setText(credentials.getId().substring(3));

           // Toast.makeText(this, "MOB"+credentials.getId().substring(3), Toast.LENGTH_SHORT).show();


        }
        else if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE)
        {
            // *** No phone numbers available ***
            Toast.makeText(Signin.this, "No phone numbers found", Toast.LENGTH_LONG).show();
        }



        //otp resquest

        if (requestCode == REQ_USER_CONSENT){

            if ((resultCode == RESULT_OK) && (data != null)){

                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                getOtpFromMessage(message);


            }


        }
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

    private void getOtpFromMessage(String message) {

        Pattern otpPattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = otpPattern.matcher(message);
        if (matcher.find()){
            String number = Sign_in_page_email.getText().toString();
            Sign_in_page_otp.setText(matcher.group(0));
            loginOtp(user_id,number);

        }


    }

    private void registerBroadcastReceiver(){

        otpReeiver = new OtpReeiver();

        otpReeiver.smsBroadcastReceiverListener = new OtpReeiver.SmsBroadcastReceiverListener(){
            @Override
            public void onSuccess(Intent intent) {

                if (getPackageManager().getNameForUid(Binder.getCallingUid()).equals(BuildConfig.APPLICATION_ID)){
                   // startActivityForResult(intent,REQ_USER_CONSENT);
//                    startActivityIfNeeded(intent,REQ_USER_CONSENT);
                }


            }

            @Override
            public void onFailure() {

            }
        };

        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(otpReeiver,intentFilter);

    }


    private void login(){
        String number = Sign_in_page_email.getText().toString();

        if(number.isEmpty()){
            Sign_in_page_email.requestFocus();
            Sign_in_page_email.setError("please enter you number");
            return;
        }



        Call<LogInRespones> call = RetrofitClient
                .getInstance()
                .getApi().login(number);
        call.enqueue(new Callback<LogInRespones>() {
            @Override
            public void onResponse(Call<LogInRespones> call, Response<LogInRespones> response) {
                LogInRespones logInRespones = response.body();
                if(response.isSuccessful()){
//                    Toast.makeText(Signin.this, logInRespones.getMessage(), Toast.LENGTH_SHORT).show();
                    user_id = logInRespones.getId();
                    Sign_in_page_otp.setVisibility(View.VISIBLE);
                    resendSingIn.setVisibility(View.VISIBLE);
                    Sign_in.setVisibility(View.GONE);
                    Submit.setVisibility(View.VISIBLE);
                    Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loginOtp(user_id,number);
                            //progressBar.setVisibility(View.GONE);

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

    private void loginOtp(String user_id,String number){
        String otp = Sign_in_page_otp.getText().toString();

        if(otp.isEmpty()){
            Sign_in_page_otp.requestFocus();
            Sign_in_page_otp.setError("please enter valid otp");
            return;
        }else {
            Call<LogInOtpRespones> call = RetrofitClient
                    .getInstance()
                    .getApi().loginOtp(user_id, otp,number);
            call.enqueue(new Callback<LogInOtpRespones>() {
                @Override
                public void onResponse(Call<LogInOtpRespones> call, Response<LogInOtpRespones> response) {
                    LogInOtpRespones logInOtpRespones = response.body();
                    if (response.isSuccessful()) {
                        UserToken = logInOtpRespones.getToken();
                        sharedPrefManager.setValue_string("token", UserToken);
                        Toast.makeText(Signin.this, "Logged in successfully", Toast.LENGTH_LONG).show();
//                    Intent in = new Intent(Signin.this, HomeNavigationActivity.class);
//                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(in);
                        finish();
                        Submit.setVisibility(View.GONE);
                        Sign_in.setVisibility(View.VISIBLE);
                        Sign_in_page_otp.setVisibility(View.GONE);
                        resendSingIn.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);

                    } else if(response.code()==401) {
                        Sign_in_page_otp.setError("please enter valid otp");
                    }else
                    {
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            Toast.makeText(Signin.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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

    private void loginOtpResend(){
        String email = Sign_in_page_email.getText().toString();
        Call<ResponseBody> callResend = RetrofitClient.getInstance().getApi().registerOtpResend(email);
        callResend.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Signin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


//    private void createGoogleRequest(){
//        //google access
//        // Configure sign-in to request the user's ID, email address, and basic
//        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        // Build a GoogleSignInClient with the options specified by gso.
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        // Check for existing Google Sign In account, if the user is already signed in
//        // the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        //updateUI(account);
//    }
    ////google
//    private void signIn() {
//
//        if (getCallingActivity().getPackageName().equals(BuildConfig.APPLICATION_ID)){
//            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//            startActivityForResult(signInIntent, RC_SIGN_IN);
//        }
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //facebook
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//
//        //google
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }



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
//            Log.d("message",e.toString());
        }
    }



    /////facebook
    GraphRequest request1;

    private void handleFacebookToken(AccessToken token){
//        Log.d("tokenfb",token.getToken());
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

                        //Log.d("email_id","email_id");
                        //Log.d("gender","gender");

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
        registerBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(otpReeiver);
    }
}