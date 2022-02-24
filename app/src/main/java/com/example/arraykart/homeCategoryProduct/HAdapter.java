package com.example.arraykart.homeCategoryProduct;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class HAdapter extends RecyclerView.Adapter<HAdapter.ViewHolder>{

    private List<MainModel> mainModel;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    Context context;

    public HAdapter(Context c, List<MainModel> mainModel){
        this.context = c;
        this.mainModel = mainModel;
    }

    @NonNull
    @Override
    public HAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_category_homepage_item,parent,false);
        return new HAdapter.ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HAdapter.ViewHolder holder, int position) {
//        int p =mainModel.size()-2 ;

//        Drawable res = context.getResources().getDrawable(context.getResources()
//                .getIdentifier(mainModel.get(position).getImage(), null, context.getPackageName()));


        Glide.with(context.getApplicationContext())
                .load(mainModel.get(position).getImage())
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
        holder.tv.setText(mainModel.get(position).getName());
        holder.tv1.setText(mainModel.get(position).getPrice());

//        if(position<=p) {
//            Glide.with(this.context)
//                    .load(mainModel.get(position).getImage())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(holder.img);
//            holder.tv.setText(mainModel.get(position).getName());
//            holder.tv1.setText(mainModel.get(position).getPrice());
//        }else {
//            holder.img.setVisibility(View.GONE);
//            holder.tv.setText("...more");
//            holder.tv.setTextSize(15);
//            holder.tv.setTextColor(Color.parseColor("#7BB7E6"));
//            holder.tv.setPadding(20,140,20,140);
//            holder.tv1.setVisibility(View.GONE);
//        }

    }

    @Override
    public int getItemCount() {
        return mainModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv;
        TextView tv1;

        public ViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            img= itemView.findViewById(R.id.imageView);
            tv = itemView.findViewById(R.id.textView);
            tv1 = itemView.findViewById(R.id.priceText);
            try {
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
            }catch (Exception e){

            }
        }
    }
}
