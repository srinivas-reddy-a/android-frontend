package com.example.arraykart.homeCategoryProduct.moreProductCategory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.arraykart.AddressActivity.AddressFormActivity;
import com.example.arraykart.AllApiModels.GetAddressRespones;
import com.example.arraykart.AllApiModels.ProductsCategoryRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.HomeNavigationActivity;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.R;
import com.example.arraykart.SearchPage.SearchPageActivity;
import com.example.arraykart.SignUP;
import com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct.ItemsForSingleProduct;
import com.google.android.material.chip.Chip;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class moreCategoryProducts extends AppCompatActivity {

    private ListView listView ;
    private ImageView back_more;

    ////cart icon on moreProductPage
    private LottieAnimationView cart_more_products_page;
    ///cart icon on moreProductsPage

    private List<MoreCotegoryModel> moreCotegoryModels;

    private TextView searchMoreCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_category_products);

        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);


        listView = findViewById(R.id.listView);

        Call<ProductsCategoryRespones> call = RetrofitClient
                .getInstance()
                .getApi().productCategory();
        call.enqueue(new Callback<ProductsCategoryRespones>() {
            @Override
            public void onResponse(Call<ProductsCategoryRespones> call, Response<ProductsCategoryRespones> response) {

                if(response.isSuccessful()){
                    moreCotegoryModels =  response.body().getCategories();
                    MyAdapter adapter = new MyAdapter(getApplicationContext(),moreCotegoryModels);
                    listView.setAdapter(adapter);

//      this helps click on every item present in more_products_category_page and open new activity of all item_product
                    try {
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    if (position < moreCotegoryModels.size()) {
                                        Intent in = new Intent(moreCategoryProducts.this, ItemsForSingleProduct.class);
                                        in.putExtra("id",moreCotegoryModels.get(position).getId());
                                        in.putExtra("name",moreCotegoryModels.get(position).getName());
                                        in.putExtra("image",moreCotegoryModels.get(position).getImage());
                                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(in);
                                    }


                            }
                        });
                    }catch(Exception e){

                    }
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(moreCategoryProducts.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(moreCategoryProducts.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ProductsCategoryRespones> call, Throwable t) {
                Toast.makeText(moreCategoryProducts.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        ///back on more_page
        try{
            back_more = findViewById(R.id.back_more);
            back_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }
        ////back on more_page

        ///cart icon on more page click
        ///cart icon clicklistener
        try {
            cart_more_products_page = findViewById(R.id.cart_more_page);
            cart_more_products_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(user_token.contains("token")) {
                        Intent in = new Intent(moreCategoryProducts.this, MYCartActivity.class);
                        startActivity(in);
                    }else{
                        startActivity(new Intent(moreCategoryProducts.this, SignUP.class));
                    }
                }
            });
        }catch(Exception e){

        }
        ///cart icon clicklistener
        ///cart icon on more page click

        //searchview
        searchMoreCategory = findViewById(R.id.searchMoreCategory);
        searchMoreCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(moreCategoryProducts.this, SearchPageActivity.class));
            }
        });
    }
}