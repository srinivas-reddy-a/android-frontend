package com.example.arraykart.AddressActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends AppCompatActivity {

    private RecyclerView addressRecyclerView;
    private LinearLayout addNewAddress;
    private ImageView back_Address_page;
    private List<AddressModel> addressModels;
    public static  final  int SELECTED_ADDRESS = 0;

    private static AddressAdapter addressAdapter;

    private Button SaveAddressDelete ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        try {
            addressRecyclerView = findViewById(R.id.AddressPageRecyclerView);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            addressRecyclerView.setLayoutManager(layoutManager);

            addressModels= new ArrayList<>();
            addressModels.add(new AddressModel("ram Lal", "gupt nagar street 420," +
                    "choro wali gali ,near bhoot ghati drna mnaa park ", "9211420",true));
            addressModels.add(new AddressModel("ram Lal", "gupt nagar street 420," +
                    "choro wali gali ,near bhoot ghati drna mnaa park ", "9211420",false));
            addressModels.add(new AddressModel("ram Lal", "gupt nagar street 420," +
                    "choro wali gali ,near bhoot ghati drna mnaa park ", "9211420",false));
            addressModels.add(new AddressModel("ram Lal", "gupt nagar street 420," +
                    "choro wali gali ,near bhoot ghati drna mnaa park ", "9211420",false));

            addressAdapter = new AddressAdapter(addressModels,0);
            addressRecyclerView.setAdapter(addressAdapter);
            ((SimpleItemAnimator)addressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
            addressAdapter.notifyDataSetChanged();

            addNewAddress = findViewById(R.id.AddNewAddress);
            addNewAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MyAddressActivity.this, AddressFormActivity.class));
                }
            });
        }catch (Exception e){

        }
        try{
            back_Address_page = findViewById(R.id.back_Address_page);
            back_Address_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }
//        try {
//            SaveAddressDelete= findViewById(R.id.SaveAddressDelete);
//            SaveAddressDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    addressModels.remove(addressAdapter.allReadySelected);
//                    addressAdapter.notifyItemRemoved(addressAdapter.allReadySelected);
//                }
//
//            });
//        }catch (Exception e){
//            Toast.makeText(this,"2 add shld be",Toast.LENGTH_LONG);
//
//        }
    }
    public static void refreshAddress (int deselected,int selected){
        addressAdapter.notifyItemChanged(deselected);
        addressAdapter.notifyItemChanged(selected);
    }

}