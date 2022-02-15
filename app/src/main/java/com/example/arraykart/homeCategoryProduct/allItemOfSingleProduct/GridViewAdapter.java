package com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct;

import static com.facebook.FacebookSdk.getApplicationContext;

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


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<ModelForSingleProduct> modelForSingleProducts;

    public GridViewAdapter(Context context, List<ModelForSingleProduct> modelForSingleProducts) {
        this.context = context;
        this.modelForSingleProducts = modelForSingleProducts;
    }

    public class ViewHolders{
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
        return modelForSingleProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent) {
        ViewHolders holder;
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_frame_single_product, parent, false);

        holder = new ViewHolders();

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

            holder = (ViewHolders) view.getTag();

//            cImg.setImageResource(modelForSingleProducts.get(position).getImgs());
            Glide.with(context.getApplicationContext())
                    .load(modelForSingleProducts.get(position).getImgs())
                    .placeholder(R.drawable.categories)
                    .centerInside()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.cImg);
            holder.txt.setText(modelForSingleProducts.get(position).getName());
            holder.prc.setText(modelForSingleProducts.get(position).getPrice());
            holder.rb.setVisibility(View.GONE);
            holder.rt.setVisibility(View.GONE);

//            if (position <= modelForSingleProducts.size()) {
////
////                    holder.rt.setText(modelForSingleProducts.get(position).getRate());
////
////                    if (position == 3) {
////                        holder.rb.setText(modelForSingleProducts.get(position).getRibbon());
////                        holder.rb.setTextColor(Color.parseColor("#FFFFFF"));
////                    } else {
////                        holder.rb.setBackgroundResource(R.color.white);
////                        holder.rb.setText(null);
////                    }
//            }

        } catch (Exception ex) {

        }

        try {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, ProductDetailActivity.class);
                    in.putExtra("id",modelForSingleProducts.get(position).getId());
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in);
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return view;
    }

}
