package com.example.arraykart.homeCategoryProduct.HomeAllCategory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.arraykart.R;
import com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct.ItemsForSingleProduct;
import com.example.arraykart.homeCategoryProduct.moreProductCategory.MoreCotegoryModel;

import java.util.List;

public class HomeAllCategoryAdapter extends RecyclerView.Adapter<HomeAllCategoryAdapter.ViewHolder> {

    List<MoreCotegoryModel> homeAllCategoryModels;
    Context context;

    public HomeAllCategoryAdapter(List<MoreCotegoryModel> homeAllCategoryModels, Context context) {
        this.homeAllCategoryModels = homeAllCategoryModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_brand,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context.getApplicationContext())
                .load(homeAllCategoryModels.get(position).getImage())
                .placeholder(R.drawable.categories)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
        holder.txt.setText(homeAllCategoryModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return homeAllCategoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img ;
        TextView txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView2);
            txt = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent in = new Intent(context, ItemsForSingleProduct.class);
                   in.putExtra("id",homeAllCategoryModels.get(getAdapterPosition()).getId());
                   context.startActivity(in);
                   String id = homeAllCategoryModels.get(getAdapterPosition()).getId();
                    Toast.makeText(context,id,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
