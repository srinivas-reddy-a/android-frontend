package com.example.arraykart.MyCart;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arraykart.AllApiModels.GetCartRespones;
import com.example.arraykart.AllApiModels.deleteWishListRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.DeliveryPage.DeliveryActivity;
import com.example.arraykart.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCartFragment extends Fragment {


    private RecyclerView cartItemsRecyclerView;
    private Button cart_continue_btn;
    private  List<CartItemModel> cartItemModelList;
    private  CartAdapter cartAdapter;
    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        sharedPrefManager = new SharedPrefManager(getContext());
        String token = sharedPrefManager.getValue_string("token");

        cartItemsRecyclerView = view.findViewById(R.id.cart_item_recyclerview);
        cart_continue_btn = view.findViewById(R.id.cart_continue_btn);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        cartItemModelList= new ArrayList<>();
//        cartItemModelList.add(new CartItemModel(0,"1",R.drawable.img,"Pesticide1",2,"Rs.---/-","RS.",1,0,0));
//        cartItemModelList.add(new CartItemModel(0,"2",R.drawable.img,"Pesticide2",0,"Rs.---/-","RS.",2,1,0));
//        cartItemModelList.add(new CartItemModel(0,"3",R.drawable.img,"Pesticide3",1,"Rs.---/-","RS.",3,0,1));
//        cartItemModelList.add(new CartItemModel(1,"Price(3 item)","RS.----","Free","RS.-----"));
//
//        cartAdapter = new CartAdapter(cartItemModelList);
//        cartItemsRecyclerView.setAdapter(cartAdapter);
//        cartAdapter.notifyDataSetChanged();

//       api call to get cart items

        Call<GetCartRespones> callG = RetrofitClient.getInstance().getApi().getCartItem(token);
        callG.enqueue(new Callback<GetCartRespones>() {
            @Override
            public void onResponse(Call<GetCartRespones> call, Response<GetCartRespones> response) {
                GetCartRespones getCartRespones = response.body();
                if(response.isSuccessful()) {
                    cartItemModelList = getCartRespones.getProducts();
                    cartAdapter = new CartAdapter(cartItemModelList, getContext());
                    cartItemsRecyclerView.setAdapter(cartAdapter);
                    cartAdapter.notifyDataSetChanged();
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCartRespones> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        try {
            cart_continue_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContext().startActivity(new Intent(getContext(), DeliveryActivity.class));
                }
            });
        }catch (Exception e){

        }

//        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListeners() {
//            @Override
//            public void onDeleteClick(int position) {
//
////         api call delete cart item
//                String id = cartItemModelList.get(position).getId();
//                Call<deleteWishListRespones> callD = RetrofitClient.getInstance().getApi().deleteCartItem(token,id);
//                callD.enqueue(new Callback<deleteWishListRespones>() {
//                    @Override
//                    public void onResponse(Call<deleteWishListRespones> call, Response<deleteWishListRespones> response) {
//                        deleteWishListRespones deleteWishListRespones = response.body();
//                        if(response.isSuccessful()){
//                            Toast.makeText(getContext(), deleteWishListRespones.getMessage(), Toast.LENGTH_LONG).show();
//                        }else {
//                            try {
//                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
//                                Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
//
//
//                            } catch (Exception e) {
//                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<deleteWishListRespones> call, Throwable t) {
//                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });


        return view;
    }
}