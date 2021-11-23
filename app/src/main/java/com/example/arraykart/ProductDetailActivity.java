package com.example.arraykart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ProductDetailActivity extends AppCompatActivity {

    CarouselView carouselView;
    TextView striketextView;
    int[] sampleImages = {R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        carouselView = findViewById(R.id.carouselViewProductDetail);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        striketextView = findViewById(R.id.textView7);
        striketextView.setPaintFlags(striketextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        ImageView backiv = findViewById(R.id.back_all_products);
        backiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}