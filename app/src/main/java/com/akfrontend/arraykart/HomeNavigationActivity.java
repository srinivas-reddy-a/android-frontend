package com.akfrontend.arraykart;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.akfrontend.arraykart.databinding.ActivityHomeNavigationBinding;
import com.akfrontend.arraykart.AllApiModels.BrandRespones;
import com.akfrontend.arraykart.AllApiModels.CategoryIdRespones;
import com.akfrontend.arraykart.AllApiModels.ProductsCategoryRespones;
import com.akfrontend.arraykart.AllApiModels.ProductsRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.AllRetrofit.SharedPrefManager;
import com.akfrontend.arraykart.BannerSlider.SliderAdapter;
import com.akfrontend.arraykart.BannerSlider.SliderModel;
import com.akfrontend.arraykart.Community.CommunityActivity;
import com.akfrontend.arraykart.MyCart.MYCartActivity;
import com.akfrontend.arraykart.NotificationPage.NotificationActivity;
import com.akfrontend.arraykart.R;
import com.akfrontend.arraykart.UserProfile.UserProfileActivity;
import com.akfrontend.arraykart.SearchPage.SearchPageActivity;
import com.akfrontend.arraykart.WishList.WishListActivity;
import com.akfrontend.arraykart.homeCategoryProduct.HAdapter;
import com.akfrontend.arraykart.homeCategoryProduct.HomeAllCategory.HomeAllCategoryAdapter;
import com.akfrontend.arraykart.homeCategoryProduct.MainModel;
import com.akfrontend.arraykart.homeCategoryProduct.NestedAdapter;
import com.akfrontend.arraykart.homeCategoryProduct.allItemOfSingleProduct.GridViewAdapter;
import com.akfrontend.arraykart.homeCategoryProduct.allItemOfSingleProduct.ItemsForSingleProduct;
import com.akfrontend.arraykart.homeCategoryProduct.allItemOfSingleProduct.ModelForSingleProduct;
import com.akfrontend.arraykart.homeCategoryProduct.moreProductCategory.MoreCotegoryModel;
import com.akfrontend.arraykart.homeCategoryProduct.moreProductCategory.moreCategoryProducts;
import com.akfrontend.arraykart.homeCategoryProduct.nestedModel;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
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
import androidx.viewpager.widget.ViewPager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeNavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeNavigationBinding binding;
    private MeowBottomNavigation meowBottomNavigation;

    private LocationRequest locationRequest;

    private LottieAnimationView lottieAnimationView;

    private ImageView notification_home_page;

    SharedPrefManager sharedPrefManager;

    //homePageCategoryProductItem

    private  RecyclerView recyclerView;
    private HAdapter hAdapter;
    private  List<MainModel> maiModel;
    private RecyclerView recyclerView1;
    private ImageView wishListHome;
    private TextView searchHome;


    //homePageCategoryProductItem

    private  List<MoreCotegoryModel> homeAllCategoryModels,homeAllCategoryModelsss;

    ///grid view on home page
    private GridView gridView2;
    //grid view on home page

    //banner slider on home page
    private ViewPager bannerSliderViewPager;

    private SliderAdapter sliderAdapter;
    private ArrayList<SliderModel> sliderModels;
    private int currentPage=2;
    private Timer timer;
    final private  long DELAY_TIME =3000;
    final private  long PERIOD_TIME = 3000;
    //banner slider on home page
    NavigationView navigationView;

    Menu optionsMenu;

    MenuItem home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getApplicationInfo().targetSdkVersion = 14;
        super.onCreate(savedInstanceState);

        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);

        sharedPrefManager = new SharedPrefManager(this);


        if(!user_token.contains("GPS")){
            checkPermission();
        }

        binding = ActivityHomeNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

          setSupportActionBar(binding.appBarHomeNavigation.toolbar);
//        binding.appBarHomeNavigation.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//
//            }
//        });

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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_wishList,
                R.id.nav_MyCart, R.id.nav_Settings,R.id.nav_SignUp,R.id.nav_LogOut,
                R.id.nav_HelpCenter,R.id.nav_PrivacyPolicy,R.id.nav_Legal,R.id.nav_Return_Policy)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

                meowBottomNavigation = findViewById(R.id.bottom_navigation);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_people_24));
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
                if(item.getId()==1){
                    if(user_token.contains("token")) {
                        startActivity(new Intent(HomeNavigationActivity.this, CommunityActivity.class));
                    }else {
                        startActivity(new Intent(HomeNavigationActivity.this, SignUP.class));
                    }
                }
                if(item.getId()==3){
                    if(user_token.contains("token")) {
                        startActivity(new Intent(HomeNavigationActivity.this, UserProfileActivity.class));
                    }else {
                        startActivity(new Intent(HomeNavigationActivity.this,SignUP.class));
                    }
                }

            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1){
                    if(user_token.contains("token")) {
                        startActivity(new Intent(HomeNavigationActivity.this, CommunityActivity.class));
                    }else {
                        startActivity(new Intent(HomeNavigationActivity.this, SignUP.class));
                    }
                }
                if(item.getId()==3){
                    if(user_token.contains("token")) {
                        startActivity(new Intent(HomeNavigationActivity.this, UserProfileActivity.class));
                    }else {
                        startActivity(new Intent(HomeNavigationActivity.this,SignUP.class));
                    }
                }
            }
        });

       //wishlist
        wishListHome = findViewById(R.id.wishListHome);
        wishListHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeNavigationActivity.this, WishListActivity.class));
            }
        });

        //homePageCategoryProductItem

        recyclerView=findViewById(R.id.recyclerView);
//        recyclerView1 = findViewById(R.id.recyclerView1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeNavigationActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        LinearLayoutManager layoutManagers = new LinearLayoutManager(HomeNavigationActivity.this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView1.setLayoutManager(layoutManagers);
//        recyclerView1.setItemAnimator(new DefaultItemAnimator());


        ///call for home products

        Call<ProductsRespones> call = RetrofitClient.getInstance().getApi().getProduct();

        call.enqueue(new Callback<ProductsRespones>() {
            @Override
            public void onResponse(Call<ProductsRespones> call, Response<ProductsRespones> response) {
                if(response.isSuccessful()){
                    maiModel = response.body().getProducts();
                    hAdapter = new HAdapter(getApplicationContext(), maiModel);
                    recyclerView.setAdapter(hAdapter);

                    /// this helps click on every item present in home_products_category and open new activity of all item_product

//                    try {
//                        hAdapter.setOnItemClickListener(new HAdapter.OnItemClickListener() {
//                            @Override
//                            public void onClickListener(int position) {
//                                Intent in = new Intent(HomeNavigationActivity.this, ProductDetailActivity.class);
//                                in.putExtra("id",maiModel.get(position).getId());
//                                in.putExtra("image",maiModel.get(position).getImage());
//                                startActivity(in);
//                            }
//                        });

//                    }catch(Exception e){
//
//                    }

//                    HAdapter hAdapters = new HAdapter(getApplicationContext(), maiModel);
//                    recyclerView1.setAdapter(hAdapters);
//                    try{
//                    hAdapters.setOnItemClickListener(new HAdapter.OnItemClickListener() {
//                            @Override
//                            public void onClickListener(int position) {
////                                for (int i = 0; i < maiModel.size(); i++) {
////                                    if (position == i-1) {
////                                        Intent in = new Intent(HomeNavigationActivity.this, ProductDetailActivity.class);
////                                        in.putExtra("id",maiModel.get(position).getId());
////                                        in.putExtra("image",maiModel.get(position).getImage());
////                                        startActivity(in);                                    }
////                                }
////                                if(position==maiModel.size()-1){
////                                    startActivity(new Intent(HomeNavigationActivity.this, moreCategoryProducts.class));
////                                }
//                                Intent in = new Intent(HomeNavigationActivity.this, ProductDetailActivity.class);
//                                in.putExtra("id",maiModel.get(position).getId());
//                                in.putExtra("image",maiModel.get(position).getImage());
//                                startActivity(in);
//                            }
//                        });
//
//                    }catch(Exception e){
//
//                    }


                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(HomeNavigationActivity.this, jsonObject.getString("err"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(HomeNavigationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsRespones> call, Throwable t) {
                Toast.makeText(HomeNavigationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        ///1st grid view for home page


        gridView2 = findViewById(R.id.gridView2);
        String url = "/api/product/?category=Adjuvants";
        Call<CategoryIdRespones> callG = RetrofitClient.getInstance().getApi().getCategory(url);
        callG.enqueue(new Callback<CategoryIdRespones>() {
            @Override
            public void onResponse(Call<CategoryIdRespones> call, Response<CategoryIdRespones> response) {
                if(response.isSuccessful()){
                    List<ModelForSingleProduct> modelForSingleProducts = response.body().getProducts();
                    GridViewAdapter gridAdapter1 = new GridViewAdapter(getApplicationContext(), modelForSingleProducts);
                    gridView2.setAdapter(gridAdapter1);
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(HomeNavigationActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(HomeNavigationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<CategoryIdRespones> call, Throwable t) {

            }
        });


        ImageSlider imageSlider = findViewById(R.id.imageSlider2);
        List<SlideModel> slideModelList = new ArrayList<>();
        slideModelList.add(new SlideModel(R.drawable.offer1homepage));
        slideModelList.add(new SlideModel(R.drawable.offer));
        slideModelList.add(new SlideModel(R.drawable.offer));

        imageSlider.setImageList(slideModelList, true);


        ///cart icon clicklistener
       lottieAnimationView = findViewById(R.id.cartHomePage);
        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_token.contains("token")) {
                    Intent in = new Intent(HomeNavigationActivity.this, MYCartActivity.class);
                    startActivity(in);
                }else{
                    startActivity(new Intent(HomeNavigationActivity.this,MYCartActivity.class));
                }
            }
        });

        ///cart icon clicklistener



        //home all Category

        RecyclerView HorizontalRecyclerView = findViewById(R.id.HorizontalRecyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeNavigationActivity.this,LinearLayoutManager.HORIZONTAL,false);
        HorizontalRecyclerView.setLayoutManager(layoutManager1);

        Call<ProductsCategoryRespones> call1 = RetrofitClient
                .getInstance()
                .getApi().productCategory();
        call1.enqueue(new Callback<ProductsCategoryRespones>() {
            @Override
            public void onResponse(Call<ProductsCategoryRespones> call, Response<ProductsCategoryRespones> response) {

                if(response.isSuccessful()){
                    homeAllCategoryModels =  response.body().getCategories();
                    HomeAllCategoryAdapter homeAllCategoryAdapter = new HomeAllCategoryAdapter(homeAllCategoryModels,getApplicationContext());
                    HorizontalRecyclerView.setAdapter(homeAllCategoryAdapter);
                    homeAllCategoryAdapter.setOnItemClickListener(new HomeAllCategoryAdapter.OnItemClickListener() {
                        @Override
                        public void onClickListener(int position) {
                            Intent in = new Intent(HomeNavigationActivity.this, ItemsForSingleProduct.class);
                            in.putExtra("id",homeAllCategoryModels.get(position).getId());
                            in.putExtra("name",homeAllCategoryModels.get(position).getName());
                            in.putExtra("image",homeAllCategoryModels.get(position).getImage());
                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                        }
                    });
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(HomeNavigationActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(HomeNavigationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ProductsCategoryRespones> call, Throwable t) {
                Toast.makeText(HomeNavigationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



        ///brands on home

        RecyclerView HorizontalRecyclerView1 = findViewById(R.id.HorizontalRecyclerView1);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(HomeNavigationActivity.this,LinearLayoutManager.HORIZONTAL,false);
        HorizontalRecyclerView1.setLayoutManager(layoutManager2);

        Call<BrandRespones> callB = RetrofitClient.getInstance().getApi().getBrand();
        callB.enqueue(new Callback<BrandRespones>() {
            @Override
            public void onResponse(Call<BrandRespones> call, Response<BrandRespones> response) {
                List<MoreCotegoryModel> homeAllCategoryModel = response.body().getBrands();
                HomeAllCategoryAdapter homeAllCategoryAdapters = new HomeAllCategoryAdapter(homeAllCategoryModel,getApplicationContext());
                HorizontalRecyclerView1.setAdapter(homeAllCategoryAdapters);
                try{
                    homeAllCategoryAdapters.setOnItemClickListener(new HomeAllCategoryAdapter.OnItemClickListener() {
                        @Override
                        public void onClickListener(int position) {
                            Intent in = new Intent(HomeNavigationActivity.this, ItemsForSingleProduct.class);
                            in.putExtra("idb",homeAllCategoryModel.get(position).getId());
                            in.putExtra("nameB",homeAllCategoryModel.get(position).getName());
                            in.putExtra("image",homeAllCategoryModel.get(position).getImage());
                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(HomeNavigationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BrandRespones> call, Throwable t) {
                Toast.makeText(HomeNavigationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        // item = findViewById(R.id.nav_slideshow);

        //bannerSlider on home page
        bannerSliderViewPager = findViewById(R.id.banner_slider_view_pager);

        localCard();
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });
        //bannerSlider on home page

        //searchview
        searchHome = findViewById(R.id.searchHome);
        searchHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeNavigationActivity.this, SearchPageActivity.class));
            }
        });

        ///home page notification click
        try{
            notification_home_page = findViewById(R.id.notification_home_page);
            notification_home_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeNavigationActivity.this, NotificationActivity.class));
                }
            });
        }catch (Exception e){

        }

//        MenuItem item = optionsMenu.findItem(R.id.nav_SignUp);
//        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);
//        if(user_token.contains("token")) {
//            id.setVisible(false);
//        }else {
//            id.setVisible(true);
//        }

        nestedRecyclerView();

    }

    //nested recyclerView

    private void nestedRecyclerView() {
        List<nestedModel> nestedModels = new ArrayList<>();
//        nestedModels.add(new nestedModel("in",models));
//        nestedModels.add(new nestedModel("in",models));
//        nestedModels.add(new nestedModel("in",models));
//        nestedModels.add(new nestedModel("in",models));
//        nestedModels.add(new nestedModel("in",models));


        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);

        LinearLayoutManager layoutManager4 = new LinearLayoutManager(HomeNavigationActivity.this);

//        NestedAdapter nestedAdapter = new NestedAdapter(nestedModels, HomeNavigationActivity.this);
//
//        recyclerView2.setLayoutManager(layoutManager4);
//        recyclerView2.setAdapter(nestedAdapter);

        Call<ProductsCategoryRespones> call4 = RetrofitClient
                .getInstance()
                .getApi().productCategory();
        call4.enqueue(new Callback<ProductsCategoryRespones>() {
            @Override
            public void onResponse(Call<ProductsCategoryRespones> call, Response<ProductsCategoryRespones> response) {

                if(response.isSuccessful()){
                    homeAllCategoryModelsss =  response.body().getCategories();
                    NestedAdapter nestedAdapter = new NestedAdapter(homeAllCategoryModelsss, HomeNavigationActivity.this);

                    recyclerView2.setLayoutManager(layoutManager4);
                    recyclerView2.setAdapter(nestedAdapter);
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(HomeNavigationActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(HomeNavigationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ProductsCategoryRespones> call, Throwable t) {
                Toast.makeText(HomeNavigationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    //function for bannerSlider
    private void localCard(){
        sliderModels =  new ArrayList<>();

        sliderModels.add(new SliderModel(R.drawable.marketingteam));
        sliderModels.add(new SliderModel(R.drawable.marketing));
        sliderModels.add(new SliderModel(R.drawable.marketingteam));
        sliderModels.add(new SliderModel(R.drawable.arraykart_icon));
        sliderModels.add(new SliderModel(R.drawable.marketing));
        sliderModels.add(new SliderModel(R.drawable.marketingteam));
        sliderModels.add(new SliderModel(R.drawable.marketing));


        sliderAdapter = new SliderAdapter(sliderModels);

        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setPadding(100,0,100,0);
    }

    private void pageLooper(){
        if(currentPage== sliderModels.size()-2){
            currentPage=2;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
        if(currentPage== 1){
            currentPage=sliderModels.size()-3;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
    }

    private void startBannerSlideShow(){
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage>= sliderModels.size()){
                    currentPage=1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }

    private void stopBannerSlideShow(){
        timer.cancel();
    }
    //function for bannerSlider

    @Override
    protected void onStart() {
        super.onStart();
        meowBottomNavigation.show(2, true);
    }

    private void showSignIn() {
        startActivity(new Intent(HomeNavigationActivity.this, Signin.class));

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout, fragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //comment out the following line to hide 3 dots in home page toolbar
//        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        // get the navigation view in which the menu is included
        navigationView= findViewById(R.id.nav_view);
        optionsMenu = navigationView.getMenu();
        MenuItem mitem = optionsMenu.findItem(R.id.nav_SignUp);
        MenuItem mite = optionsMenu.findItem(R.id.nav_LogOut);
        home = optionsMenu.findItem(R.id.nav_home);
        // akfrontend
//        mitem.setTitle("allo");
        SharedPreferences user_token = getSharedPreferences("arraykartuser",MODE_PRIVATE);
        if(user_token.contains("token")){
            mite.setVisible(true);
        }else {
            mitem.setVisible(true);
        }

        return true;
    }

    @Override
    public void onRestart() {
        super.onRestart();


        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
        startActivity(getIntent());
        finish();
//        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_home);
//        NavController navController = navHost.getNavController();
//
//        NavInflater navInflater = navController.getNavInflater();
//        NavGraph graph = navInflater.inflate(R.navigation.mobile_navigation);
//
//        graph.setStartDestination(R.id.nav_home);
//
//        navController.setGraph(graph);


    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_slideshow) {
            Intent in = new Intent(HomeNavigationActivity.this, moreCategoryProducts.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                if (isGPSEnabled()) {

                    checkPermission();

                }else {

                    turnOnGPS();
                }
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                checkPermission();
            }
        }
    }

    private void checkPermission(){

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ActivityCompat.checkSelfPermission(HomeNavigationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if(isGPSEnabled()){
                    LocationServices.getFusedLocationProviderClient(HomeNavigationActivity.this)
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);
                                    LocationServices.getFusedLocationProviderClient(HomeNavigationActivity.this)
                                            .removeLocationUpdates(this);
                                    if(locationResult != null && locationResult.getLocations().size() > 0){
                                        int index = locationResult.getLocations().size() - 1 ;
                                        double latitude = locationResult.getLocations().get(index).getLatitude();
                                        double longitude = locationResult.getLocations().get(index).getLongitude();
                                        sharedPrefManager.setValue_string("GPS","gps");

                                        float[] results = new float[1];
                                        Location.distanceBetween(latitude, longitude, 27.8899595, 78.0989536, results);
                                        float distanceInMeters = results[0];
                                        boolean isWithinRange = distanceInMeters < 30000;


                                        if (isWithinRange) {

                                        }else {
                                            alert("we are not servicing in your area we will reach you soon");
                                        }
                                    }
                                }
                            }, Looper.getMainLooper());
                }else {
                    turnOnGPS();
                    sharedPrefManager.setValue_string("GPS","gps");
                }
            }else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }

    private void turnOnGPS() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(HomeNavigationActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException)e;
                                resolvableApiException.startResolutionForResult(HomeNavigationActivity.this,2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });
    }

    private Boolean isGPSEnabled(){
        LocationManager locationManager = null;
        boolean isEnabled =false;
        if(locationManager == null){
            locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        }
        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled ;

    }

    private void alert(String message){
        AlertDialog alg = new AlertDialog.Builder(HomeNavigationActivity.this)
                .setTitle("Sorry!!!!")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alg.show();
    }
}