package com.example.arraykart.ui.MyCart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.arraykart.HomeNavigationActivity;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.R;
import com.example.arraykart.databinding.FragmentMyCartNavBinding;


public class MyCartNavFragment extends Fragment {

    private FragmentMyCartNavBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentMyCartNavBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        startActivity(new Intent(getContext(), MYCartActivity.class));
        ((Activity) getActivity()).overridePendingTransition(0,0);
        return root;
    }
    public void onDestroyView(){
        super.onDestroyView();
       // binding=null;

    }
}
