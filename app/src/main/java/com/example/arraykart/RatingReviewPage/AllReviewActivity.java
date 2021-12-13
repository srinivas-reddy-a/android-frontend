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
import android.widget.Toolbar;

import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class AllReviewActivity extends AppCompatActivity {


    private RecyclerView ReviewRecyclerView;
    private List<ReviewModel> reviewModelList ;
    private ReviewAdapter reviewAdapter;

    ///back button
    private ImageView back_AllReview_page;


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
            reviewAdapter.notifyDataSetChanged();
        }catch (Exception e){

        }
        try {
            back_AllReview_page = findViewById(R.id.back_AllReview_page);
            back_AllReview_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }
    }
}