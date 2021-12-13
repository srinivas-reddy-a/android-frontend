package com.example.arraykart.RatingReviewPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class AllReviewActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private List<Integer> list;
    private ImageView Rating_layout_image;

    private RecyclerView ReviewRecyclerView;
    private List<ReviewModel> reviewModelList ;
    private ReviewAdapter reviewAdapter;

    private TextView lines_or_text;
    private TextView ReviewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_review);

        try{
            ReviewRecyclerView = findViewById(R.id.ReviewRecyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ReviewRecyclerView.setLayoutManager(layoutManager);

            reviewModelList = new ArrayList<>();
            reviewModelList.add(new ReviewModel("4.4","Cool Product",
                    "hbvashbdjajdvbaschbjjacjacjjascjavscj a cjabcjavdab " +
                            "d acj ajscdja dqochqwdb qjhd jhavc cjhs jaca cjhac dvbasjca " +
                            " cjha sjcajc a cujhascb jc ahcjackwdbiwdb " +
                            " wdi bxbxibISX  sx     djb dbuscbxhhxAXB   DDQ WKJDJBB WDKKw","sachin jha","noia","Jan,2021" ));
            reviewModelList.add(new ReviewModel("4.4","Cool Product",
                    "hbvashbdjajdvbaschbjjacjacjjascjavscj a cjabcjavdab " +
                            "d acj ajscdja dqochqwdb qjhd jhavc cjhs jaca cjhac dvbasjca " +
                            " cjha sjcajc a cujhascb jc ahcjac","sachin jha","noia","Jan,2021" ));
            reviewModelList.add(new ReviewModel("4.4","Cool Product",
                    "hbvashbdjajdvbaschbjjacjacjjascjavscj a cjabcjavdab " +
                            "d acj ajscdja dqochqwdb qjhd jhavc cjhs jaca cjhac dvbasjca " +
                            " cjha sjcajc a cujhascb jc ahcjac","sachin jha","noia","Jan,2021" ));
            reviewModelList.add(new ReviewModel("4.4","Cool Product",
                    "hbvashbdjajdvbaschbjjacjacjjascjavscj a cjabcjavdab " +
                            "d acj ajscdja dqochqwdb qjhd jhavc cjhs jaca cjhac dvbasjca " +
                            " cjha sjcajc a cujhascb jc ahcjac","sachin jha","noia","Jan,2021" ));
            reviewModelList.add(new ReviewModel("4.4","Cool Product",
                    "hbvashbdjajdvbaschbjjacjacjjascjavscj a cjabcjavdab " +
                            "d acj ajscdja dqochqwdb qjhd jhavc cjhs jaca cjhac dvbasjca " +
                            " cjha sjcajc a cujhascb jc ahcjac","sachin jha","noia","Jan,2021" ));

            reviewAdapter = new ReviewAdapter(reviewModelList);
            ReviewRecyclerView.setAdapter(reviewAdapter);
        }catch (Exception e){

        }
        try{
            lines_or_text = findViewById(R.id.lines_or_text);
            ReviewDescription = findViewById(R.id.ReviewDescription);
            lines_or_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ReviewDescription.setLines(10);
                }
            });
        }catch (Exception e){

        }
        try {
            linearLayout = findViewById(R.id.image_layout);
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