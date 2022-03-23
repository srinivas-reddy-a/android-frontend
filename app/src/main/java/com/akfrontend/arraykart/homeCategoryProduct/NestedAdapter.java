package com.akfrontend.arraykart.homeCategoryProduct;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akfrontend.arraykart.AllApiModels.ProductsRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.R;
import com.akfrontend.arraykart.homeCategoryProduct.allItemOfSingleProduct.ItemsForSingleProduct;
import com.akfrontend.arraykart.homeCategoryProduct.moreProductCategory.MoreCotegoryModel;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool
            viewPool
            = new RecyclerView
            .RecycledViewPool();
    private List<MoreCotegoryModel> nestedModelss;
    private List<MainModel> mainModels;

    Context context;

    public NestedAdapter(List<MoreCotegoryModel> nestedModelss,Context context) {
        this.nestedModelss = nestedModelss;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_recyclerview_for_products,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MoreCotegoryModel nestedModel = nestedModelss.get(position);

        holder.nested_category_name.setText(nestedModel.getName());

        String name = nestedModel.getName();

        holder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,ItemsForSingleProduct.class);
                in.putExtra("name",name);
                context.startActivity(in);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                holder.nested_recyclerview.getContext(),LinearLayoutManager.HORIZONTAL,false
        );

//        linearLayoutManager.setInitialPrefetchItemCount(
//                nestedModel.size()
//        );

//        HAdapter hAdapter = new HAdapter(context,nestedModel.getProducts());
//
//        holder.nested_recyclerview.setLayoutManager(linearLayoutManager);
//
//        holder.nested_recyclerview.setAdapter(hAdapter);
//
//        holder.nested_recyclerview.setRecycledViewPool(viewPool);


        if(name != null) {
            String url = "/api/product/category/filter/product/" + name + "/?limit=8";
            Call<ProductsRespones> call = RetrofitClient.getInstance().getApi().getNestedCategory(url);
            call.enqueue(new Callback<ProductsRespones>() {
                @Override
                public void onResponse(Call<ProductsRespones> call, Response<ProductsRespones> response) {

                    if (response.isSuccessful()) {
                        mainModels = response.body().getProducts();
                        HAdapter hAdapter = new HAdapter(context, mainModels);
                        holder.nested_recyclerview.setLayoutManager(linearLayoutManager);
                        holder.nested_recyclerview.setAdapter(hAdapter);
                        holder.nested_recyclerview.setRecycledViewPool(viewPool);

                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ProductsRespones> call, Throwable t) {

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return nestedModelss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nested_category_name , moreButton;
        private RecyclerView nested_recyclerview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nested_category_name = itemView.findViewById(R.id.nested_category_name);
            nested_recyclerview = itemView.findViewById(R.id.nested_recyclerview);
            moreButton = itemView.findViewById(R.id.moreButton);


        }
    }
}
