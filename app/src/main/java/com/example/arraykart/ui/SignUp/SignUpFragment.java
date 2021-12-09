package com.example.arraykart.ui.SignUp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arraykart.R;
import com.example.arraykart.SignUP;

import java.util.zip.Inflater;
import com.example.arraykart.databinding.FragmentSignUpBinding;



public class SignUpFragment extends Fragment {

    private  FragmentSignUpBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSignUpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        startActivity(new Intent(getContext(),SignUP.class));

        return root;
    }
}