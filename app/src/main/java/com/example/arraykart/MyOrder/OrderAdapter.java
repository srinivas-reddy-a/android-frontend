package com.example.arraykart.MyOrder;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.arraykart.AllApiModels.getProductsRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.R;
import com.example.arraykart.RatingReviewPage.FeedbakRatingReview;
import com.example.arraykart.homeCategoryProduct.MainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<OrderItemModel> orderItemModels;
    private List<MainModel>mainModels;
    SharedPrefManager sharedPrefManager;
    Context context;
    String prev;

    public OrderAdapter( Context context,List<OrderItemModel> orderItemModels) {
        this.context = context;
        this.orderItemModels = orderItemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        if(orderItemModels.size()>0) {
            String orderId = orderItemModels.get(position).getOrder_id();
            String addId = orderItemModels.get(position).getAddress_id();
            String qty = orderItemModels.get(position).getQuantity();
            String productId = orderItemModels.get(position).getProducts_id();
            String deliveryStatus = orderItemModels.get(position).getDelivery_date();
            String volume = orderItemModels.get(position).getVolume();
            final String[] title = new String[1];
            final String[] resource = new String[1];

            sharedPrefManager = new SharedPrefManager(context);

            String token = sharedPrefManager.getValue_string("token");

            Call<getProductsRespones> callgetp = RetrofitClient.getInstance().getApi().productId(token, productId);
            callgetp.enqueue(new Callback<getProductsRespones>() {
                @Override
                public void onResponse(Call<getProductsRespones> call, Response<getProductsRespones> response) {
                    getProductsRespones getProductsRespones = response.body();
                    if (response.isSuccessful()) {
                        mainModels = getProductsRespones.getProduct();
                        title[0] = mainModels.get(0).getName();
                        resource[0] = mainModels.get(0).getImage();
                        holder.setOrderDetail(resource[0], title[0], deliveryStatus,orderId);
                    } else {
                        Toast.makeText(context, "not respones", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<getProductsRespones> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, OrderDetail.class);
                    i.putExtra("order_id",orderId);
                    i.putExtra("addId",addId);
                    i.putExtra("productId",productId);
                    i.putExtra("qty",qty);
                    i.putExtra("volume",volume);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });


    }

    @Override
    public int getItemCount() {
        return orderItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private ImageView deliveryIndicator;
        private TextView productTitle;
        private TextView deliveryStatus;
        private View grouping;
        private LinearLayout rateContainer ;
        private Button RateAndReview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage =itemView.findViewById(R.id.ordered_image_orderPage);
            productTitle =itemView.findViewById(R.id.title_orderPage);
            deliveryIndicator =itemView.findViewById(R.id.order_indicator);
            deliveryStatus =itemView.findViewById(R.id.order_delivery_date);
            rateContainer = itemView.findViewById(R.id.rating_containers);
            RateAndReview = itemView.findViewById(R.id.RateAndReview);
           // grouping = itemView.findViewById(R.id.grouping);


            RateAndReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, FeedbakRatingReview.class));
                }
            });


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(itemView.getContext(), OrderDetail.class);
//                    i.putExtra("order_id",orde)
//                    itemView.getContext().startActivity(i);
//                }
//            });
        }
        public void setOrderDetail(String image,String title,String delivery,String orderId){
            try {
//                productImage.setImageResource(image);
                Glide.with(context)
                        .load(image)
                        .placeholder(R.drawable.placeholder)
                        .centerCrop()
                        .into(productImage);
                productTitle.setText(title);
                deliveryStatus.setText(delivery);
//                if (delivery.equals("Canceled")) {
//                    deliveryIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
//                }
//                deliveryStatus.setText(delivery);
//                //rating start logic
//                setRating(rating);
                for(int i = 0;i<rateContainer.getChildCount();i++){
                    final int starPosition = i;
                    rateContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setRating(starPosition);
                        }
                    });
                    //rating start logic
//                    prev = orderItemModels.get(getAdapterPosition()).getOrder_id();
//                    String ne = prev;
//
//                    if (orderId == prev){
//                        grouping.setVisibility(View.GONE);
//                        prev = orderId;
//                    }else {
//                        grouping.setVisibility(View.VISIBLE);
//                        prev = ne;
//                    }

                }
            }catch(Exception e){

            }
        }
        //rating start logic
        private void setRating(int starPosition){
            try{
                for(int i = 0;i<rateContainer.getChildCount();i++){
                    ImageView starBar = (ImageView) rateContainer.getChildAt(i);
                    starBar.setImageTintList(ColorStateList.valueOf(Color.parseColor("#CCCCCC")));
                    if(i<=starPosition){
                        starBar.setImageTintList(ColorStateList.valueOf(Color.parseColor("#F9DB22")));
                    }
                }
            }catch (Exception e){

            }
        }

    }
}
