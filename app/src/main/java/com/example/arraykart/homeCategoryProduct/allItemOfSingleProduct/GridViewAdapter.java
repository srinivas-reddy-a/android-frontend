package com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.arraykart.AllApiModels.CartUPdateRespones;
import com.example.arraykart.AllApiModels.WishListAddRespones;
import com.example.arraykart.AllApiModels.deleteWishListRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GridViewAdapter extends BaseAdapter {
    Context context;
    private List<ModelForSingleProduct> modelForSingleProducts;
    SharedPrefManager sharedPrefManager = new SharedPrefManager(getApplicationContext());
    SharedPreferences userToken = getApplicationContext().getSharedPreferences("arraykartuser",MODE_PRIVATE);


    public GridViewAdapter(Context context, List<ModelForSingleProduct> modelForSingleProducts) {
        this.context = context;
        this.modelForSingleProducts = modelForSingleProducts;
    }

    public class ViewHolders{
        public ImageView cImg;
        public TextView txt;
        public TextView prc;
        public TextView rt;
        public TextView rb;
    }


    @Override
    public int getCount() {
        return modelForSingleProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return modelForSingleProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent) {
        ViewHolders holder;
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_frame_single_product, parent, false);

        holder = new ViewHolders();

        // it will help take item from single product and put that item in this page

            holder.cImg = view.findViewById(R.id.gridImage);
            holder.txt = view.findViewById(R.id.gridText);
            holder.prc = view.findViewById(R.id.priceGrid);
            holder.rt = view.findViewById(R.id.rateGrid);
            holder.rb = view.findViewById(R.id.ribbonTag);
            CheckBox wish = view.findViewById(R.id.wishListSingleProducts);

            String token = sharedPrefManager.getValue_string("token");


            view.setTag(holder);

            holder = (ViewHolders) view.getTag();

//            cImg.setImageResource(modelForSingleProducts.get(position).getImgs());
            Glide.with(context.getApplicationContext())
                    .load(modelForSingleProducts.get(position).getImgs())
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.cImg);
            holder.txt.setText(modelForSingleProducts.get(position).getName());
            holder.prc.setText("RS."+modelForSingleProducts.get(position).getPrice()+"/--");
            holder.rb.setVisibility(View.GONE);
            holder.rt.setVisibility(View.GONE);

        // wishlist status
        if(userToken.contains("token")){
            String Id = modelForSingleProducts.get(position).getId();
            Call<CartUPdateRespones> callWishStatus = RetrofitClient.getInstance().getApi().getStatusWishList(token,Id);
            callWishStatus.enqueue(new Callback<CartUPdateRespones>() {
                @Override
                public void onResponse(Call<CartUPdateRespones> call, Response<CartUPdateRespones> response) {
                    CartUPdateRespones cartUPdateRespones = response.body();
                    if(response.isSuccessful()){
                        String wstr = cartUPdateRespones.getMessage();
                        if(wstr.contains("Product already exists in wishlist!")){
                            wish.setChecked(true);
                        }
                    }else {
                        wish.setChecked(false);
                    }
                }

                @Override
                public void onFailure(Call<CartUPdateRespones> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

        ///// wish list add and delete
        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userToken.contains("token")) {
                    String id = modelForSingleProducts.get(position).getId();
                    String qty ="1";
                    Call<WishListAddRespones> call = RetrofitClient.getInstance().getApi().addWishlist(token, id, qty);
                    call.enqueue(new Callback<WishListAddRespones>() {
                        @Override
                        public void onResponse(Call<WishListAddRespones> call, Response<WishListAddRespones> response) {
                            WishListAddRespones wishListAddRespones = response.body();
                            if (response.isSuccessful()) {
                                String msg = wishListAddRespones.getMessage();

                                if(msg.contains("Product already exists in wish list!")){
                                    //delete products from wishlist
                                    wish.setChecked(false);
                                    Call<deleteWishListRespones> callD = RetrofitClient.getInstance().getApi().deleteWishList("application/json",token,id);
                                    callD.enqueue(new Callback<deleteWishListRespones>() {
                                        @Override
                                        public void onResponse(Call<deleteWishListRespones> call, Response<deleteWishListRespones> response) {
                                            deleteWishListRespones deleteWishListRespones = response.body();
                                            if (response.isSuccessful()){
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


                                } else {
                                    Toast.makeText(context, wishListAddRespones.getMessage(), Toast.LENGTH_LONG).show();
                                    wish.setChecked(true);
                                }

                            } else {
                                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<WishListAddRespones> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "SignUp First", Toast.LENGTH_SHORT).show();
                    wish.setChecked(false);
                }

            }
        });

//            if (position <= modelForSingleProducts.size()) {
////
////                    holder.rt.setText(modelForSingleProducts.get(position).getRate());
////
////                    if (position == 3) {
////                        holder.rb.setText(modelForSingleProducts.get(position).getRibbon());
////                        holder.rb.setTextColor(Color.parseColor("#FFFFFF"));
////                    } else {
////                        holder.rb.setBackgroundResource(R.color.white);
////                        holder.rb.setText(null);
////                    }
//            }


        try {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, ProductDetailActivity.class);
                    in.putExtra("id",modelForSingleProducts.get(position).getId());
                    in.putExtra("qlt","1");
                    in.putExtra("image",modelForSingleProducts.get(position).getImgs());
                    in.putExtra("price",modelForSingleProducts.get(position).getPrice());
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in);
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return view;
    }

}
