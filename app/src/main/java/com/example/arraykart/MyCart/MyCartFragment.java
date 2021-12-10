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

import com.example.arraykart.DeliveryPage.DeliveryActivity;
import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {


    private RecyclerView cartItemsRecyclerView;
    private Button cart_continue_btn;
    private  List<CartItemModel> cartItemModelList;
    private  CartAdapter cartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartItemsRecyclerView = view.findViewById(R.id.cart_item_recyclerview);
        cart_continue_btn = view.findViewById(R.id.cart_continue_btn);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        cartItemModelList= new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.img,"Pesticide1",2,"Rs.---/-","RS.",1,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.img,"Pesticide2",0,"Rs.---/-","RS.",2,1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.img,"Pesticide3",1,"Rs.---/-","RS.",3,0,1));
        cartItemModelList.add(new CartItemModel(1,"Price(3 item)","RS.----","Free","RS.-----"));

        cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        cart_continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), DeliveryActivity.class));
            }
        });

        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListeners() {
            @Override
            public void onDeleteClick(int position) {
                cartItemModelList.remove(position);
                cartAdapter.notifyItemRemoved(position);
            }
        });

        return view;
    }
}