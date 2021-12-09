package com.example.arraykart.ui.LogOut;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arraykart.R;
import com.example.arraykart.databinding.FragmentLogOutBinding;

public class LogOutFragment extends Fragment {

    private FragmentLogOutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =FragmentLogOutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return  root;
    }
}