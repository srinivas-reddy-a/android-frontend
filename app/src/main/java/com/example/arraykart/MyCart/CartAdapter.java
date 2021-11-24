package com.example.arraykart.MyCart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                return new CartItemViewHolder(cartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout,parent,false);
                return new CartTotalAmountViewHolder(cartTotalView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (cartItemModelList.get(position).getType()){
            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                int freeCoupon = cartItemModelList.get(position).getFreeCoupons();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String cuttedPrice = cartItemModelList.get(position).getCuttedPerice();
                int offerApplied = cartItemModelList.get(position).getOffersApplied();
                ((CartItemViewHolder)holder).cartItemDetail(resource,title,freeCoupon,productPrice,cuttedPrice,offerApplied);
                break;
            case  CartItemModel.TOTAL_AMOUNT:
                String totalItem = cartItemModelList.get(position).getTotalItems();
                String totalItemAmount = cartItemModelList.get(position).getTotalItemPrice();
                String deliveryPrice = cartItemModelList.get(position).getDeliveryPrice();
                String savedAmount = cartItemModelList.get(position).getSavedAmount();
                ((CartTotalAmountViewHolder)holder).setTotalAmount(totalItem,totalItemAmount,deliveryPrice,savedAmount);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private ImageView freeCouponIcon;
        private TextView productTitle;
        private TextView freeCoupons;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView offerApplied;
        private TextView couponsApplied;
        private TextView productQuantity;

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
            productQuantity = itemView.findViewById(R.id.product_quantity);
        }
        private void cartItemDetail(int image,String title,int freeCoupon,String productPriceText,String cuttedPriceText,int offerAppliedNo){
            try {

                productImage.setImageResource(image);
                productTitle.setText(title);
                if (freeCoupon > 0) {
                    freeCouponIcon.setVisibility(View.VISIBLE);
                    freeCoupons.setVisibility(View.VISIBLE);
                    if (freeCoupon == 1) {
                        freeCoupons.setText("free"+freeCoupon+"coupon");
                    }else{
                        freeCoupons.setText("free"+freeCoupon+"coupons");
                    }
                }else {
                    freeCouponIcon.setVisibility(View.INVISIBLE);
                    freeCoupons.setVisibility(View.INVISIBLE);
                }
                productPrice.setText(productPriceText);
                cuttedPrice.setText(cuttedPriceText);
                if(offerAppliedNo>0){
                    offerApplied.setVisibility(View.VISIBLE);
                    offerApplied.setText(offerAppliedNo +"offer applied");
                }else{
                    offerApplied.setVisibility(View.INVISIBLE);
                }
//                if(couponAppliedNo>0){
//                    couponsApplied.setVisibility(View.VISIBLE);
//                    couponsApplied.setText(couponAppliedNo +"coupon applied");
//                }else {
//                    couponsApplied.setVisibility(View.INVISIBLE);
//                }
            }catch (Exception e){

            }

        }

    }

    class CartTotalAmountViewHolder extends  RecyclerView.ViewHolder{
        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView savedPrice;

        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
            totalItems = itemView.findViewById(R.id.total_item);
            totalItemPrice=itemView.findViewById(R.id.total_item_price);
            deliveryPrice=itemView.findViewById(R.id.delivery_price);
            savedPrice=itemView.findViewById(R.id.saved_price);

        }
        private void setTotalAmount(String totalItemText,String totalItemPriceText,String deliveryPriceText,String savedAmountText){
            totalItems.setText(totalItemText);
            totalItemPrice.setText(totalItemPriceText);
            deliveryPrice.setText(deliveryPriceText);
            savedPrice.setText(savedAmountText);

        }

    }
}