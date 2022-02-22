package com.example.arraykart.AddressActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.arraykart.AllApiModels.GetAddressRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.R;
import com.example.arraykart.SignUP;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAddressActivity extends AppCompatActivity {

    private RecyclerView addressRecyclerView;
    private LinearLayout addNewAddress;
    private ImageView back_Address_page;
    private List<AddressModel> addressModels;
    public static  final  int SELECTED_ADDRESS = 0;


    private Button AddressContinue ;
    SharedPrefManager sharedPrefManager;

    private static AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        AddressContinue = findViewById(R.id.AddressContinue);

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        addressRecyclerView = findViewById(R.id.AddressPageRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addressRecyclerView.setLayoutManager(layoutManager);



            addressModels= new ArrayList<>();
//            addressModels.add(new AddressModel("1","1","sachin","cghjvki","hgvfyfvj","hcytvj","11987","fufjvj","476586",true));
//            addressModels.add(new AddressModel("2","1","rahul","cghjvki","hgvfyfvj","hcytvj","11987","fufjvj","476586",false));
//            addressModels.add(new AddressModel("3","1","rohan","cghjvki","hgvfyfvj","hcytvj","11987","fufjvj","476586",false));
//            addressModels.add(new AddressModel("4","1","panda","cghjvki","hgvfyfvj","hcytvj","11987","fufjvj","476586",false));

//            addressAdapter = new AddressAdapter(addressModels,0,getApplicationContext());
//            addressRecyclerView.setAdapter(addressAdapter);
//            ((SimpleItemAnimator)addressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
//            addressAdapter.notifyDataSetChanged();

        Call<GetAddressRespones> call = RetrofitClient
                .getInstance()
                .getApi().getAddress(token);
        call.enqueue(new Callback<GetAddressRespones>() {
            @Override
            public void onResponse(Call<GetAddressRespones> call, Response<GetAddressRespones> response) {
                if(response.isSuccessful()) {
                    addressModels = response.body().getAddress();
                    addressAdapter = new AddressAdapter(addressModels, 0,getApplicationContext());
                    addressRecyclerView.setAdapter(addressAdapter);
//                        ((SimpleItemAnimator) addressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                    addressAdapter.notifyDataSetChanged();

                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(MyAddressActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        Toast.makeText(MyAddressActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAddressRespones> call, Throwable t) {
                Toast.makeText(MyAddressActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        addNewAddress = findViewById(R.id.AddNewAddress);
        addNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAddressActivity.this, AddressFormActivity.class));
            }
        });

        back_Address_page = findViewById(R.id.back_Address_page);
        back_Address_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            AddressContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    continues();
                }
            });
        }catch (Exception e){

        }


    }
    public static void refreshAddress (int deselected,int selected){
        addressAdapter.notifyItemChanged(deselected);
        addressAdapter.notifyItemChanged(selected);
    }

    private void continues(){


    }

    @Override
    protected void onRestart() {
        super.onRestart();


        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        addressRecyclerView = findViewById(R.id.AddressPageRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addressRecyclerView.setLayoutManager(layoutManager);



        addressModels= new ArrayList<>();
//            addressModels.add(new AddressModel("1","1","sachin","cghjvki","hgvfyfvj","hcytvj","11987","fufjvj","476586",true));
//            addressModels.add(new AddressModel("2","1","rahul","cghjvki","hgvfyfvj","hcytvj","11987","fufjvj","476586",false));
//            addressModels.add(new AddressModel("3","1","rohan","cghjvki","hgvfyfvj","hcytvj","11987","fufjvj","476586",false));
//            addressModels.add(new AddressModel("4","1","panda","cghjvki","hgvfyfvj","hcytvj","11987","fufjvj","476586",false));

//            addressAdapter = new AddressAdapter(addressModels,0,getApplicationContext());
//            addressRecyclerView.setAdapter(addressAdapter);
//            ((SimpleItemAnimator)addressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
//            addressAdapter.notifyDataSetChanged();

        Call<GetAddressRespones> call = RetrofitClient
                .getInstance()
                .getApi().getAddress(token);
        call.enqueue(new Callback<GetAddressRespones>() {
            @Override
            public void onResponse(Call<GetAddressRespones> call, Response<GetAddressRespones> response) {
                if(response.isSuccessful()) {
                    addressModels = response.body().getAddress();
                    addressAdapter = new AddressAdapter(addressModels, 0,getApplicationContext());
                    addressRecyclerView.setAdapter(addressAdapter);
//                        ((SimpleItemAnimator) addressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                    addressAdapter.notifyDataSetChanged();

                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(MyAddressActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        Toast.makeText(MyAddressActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAddressRespones> call, Throwable t) {
                Toast.makeText(MyAddressActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}