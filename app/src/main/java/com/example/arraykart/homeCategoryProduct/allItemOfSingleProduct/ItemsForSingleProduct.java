package com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.arraykart.Filter.BottomSheetFragment;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;
import com.example.arraykart.Sort.BottomSheetFragmentSort;
import com.example.arraykart.homeCategoryProduct.moreProductCategory.moreCategoryProducts;
import com.google.android.material.chip.Chip;

public class ItemsForSingleProduct extends AppCompatActivity {

    private GridView gridView;
    private ImageView back_all_products;
    private String[] name = {"Herbicides", "Insecticides",
            "Insecticides", "name", "name","name", "name", "name", "name", "name"};

    private String[] price = {"2400", "2400", "2400", "2400", "2400","2400", "2400", "2400", "2400", "2400"};

    private String[] rate = {"4.3 *|348k", "4.3 *|348k", "4.3 *|348k", "4.3 *|348k", "4.3 *|348k",
            "4.3 *|348k", "4.3 *|348k", "4.3 *|348k", "4.3 *|348k", "4.3 *|348k"};

    private String[] ribbon ={"new","new","new","new","new","new","new","new","new","new"};

    private int[] imgs = {R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img,R.drawable.img,
            R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img};

    ///cart icon on item product page
    private LottieAnimationView cart_item_product_page;
    ///cart icon on item product page

    ///filter for page
    private Button filterBtn;
    private Button sortBtn;
    private Chip chip1;
    ///filter fo page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_for_single_product);

        gridView = findViewById(R.id.gridView);

        GridAdapter gridAdapter = new GridAdapter(this, name, price, rate, ribbon, imgs);
        gridView.setAdapter(gridAdapter);

        try{
            back_all_products=findViewById(R.id.back_all_products);
            back_all_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

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
        }catch(Exception e){

        }
        //bottom sheet for filter and sort

        filterBtn = findViewById(R.id.filterButton);
        sortBtn = findViewById(R.id.sortButton);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(),"TAG");
            }
        });
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragmentSort bottomSheetFragmentSort = new BottomSheetFragmentSort();
                bottomSheetFragmentSort.show(getSupportFragmentManager(),"TAG");
            }
        });
        //bottom sheet for filter and sort
    }
    public class GridAdapter extends BaseAdapter {
        Context context;
        String[] name;
        String[] price;
        String[] rate;
        String[] ribbon;
        int[] imgs;

        public GridAdapter(Context context, String[] name,String[] price,String[] rate, String[] ribbon, int[] imgs) {
            this.context = context;
            this.name = name;
            this.price = price;
            this.rate = rate;
            this.ribbon = ribbon;
            this.imgs = imgs;
        }


        @Override
        public int getCount() {
            return name.length;
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
            View view = layoutInflater.inflate(R.layout.item_frame_single_product, parent, false);

            // it will help take item from single product and put that item in this page
            try {
                ImageView cImg = view.findViewById(R.id.gridImage);
                TextView txt = view.findViewById(R.id.gridText);
                TextView prc = view.findViewById(R.id.priceGrid);
                TextView rt = view.findViewById(R.id.rateGrid);
                TextView rb = view.findViewById(R.id.ribbonTag);

                cImg.setImageResource(imgs[position]);
                txt.setText(name[position]);
                prc.setText(price[position]);
                rt.setText(rate[position]);

                if (position >= 0 && position <= 2) {
                    rb.setText(ribbon[position]);
                } else {
                    rb.setBackgroundResource(R.color.white);
                    rb.setText(null);
                }

            } catch (Exception ex) {

            }
            try {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < imgs.length; i++) {
                            if (position == i) {
                                startActivity(new Intent(ItemsForSingleProduct.this, ProductDetailActivity.class));
                            }
                        }
                    }
                });
            } catch (Exception e) {

            }

            return view;
        }


    }

}