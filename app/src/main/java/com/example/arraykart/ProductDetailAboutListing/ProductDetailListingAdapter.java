package com.example.arraykart.ProductDetailAboutListing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;

import java.util.List;

public class ProductDetailListingAdapter extends RecyclerView.Adapter<ProductDetailListingAdapter.Holder> {

    private List<ProductDetailListingModel> productDetailListingModels;
    Context context;

    public ProductDetailListingAdapter(List<ProductDetailListingModel> productDetailListingModels, Context context) {
        this.productDetailListingModels = productDetailListingModels;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.product_detail_listing_layout,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String text = productDetailListingModels.get(position).getListName();
        int img = productDetailListingModels.get(position).getListImage();
        String txtD = productDetailListingModels.get(position).getListDetail();

        holder.setData(img,text,txtD);

    }

    @Override
    public int getItemCount() {
        return productDetailListingModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView li;
        TextView lt;
        TextView ld;
        LinearLayout descriptionLL;
        public Holder(@NonNull View itemView) {
            super(itemView);
            li = itemView.findViewById(R.id.listImage);
            lt = itemView.findViewById(R.id.listText);
            ld = itemView.findViewById(R.id.listDetail);
            descriptionLL = itemView.findViewById(R.id.descriptionLL);
            descriptionLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ((ld.getVisibility() == View.GONE)) {
                        ld.setVisibility(View.VISIBLE);
                        li.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    } else {
                        ld.setVisibility(View.GONE);
                        li.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    }
                }
            });
        }
        private void setData(int lim, String ltx , String ldl){
            li.setImageResource(lim);
            lt.setText(ltx);
            ld.setText(ldl);
        }
    }
}
