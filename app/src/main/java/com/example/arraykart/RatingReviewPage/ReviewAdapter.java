package com.example.arraykart.RatingReviewPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rating = itemView.findViewById(R.id.rateGrid);
            title= itemView.findViewById(R.id.ReviewTitle);
            description = itemView.findViewById(R.id.ReviewDescription);
            buyerName= itemView.findViewById(R.id.name_of_buyer);
            buyerLocation = itemView.findViewById(R.id.location_of_buyer);
            buyingDate = itemView.findViewById(R.id.date_of_buying);
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
