package com.akfrontend.arraykart.NotificationPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akfrontend.arraykart.R;

import java.util.List;

public class NotificationAdapter extends  RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    private List<NotificationModel> notificationModelList ;

    private OnItemClickListener nListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        nListener=listener;
    }


    public NotificationAdapter(List<NotificationModel> notificationModelList) {
        this.notificationModelList = notificationModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_layout,parent,false);

        return new ViewHolder(view,nListener);
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
        private ImageView notification_delete;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            image= itemView.findViewById(R.id.notifi_image);
            tv1 =itemView.findViewById(R.id.notifi_boldTv);
            tv2 = itemView.findViewById(R.id.notifi_normalTv);
            notification_delete= itemView.findViewById(R.id.notification_delete);
            try {
                notification_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onDeleteClick(position);
                            }
                        }

                    }
                });
            }catch (Exception e){

            }

        }
        private void setData(int images,String tv , String ntv){
            image.setImageResource(images);
            tv1.setText(tv);
            tv2.setText(ntv);
        }
    }
}
