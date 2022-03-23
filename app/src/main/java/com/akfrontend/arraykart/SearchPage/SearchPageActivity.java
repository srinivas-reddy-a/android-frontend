package com.akfrontend.arraykart.SearchPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akfrontend.arraykart.AllApiModels.SearchProducRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.AllRetrofit.SharedPrefManager;
import com.akfrontend.arraykart.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPageActivity extends AppCompatActivity implements SearchAdapter.OnRecentSearchListener{//implements SearchAdapter.OnRecentSearchListener
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private ArrayList<String> recentSearches;
    private List<SearchProductModel> searchProductModels;
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

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchFilter(s.toString());
            }
        });



        searchPageBackIV = findViewById(R.id.searchPageBackIV);
        searchPageBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == event.ACTION_DOWN &&  keyCode == KeyEvent.KEYCODE_ENTER){
                    if ((edt.getText().toString().equals(""))) {
                        Toast.makeText(
                                getBaseContext(),
                                "Whoa! You haven't entered anything in the search box.",
                                Toast.LENGTH_SHORT).show();

                    }
                    else {
//
//                    String text = edt.getText().toString();
//                   sharedPrefManager.setValue_list("search",text);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        String p = edt.getText().toString();
                        String url = "/api/product/?search="+p;

                        Call<SearchProducRespones> call = RetrofitClient.getInstance().getApi().getSearchProduct(url);
                        call.enqueue(new Callback<SearchProducRespones>() {
                            @Override
                            public void onResponse(Call<SearchProducRespones> call, Response<SearchProducRespones> response) {
                                try{
                                    searchProductModels = response.body().getProducts();
                                    SearchProductAdapter searchProductAdapter = new SearchProductAdapter(searchProductModels,getApplicationContext());
                                    recyclerView.setAdapter(searchProductAdapter);
                                    findViewById(R.id.textForSearch).setVisibility(View.VISIBLE);
                                    TextView txt = findViewById(R.id.textForSearch);
                                    txt.setText("search for "+ p);
                                }catch (Exception e){
                                    Toast.makeText(SearchPageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<SearchProducRespones> call, Throwable t) {

                            }
                        });
                    }
                    return true;
                }
                return false;
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
                else {
//
//                    String text = edt.getText().toString();
//                   sharedPrefManager.setValue_list("search",text);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    String p = edt.getText().toString();
                    String url = "/api/product/?search="+p;

                    Call<SearchProducRespones> call = RetrofitClient.getInstance().getApi().getSearchProduct(url);
                    call.enqueue(new Callback<SearchProducRespones>() {
                        @Override
                        public void onResponse(Call<SearchProducRespones> call, Response<SearchProducRespones> response) {
                            try{
                                searchProductModels = response.body().getProducts();
                                SearchProductAdapter searchProductAdapter = new SearchProductAdapter(searchProductModels,getApplicationContext());
                                recyclerView.setAdapter(searchProductAdapter);
                            }catch (Exception e){
                                Toast.makeText(SearchPageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SearchProducRespones> call, Throwable t) {

                        }
                    });
                }
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

    /// search filter
    private void searchFilter(String text){
        ArrayList<String> filteredList = new ArrayList<>();
        for(String item : recentSearches){
            if(item.toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        searchAdapter.filterList(filteredList);
    }

}