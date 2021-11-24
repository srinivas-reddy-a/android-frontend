package com.example.arraykart.Filter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.arraykart.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {


    @SuppressLint("RestrictedApi")
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, R.style.BottomSheetTheme);

        //Set the custom view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_filter_page, null);
        dialog.setContentView(view);
    }
}
