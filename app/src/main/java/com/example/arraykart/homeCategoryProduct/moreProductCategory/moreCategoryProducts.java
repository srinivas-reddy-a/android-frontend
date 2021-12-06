package com.example.arraykart.homeCategoryProduct.moreProductCategory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
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
import com.example.arraykart.HomeNavigationActivity;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.R;
import com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct.ItemsForSingleProduct;
import com.google.android.material.chip.Chip;

public class moreCategoryProducts extends AppCompatActivity {

    private ListView listView ;
    private ImageView back_more;
    private String fTitle[] = {"Herbicides","Insecticides","Fungicides","Water Soluble Fertilizer",
            "Seeds","Herbicides","Insecticides","Fungicides","Water Soluble Fertilizer","Seeds"};
    private  String fDescription[]={"Herbicides Description","Insecticides Description","Fungicides Description",
            "Water Soluble Fertilizer Description","Seed Description","Herbicides Description","Insecticides Description",
            "Fungicides Description","Water fertilizer Description","Seed Description"};
    private String fRate[] ={"4.2 r","4.3 r","4.4 r","4.5 r","4.6 r","4.2 r","4.3 r","4.4 r","4.5 r","4.6 r"};
    private String fPrice[] ={"","","","","","","price","price","price","price"};
    private int images[] ={R.drawable.categories,R.drawable.categories,R.drawable.categories,R.drawable.watersoluble,R.drawable.categories,R.drawable.categories,R.drawable.categories,R.drawable.categories};

    ////cart icon on moreProductPage
    private LottieAnimationView cart_more_products_page;
    ///cart icon on moreProductsPage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_category_products);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this,fTitle,fDescription,fRate,fPrice,images);
        listView.setAdapter(adapter);

//      this helps click on every item present in more_products_category_page and open new activity of all item_product
        try {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (int i = 0; i < images.length; i++) {
                        if (position == i) {
                            startActivity(new Intent(moreCategoryProducts.this, ItemsForSingleProduct.class));
                        }
                    }

                }
            });
        }catch(Exception e){

        }
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
                    Intent in = new Intent(moreCategoryProducts.this, MYCartActivity.class);
                    startActivity(in);
                }
            });
        }catch(Exception e){

        }
        ///cart icon clicklistener
        ///cart icon on more page click
    }
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String cTitle[];
        String cDescription[];
        String cRate[];
        String cPrice[];
        int cImgs[];
        MyAdapter(Context c,String title[],String description[],String rate[],String price[],int imgs[]){
            super(c,R.layout.products_category_morepage_item,R.id.textView3,title);
            this.context=c;
            this.cTitle=title;
            this.cDescription=description;
            this.cRate=rate;
            this.cPrice=price;
            this.cImgs=imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater =(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View More_item = layoutInflater.inflate(R.layout.products_category_morepage_item,parent,false);

    //     it will help to take the item and put that item in more_product_category_page
            try {
                ImageView images = More_item.findViewById(R.id.imageView);
                TextView myTitle = More_item.findViewById(R.id.textView3);
                TextView myDescription = More_item.findViewById(R.id.textView4);
                TextView myRate = More_item.findViewById(R.id.textView5);
                TextView myPrice = More_item.findViewById(R.id.textView6A);

                images.setImageResource(cImgs[position]);
                myTitle.setText(cTitle[position]);
                myDescription.setText(cDescription[position]);
                myRate.setText(cRate[position]);
                myPrice.setText(cPrice[position]);
            }catch(Exception e){

            }


            return More_item;
        }
    }
}