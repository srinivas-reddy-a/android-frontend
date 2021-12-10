package com.example.arraykart.UserProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arraykart.AddressActivity.MyAddressActivity;
import com.example.arraykart.HomeNavigationActivity;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.MyOrder.MyOrder;
import com.example.arraykart.NotificationPage.NotificationActivity;
import com.example.arraykart.R;
import com.example.arraykart.WishList.WishListActivity;
import com.example.arraykart.homeCategoryProduct.HAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserProfileAdapter adapter;
    private UserProfileModel model;
    
    private ImageView editProfile;

    private ImageView back_user_profile_page;

    private TextView account_settings;

    private TextView notification_preferences;

    private TextView logout_of_this_app;

    private TextView logout_of_this_devices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        recyclerView = findViewById(R.id.UserProfileRecyclerView);

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
        try{
            logout_of_this_devices = findViewById(R.id.logout_of_this_devices);
            logout_of_this_devices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }catch (Exception e){

        }
    }
}