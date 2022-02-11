package com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.arraykart.Filter.BottomSheetFragment;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;
import com.example.arraykart.SearchPage.SearchPageActivity;
import com.example.arraykart.Sort.BottomSheetFragmentSort;
import com.example.arraykart.homeCategoryProduct.moreProductCategory.moreCategoryProducts;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class ItemsForSingleProduct extends AppCompatActivity {

    private GridView gridView;
    private ImageView back_all_products;


    ///cart icon on item product page
    private LottieAnimationView cart_item_product_page;
    ///cart icon on item product page

    ///filter for page
    private Button filterBtn;
    private Button sortBtn;
    private Chip chip1;
    ///filter fo page

    List<ModelForSingleProduct> modelForSingleProducts;
    private TextView searchAllProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_for_single_product);


        modelForSingleProducts = new ArrayList<>();
        modelForSingleProducts.add(new ModelForSingleProduct("1", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("2", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("3", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("4", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("1", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("2", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("3", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("4", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("1", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("2", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("3", "name", "price", "rate", "ribbon", R.drawable.img));
        modelForSingleProducts.add(new ModelForSingleProduct("4", "name", "price", "rate", "ribbon", R.drawable.img));

        gridView = findViewById(R.id.gridView);

        GridAdapter gridAdapter = new GridAdapter(this, modelForSingleProducts);
        gridView.setAdapter(gridAdapter);

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
                    Intent in = new Intent(ItemsForSingleProduct.this, MYCartActivity.class);
                    startActivity(in);
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
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_frame_single_product, null, false);

            // it will help take item from single product and put that item in this page
            try {
                ImageView cImg = view.findViewById(R.id.gridImage);
                TextView txt = view.findViewById(R.id.gridText);
                TextView prc = view.findViewById(R.id.priceGrid);
                TextView rt = view.findViewById(R.id.rateGrid);
                TextView rb = view.findViewById(R.id.ribbonTag);
                CheckBox wish = view.findViewById(R.id.wishListSingleProducts);

                wish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, modelForSingleProducts.get(position).getId(), Toast.LENGTH_SHORT).show();
                    }
                });

                if (position <= modelForSingleProducts.size()) {
                    cImg.setImageResource(modelForSingleProducts.get(position).getImgs());
                    txt.setText(modelForSingleProducts.get(position).getName());
                    prc.setText(modelForSingleProducts.get(position).getPrice());
                    rt.setText(modelForSingleProducts.get(position).getRate());

                    if (position == 3) {
                        rb.setText(modelForSingleProducts.get(position).getRibbon());
                        rb.setTextColor(Color.parseColor("#FFFFFF"));
                    } else {
                        rb.setBackgroundResource(R.color.white);
                        rb.setText(null);
                    }
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