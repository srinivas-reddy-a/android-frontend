package com.akfrontend.arraykart.BannerSlider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.akfrontend.arraykart.R;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private List<SliderModel> sliderModellist;

    public SliderAdapter(List<SliderModel> sliderModellist) {
        this.sliderModellist = sliderModellist;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout,container,false);
        ImageView banner=view.findViewById(R.id.banner_slider);

        banner.setImageResource(sliderModellist.get(position).getImage());
        container.addView(view,0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return sliderModellist.size();
    }
}
