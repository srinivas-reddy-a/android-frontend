package com.example.arraykart.homeCategoryProduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;

import java.util.ArrayList;

public class HAdapter extends RecyclerView.Adapter<HAdapter.ViewHolder>{

    private ArrayList<MainModel> mainModel;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    Context context;

    public HAdapter(Context c, ArrayList<MainModel> mainModel){
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
        holder.img.setImageResource(mainModel.get(position).getImgs());
        holder.tv.setText(mainModel.get(position).getName());
        holder.tv1.setText(mainModel.get(position).getPrice());

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
