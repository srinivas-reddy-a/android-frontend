package com.akfrontend.arraykart.ui.slideshow;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.akfrontend.arraykart.MyOrder.MyOrder;
import com.akfrontend.arraykart.SignUP;
import com.akfrontend.arraykart.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        slideshowViewModel =
//                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences user_token = getContext().getSharedPreferences("arraykartuser",MODE_PRIVATE);


//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        if(user_token.contains("token")) {
//            Toast.makeText(getContext(), "coming soon", Toast.LENGTH_SHORT).show();
            getContext().startActivity(new Intent(getContext(), MyOrder.class));
        }else {
            startActivity(new Intent(getContext(), SignUP.class));
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}