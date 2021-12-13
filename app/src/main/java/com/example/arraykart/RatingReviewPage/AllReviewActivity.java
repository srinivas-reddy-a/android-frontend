package com.example.arraykart.RatingReviewPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class AllReviewActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private List<Integer> list;
    private ImageView Rating_layout_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_review);

        try {
            linearLayout = findViewById(R.id.RatingImage);
            list = new ArrayList<>();
            list.add(R.drawable.img);
            list.add(R.drawable.img);
            list.add(R.drawable.img);
            list.add(R.drawable.img);
            for (int i =0;i<list.size();i++){
                View view = LayoutInflater.from(this).inflate(R.layout.rating_image_layout,linearLayout,false);
                Rating_layout_image = view.findViewById(R.id.Rating_layout_image);
                Rating_layout_image.setImageResource(list.get(i));

                linearLayout.addView(view);
            }
        }catch (Exception e){

        }
    }
}