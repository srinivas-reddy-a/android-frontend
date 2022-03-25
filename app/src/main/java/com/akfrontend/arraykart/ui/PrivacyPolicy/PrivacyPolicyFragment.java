package com.akfrontend.arraykart.ui.PrivacyPolicy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akfrontend.arraykart.databinding.FragmentPrivacyPolicyBinding;


public class PrivacyPolicyFragment extends Fragment {
    private FragmentPrivacyPolicyBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentPrivacyPolicyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        getContext().startActivity(new Intent(getContext(),PrivacyPolicy.class));
        return root;
    }
}