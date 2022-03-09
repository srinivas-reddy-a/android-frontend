package com.example.arraykart.MyCart;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arraykart.AddressActivity.AddressModel;
import com.example.arraykart.AddressActivity.MyAddressActivity;
import com.example.arraykart.AllApiModels.GetCartRespones;
import com.example.arraykart.AllApiModels.deleteWishListRespones;
import com.example.arraykart.AllApiModels.getSelectedAddressRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.DeliveryPage.DeliveryActivity;
import com.example.arraykart.MyOrder.OrderPlacedPage;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;
import com.example.arraykart.SignUP;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCartFragment extends Fragment {


    private RecyclerView cartItemsRecyclerView;
    private Button delivery_continue_btn,buy_on_cart,changeAddress;
    private  List<CartItemModel> cartItemModelList;
    private  CartAdapter cartAdapter;
    private TextView total_cart_amount;
    private ImageView close_address_Prouct_detail_page;
    private String total;
    int size;
    private LinearLayout ProductDetailPageAddressShow;
    private TextView shoppingDetailName,shoppingDetailAddress,textView6;
    private List<AddressModel> addressModels;

    SharedPrefManager sharedPrefManager;

    String AddId ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        sharedPrefManager = new SharedPrefManager(getContext());
        String token = sharedPrefManager.getValue_string("token");

        cartItemsRecyclerView = view.findViewById(R.id.cart_item_recyclerview);
        delivery_continue_btn = view.findViewById(R.id.delivery_continue_btn);
        buy_on_cart = view.findViewById(R.id.buy_on_cart);
        changeAddress = view.findViewById(R.id.changeAddress);
        ProductDetailPageAddressShow = view.findViewById(R.id.ProductDetailPageAddressShow);
        close_address_Prouct_detail_page = view.findViewById(R.id.close_address_Prouct_detail_page);
        total_cart_amount = view.findViewById(R.id.total_cart_amount);

        shoppingDetailName = view.findViewById(R.id.shoppingDetailName);
        shoppingDetailAddress = view.findViewById(R.id.shoppingDetailAddress);
        textView6 = view.findViewById(R.id.textView6);

        ShippingAddress();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        cartItemModelList= new ArrayList<>();
//        cartItemModelList.add(new CartItemModel(0,"1",R.drawable.img,"Pesticide1",2,"₹ ---/-","₹",1,0,0));
//        cartItemModelList.add(new CartItemModel(0,"2",R.drawable.img,"Pesticide2",0,"₹ ---/-","₹",2,1,0));
//        cartItemModelList.add(new CartItemModel(0,"3",R.drawable.img,"Pesticide3",1,"₹ ---/-","₹",3,0,1));
//        cartItemModelList.add(new CartItemModel(1,"Price(3 item)","₹ ----","Free","₹ -----"));
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

                    size += cartItemModelList.size();
                    int t=0;
                    for (int j =0;j<size;j++) {
                        t += Integer.parseInt( cartItemModelList.get(j).getQuantity()) * Integer.parseInt( cartItemModelList.get(j).getPrice());
                    }
                    total = Integer.toString(t);

                    total_cart_amount.setText("₹ "+total+"---");
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();


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
        try{
            buy_on_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeAddress.setVisibility(View.VISIBLE);
                    ProductDetailPageAddressShow.setVisibility(View.VISIBLE);
                    buy_on_cart.setVisibility(View.GONE);
                    delivery_continue_btn.setVisibility(View.VISIBLE);

                }
            });
        }catch (Exception e){

        }

        try {
            delivery_continue_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orederPlaced(AddId,token,total);
                }
            });
            close_address_Prouct_detail_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailPageAddressShow.setVisibility(View.GONE);
                    buy_on_cart.setVisibility(View.VISIBLE);
                    delivery_continue_btn.setVisibility(View.GONE);
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

        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), MyAddressActivity.class));
            }
        });


        return view;
    }


    private void ShippingAddress(){
        ///shipping address

        sharedPrefManager = new SharedPrefManager(getContext());
        String token = sharedPrefManager.getValue_string("token");

        final String[] add1 = new String[1];
        final String[] add2 = new String[1];
        final String[] city = new String[1];
        final String[] state = new String[1];

        SharedPreferences userToken = getContext().getSharedPreferences("arraykartuser",MODE_PRIVATE);
        if(userToken.contains("token")) {
            Call<getSelectedAddressRespones> callSelectedAddress = RetrofitClient.getInstance().getApi().getSelectedAddress(token);
            callSelectedAddress.enqueue(new Callback<getSelectedAddressRespones>() {
                @Override
                public void onResponse(Call<getSelectedAddressRespones> call, Response<getSelectedAddressRespones> response) {
                    getSelectedAddressRespones getSelectedAddressRespones = response.body();
                    addressModels = getSelectedAddressRespones.getAddress();
                    if(!addressModels.isEmpty()) {
                        shoppingDetailName.setText(addressModels.get(0).getAddress_name());
                        AddId = addressModels.get(0).getId();
                        add1[0] = addressModels.get(0).getAddress_line1();
                        add2[0] = addressModels.get(0).getAddress_line2();
                        state[0] = addressModels.get(0).getState();
                        city[0] = addressModels.get(0).getCity();
                        textView6.setText(addressModels.get(0).getPostal_code());
                        shoppingDetailAddress.setText(add1[0] + "," + add2[0] + "," + state[0] + "," + city[0]);
                    }else {
                        shoppingDetailName.setText("Full Name");
                        textView6.setText("PinCode");
                        shoppingDetailAddress.setText("Full Address");
                        AddId = "null";
                    }

                }

                @Override
                public void onFailure(Call<getSelectedAddressRespones> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
        }
    }

    private void  orederPlaced(String AddIds,String token,String total){
        if(!AddIds.contains("null")){
            Call<deleteWishListRespones> callOrder = RetrofitClient.getInstance().getApi().orderAdd(token,total,AddIds);
            callOrder.enqueue(new Callback<deleteWishListRespones>() {
                @Override
                public void onResponse(Call<deleteWishListRespones> call, Response<deleteWishListRespones> response) {
                    deleteWishListRespones deleteWishListRespones = response.body();
                    if(response.isSuccessful()){
                        String order_id = deleteWishListRespones.getMessage();
                        for (int i = 0;i<cartItemModelList.size();i++) {
                            Call<ResponseBody> callDetail = RetrofitClient.getInstance().getApi().OrderDetail(order_id, cartItemModelList.get(i).getId(),  cartItemModelList.get(i).getQuantity(),cartItemModelList.get(i).getVolume());
                            callDetail.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {

                                        Intent in = new Intent(getContext(), OrderPlacedPage.class);
                                        in.putExtra("page","cart");
//                                        in.putExtra("id", id);
//                                        in.putExtra("qlt", qty);
//                                        in.putExtra("image", imgs);
//                                        in.putExtra("total", total);
//                                        in.putExtra("order_id", order_id);
//                                        in.putExtra("Add", AddIds);
//                                        in.putExtra("name", pdProductName.getText().toString());
//                                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(in);
                                        ProductDetailPageAddressShow.setVisibility(View.GONE);
                                        delivery_continue_btn.setVisibility(View.GONE);
                                        buy_on_cart.setVisibility(View.VISIBLE);
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(Call<deleteWishListRespones> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(getContext(), "Please Add you Address First", Toast.LENGTH_LONG).show();
        }

    }


}