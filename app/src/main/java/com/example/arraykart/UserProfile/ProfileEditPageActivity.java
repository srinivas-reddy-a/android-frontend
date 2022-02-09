package com.example.arraykart.UserProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arraykart.AllApiModels.AuthRespones;
import com.example.arraykart.AllApiModels.User;
import com.example.arraykart.AllApiModels.UserUpdateResponse;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.HomeNavigationActivity;
import com.example.arraykart.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEditPageActivity extends AppCompatActivity {

    private ImageView back_edit_profile_page;
    private EditText UpdateUserName,UpdateUserLastName,UpdateUserContent,UpdateUserEmail;
    private Button UpdateUserButton;

    SharedPrefManager sharedPrefManager;
    private List<User> userList;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_page);
        UpdateUserName=findViewById(R.id.UpdateUserName);
        UpdateUserLastName=findViewById(R.id.UpdateUserLastName);
        UpdateUserContent=findViewById(R.id.UpdateUserContent);
        UpdateUserEmail=findViewById(R.id.UpdateUserEmail);
        UpdateUserButton=findViewById(R.id.UpdateUserButton);

        sharedPrefManager = new SharedPrefManager(this);

        user = sharedPrefManager.getValue_string("token");

        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);

        if(user_token.contains("token")) {
            Call<AuthRespones> call = RetrofitClient.getInstance().getApi().auth(user);
            call.enqueue(new Callback<AuthRespones>() {
                @Override
                public void onResponse(Call<AuthRespones> call, Response<AuthRespones> response) {
                    AuthRespones authRespones = response.body();
                    userList = authRespones.getUser();
                    UpdateUserName.setText(userList.get(0).getName());
                    UpdateUserContent.setText(userList.get(0).getPhone_number());
                    UpdateUserEmail.setText(userList.get(0).getEmail());

                }

                @Override
                public void onFailure(Call<AuthRespones> call, Throwable t) {
                    Toast.makeText(ProfileEditPageActivity.this, user, Toast.LENGTH_SHORT).show();

                }
            });
        }


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


        try{
            UpdateUserButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UpdateUser();
                }
            });

        }catch (Exception e){

        }


    }
    private void  UpdateUser(){
        String UserName = UpdateUserName.getText().toString();
        String UserLastName = UpdateUserLastName.getText().toString();
        String UserContent = UpdateUserContent.getText().toString();
        String UserEmail = UpdateUserEmail.getText().toString();

        Call<UserUpdateResponse> call = RetrofitClient .getInstance().getApi().updateUser(user,UserContent,UserEmail,UserName);
        call.enqueue(new Callback<UserUpdateResponse>() {
            @Override
            public void onResponse(Call<UserUpdateResponse> call, Response<UserUpdateResponse> response) {
//                UserUpdateResponse responseBody = response.body();
                finish();
            }

            @Override
            public void onFailure(Call<UserUpdateResponse> call, Throwable t) {
                Toast.makeText(ProfileEditPageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}