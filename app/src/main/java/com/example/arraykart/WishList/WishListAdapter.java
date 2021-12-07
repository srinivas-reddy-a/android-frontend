package com.example.arraykart.WishList;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.MyOrder.OrderDetail;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {
    private List<WishListModel> wishListModelList ;

    public WishListAdapter(List<WishListModel> wishListModelList) {
        this.wishListModelList = wishListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = wishListModelList.get(position).getProductImage();
        String title = wishListModelList.get(position).getProductTitle();
        int freeOffer = wishListModelList.get(position).getFreeOffer();
        String rating = wishListModelList.get(position).getRating();
        String price = wishListModelList.get(position).getProductPrice();
        String cuttedPrice=wishListModelList.get(position).getCuttedPrice();
        String paymentMethod= wishListModelList.get(position).getPaymentMethod();

        holder.setData(resource,title,freeOffer,rating,price,cuttedPrice,paymentMethod);

    }

    @Override
    public int getItemCount() {
        return wishListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private TextView productTitle;
        private ImageView couponIcon;
        private TextView freeCoupon;
        private TextView rating;
        private TextView price;
        private TextView cuttedPrice;
        private TextView paymentMethod;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.wishListImage);
            productTitle = itemView.findViewById(R.id.wishListTitle);
            couponIcon = itemView.findViewById(R.id.wishListCouponIndicator);
            freeCoupon = itemView.findViewById(R.id.wishListCouponText);
            rating = itemView.findViewById(R.id.wishListRating);
            price = itemView.findViewById(R.id.wishListPrice);
            cuttedPrice = itemView.findViewById(R.id.wishListCuttedPrice);
            paymentMethod = itemView.findViewById(R.id.wishListDeliveryProcess);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(), ProductDetailActivity.class);
                    itemView.getContext().startActivity(i);
                }
            });
        }
        private void setData(int resource,String title,int couponNo,String rate,String prices,String cuttedPricess,String method){
            try {
                productImage.setImageResource(resource);
                productTitle.setText(title);
                if (couponNo != 0) {
                    couponIcon.setVisibility(View.VISIBLE);
                    freeCoupon.setVisibility(View.VISIBLE);
                    if (couponNo == 1) {
                        freeCoupon.setText("free (" + couponNo + ") coupon");
                    } else {
                        freeCoupon.setText("free (" + couponNo +") coupons");
                    }
                } else {
                    couponIcon.setVisibility(View.INVISIBLE);
                    freeCoupon.setVisibility(View.INVISIBLE);
                }
                rating.setText(rate);
                price.setText(prices);
                cuttedPrice.setText(cuttedPricess);
                paymentMethod.setText(method);
            }catch (Exception e){

            }
        }
    }

}

