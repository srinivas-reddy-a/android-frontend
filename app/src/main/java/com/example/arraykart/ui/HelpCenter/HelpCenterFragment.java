package com.example.arraykart.ui.HelpCenter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arraykart.R;
import com.example.arraykart.databinding.FragmentHelpCenterBinding;


public class HelpCenterFragment extends Fragment {

    private FragmentHelpCenterBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHelpCenterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}