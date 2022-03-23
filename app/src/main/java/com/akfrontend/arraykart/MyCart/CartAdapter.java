package com.akfrontend.arraykart.MyCart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.akfrontend.arraykart.AllApiModels.CartUPdateRespones;
import com.akfrontend.arraykart.AllApiModels.WishListAddRespones;
import com.akfrontend.arraykart.AllApiModels.deleteWishListRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.AllRetrofit.SharedPrefManager;
import com.akfrontend.arraykart.ProductDetailAboutListing.ProductDetailPageModel;
import com.akfrontend.arraykart.ProductDetailActivity;
import com.akfrontend.arraykart.R;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartItemViewHolder> {

    private List<CartItemModel> cartItemModelList;

    SharedPrefManager sharedPrefManager;

    private List<ProductDetailPageModel> product;


//    private OnItemClickListeners cListener;

    Context context;


//
//    public  interface OnItemClickListeners{
//        void  onDeleteClick(int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListeners listener){
//        cListener = listener;
//    }

    public CartAdapter(List<CartItemModel> cartItemModelList,Context context) {
        this.cartItemModelList = cartItemModelList;
        this.context = context;
    }

//    @Override
//    public int getItemViewType(int position) {
//        switch (cartItemModelList.get(position).getType()){
//            case 0:
//                return CartItemModel.CART_ITEM;
//            case 1:
//                return CartItemModel.TOTAL_AMOUNT;
//            default:
//                return -1;
//        }
//    }

//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
//        return new CartItemViewHolder(cartItemView,cListener);
//
////        switch (viewType){
////            case CartItemModel.CART_ITEM:
////                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
////                return new CartItemViewHolder(cartItemView,cListener);
////            case CartItemModel.TOTAL_AMOUNT:
////                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout,parent,false);
////                return new CartTotalAmountViewHolder(cartTotalView);
////            default:
////                return null;
////        }
//    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        int resource = cartItemModelList.get(position).getProductImage();
//        String title = cartItemModelList.get(position).getProductTitle();
//        int freeCoupon = cartItemModelList.get(position).getFreeCoupons();
//        String productPrice = cartItemModelList.get(position).getProductPrice();
//        String cuttedPrice = cartItemModelList.get(position).getCuttedPerice();
//        int offerApplied = cartItemModelList.get(position).getOffersApplied();
////        switch (cartItemModelList.get(position).getType()){
////            case CartItemModel.CART_ITEM:
////                int resource = cartItemModelList.get(position).getProductImage();
////                String title = cartItemModelList.get(position).getProductTitle();
////                int freeCoupon = cartItemModelList.get(position).getFreeCoupons();
////                String productPrice = cartItemModelList.get(position).getProductPrice();
////                String cuttedPrice = cartItemModelList.get(position).getCuttedPerice();
////                int offerApplied = cartItemModelList.get(position).getOffersApplied();
////                ((CartItemViewHolder)holder).cartItemDetail(resource,title,freeCoupon,productPrice,cuttedPrice,offerApplied);
////                break;
////            case  CartItemModel.TOTAL_AMOUNT:
////                String totalItem = cartItemModelList.get(position).getTotalItems();
////                String totalItemAmount = cartItemModelList.get(position).getTotalItemPrice();
////                String deliveryPrice = cartItemModelList.get(position).getDeliveryPrice();
////                String savedAmount = cartItemModelList.get(position).getSavedAmount();
////                ((CartTotalAmountViewHolder)holder).setTotalAmount(totalItem,totalItemAmount,deliveryPrice,savedAmount);
////                break;
//////            default:
////                return
//        holder.cartItemDetail(resource,title,freeCoupon,productPrice,cuttedPrice,offerApplied);
//    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
        return new CartItemViewHolder(cartItemView);
    }


    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        String resource = cartItemModelList.get(position).getImage();
        String title = cartItemModelList.get(position).getName();
        String id = cartItemModelList.get(position).getId();
//        int freeCoupon = cartItemModelList.get(position).getFreeCoupons();
        String productPrice = cartItemModelList.get(position).getPrice();
        String productQuantity = cartItemModelList.get(position).getQuantity();
        String volume = cartItemModelList.get(position).getVolume();
//        String cuttedPrice = cartItemModelList.get(position).getCuttedPerice();
//        int offerApplied = cartItemModelList.get(position).getOffersApplied()
        holder.cartItemDetail(resource,title,productPrice,productQuantity,volume);


    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImage,Add_quantity_cart,mini_quantity_cart;
        private ImageView freeCouponIcon;
        private TextView productTitle;
        private TextView freeCoupons;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView offerApplied;
        private TextView couponsApplied;
        private TextView productQuantity;
        private ConstraintLayout remove_item_btn;
        private TextView add_wishList_cart_page,remove_item_btn_cartPage ,cartVolume;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            freeCouponIcon = itemView.findViewById(R.id.free_offer_icon);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupons = itemView.findViewById(R.id.tv_free_offer);
            productPrice = itemView.findViewById(R.id.cart_item_price);
            cuttedPrice = itemView.findViewById(R.id.cart_item_off_price);
            offerApplied = itemView.findViewById(R.id.offer_applied);
            couponsApplied = itemView.findViewById(R.id.coupon_applied);
            cartVolume = itemView.findViewById(R.id.selected_volume);
            productQuantity = itemView.findViewById(R.id.product_quantity_text);
            remove_item_btn=itemView.findViewById(R.id.remove_item_btn);
            Add_quantity_cart = itemView.findViewById(R.id.Add_quantity_cart);
            mini_quantity_cart = itemView.findViewById(R.id.mini_quantity_cart);
            remove_item_btn_cartPage = itemView.findViewById(R.id.remove_item_btn_cartPage);
            add_wishList_cart_page = itemView.findViewById(R.id.add_wishList_cart_page);


            sharedPrefManager = new SharedPrefManager(itemView.getContext());
            String token = sharedPrefManager.getValue_string("token");


            try{
                Add_quantity_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Atext = productQuantity.getText().toString();
                        int Aqlt = Integer.parseInt(Atext);
                        Aqlt +=1;
                        String nATxt = Integer.toString(Aqlt);
                        productQuantity.setText(nATxt);

//            api call to update cart quantity
                        String id = cartItemModelList.get(getAdapterPosition()).getId();
                        Call<CartUPdateRespones> callU = RetrofitClient.getInstance().getApi().updateCart(token,id,nATxt,cartItemModelList.get(getAdapterPosition()).getVolume(),cartItemModelList.get(getAdapterPosition()).getVolume());
                        callU.enqueue(new Callback<CartUPdateRespones>() {
                            @Override
                            public void onResponse(Call<CartUPdateRespones> call, Response<CartUPdateRespones> response) {
                                 CartUPdateRespones cartUPdateRespones = response.body();
                                if (response.isSuccessful()) {
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
                            public void onFailure(Call<CartUPdateRespones> call, Throwable t) {
                                Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }catch (Exception e){

            }

            try{
                mini_quantity_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String stext = productQuantity.getText().toString();
                        int sqlt = Integer.parseInt(stext);
                        if(sqlt==1) {
                            String nSTxt = Integer.toString(sqlt);
                            productQuantity.setText(nSTxt);
                        }else if(sqlt>1){
                            sqlt -=1;
                            String nSTxt = Integer.toString(sqlt);
                            productQuantity.setText(nSTxt);

//      api call to update cart quantity
                        String id = cartItemModelList.get(getAdapterPosition()).getId();
                        Call<CartUPdateRespones> callU = RetrofitClient.getInstance().getApi().updateCart(token,id,nSTxt,cartItemModelList.get(getAdapterPosition()).getVolume(),cartItemModelList.get(getAdapterPosition()).getVolume());
                        callU.enqueue(new Callback<CartUPdateRespones>() {
                            @Override
                            public void onResponse(Call<CartUPdateRespones> call, Response<CartUPdateRespones> response) {
                                CartUPdateRespones cartUPdateRespones = response.body();
                                if (response.isSuccessful()) {
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
                            public void onFailure(Call<CartUPdateRespones> call, Throwable t) {
                                Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        }
                    }
                });
            }catch (Exception e){

            }

            try{
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(itemView.getContext(), ProductDetailActivity.class);
                        i.putExtra("id",cartItemModelList.get(getAdapterPosition()).getId());
                        i.putExtra("qlt",productQuantity.getText().toString());
                        i.putExtra("image",cartItemModelList.get(getAdapterPosition()).getImage());
                        i.putExtra("volume",cartItemModelList.get(getAdapterPosition()).getVolume());
                        itemView.getContext().startActivity(i);
                    }
                });

            }catch (Exception e){

            }
            try{
                remove_item_btn_cartPage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (listener != null) {
//                            int position = getAdapterPosition();
//                            if (position != RecyclerView.NO_POSITION) {
//                                listener.onDeleteClick(position);
//                            }
//                        }

                        AlertDialog alg = new AlertDialog.Builder(context)
                                .setTitle("Message")
                                .setMessage("Are you sure ?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        String id = cartItemModelList.get(getAdapterPosition()).getId();
                                        Call<deleteWishListRespones> callD = RetrofitClient.getInstance().getApi().deleteCartItem(token,id);
                                        callD.enqueue(new Callback<deleteWishListRespones>() {
                                            @Override
                                            public void onResponse(Call<deleteWishListRespones> call, Response<deleteWishListRespones> response) {
                                                deleteWishListRespones deleteWishListRespones = response.body();
                                                if(response.isSuccessful()){
                                                    cartItemModelList.remove(getAdapterPosition());
                                                    notifyItemRemoved(getAdapterPosition());
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
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .create();
                        alg.show();

                    }
                });
            }catch (Exception e){

            }

            add_wishList_cart_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///add to wishlist on cart

                    String id = cartItemModelList.get(getAdapterPosition()).getId();
                    String qty = productQuantity.getText().toString();
                    Call<WishListAddRespones> call = RetrofitClient.getInstance().getApi().addWishlist(token, id, qty);
                    call.enqueue(new Callback<WishListAddRespones>() {
                        @Override
                        public void onResponse(Call<WishListAddRespones> call, Response<WishListAddRespones> response) {
                            WishListAddRespones wishListAddRespones = response.body();
                            if (response.isSuccessful()) {
                                String msg = wishListAddRespones.getMessage();

                                //////delete item to cart and add to wishlist
                                Call<deleteWishListRespones> callD = RetrofitClient.getInstance().getApi().deleteCartItem(token,id);
                                callD.enqueue(new Callback<deleteWishListRespones>() {
                                    @Override
                                    public void onResponse(Call<deleteWishListRespones> call, Response<deleteWishListRespones> response) {
                                        deleteWishListRespones deleteWishListRespones = response.body();
                                        if(response.isSuccessful()){
                                            cartItemModelList.remove(getAdapterPosition());
                                            notifyItemRemoved(getAdapterPosition());
                                            notifyDataSetChanged();
                                            Toast.makeText(context, "Product Added to WishList", Toast.LENGTH_LONG).show();
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


                            } else {
                                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<WishListAddRespones> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });






//
        }

        private void cartItemDetail(String image,String title,String productPriceText,String qty,String volume){
            try {

//                productImage.setImageResource(image);
                Glide.with(context)
                        .load(image)
                        .placeholder(R.drawable.placeholder)
                        .centerCrop()
                        .into(productImage);
                productTitle.setText(title);
                freeCoupons.setVisibility(View.GONE);
                freeCouponIcon.setVisibility(View.GONE);
                cartVolume.setText("Volume "+ volume );
                productPrice.setText("₹ "+" "+productPriceText+"---");
//                productPrice.setText("Price coming soon");
                cuttedPrice.setVisibility(View.GONE);
                offerApplied.setVisibility(View.GONE);
                productQuantity.setText(qty);
//                if (freeCoupon > 0) {
//                    freeCouponIcon.setVisibility(View.VISIBLE);
//                    freeCoupons.setVisibility(View.VISIBLE);
//                    if (freeCoupon == 1) {
//                        freeCoupons.setText("free"+freeCoupon+"coupon");
//                    }else{
//                        freeCoupons.setText("free"+freeCoupon+"coupons");
//                    }
//                }else {
//                    freeCouponIcon.setVisibility(View.INVISIBLE);
//                    freeCoupons.setVisibility(View.INVISIBLE);
//                }
//                productPrice.setText(productPriceText);
//                cuttedPrice.setText(cuttedPriceText);
//                if(offerAppliedNo>0){
//                    offerApplied.setVisibility(View.VISIBLE);
//                    offerApplied.setText(offerAppliedNo +"offer applied");
//                }else{
//                    offerApplied.setVisibility(View.INVISIBLE);
//                }
////                if(couponAppliedNo>0){
////                    couponsApplied.setVisibility(View.VISIBLE);
////                    couponsApplied.setText(couponAppliedNo +"coupon applied");
////                }else {
////                    couponsApplied.setVisibility(View.INVISIBLE);
////                }
            }catch (Exception e){

            }

        }

    }

    private void alert(String message){
        AlertDialog alg = new AlertDialog.Builder(context)
                .setTitle("Message")
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        alg.show();
    }

//    class CartTotalAmountViewHolder extends  RecyclerView.ViewHolder{
//        private TextView totalItems;
//        private TextView totalItemPrice;
//        private TextView deliveryPrice;
//        private TextView savedPrice;
//
//        public CartTotalAmountViewHolder(@NonNull View itemView) {
//            super(itemView);
//            totalItems = itemView.findViewById(R.id.total_item);
//            totalItemPrice=itemView.findViewById(R.id.total_item_price);
//            deliveryPrice=itemView.findViewById(R.id.delivery_price);
//            savedPrice=itemView.findViewById(R.id.saved_price);
//
//        }
//        private void setTotalAmount(String totalItemText,String totalItemPriceText,String deliveryPriceText,String savedAmountText){
//            totalItems.setText(totalItemText);
//            totalItemPrice.setText(totalItemPriceText);
//            deliveryPrice.setText(deliveryPriceText);
//            savedPrice.setText(savedAmountText);
//
//        }
//
//    }

}
