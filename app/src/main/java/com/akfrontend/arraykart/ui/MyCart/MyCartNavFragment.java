package com.akfrontend.arraykart.ui.MyCart;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akfrontend.arraykart.MyCart.MYCartActivity;
import com.akfrontend.arraykart.SignUP;
import com.akfrontend.arraykart.databinding.FragmentMyCartNavBinding;


public class MyCartNavFragment extends Fragment {

    private FragmentMyCartNavBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentMyCartNavBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SharedPreferences user_token = getContext().getSharedPreferences("arraykartuser",MODE_PRIVATE);

        if (user_token.contains("token")) {
            startActivity(new Intent(getContext(), MYCartActivity.class));
            ((Activity) getActivity()).overridePendingTransition(0, 0);
        }else {
            startActivity(new Intent(getContext(), SignUP.class));
        }
        return root;
    }

}
