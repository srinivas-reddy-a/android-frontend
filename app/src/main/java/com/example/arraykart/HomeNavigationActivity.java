package com.example.arraykart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.arraykart.BannerSlider.SliderAdapter;
import com.example.arraykart.BannerSlider.SliderModel;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.WishList.WishListActivity;
import com.example.arraykart.homeCategoryProduct.HAdapter;
import com.example.arraykart.homeCategoryProduct.MainModel;
import com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct.GridViewAdapter;
import com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct.ItemsForSingleProduct;
import com.example.arraykart.homeCategoryProduct.moreProductCategory.moreCategoryProducts;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
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
import com.example.arraykart.databinding.ActivityHomeNavigationBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeNavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeNavigationBinding binding;
    private MeowBottomNavigation meowBottomNavigation;

    private LottieAnimationView lottieAnimationView;

    //homePageCategoryProductItem

    private  RecyclerView recyclerView;
    private  HAdapter hAdapter;
    private ArrayList<MainModel> maiModel;
    private RecyclerView recyclerView1;
    private ImageView wishListHome;
    private SearchView searchHome;


    //homePageCategoryProductItem

    private MenuItem items ;
    private  Context context;

    ///grid view on home page
    private GridView gridView;
    private GridView gridView2;

    private String[] name = {"Herbicides", "Insecticides",
            "Insecticides", "name"};

    private String[] price = {"2400", "2400", "2400", "2400"};

    private String[] rate = {"4.3 *|348k", "4.3 *|348k", "4.3 *|348k", "4.3 *|348k"};

    private String[] ribbon ={"new","new","new","new"};

    private int[] imgs = {R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img};

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_wishList, R.id.nav_MyCart, R.id.nav_Settings)
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
                if(item.getId()==1){
                    Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                }
                if(item.getId()==3){
                    showSignIn();
                }

            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1){
                    Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                }
                if(item.getId()==3){
                    showSignIn();
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
        recyclerView1 = findViewById(R.id.recyclerView1);

        int[] imgs ={R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
        String[] name = {"name","name","name","name","name"};
        String[] price ={"price","price","price","price","price"};

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

        LinearLayoutManager layoutManagers = new LinearLayoutManager(HomeNavigationActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(layoutManagers);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        HAdapter hAdapters = new HAdapter(this,maiModel);
        recyclerView1.setAdapter(hAdapters);


//      this helps click on every item present in home_products_category and open new activity of all item_product
        try {
            hAdapter.setOnItemClickListener(new HAdapter.OnItemClickListener() {
                @Override
                public void onClickListener(int position) {
                    for (int i = 0; i < imgs.length; i++) {
                        if (position == i-1) {
                            startActivity(new Intent(HomeNavigationActivity.this, ItemsForSingleProduct.class));
                        }
                    }
                    if(position==imgs.length-1){
                        startActivity(new Intent(HomeNavigationActivity.this, moreCategoryProducts.class));
                    }
                }
            });

        }catch(Exception e){

        }


        ///1st grid view for home page
        gridView = findViewById(R.id.gridView1);

        GridViewAdapter gridAdapter = new GridViewAdapter(this, name, price, rate, ribbon, imgs);
        gridView.setAdapter(gridAdapter);
        ///1nd grid view for home page

        ///2nd grid view for home page
        gridView2 = findViewById(R.id.gridView2);

        GridViewAdapter gridAdapters = new GridViewAdapter(this, name, price, rate, ribbon, imgs);
        gridView2.setAdapter(gridAdapters);
        ///2nd grid view for home page

        ImageSlider imageSlider = findViewById(R.id.imageSlider2);
        List<SlideModel> slideModelList = new ArrayList<>();
        slideModelList.add(new SlideModel(R.drawable.offer));
        slideModelList.add(new SlideModel(R.drawable.offer));
        slideModelList.add(new SlideModel(R.drawable.offer));
        slideModelList.add(new SlideModel(R.drawable.offer));
        slideModelList.add(new SlideModel(R.drawable.offer));

        imageSlider.setImageList(slideModelList, true);

//
//        LinearLayout brandsv = findViewById(R.id.brandsv);
//        View v = getLayoutInflater().inflate(R.layout.home_brand, null);
//        ImageView isv = v.findViewById(R.id.imageView2);
//        isv.setImageResource(R.drawable.img);
//        TextView tsv = v.findViewById(R.id.textView2);
//        tsv.setText("allo");
//        brandsv.addView(v);
//        brandsv.addView(v);
//        brandsv.addView(v);

        ///cart icon clicklistener
       lottieAnimationView = findViewById(R.id.cartHomePage);
        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeNavigationActivity.this, MYCartActivity.class);
                startActivity(in);
//                Intent in = new Intent(HomeNavigationActivity.this, MyOrder.class);
//                startActivity(in);
            }
        });

        ///cart icon clicklistener


        for(int i=1;i<9;i++){
            LinearLayout categoryhsv = findViewById(R.id.horizontalscrollview);
            View v = getLayoutInflater().inflate(R.layout.home_brand, null);
            ImageView isv = v.findViewById(R.id.imageView2);
            isv.setImageResource(R.drawable.img);
            TextView tsv = v.findViewById(R.id.textView2);
            tsv.setText("Category"+i);
            isv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeNavigationActivity.this, ItemsForSingleProduct.class));
                }
            });
            categoryhsv.addView(v);
        }
        for(int i=1;i<9;i++){
            LinearLayout brandhsv = findViewById(R.id.horizontalscrollview2);
            View v = getLayoutInflater().inflate(R.layout.home_brand, null);
            ImageView isv = v.findViewById(R.id.imageView2);
            isv.setImageResource(R.drawable.img);
            TextView tsv = v.findViewById(R.id.textView2);
            tsv.setText("brand"+i);
            isv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeNavigationActivity.this, ItemsForSingleProduct.class));
                }
            });
            brandhsv.addView(v);
        }




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

//        //searchview
//        searchHome = findViewById(R.id.searchHome);
//        searchHome.

    }


    //

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

        sliderModels.add(new SliderModel(R.drawable.img));
        sliderModels.add(new SliderModel(R.drawable.img));
        sliderModels.add(new SliderModel(R.drawable.img));
        sliderModels.add(new SliderModel(R.drawable.img));
        sliderModels.add(new SliderModel(R.drawable.img));
        sliderModels.add(new SliderModel(R.drawable.img));
        sliderModels.add(new SliderModel(R.drawable.img));


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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.nav_slideshow) {
//            Intent intent1 = new Intent(this,MyOrder.class);
//            this.startActivity(intent1);
//            return true;
//        }
//
////            if (id == R.id.settings) {
////                Toast.makeText(this, "Setting", Toast.LENGTH_LONG).show();
////                return true;
////            }
//
//        return super.onOptionsItemSelected(item);
//    }

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
}