package com.example.arraykart.Filter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.arraykart.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {


    private Button button;
    @SuppressLint("RestrictedApi")
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, R.style.BottomSheetTheme);



        //Set the custom view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_filter_page, null);
        button = view.findViewById(R.id.buttonfilter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "hiii", Toast.LENGTH_SHORT).show();
                ((Activity) getContext()).finish();
            }
        });
        dialog.setContentView(view);

    }

}
