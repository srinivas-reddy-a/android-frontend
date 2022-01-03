package com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;

public class GridViewAdapter extends BaseAdapter {
    Context context;
    String[] name;
    String[] price;
    String[] rate;
    String[] ribbon;
    int[] imgs;

    public GridViewAdapter(Context context, String[] name,String[] price,String[] rate, String[] ribbon, int[] imgs) {
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
        View view = layoutInflater.inflate(R.layout.item_frame_single_product, null, false);

        // it will help take item from single product and put that item in this page
        try {
            ImageView cImg = view.findViewById(R.id.gridImage);
            TextView txt = view.findViewById(R.id.gridText);
            TextView prc = view.findViewById(R.id.priceGrid);
            TextView rt = view.findViewById(R.id.rateGrid);
            TextView rb = view.findViewById(R.id.ribbonTag);
            if(position<=name.length) {
                cImg.setImageResource(imgs[position]);
                txt.setText(name[position]);
                prc.setText(price[position]);
                rt.setText(rate[position]);

                if (position == 3) {
                    rb.setText(ribbon[position]);
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
                    if(position<= imgs.length) {
                        context.startActivity(new Intent(context, ProductDetailActivity.class));
                    }
                }
            });
        } catch (Exception e) {

        }

        return view;
    }


}
