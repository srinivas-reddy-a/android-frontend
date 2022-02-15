package com.example.arraykart.homeCategoryProduct.moreProductCategory;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.arraykart.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    List<MoreCotegoryModel> moreCotegoryModels;
    RecyclerView.ViewHolder vh = null;


    public MyAdapter(Context context, List<MoreCotegoryModel> moreCotegoryModels) {
        this.context = context;
        this.moreCotegoryModels = moreCotegoryModels;
    }

     public class ViewHolder {

        public TextView tvName;
        public TextView tvDescription;
        public ImageView img ;


    }

    @Override
    public int getCount() {
        return moreCotegoryModels.size();
    }

    @Override
    public Object getItem(int position) {
        return moreCotegoryModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View More_item ;
        ViewHolder holder;

        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        More_item = layoutInflater.inflate(R.layout.products_category_morepage_item, parent, false);

        holder = new ViewHolder();

        holder.img = More_item.findViewById(R.id.imageView);
        holder.tvName = More_item.findViewById(R.id.textView3);
        holder.tvDescription = More_item.findViewById(R.id.textView4);

        More_item.setTag(holder);


        holder = (ViewHolder) More_item.getTag();

        Glide.with(context.getApplicationContext())
                .load(moreCotegoryModels.get(position).getImage())
                .placeholder(R.drawable.categories)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
        holder.tvName.setText(moreCotegoryModels.get(position).getName());
        holder.tvDescription.setText(moreCotegoryModels.get(position).getDescription());



        return More_item;
    }
}
