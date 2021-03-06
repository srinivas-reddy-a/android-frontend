package com.akfrontend.arraykart.SearchPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.akfrontend.arraykart.ProductDetailActivity;
import com.akfrontend.arraykart.R;

import java.util.List;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ViewHolder> {
    private List<SearchProductModel> searchProductModels;
    Context context;

    public SearchProductAdapter(List<SearchProductModel> searchProductModels, Context context) {
        this.searchProductModels = searchProductModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_product_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nm.setText(searchProductModels.get(position).getName());
        String p = searchProductModels.get(position).getPrice();
        String[] price;
        price = p.split(",");
        if(price[0].toLowerCase().contains("na") || price[0].isEmpty() || price[0] == null){
            holder.pr.setText("out of stock");
        }else {
            holder.pr.setText("₹ " + price[0] + "/---");
        }
//        holder.pr.setText("Price coming soon");
        Glide.with(context.getApplicationContext())
                .load(searchProductModels.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return searchProductModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nm;
        TextView pr;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nm = itemView.findViewById(R.id.afterSearchProductName);
            pr = itemView.findViewById(R.id.afterSearchProductPrice);
            img = itemView.findViewById(R.id.afterSearchProductImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, ProductDetailActivity.class);
                    in.putExtra("id",searchProductModels.get(getAdapterPosition()).getId());
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in);
                }
            });
        }
    }
}
