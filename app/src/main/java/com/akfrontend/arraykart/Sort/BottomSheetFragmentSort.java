package com.akfrontend.arraykart.Sort;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;

import com.akfrontend.arraykart.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragmentSort extends BottomSheetDialogFragment {

    @SuppressLint("RestrictedApi")
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, R.style.BottomSheetTheme);

        //Set the custom view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_sort_page, null);
        dialog.setContentView(view);
    }
}
