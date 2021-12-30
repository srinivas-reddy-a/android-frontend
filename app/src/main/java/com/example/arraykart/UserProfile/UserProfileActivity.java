package com.example.arraykart.UserProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arraykart.AddressActivity.MyAddressActivity;
import com.example.arraykart.AllApiModels.AuthRespones;
import com.example.arraykart.AllApiModels.UserId;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.HomeNavigationActivity;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.MyOrder.MyOrder;
import com.example.arraykart.NotificationPage.NotificationActivity;
import com.example.arraykart.R;
import com.example.arraykart.SignUP;
import com.example.arraykart.WishList.WishListActivity;
import com.example.arraykart.homeCategoryProduct.HAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;

import org.json.JSONObject;

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
    List<UserId> userIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        recyclerView = findViewById(R.id.UserProfileRecyclerView);
        UserName = findViewById(R.id.UserName);
        UserEmail = findViewById(R.id.UserEmail);
        UserProfileImage = findViewById(R.id.UserProfileImage);

        sharedPrefManager = new SharedPrefManager(this);

        String user = sharedPrefManager.getValue_string("token");

        Call<AuthRespones> call = RetrofitClient.getInstance().getApi().auth(user);
        call.enqueue(new Callback<AuthRespones>() {
            @Override
            public void onResponse(Call<AuthRespones> call, Response<AuthRespones> response) {
                if(response.isSuccessful()){
                    AuthRespones authRespones = response.body();
                    String name = authRespones.getUser().getName();
                    UserName.setText(name);

                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(UserProfileActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(UserProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<AuthRespones> call, Throwable t) {
                Toast.makeText(UserProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
                    startActivity(new Intent(UserProfileActivity.this, ProfileEditPageActivity.class));

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
                    startActivity(new Intent(UserProfileActivity.this,ProfileEditPageActivity.class));
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

                }
            });
        }catch (Exception e){

        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        logout_of_this_app = findViewById(R.id.logout_of_this_app);
        logout_of_this_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);
                if(user_token.contains("token")){
                    sharedPrefManager.clear();
                    startActivity(new Intent(UserProfileActivity.this,HomeNavigationActivity.class));
                    finish();
                }else {
                    signOut();
                    finish();
                }
            }
        });

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
}