package com.example.arraykart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.arraykart.homeCategoryProduct.HAdapter;
import com.example.arraykart.homeCategoryProduct.MainModel;
import com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct.ItemsForSingleProduct;
import com.example.arraykart.homeCategoryProduct.moreProductCategory.moreCategoryProducts;
import com.example.arraykart.ui.BottomNotificationFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.databinding.ActivityHomeNavigationBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeNavigationActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeNavigationBinding binding;
    private MeowBottomNavigation meowBottomNavigation;

    //homePageCategoryProductItem

    RecyclerView recyclerView;
    HAdapter hAdapter;
    ArrayList<MainModel> maiModel;

    //homePageCategoryProductItem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHomeNavigation.toolbar);
        binding.appBarHomeNavigation.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        meowBottomNavigation = findViewById(R.id.bottom_navigation);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_agriculture_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_home_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_account_circle_24));

        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
//                        fragment = new BottomNotificationFragment();
                        break;
                    case 2:
//                        fragment = new BottomNotificationFragment();
                        break;
                    case 3:
//                        fragment = new BottomNotificationFragment();
                        break;

                }
//                loadFragment(fragment);
            }
        });

        meowBottomNavigation.show(2, true);

        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                if(item.getId()!=2){
                    Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                }

            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                if(item.getId()!=2){
                    Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //homePageCategoryProductItem

        recyclerView=findViewById(R.id.recyclerView);

        int[] imgs ={R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
        String[] name = {"name","name","name","name"};
        String[] price ={"price","price","price","price"};

        maiModel = new ArrayList<>();
        try {
            for (int i = 0; i < name.length; i++) {
                MainModel model = new MainModel(name[i], price[i], imgs[i]);
                maiModel.add(model);
            }
        }catch(Exception e){

        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeNavigationActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        hAdapter = new HAdapter(this,maiModel);
        recyclerView.setAdapter(hAdapter);

//      this helps click on every item present in home_products_category and open new activity of all item_product
        try {
            hAdapter.setOnItemClickListener(new HAdapter.OnItemClickListener() {
                @Override
                public void onClickListener(int position) {
                    for (int i = 0; i < imgs.length; i++) {
                        if (position == i) {
                            startActivity(new Intent(HomeNavigationActivity.this, ItemsForSingleProduct.class));
                        }
                    }
                }
            });

        }catch(Exception e){

        }



        ImageSlider imageSlider = findViewById(R.id.imageSlider2);
        List<SlideModel> slideModelList = new ArrayList<>();
        slideModelList.add(new SlideModel(R.drawable.offer));
        slideModelList.add(new SlideModel(R.drawable.offer));
        slideModelList.add(new SlideModel(R.drawable.offer));
        slideModelList.add(new SlideModel(R.drawable.offer));
        slideModelList.add(new SlideModel(R.drawable.offer));

        imageSlider.setImageList(slideModelList, true);


        LinearLayout brandsv = findViewById(R.id.brandsv);
        View v = getLayoutInflater().inflate(R.layout.home_brand, null);
        ImageView isv = v.findViewById(R.id.imageView2);
        isv.setImageResource(R.drawable.img);
        TextView tsv = v.findViewById(R.id.textView2);
        tsv.setText("allo");
        brandsv.addView(v);
        brandsv.addView(v);
        brandsv.addView(v);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout, fragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //button for MoreCategoryProduct

    public void MoreItem(View view){
        Intent in = new Intent(HomeNavigationActivity.this, moreCategoryProducts.class);
        startActivity(in);
    }


}