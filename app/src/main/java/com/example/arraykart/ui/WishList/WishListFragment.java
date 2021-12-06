package com.example.arraykart.ui.WishList;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arraykart.MyOrder.MyOrder;
import com.example.arraykart.R;
import com.example.arraykart.WishList.WishListActivity;
import com.example.arraykart.databinding.FragmentWishList2Binding;
import com.example.arraykart.databinding.FragmentWishListBinding;


public class WishListFragment extends Fragment {

    private FragmentWishListBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentWishListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getContext().startActivity(new Intent(getContext(), WishListActivity.class));
        return root;
    }

    public void onDestroyView(){
        super.onDestroyView();
        binding=null;
    }
}