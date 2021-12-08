package com.example.arraykart.UserProfile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;
import com.example.arraykart.homeCategoryProduct.HAdapter;

import org.w3c.dom.Text;

import java.util.List;

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.ViewHolder> {

    private List<UserProfileModel> userProfileModelList;

    private OnItemClickListener nListener;

    public interface OnItemClickListener{
        void onClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        nListener=listener;
    }

    public UserProfileAdapter(List<UserProfileModel> userProfileModelList) {
        this.userProfileModelList = userProfileModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile_layout,parent,false);
       return  new ViewHolder(view,nListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = userProfileModelList.get(position).getPageName();
        String longText = userProfileModelList.get(position).getPageView();

        holder.setData(text,longText);

    }

    @Override
    public int getItemCount() {
        return userProfileModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pageName;
        private TextView pageView;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            pageName = itemView.findViewById(R.id.allPageUP);
            pageView = itemView.findViewById(R.id.PageViewUP);

            try {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onClickListener(position);
                            }
                        }

                    }
                });
            }catch (Exception e){

            }
        }
        public void setData(String text,String longText){
            pageName.setText(text);
            pageView.setText(longText);
        }
    }

}
