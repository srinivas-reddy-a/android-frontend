package com.example.arraykart.homeCategoryProduct.moreProductCategory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arraykart.R;
import com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct.ItemsForSingleProduct;

public class moreCategoryProducts extends AppCompatActivity {

    ListView listView ;
    ImageView back_more;
    String fTitle[] = {"Herbicides","Insecticides","Fungicides","Water fertilizer",
            "Seeds","Herbicides","Insecticides","Fungicides","Water fertilizer","Seeds"};
    String fDescription[]={"Herbicides Description","Insecticides Description","Fungicides Description",
            "Water fertilizer Description","Seed Description","Herbicides Description","Insecticides Description",
            "Fungicides Description","Water fertilizer Description","Seed Description"};
    String fRate[] ={"4.2 r","4.3 r","4.4 r","4.5 r","4.6 r","4.2 r","4.3 r","4.4 r","4.5 r","4.6 r"};
    String fPrice[] ={"price","price","price","price","price","price","price","price","price","price"};
    int images[] ={R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,
            R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};

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