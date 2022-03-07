package com.example.arraykart.WishList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.arraykart.AllApiModels.deleteWishListRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.MyOrder.OrderDetail;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {
    private List<WishListModel> wishListModelList ;

//    private onItemClickListener wListener;

    SharedPrefManager sharedPrefManager;


    Context context;

//    public interface onItemClickListener{
//        void onDeleteClick(int position);
//    }
//
//    public void setOnItemClickListener(onItemClickListener listener){
//        wListener = listener;
//    }

    public WishListAdapter(List<WishListModel> wishListModelList,Context context) {
        this.wishListModelList = wishListModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);

//        return new ViewHolder(view,wListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String resource = wishListModelList.get(position).getImage();
        String title = wishListModelList.get(position).getName();
//        int freeOffer = wishListModelList.get(position).getFreeOffer();
//        String rating = wishListModelList.get(position).getRating();
        String price = wishListModelList.get(position).getPrice();
//        String cuttedPrice=wishListModelList.get(position).getCuttedPrice();
//        String paymentMethod= wishListModelList.get(position).getPaymentMethod();

        holder.setData(resource,title,price);

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
        private ImageButton wishListDelete;

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
            wishListDelete = itemView.findViewById(R.id.wishListDelete);
            try {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(itemView.getContext(), ProductDetailActivity.class);
                        i.putExtra("id",wishListModelList.get(getAdapterPosition()).getId());
                        itemView.getContext().startActivity(i);
                    }
                });
            }catch (Exception e){

            }
            sharedPrefManager = new SharedPrefManager(context);


            wishListDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     String token = sharedPrefManager.getValue_string("token");

//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onDeleteClick(position);
//                        }
//                    }
                    String id = wishListModelList.get(getAdapterPosition()).getId();
////                remove wishlist call

                    Call<deleteWishListRespones> callD = RetrofitClient.getInstance().getApi().deleteWishList("application/json",token,id);
                    callD.enqueue(new Callback<deleteWishListRespones>() {
                        @Override
                        public void onResponse(Call<deleteWishListRespones> call, Response<deleteWishListRespones> response) {
                            deleteWishListRespones deleteWishListRespones = response.body();
                            if (response.isSuccessful()){
                                wishListModelList.remove(getAdapterPosition());
                                notifyDataSetChanged();
                                Toast.makeText(context, deleteWishListRespones.getMessage(), Toast.LENGTH_LONG).show();
                            }else {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                    Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();

                                } catch (Exception e) {
                                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<deleteWishListRespones> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            });

        }
        private void setData(String resource,String title,String prices){
            try {
                Glide.with(context)
                        .load(resource)
                        .placeholder(R.drawable.placeholder)
                        .centerCrop()
                        .into(productImage);
                productTitle.setText(title);
                couponIcon.setVisibility(View.GONE);
                freeCoupon.setVisibility(View.GONE);
//                if (couponNo != 0) {
//                    couponIcon.setVisibility(View.VISIBLE);
//                    freeCoupon.setVisibility(View.VISIBLE);
//                    if (couponNo == 1) {
//                        freeCoupon.setText("free (" + couponNo + ") coupon");
//                    } else {
//                        freeCoupon.setText("free (" + couponNo +") coupons");
//                    }
//                } else {
//                    couponIcon.setVisibility(View.INVISIBLE);
//                    freeCoupon.setVisibility(View.INVISIBLE);
//                }
//                rating.setText(rate);
                rating.setVisibility(View.GONE);
                price.setText(prices);
//                cuttedPrice.setText(cuttedPricess);
                cuttedPrice.setVisibility(View.GONE);
//                paymentMethod.setText(method);
                paymentMethod.setVisibility(View.GONE);
            }catch (Exception e){

            }
        }
    }

}

