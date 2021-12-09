package com.example.arraykart.NotificationPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView notificationRecyclerView;
    private ImageView back_notification_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        try{
            notificationRecyclerView=findViewById(R.id.notificationRecyclerView);



            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            notificationRecyclerView.setLayoutManager(layoutManager);


            List<NotificationModel> notificationModelList = new ArrayList<>();
            notificationModelList.add(new NotificationModel(R.drawable.img,
                    "yuyigjgxccnccncnbmnvnvmvmvmmnnvnvnvvvvvvvbnbbnbnbnbnbnbnbnb","yobvusdj"));
            notificationModelList.add(new NotificationModel(R.drawable.img,
                    "yuyigjgxccnccncnbmnvnvmvmvmmnnvnvnvvvvvvvbnbbnbnbnbnbnbnbnb","yobvusdj"));
            notificationModelList.add(new NotificationModel(R.drawable.img,
                    "yuyigjgxccnccncnbmnvnvmvmvmmnnvnvnvvvvvvvbnbbnbnbnbnbnbnbnb","yobvusdj"));

            NotificationAdapter notificationAdapter= new NotificationAdapter(notificationModelList);
            notificationRecyclerView.setAdapter(notificationAdapter);
            notificationAdapter.notifyDataSetChanged();

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

    }
}