package com.example.arraykart.Filter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;

import com.example.arraykart.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class FilterPage extends AppCompatActivity {

    private Chip chip1,chip2,chip3,chip4,chip5;
    private Chip chip6,chip7,chip8,chip9,chip10;

    private Button button;
    private ArrayList<String> selectedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_page);

        chip1=findViewById(R.id.chip1);
        chip2=findViewById(R.id.chip2);
        chip3=findViewById(R.id.chip3);
        chip4=findViewById(R.id.chip4);
        chip5=findViewById(R.id.chip5);

        chip6=findViewById(R.id.chip6);
        chip7=findViewById(R.id.chip7);
        chip8=findViewById(R.id.chip8);
        chip9=findViewById(R.id.chip9);
        chip10=findViewById(R.id.chip10);

        selectedData = new ArrayList<>();


        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectedData.add(buttonView.getText().toString());
                }else{
                    selectedData.remove(buttonView.getText().toString());
                }
            }
        };

        chip1.setOnCheckedChangeListener(checkedChangeListener);
        chip2.setOnCheckedChangeListener(checkedChangeListener);
        chip3.setOnCheckedChangeListener(checkedChangeListener);
        chip4.setOnCheckedChangeListener(checkedChangeListener);
        chip5.setOnCheckedChangeListener(checkedChangeListener);
    }
}