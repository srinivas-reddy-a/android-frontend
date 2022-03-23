package com.akfrontend.arraykart.NotificationPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akfrontend.arraykart.R;
import com.akfrontend.arraykart.SearchPage.SearchPageActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView notificationRecyclerView;
    private ImageView back_notification_page;
    private ImageView notification_delete;
    private NotificationAdapter notificationAdapter;
    private List<NotificationModel> notificationModelList;
    private TextView searchNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        try{
            notificationRecyclerView=findViewById(R.id.notificationRecyclerView);



            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            notificationRecyclerView.setLayoutManager(layoutManager);


            notificationModelList = new ArrayList<>();
            if(notificationModelList == null){
//                Toast.makeText(this, "yoyo", Toast.LENGTH_SHORT).show();
//                findViewById(R.id.NoNotification).setVisibility(View.VISIBLE);
            }else {
                notificationAdapter = new NotificationAdapter(notificationModelList);
                notificationRecyclerView.setAdapter(notificationAdapter);
                notificationAdapter.notifyDataSetChanged();
            }

        }catch (Exception e){

        }

        try{
            back_notification_page = findViewById(R.id.back_notification_page);
            back_notification_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){

        }
        try{
            notificationAdapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
                @Override
                public void onDeleteClick(int position) {
                    notificationModelList.remove(position);
                    notificationAdapter.notifyItemRemoved(position);
                }
            });

        }catch (Exception e){

        }
        //searchview
        try {
            searchNotifications = findViewById(R.id.searchNotifications);
            searchNotifications.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(NotificationActivity.this, SearchPageActivity.class));
                }
            });
        }catch (Exception e){

        }

    }
}