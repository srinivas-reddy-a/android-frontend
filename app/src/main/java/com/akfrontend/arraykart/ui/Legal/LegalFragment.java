package com.akfrontend.arraykart.ui.Legal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akfrontend.arraykart.databinding.FragmentLegalBinding;


public class LegalFragment extends Fragment {

   private FragmentLegalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentLegalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getContext().startActivity(new Intent(getContext(),Legal.class));
        return  root;
    }

}