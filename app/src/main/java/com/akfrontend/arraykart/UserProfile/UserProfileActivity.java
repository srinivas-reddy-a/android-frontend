package com.akfrontend.arraykart.UserProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akfrontend.arraykart.BuildConfig;
import com.bumptech.glide.Glide;
import com.akfrontend.arraykart.AddressActivity.MyAddressActivity;
import com.akfrontend.arraykart.AllApiModels.AuthRespones;
import com.akfrontend.arraykart.AllApiModels.LogOutRespones;
import com.akfrontend.arraykart.AllApiModels.User;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.AllRetrofit.SharedPrefManager;
import com.akfrontend.arraykart.MyCart.MYCartActivity;
import com.akfrontend.arraykart.MyOrder.MyOrder;
import com.akfrontend.arraykart.NotificationPage.NotificationActivity;
import com.akfrontend.arraykart.R;
import com.akfrontend.arraykart.WishList.WishListActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;

    private RecyclerView recyclerView;
    private UserProfileAdapter adapter;
    private UserProfileModel model;
    
    private ImageView editProfile;

    private ImageView back_user_profile_page;

    private TextView account_settings;

    private TextView notification_preferences;

    private TextView logout_of_this_app;

    private TextView logout_of_this_devices;

    private ShapeableImageView UserProfileImage;
    private TextView UserName;
    private TextView UserEmail;

    SharedPrefManager sharedPrefManager;
    private List<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        recyclerView = findViewById(R.id.UserProfileRecyclerView);
        UserName = findViewById(R.id.UserName);
        UserEmail = findViewById(R.id.UserEmail);
        UserProfileImage = findViewById(R.id.UserProfileImage);

        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        UserProfile();

        LinearLayoutManager layoutManager = new LinearLayoutManager(UserProfileActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        List<UserProfileModel> userProfileModelList = new ArrayList<>();
        userProfileModelList.add(new UserProfileModel("My WishList","View Your WishList"));
        userProfileModelList.add(new UserProfileModel("My Order","View Your Order"));
        userProfileModelList.add(new UserProfileModel("My Cart","View Your Cart"));
        userProfileModelList.add(new UserProfileModel("My Address","View Your MyAddress"));
        adapter = new UserProfileAdapter(userProfileModelList);
        recyclerView.setAdapter(adapter);

        try{
            adapter.setOnItemClickListener(new UserProfileAdapter.OnItemClickListener() {
                @Override
                public void onClickListener(int position) {
//                    for (int i = 0;i<userProfileModelList.size();i++){
                        if(position==0){
                            startActivity(new Intent(UserProfileActivity.this, WishListActivity.class));
                        }else if(position==1){
                            startActivity(new Intent(UserProfileActivity.this, MyOrder.class));
                        }else if(position==2){
                            startActivity(new Intent(UserProfileActivity.this, MYCartActivity.class));
                        }else if(position==3){
                            startActivity(new Intent(UserProfileActivity.this, MyAddressActivity.class));
                        }
//                    }
                }
            });
        }catch (Exception e){

        }

        try{
            editProfile = findViewById(R.id.EditProfile);
            editProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //edit page
                    if(getPackageManager().getNameForUid(Binder.getCallingUid()).equals(BuildConfig.APPLICATION_ID)){
                        startActivity(new Intent(UserProfileActivity.this, ProfileEditPageActivity.class));
                    }


                }
            });

        }catch (Exception e){

        }

        try{
            back_user_profile_page = findViewById(R.id.back_user_profile_page);
            back_user_profile_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch(Exception e){

        }
        try {
            account_settings=findViewById(R.id.account_settings);
            account_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getPackageManager().getNameForUid(Binder.getCallingUid()).equals(BuildConfig.APPLICATION_ID)){
                        startActivity(new Intent(UserProfileActivity.this,ProfileEditPageActivity.class));
                    }
                }
            });

        }catch (Exception e){

        }
        try{
            notification_preferences = findViewById(R.id.notification_preferences);
            notification_preferences.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(UserProfileActivity.this, NotificationActivity.class));
                }
            });

        }catch (Exception e){

        }
        try{
            logout_of_this_app=findViewById(R.id.logout_of_this_app);
            logout_of_this_app.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alg = new AlertDialog.Builder(UserProfileActivity.this)
                            .setTitle("Message")
                            .setMessage("Are You Sure You Want To LogOut ??")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    if(user_token.contains("token")){
                                        sharedPrefManager.clear();
                                        sharedPrefManager.setValue_string("GPS","gps");


                                        Call<LogOutRespones> callOut = RetrofitClient.getInstance().getApi().logout(token);
                                        callOut.enqueue(new Callback<LogOutRespones>() {
                                            @Override
                                            public void onResponse(Call<LogOutRespones> call, Response<LogOutRespones> response) {
                                                LogOutRespones logOutRespones = response.body();
                                                Toast.makeText(UserProfileActivity.this, logOutRespones.getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(Call<LogOutRespones> call, Throwable t) {
                                                Toast.makeText(UserProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
                                    }else {
                                        signOut();
                                    }
//
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create();
                    alg.show();
                          // finish();

                }
            });
        }catch (Exception e){

        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
//            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            UserName.setText(personName);
            UserEmail.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(UserProfileImage);

        }

        //sharedpre
//
//        String username = sharedPrefManager.getUser().getName();
//        String useremail = sharedPrefManager.getUser().getPhone_number();
//        UserName.setText(username);
//        UserEmail.setText(useremail);

    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        UserProfile();
    }

    private void UserProfile(){
        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        if(user_token.contains("token")) {

            Call<AuthRespones> call = RetrofitClient.getInstance().getApi().auth(token);
            call.enqueue(new Callback<AuthRespones>() {
                @Override
                public void onResponse(Call<AuthRespones> call, Response<AuthRespones> response) {
                    AuthRespones authRespones = response.body();
                    try {
                        users = authRespones.getUser();
                        String userss =users.get(0).getName();
                        if(userss == null){
                            UserName.setText("User Name");
                            UserEmail.setText(users.get(0).getPhone_number());
                        }else {
                            UserName.setText(users.get(0).getName());
                            UserEmail.setText(users.get(0).getPhone_number());
                        }
                    }catch (Exception e){
                        Toast.makeText(UserProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<AuthRespones> call, Throwable t) {
                    Toast.makeText(UserProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}