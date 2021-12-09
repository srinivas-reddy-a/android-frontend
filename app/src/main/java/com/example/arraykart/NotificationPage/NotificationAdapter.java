package com.example.arraykart.NotificationPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;

import java.util.List;

public class NotificationAdapter extends  RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    private List<NotificationModel> notificationModelList ;

    public NotificationAdapter(List<NotificationModel> notificationModelList) {
        this.notificationModelList = notificationModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int image = notificationModelList.get(position).getNotiImage();
        String btv=notificationModelList.get(position).getBoldText();
        String ntv=notificationModelList.get(position).getNormalText();

        holder.setData(image,btv,ntv);

    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image ;
        private TextView tv1;
        private TextView tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.notifi_image);
            tv1 =itemView.findViewById(R.id.notifi_boldTv);
            tv2 = itemView.findViewById(R.id.notifi_normalTv);

        }
        private void setData(int images,String tv , String ntv){
            image.setImageResource(images);
            tv1.setText(tv);
            tv2.setText(ntv);
        }
    }
}
