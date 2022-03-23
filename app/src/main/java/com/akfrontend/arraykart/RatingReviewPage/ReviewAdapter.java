package com.akfrontend.arraykart.RatingReviewPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akfrontend.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<ReviewModel> reviewModels ;

    public ReviewAdapter(List<ReviewModel> reviewModels) {
        this.reviewModels = reviewModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_review_item_layout,parent,false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String rate = (reviewModels.get(position).getRating());
        String title = reviewModels.get(position).getTitle();
        String des = reviewModels.get(position).getDescription();
        String buyerName =reviewModels.get(position).getBuyerName();
        String buyerLocation = reviewModels.get(position).getBuyerLocation();
        String buyingDate = reviewModels.get(position).getBuyingDate();

        holder.setData(rate,title,des,buyerName,buyerLocation,buyingDate);


    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rating;
        private TextView title;
        private TextView description;
        private TextView buyerName;
        private TextView buyerLocation;
        private TextView buyingDate;
        private TextView lines_or_text;
        private TextView lessLines_or_text;
        private LinearLayout linearLayout;
        private ImageView Rating_layout_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rating = itemView.findViewById(R.id.rateGrid);
            title= itemView.findViewById(R.id.ReviewTitle);
            description = itemView.findViewById(R.id.ReviewDescription);
            buyerName= itemView.findViewById(R.id.name_of_buyer);
            buyerLocation = itemView.findViewById(R.id.location_of_buyer);
            buyingDate = itemView.findViewById(R.id.date_of_buying);
            lines_or_text = itemView.findViewById(R.id.lines_or_text);
            lessLines_or_text = itemView.findViewById(R.id.lessLines_or_text);
            lessLines_or_text.setVisibility(View.GONE);
            lines_or_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    description.setMaxLines(10);
                    lessLines_or_text.setVisibility(View.VISIBLE);
                    lines_or_text.setVisibility(View.GONE);

                }
            });
            lessLines_or_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    description.setMaxLines(3);
                    lines_or_text.setVisibility(View.VISIBLE);
                    lessLines_or_text.setVisibility(View.GONE);

                }
            });
            try {
                linearLayout = itemView.findViewById(R.id.imageLayout);
                List<Integer> list = new ArrayList<>();
                list.add(R.drawable.img);
                list.add(R.drawable.img);
                list.add(R.drawable.img);
                list.add(R.drawable.img);
                for (int i =0;i<list.size();i++){
                    View view;
                    view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.rating_image_layout,linearLayout,false);
                    Rating_layout_image = view.findViewById(R.id.Rating_layout_image);
                    Rating_layout_image.setImageResource(list.get(i));

                    linearLayout.addView(view);
                }
            }catch (Exception e){

            }
        }
        private void setData(String rate,String Title,String Description,String name,String location,String date){
            rating.setText(rate);
            title.setText(Title);
            description.setText(Description);
            buyerName.setText(name);
            buyerLocation.setText(location);
            buyingDate.setText(date);
        }
    }
}
