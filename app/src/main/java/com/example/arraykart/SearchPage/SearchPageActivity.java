package com.example.arraykart.SearchPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.R;

import java.util.ArrayList;

public class SearchPageActivity extends AppCompatActivity implements SearchAdapter.OnRecentSearchListener {
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private ArrayList<String> recentSearches;
    private EditText searchPageET;
    private ImageView searchPageBackIV , Search;
    SharedPrefManager sharedPrefManager;
    private  EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        edt = findViewById(R.id.searchPageET);
        sharedPrefManager= new SharedPrefManager(this);
        Search = findViewById(R.id.SearchPP);

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

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((edt.getText().toString().equals(""))) {
                    Toast.makeText(
                            getBaseContext(),
                            "Whoa! You haven't entered anything in the search box.",
                            Toast.LENGTH_SHORT).show();

                }
//                else {
//
//                    String text = edt.getText().toString();
//                   sharedPrefManager.setValue_list("search",text);
//                }
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