package com.akfrontend.arraykart.homeCategoryProduct;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.akfrontend.arraykart.ProductDetailActivity;
import com.akfrontend.arraykart.R;

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
                .placeholder(R.drawable.placeholder)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
        holder.tv.setText(mainModel.get(position).getName());
        String p = mainModel.get(position).getPrice();
        String[] price;
        price = p.split(",");
        if(price[0].toLowerCase().contains("na") || price[0].isEmpty() || price[0] == null){
            holder.tv1.setText("out of stock");
        }else {
            holder.tv1.setText("??? " + price[0] + "/--");
        }
//        holder.tv1.setText("Price coming soon");

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

                        Intent in = new Intent(context, ProductDetailActivity.class);
                        in.putExtra("id",mainModel.get(getAdapterPosition()).getId());
                        in.putExtra("qlt","1");
                        in.putExtra("image",mainModel.get(getAdapterPosition()).getImage());
                        in.putExtra("price",mainModel.get(getAdapterPosition()).getPrice());
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(in);

                    }
                });
            }catch (Exception e){

            }
        }
    }
}
