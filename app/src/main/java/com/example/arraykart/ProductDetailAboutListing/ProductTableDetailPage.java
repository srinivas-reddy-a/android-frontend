package com.example.arraykart.ProductDetailAboutListing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arraykart.AllApiModels.ProductDetailPageRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.ProductDetailActivity;
import com.example.arraykart.R;

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
            dimensions,state,category,inventory_id,brand,sowing_time,seed_Rate,maturity_duration,colour,usp,NumberofSeeds;

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
        volume = findViewById(R.id.volume);
        dimensions = findViewById(R.id.dimensions);
        state = findViewById(R.id.state);
        category = findViewById(R.id.category);
        inventory_id = findViewById(R.id.inventory_id);
        brand = findViewById(R.id.brand);
        sowing_time = findViewById(R.id.sowing_time);
        seed_Rate = findViewById(R.id.seed_Rate);
        maturity_duration = findViewById(R.id.maturity_duration);
        colour = findViewById(R.id.colour);
        usp = findViewById(R.id.usp);
        NumberofSeeds = findViewById(R.id.NumberofSeeds);

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
                    if(product.get(0).getTechnical_name().contains("NA") || product.get(0).getTechnical_name().equals(null) || product.get(0).getTechnical_name().isEmpty()){
                        findViewById(R.id.technical_name_text).setVisibility(View.GONE);
                        findViewById(R.id.technical_name_view).setVisibility(View.GONE);
                        technical_name.setVisibility(View.GONE);
                    }else {
                        technical_name.setText(product.get(0).getTechnical_name());
                    }
                    if(product.get(0).getDescription().contains("NA") || product.get(0).getDescription().equals(null) || product.get(0).getDescription().isEmpty()){
                        findViewById(R.id.description_text).setVisibility(View.GONE);
                        findViewById(R.id.description_view).setVisibility(View.GONE);
                        description.setVisibility(View.GONE);
                    }else {
                        description.setText(product.get(0).getDescription());
                    }
                    if(product.get(0).getTarget_disease().contains("NA") || product.get(0).getTarget_disease().equals(null) || product.get(0).getTarget_disease().isEmpty()){
                        findViewById(R.id.target_disease_text).setVisibility(View.GONE);
                        findViewById(R.id.target_disease_view).setVisibility(View.GONE);
                        target_disease.setVisibility(View.GONE);
                    }else {
                        target_disease.setText(product.get(0).getTarget_disease());
                    }
                    if(product.get(0).getTarget_field_crops().contains("NA") || product.get(0).getTarget_field_crops().equals(null) || product.get(0).getTarget_field_crops().isEmpty()){
                        findViewById(R.id.target_field_crops_text).setVisibility(View.GONE);
                        findViewById(R.id.target_field_crops_view).setVisibility(View.GONE);
                        target_field_crops.setVisibility(View.GONE);
                    }else {
                        target_field_crops.setText(product.get(0).getTarget_field_crops());
                    }
                    if(product.get(0).getTarget_vegetable_crops().contains("NA") || product.get(0).getTarget_vegetable_crops().equals(null) || product.get(0).getTarget_vegetable_crops().isEmpty()){
                        findViewById(R.id.target_vegetable_crops_text).setVisibility(View.GONE);
                        findViewById(R.id.target_vegetable_crops_view).setVisibility(View.GONE);
                        target_vegetable_crops.setVisibility(View.GONE);
                    }else {
                        target_vegetable_crops.setText(product.get(0).getTarget_vegetable_crops());
                    }
                    if(product.get(0).getTarget_fruit_crops().contains("NA") || product.get(0).getTarget_fruit_crops().equals(null) || product.get(0).getTarget_fruit_crops().isEmpty()){
                        findViewById(R.id.target_fruit_crops_text).setVisibility(View.GONE);
                        findViewById(R.id.target_fruit_crops_view).setVisibility(View.GONE);
                        target_fruit_crops.setVisibility(View.GONE);
                    }else {
                        target_fruit_crops.setText(product.get(0).getTarget_fruit_crops());
                    }
                    if(product.get(0).getTarget_plantation_crops().contains("NA") || product.get(0).getTarget_plantation_crops().equals(null) || product.get(0).getTarget_plantation_crops().isEmpty()){
                        findViewById(R.id.target_plantation_crops_text).setVisibility(View.GONE);
                        findViewById(R.id.target_plantation_crops_view).setVisibility(View.GONE);
                        target_plantation_crops.setVisibility(View.GONE);
                    }else {
                        target_plantation_crops.setText(product.get(0).getTarget_plantation_crops());
                    }
                    if(product.get(0).getMode_of_action().contains("NA") || product.get(0).getMode_of_action().equals(null) || product.get(0).getMode_of_action().isEmpty()){
                        findViewById(R.id.mode_of_action_text).setVisibility(View.GONE);
                        findViewById(R.id.mode_of_action_view).setVisibility(View.GONE);
                        mode_of_action.setVisibility(View.GONE);
                    }else {
                        mode_of_action.setText(product.get(0).getMode_of_action());
                    }
                    if(product.get(0).getDuration_of_effect().contains("NA") || product.get(0).getDuration_of_effect().equals(null) || product.get(0).getDuration_of_effect().isEmpty()){
                        findViewById(R.id.duration_of_effect_text).setVisibility(View.GONE);
                        findViewById(R.id.duration_of_effect_view).setVisibility(View.GONE);
                        duration_of_effect.setVisibility(View.GONE);
                    }else {
                        duration_of_effect.setText(product.get(0).getDuration_of_effect());
                    }
                    if(product.get(0).getCompatability_with_other_chemicals().contains("NA") || product.get(0).getCompatability_with_other_chemicals().equals(null) || product.get(0).getCompatability_with_other_chemicals().isEmpty()){
                        findViewById(R.id.compatability_with_other_chemicals_text).setVisibility(View.GONE);
                        findViewById(R.id.compatability_with_other_chemicals_view).setVisibility(View.GONE);
                        compatability_with_other_chemicals.setVisibility(View.GONE);
                    }else {
                        compatability_with_other_chemicals.setText(product.get(0).getCompatability_with_other_chemicals());
                    }
                    if(product.get(0).getFrequency_of_application().contains("NA") || product.get(0).getFrequency_of_application().equals(null) || product.get(0).getFrequency_of_application().isEmpty()){
                        findViewById(R.id.frequency_of_application_text).setVisibility(View.GONE);
                        findViewById(R.id.frequency_of_application_view).setVisibility(View.GONE);
                        frequency_of_application.setVisibility(View.GONE);
                    }else {
                        frequency_of_application.setText(product.get(0).getFrequency_of_application());
                    }
                    if(product.get(0).getDosage().contains("NA") || product.get(0).getDosage().equals(null) || product.get(0).getDosage().isEmpty()){
                        findViewById(R.id.dosage_text).setVisibility(View.GONE);
                        findViewById(R.id.dosage_view).setVisibility(View.GONE);
                        dosage.setVisibility(View.GONE);
                    }else {
                        dosage.setText(product.get(0).getDosage());
                    }
                    if(product.get(0).getWater_requirement().contains("NA") || product.get(0).getWater_requirement().equals(null) || product.get(0).getWater_requirement().isEmpty()){
                        findViewById(R.id.water_requirement_text).setVisibility(View.GONE);
                        findViewById(R.id.water_requirement_view).setVisibility(View.GONE);
                        water_requirement.setVisibility(View.GONE);
                    }else {
                        water_requirement.setText(product.get(0).getWater_requirement());
                    }
                    if(product.get(0).getTime_of_application().contains("NA") || product.get(0).getTime_of_application().equals(null) || product.get(0).getTime_of_application().isEmpty()){
                        findViewById(R.id.time_of_application_text).setVisibility(View.GONE);
                        findViewById(R.id.time_of_application_view).setVisibility(View.GONE);
                        time_of_application.setVisibility(View.GONE);
                    }else {
                        time_of_application.setText(product.get(0).getTime_of_application());
                    }
                    if(product.get(0).getMethod_of_application().contains("NA") || product.get(0).getMethod_of_application().equals(null) || product.get(0).getMethod_of_application().isEmpty()){
                        findViewById(R.id.method_of_application_text).setVisibility(View.GONE);
                        findViewById(R.id.method_of_application_view).setVisibility(View.GONE);
                        method_of_application.setVisibility(View.GONE);
                    }else {
                        method_of_application.setText(product.get(0).getMethod_of_application());
                    }
                    if(product.get(0).getWaiting_period().contains("NA") || product.get(0).getWaiting_period().equals(null) || product.get(0).getWaiting_period().isEmpty()){
                        findViewById(R.id.waiting_period_text).setVisibility(View.GONE);
                        findViewById(R.id.waiting_period_view).setVisibility(View.GONE);
                        waiting_period.setVisibility(View.GONE);
                    }else {
                        waiting_period.setText(product.get(0).getWaiting_period());
                    }
                    if(product.get(0).getStorage().contains("NA") || product.get(0).getStorage().equals(null) || product.get(0).getStorage().isEmpty()){
                        findViewById(R.id.storage_text).setVisibility(View.GONE);
                        findViewById(R.id.storage_view).setVisibility(View.GONE);
                        storage.setVisibility(View.GONE);
                    }else {
                        storage.setText(product.get(0).getStorage());
                    }
                    if(product.get(0).getPhytotoxicity().contains("NA") || product.get(0).getPhytotoxicity().equals(null) || product.get(0).getPhytotoxicity().isEmpty()){
                        findViewById(R.id.label_on_product_text).setVisibility(View.GONE);
                        findViewById(R.id.label_on_product_view).setVisibility(View.GONE);
                        label_on_product.setVisibility(View.GONE);
                    }else {
                        label_on_product.setText(product.get(0).getPhytotoxicity());
                    }
                    if(product.get(0).getCountry_of_origin().contains("NA") || product.get(0).getCountry_of_origin().equals(null) || product.get(0).getCountry_of_origin().isEmpty()){
                        findViewById(R.id.country_of_origin_text).setVisibility(View.GONE);
                        findViewById(R.id.country_of_origin_view).setVisibility(View.GONE);
                        country_of_origin.setVisibility(View.GONE);
                    }else {
                        country_of_origin.setText(product.get(0).getCountry_of_origin());
                    }
                    if(product.get(0).getVolume().contains("NA") || product.get(0).getVolume().equals(null) || product.get(0).getVolume().isEmpty()){
                        findViewById(R.id.volume_text).setVisibility(View.GONE);
                        findViewById(R.id.volume_view).setVisibility(View.GONE);
                        volume.setVisibility(View.GONE);
                    }else {
                        volume.setText(product.get(0).getVolume());
                    }
                    if(product.get(0).getDimensions().contains("NA") || product.get(0).getDimensions().equals(null) || product.get(0).getDimensions().isEmpty()){
                        findViewById(R.id.dimensions_text).setVisibility(View.GONE);
                        findViewById(R.id.dimensions_view).setVisibility(View.GONE);
                        dimensions.setVisibility(View.GONE);
                    }else {
                        dimensions.setText(product.get(0).getDimensions());
                    }
                    if(product.get(0).getState().contains("NA") || product.get(0).getState().equals(null) || product.get(0).getState().isEmpty()){
                        findViewById(R.id.state_text).setVisibility(View.GONE);
                        findViewById(R.id.state_view).setVisibility(View.GONE);
                        state.setVisibility(View.GONE);
                    }else {
                        state.setText(product.get(0).getState());
                    }
                    if(product.get(0).getCategory().contains("NA") || product.get(0).getCategory().equals(null) || product.get(0).getCategory().isEmpty()){
                        findViewById(R.id.category_text).setVisibility(View.GONE);
                        findViewById(R.id.category_view).setVisibility(View.GONE);
                        category.setVisibility(View.GONE);
                    }else {
                        category.setText(product.get(0).getCategory());
                    }
                    if(product.get(0).getInventory_id().contains("NA") || product.get(0).getInventory_id().equals(null) || product.get(0).getInventory_id().isEmpty()){
                        findViewById(R.id.inventory_id_text).setVisibility(View.GONE);
                        findViewById(R.id.inventory_id_view).setVisibility(View.GONE);
                        inventory_id.setVisibility(View.GONE);
                    }else {
                        inventory_id.setText(product.get(0).getInventory_id());
                    }
                    if(product.get(0).getBrand().contains("NA") || product.get(0).getBrand().equals(null) || product.get(0).getBrand().isEmpty()){
                        findViewById(R.id.brand_text).setVisibility(View.GONE);
                        findViewById(R.id.brand_view).setVisibility(View.GONE);
                        brand.setVisibility(View.GONE);
                    }else {
                        brand.setText(product.get(0).getBrand());
                    }
                    if(product.get(0).getSowing_time().contains("NA") || product.get(0).getSowing_time().equals(null) || product.get(0).getSowing_time().isEmpty()){
                        findViewById(R.id.sowing_time_text).setVisibility(View.GONE);
                        findViewById(R.id.sowing_time_view).setVisibility(View.GONE);
                        sowing_time.setVisibility(View.GONE);
                    }else {
                        sowing_time.setText(product.get(0).getSowing_time());
                    }
                    if(product.get(0).getSeed_Rate().contains("NA") || product.get(0).getSeed_Rate().equals(null) || product.get(0).getSeed_Rate().isEmpty()){
                        findViewById(R.id.seed_Rate_text).setVisibility(View.GONE);
                        findViewById(R.id.seed_Rate_view).setVisibility(View.GONE);
                        seed_Rate.setVisibility(View.GONE);
                    }else {
                        seed_Rate.setText(product.get(0).getSeed_Rate());
                    }
                    if(product.get(0).getMaturity_duration().contains("NA") || product.get(0).getMaturity_duration().equals(null) || product.get(0).getMaturity_duration().isEmpty()){
                        findViewById(R.id.maturity_duration_text).setVisibility(View.GONE);
                        findViewById(R.id.maturity_duration_view).setVisibility(View.GONE);
                        maturity_duration.setVisibility(View.GONE);
                    }else {
                        maturity_duration.setText(product.get(0).getMaturity_duration());
                    }
                    if(product.get(0).getColour().contains("NA") || product.get(0).getColour().equals(null) || product.get(0).getColour().isEmpty()){
                        findViewById(R.id.colour_text).setVisibility(View.GONE);
                        findViewById(R.id.colour_view).setVisibility(View.GONE);
                        colour.setVisibility(View.GONE);
                    }else {
                        colour.setText(product.get(0).getColour());
                    }
                    if(product.get(0).getUsp().contains("NA") || product.get(0).getUsp().equals(null) || product.get(0).getUsp().isEmpty()){
                        findViewById(R.id.usp_text).setVisibility(View.GONE);
                        findViewById(R.id.usp_view).setVisibility(View.GONE);
                        usp.setVisibility(View.GONE);
                    }else {
                        usp.setText(product.get(0).getUsp());
                    }
                    if(product.get(0).getNumberOfSeeds().contains("NA") || product.get(0).getNumberOfSeeds().equals(null) || product.get(0).getNumberOfSeeds().isEmpty()){
                        findViewById(R.id.NumberofSeeds_text).setVisibility(View.GONE);
                        findViewById(R.id.NumberofSeeds_view).setVisibility(View.GONE);
                        NumberofSeeds.setVisibility(View.GONE);
                    }else {
                        NumberofSeeds.setText(product.get(0).getNumberOfSeeds());
                    }

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