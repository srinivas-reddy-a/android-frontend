package com.example.arraykart.MyOrder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<OrderItemModel> orderItemModelList;

    public OrderAdapter(List<OrderItemModel> orderItemModelList) {
        this.orderItemModelList = orderItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = orderItemModelList.get(position).getProductImage();
        int rating = orderItemModelList.get(position).getRating();
        String title = orderItemModelList.get(position).getProductTitle();
        String deliveryStatus = orderItemModelList.get(position).getDeliveryStatus();
        holder.setOrderDetail(resource,title,deliveryStatus,rating);
    }

    @Override
    public int getItemCount() {
        return orderItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private ImageView deliveryIndicator;
        private TextView productTitle;
        private TextView deliveryStatus;
        private ConstraintLayout rateContainer ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage =itemView.findViewById(R.id.ordered_image_orderPage);
            productTitle =itemView.findViewById(R.id.title_orderPage);
            deliveryIndicator =itemView.findViewById(R.id.order_indicator);
            deliveryStatus =itemView.findViewById(R.id.order_delivery_date);
            rateContainer = itemView.findViewById(R.id.rating_container);
        }
        public void setOrderDetail(int image,String title,String delivery,int rating){
            try {
                productImage.setImageResource(image);
                productTitle.setText(title);
                if (delivery.equals("Canceled")) {
                    deliveryIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                }
                deliveryStatus.setText(delivery);
                //rating start logic
                setRating(rating);
                for(int i = 0;i<rateContainer.getChildCount();i++){
                    final int starPosition = i;
                    rateContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setRating(starPosition);
                        }
                    });
                    //rating start logic

                }
            }catch(Exception e){

            }
        }
        //rating start logic
        private void setRating(int starPosition){
            try{
                for(int i = 0;i<rateContainer.getChildCount();i++){
                    ImageView starBar = (ImageView) rateContainer.getChildAt(i);
                    starBar.setImageTintList(ColorStateList.valueOf(Color.parseColor("##FFFF00")));
                    if(i<=starPosition){
                        starBar.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FFFF00")));
                    }
                }
            }catch (Exception e){

            }
        }

    }
}
