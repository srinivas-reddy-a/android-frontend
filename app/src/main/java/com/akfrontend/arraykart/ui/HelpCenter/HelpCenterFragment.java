package com.akfrontend.arraykart.ui.HelpCenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akfrontend.arraykart.HelpCenterActivity.HelpCenterActivity;
import com.akfrontend.arraykart.databinding.FragmentHelpCenterBinding;


public class HelpCenterFragment extends Fragment {

    private FragmentHelpCenterBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHelpCenterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Intent intent = new Intent(getContext(), HelpCenterActivity.class);
        startActivity(intent);
        ((Activity)getContext()).finish();

        return root;
    }
}