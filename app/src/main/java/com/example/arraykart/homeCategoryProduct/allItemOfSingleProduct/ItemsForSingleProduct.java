package com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.arraykart.AllApiModels.CategoryIdRespones;
import com.example.arraykart.AllApiModels.ProductsRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.Filter.BottomSheetFragment;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;
import com.example.arraykart.SearchPage.SearchPageActivity;
import com.example.arraykart.SignUP;
import com.example.arraykart.Sort.BottomSheetFragmentSort;
import com.example.arraykart.homeCategoryProduct.moreProductCategory.MyAdapter;
import com.example.arraykart.homeCategoryProduct.moreProductCategory.moreCategoryProducts;
import com.google.android.material.chip.Chip;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsForSingleProduct extends AppCompatActivity {

    private GridView gridView;
    private ImageView back_all_products;
    private TextView gridViewProductNAme;


    ///cart icon on item product page
    private LottieAnimationView cart_item_product_page;
    ///cart icon on item product page

    ///filter for page
    private Button filterBtn;
    private Button sortBtn;
    private Chip chip1;
    ///filter fo page

    private List<ModelForSingleProduct> modelForSingleProducts;
    private TextView searchAllProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);


        setContentView(R.layout.items_for_single_product);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String idb = getIntent().getStringExtra("idb");
        String nameB = getIntent().getStringExtra("nameB");
        gridViewProductNAme = findViewById(R.id.gridViewProductNAme);



        gridView = findViewById(R.id.gridView);
        ////for home category
        String url = "/api/product/category/filter/product/"+name;
        Call<CategoryIdRespones> call = RetrofitClient.getInstance().getApi().getCategory(url);
        call.enqueue(new Callback<CategoryIdRespones>() {
            @Override
            public void onResponse(Call<CategoryIdRespones> call, Response<CategoryIdRespones> response) {

                if(response.isSuccessful()){
                        modelForSingleProducts = response.body().getProducts();
                        GridViewAdapter gridAdapter = new GridViewAdapter(getApplicationContext(), modelForSingleProducts);
                        gridView.setAdapter(gridAdapter);
                        gridAdapter.notifyDataSetChanged();
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(ItemsForSingleProduct.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(ItemsForSingleProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<CategoryIdRespones> call, Throwable t) {

            }
        });

        ///home page brand

        String urll = "/api/product/brand/product/"+nameB;
        Call<CategoryIdRespones> callB = RetrofitClient.getInstance().getApi().getCategory(urll);
        callB.enqueue(new Callback<CategoryIdRespones>() {
            @Override
            public void onResponse(Call<CategoryIdRespones> call, Response<CategoryIdRespones> response) {

                if(response.isSuccessful()){
                        modelForSingleProducts = response.body().getProducts();
                        GridViewAdapter gridAdapter = new GridViewAdapter(getApplicationContext(), modelForSingleProducts);
                        gridView.setAdapter(gridAdapter);
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(ItemsForSingleProduct.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(ItemsForSingleProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<CategoryIdRespones> call, Throwable t) {

            }
        });

        if(name!=null) {
            gridViewProductNAme.setText(name);
        }else{
            gridViewProductNAme.setText(nameB);
        }




        try {
            back_all_products = findViewById(R.id.back_all_products);
            back_all_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {

        }

        try {
            cart_item_product_page = findViewById(R.id.cart_item_product_page);
            cart_item_product_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(user_token.contains("token")) {
                        Intent in = new Intent(ItemsForSingleProduct.this, MYCartActivity.class);
                        startActivity(in);
                    }else {
                        startActivity(new Intent(ItemsForSingleProduct.this, SignUP.class));
                    }
                }
            });
        } catch (Exception e) {

        }
        //bottom sheet for filter and sort

        filterBtn = findViewById(R.id.filterButton);
        sortBtn = findViewById(R.id.sortButton);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(), "TAG");
            }
        });
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragmentSort bottomSheetFragmentSort = new BottomSheetFragmentSort();
                bottomSheetFragmentSort.show(getSupportFragmentManager(), "TAG");
            }
        });
        //bottom sheet for filter and sort

        //searchview
        try {
            searchAllProducts = findViewById(R.id.searchAllProducts);
            searchAllProducts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ItemsForSingleProduct.this, SearchPageActivity.class));
                }
            });
        }catch (Exception e){

        }
    }

    public class GridAdapter extends BaseAdapter {
        Context context;
        List<ModelForSingleProduct> modelForSingleProducts;

        public GridAdapter(Context context, List<ModelForSingleProduct> modelForSingleProducts) {
            this.context = context;
            this.modelForSingleProducts = modelForSingleProducts;
        }

        public class ViewHolder{
           public ImageView cImg;
           public TextView txt;
           public TextView prc;
           public TextView rt;
           public TextView rb;
        }


        @Override
        public int getCount() {
            return modelForSingleProducts.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_frame_single_product, null, false);

            holder = new ViewHolder();

            // it will help take item from single product and put that item in this page
            try {
                holder.cImg = view.findViewById(R.id.gridImage);
                holder.txt = view.findViewById(R.id.gridText);
                holder.prc = view.findViewById(R.id.priceGrid);
                holder.rt = view.findViewById(R.id.rateGrid);
                holder.rb = view.findViewById(R.id.ribbonTag);
                CheckBox wish = view.findViewById(R.id.wishListSingleProducts);

                wish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, modelForSingleProducts.get(position).getId(), Toast.LENGTH_SHORT).show();
                    }
                });

                view.setTag(holder);

                holder = (ViewHolder) view.getTag();

                if (position <= modelForSingleProducts.size()) {
//                    cImg.setImageResource(modelForSingleProducts.get(position).getImgs());
                    Glide.with(context.getApplicationContext())
                            .load(modelForSingleProducts.get(position).getImgs())
                            .placeholder(R.drawable.categories)
                            .centerInside()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.cImg);
                    holder.txt.setText(modelForSingleProducts.get(position).getName());
                    holder.prc.setText(modelForSingleProducts.get(position).getPrice());
//                    holder.rt.setText(modelForSingleProducts.get(position).getRate());
//
//                    if (position == 3) {
//                        holder.rb.setText(modelForSingleProducts.get(position).getRibbon());
//                        holder.rb.setTextColor(Color.parseColor("#FFFFFF"));
//                    } else {
//                        holder.rb.setBackgroundResource(R.color.white);
//                        holder.rb.setText(null);
//                    }
                }

            } catch (Exception ex) {

            }

            try {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position <= modelForSingleProducts.size()) {
                            context.startActivity(new Intent(context, ProductDetailActivity.class));
                        }
                    }
                });
            } catch (Exception e) {

            }

            return view;
        }



    }

}