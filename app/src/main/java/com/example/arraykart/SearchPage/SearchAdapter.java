package com.example.arraykart.SearchPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>  {
    private List<String> recentSearches;
    private Context context;
    private OnRecentSearchListener onRecentSearchListener;

    public SearchAdapter(Context context, ArrayList<String> recentSearches) {
        this.recentSearches = recentSearches;
        this.context = context;
        try {
            this.onRecentSearchListener = ((OnRecentSearchListener) context);
        }catch (Exception e){

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_page_body, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(recentSearches.get(holder.getAdapterPosition()));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("recentSearch", recentSearches.get(holder.getAdapterPosition()));
                onRecentSearchListener.onRecentSearchListener(intent);
            }
        });
        holder.rrecentSearchesArrowIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("recentSearch", recentSearches.get(holder.getAdapterPosition()));
                onRecentSearchListener.onRecentSearchListener(intent);
            }
        });
        holder.recentSearchesDeleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recentSearches.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), recentSearches.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recentSearches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView rrecentSearchesArrowIV;
        ImageView recentSearchesDeleteIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recentSearchTV);
            rrecentSearchesArrowIV = itemView.findViewById(R.id.recentSearchesArrowIV);
            recentSearchesDeleteIV = itemView.findViewById(R.id.recentSearchesDeleteIV);
        }
    }

    public interface OnRecentSearchListener{
        public void onRecentSearchListener(Intent intent);
    }

    public void filterList(ArrayList<String> filteredList){
        recentSearches =filteredList;
        notifyDataSetChanged();
    }

}
