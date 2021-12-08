package com.example.arraykart.SearchPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.arraykart.R;

import java.util.ArrayList;

public class SearchPageActivity extends AppCompatActivity implements SearchAdapter.OnRecentSearchListener {
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private ArrayList<String> recentSearches;
    private EditText searchPageET;
    private ImageView searchPageBackIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        recentSearches = new ArrayList<>();
        recentSearches.add("pesticide");
        recentSearches.add("url");
        recentSearches.add("yara");
        recentSearches.add("pest");
        recentSearches.add("seeds");



        recyclerView = findViewById(R.id.searchPageRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(this, recentSearches);
        recyclerView.setAdapter(searchAdapter);
        searchPageBackIV = findViewById(R.id.searchPageBackIV);
        searchPageBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onRecentSearchListener(Intent intent) {
        String recentSearch = intent.getStringExtra("recentSearch");
        searchPageET = findViewById(R.id.searchPageET);
        searchPageET.setText(recentSearch);
        searchPageET.setSelection(recentSearch.length());
    }
}