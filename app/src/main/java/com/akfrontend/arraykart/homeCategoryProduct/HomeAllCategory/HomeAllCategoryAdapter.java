package com.akfrontend.arraykart.homeCategoryProduct.HomeAllCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.akfrontend.arraykart.R;
import com.akfrontend.arraykart.homeCategoryProduct.moreProductCategory.MoreCotegoryModel;

import java.util.List;

public class HomeAllCategoryAdapter extends RecyclerView.Adapter<HomeAllCategoryAdapter.ViewHolder> {

    private List<MoreCotegoryModel> homeAllCategoryModels;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    Context context;

    public HomeAllCategoryAdapter(List<MoreCotegoryModel> homeAllCategoryModels, Context context) {
        this.homeAllCategoryModels = homeAllCategoryModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_brand,parent,false);
        return new ViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context.getApplicationContext())
                .load(homeAllCategoryModels.get(position).getImage())
                .placeholder(R.drawable.placeholder)
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
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView2);
            txt = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onClickListener(position);
                        }
                    }
                }
            });
        }
    }
}
