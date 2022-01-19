package com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<ModelForSingleProduct> modelForSingleProducts;

    public GridViewAdapter(Context context, List<ModelForSingleProduct> modelForSingleProducts) {
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

            if(position<=modelForSingleProducts.size()) {
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
                    if(position<= modelForSingleProducts.size()) {
                        context.startActivity(new Intent(context, ProductDetailActivity.class));
                    }
                }
            });
        } catch (Exception e) {

        }

        return view;
    }


}
