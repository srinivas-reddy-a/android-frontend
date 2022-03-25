package com.akfrontend.arraykart.ProductDetailAboutListing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akfrontend.arraykart.AllApiModels.ProductDetailPageRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductTableDetailPage extends AppCompatActivity {
    private ImageView back_productTableDetail_page;
    private TextView technical_name,description,target_disease,target_field_crops,
            target_vegetable_crops,target_fruit_crops,target_plantation_crops,mode_of_action,
            duration_of_effect,compatability_with_other_chemicals,frequency_of_application,dosage,water_requirement,
            time_of_application,method_of_application,waiting_period,label_on_product,storage,country_of_origin,shelf_life,volume,
            dimensions,state,category,inventory_id,brand;

    List<ProductDetailPageModel> product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_table_detail_page);

        String id = getIntent().getStringExtra("id");

        back_productTableDetail_page= findViewById(R.id.back_productTableDetail_page);
        technical_name = findViewById(R.id.technical_name);
        description = findViewById(R.id.description);
        target_disease = findViewById(R.id.target_disease);
        target_field_crops = findViewById(R.id.target_field_crops);
        target_vegetable_crops = findViewById(R.id.target_vegetable_crops);
        target_fruit_crops = findViewById(R.id.target_fruit_crops);
        target_plantation_crops = findViewById(R.id.target_plantation_crops);
        mode_of_action = findViewById(R.id.mode_of_action);
        duration_of_effect = findViewById(R.id.duration_of_effect);
        compatability_with_other_chemicals = findViewById(R.id.compatability_with_other_chemicals);
        frequency_of_application = findViewById(R.id.frequency_of_application);
        dosage = findViewById(R.id.dosage);
        water_requirement = findViewById(R.id.water_requirement);
        time_of_application = findViewById(R.id.time_of_application);
        method_of_application = findViewById(R.id.method_of_application);
        waiting_period = findViewById(R.id.waiting_period);
        storage = findViewById(R.id.storage);
        label_on_product = findViewById(R.id.label_on_product);
        country_of_origin = findViewById(R.id.country_of_origin);
        shelf_life = findViewById(R.id.shelf_life);
        volume = findViewById(R.id.volume);
        dimensions = findViewById(R.id.dimensions);
        state = findViewById(R.id.state);
        category = findViewById(R.id.category);
        inventory_id = findViewById(R.id.inventory_id);
        brand = findViewById(R.id.brand);

        try{
            back_productTableDetail_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        String url = "/api/product/"+id;
        Call<ProductDetailPageRespones> CallDetail = RetrofitClient.getInstance().getApi().getDetail(url);
        CallDetail.enqueue(new Callback<ProductDetailPageRespones>() {
            @Override
            public void onResponse(Call<ProductDetailPageRespones> call, Response<ProductDetailPageRespones> response) {

                try {
                    product = response.body().getProduct();
                    technical_name.setText(product.get(0).getTechnical_name());
                    description.setText(product.get(0).getDescription());
                    target_disease.setText(product.get(0).getTarget_disease());
                    target_field_crops.setText(product.get(0).getTarget_field_crops());
                    target_vegetable_crops.setText(product.get(0).getTarget_vegetable_crops());
                    target_fruit_crops.setText(product.get(0).getTarget_fruit_crops());
                    target_plantation_crops.setText(product.get(0).getTarget_plantation_crops());
                    mode_of_action.setText(product.get(0).getMode_of_action());
                    duration_of_effect.setText(product.get(0).getDuration_of_effect());
                    compatability_with_other_chemicals.setText(product.get(0).getCompatability_with_other_chemicals());
                    frequency_of_application.setText(product.get(0).getFrequency_of_application());
                    dosage.setText(product.get(0).getDosage());
                    water_requirement.setText(product.get(0).getWater_requirement());
                    time_of_application.setText(product.get(0).getTime_of_applicationid());
                    method_of_application.setText(product.get(0).getMethod_of_application());
                    waiting_period.setText(product.get(0).getWaiting_period());
                    storage.setText(product.get(0).getStorage());
                    label_on_product.setText(product.get(0).getLabel_on_product());
                    country_of_origin.setText(product.get(0).getCountry_of_origin());
                    shelf_life.setText(product.get(0).getShelf_life());
                    volume.setText(product.get(0).getVolume());
                    dimensions.setText(product.get(0).getDimensions());
                    state.setText(product.get(0).getState());
                    category.setText(product.get(0).getCategory());
                    inventory_id.setText(product.get(0).getInventory_id());
                    brand.setText(product.get(0).getBrand());

                }catch (Exception e){
                    Toast.makeText(ProductTableDetailPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductDetailPageRespones> call, Throwable t) {
                Toast.makeText(ProductTableDetailPage.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}