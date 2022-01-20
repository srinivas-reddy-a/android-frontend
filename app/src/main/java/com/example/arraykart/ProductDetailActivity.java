package com.example.arraykart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.arraykart.AddressActivity.MyAddressActivity;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.RatingReviewPage.AllReviewActivity;
import com.example.arraykart.RatingReviewPage.ReviewAdapter;
import com.example.arraykart.RatingReviewPage.ReviewModel;
import com.example.arraykart.homeCategoryProduct.HAdapter;
import com.example.arraykart.homeCategoryProduct.MainModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    private CarouselView carouselView;
    private TextView striketextView;
    private int[] sampleImages = {R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img};

    ///rating layout
    private LinearLayout linearLayout;
    private List<Integer> list;
    private ImageView Rating_layout_image;


    ///recyclerView for products in productdetailpage
    private RecyclerView recyclerView;
    private HAdapter hAdapter;
    private ArrayList<MainModel> maiModel;
    ///recyclerView for products in productdetailpage

    ///cart icon product detail page
    private LottieAnimationView cart_product_detail_page;
    //cart icon product detail page

    //productdetail description
    private LinearLayout descriptionLL;
    private ImageView desArrowDownIV;
    private TextView textView11;

    //productdetail chemicalComposition
    private LinearLayout chemCompLL;
    private ImageView chemCompIV;
    private TextView textView25;

    //product detail offers
    private LinearLayout offerLL1;
    private ImageView closeBsdOffers1;
    private LinearLayout offerLL2;
    private ImageView closeBsdOffers2;

    ///buttons on product detail page
    private Button cart_on_product_detail;
    private Button buy_on_product_detail;

    //review for this page
    private RecyclerView ReviewRecyclerView;
    private List<ReviewModel> reviewModelList ;
    private ReviewAdapter reviewAdapter;
    private TextView MoreReview;

    private CheckBox wishListProductsDetail;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        String id = getIntent().getStringExtra("id");


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

        ///recyclerView for products in productdetailpage
        recyclerView=findViewById(R.id.recyclerView);

//        int[] imgs ={R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
//        String[] name = {"name","name","name","name"};
//        String[] price ={"price","price","price","price"};
//
//        maiModel = new ArrayList<>();
//        try {
//            for (int i = 0; i < name.length; i++) {
//                MainModel model = new MainModel(name[i], price[i], imgs[i]);
//                maiModel.add(model);
//            }
//        }catch(Exception e){
//
//        }
//        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailActivity.this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        hAdapter = new HAdapter(this,maiModel);
//        recyclerView.setAdapter(hAdapter);
        ///recyclerView for products in productdetailpage

        ///cart icon clicklistener
        cart_product_detail_page = findViewById(R.id.cart_product_detail_page);
        cart_product_detail_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ProductDetailActivity.this, AllReviewActivity.class);
                startActivity(in);
            }
        });

        ///product detail des clicklistener
        descriptionLL = findViewById(R.id.descriptionLL);
        descriptionLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView11=findViewById(R.id.textView11);
                desArrowDownIV = findViewById(R.id.imageView7);
                if ((textView11.getVisibility() == View.GONE)) {
                    textView11.setVisibility(View.VISIBLE);
                    desArrowDownIV.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    textView11.setVisibility(View.GONE);
                    desArrowDownIV.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        ///product detail chemical comp clicklistener
        chemCompLL = findViewById(R.id.chemCompLL);
        chemCompLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView25=findViewById(R.id.textView25);
                chemCompIV = findViewById(R.id.imageView8);
                if ((textView25.getVisibility() == View.GONE)) {
                    textView25.setVisibility(View.VISIBLE);
                    chemCompIV.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    textView25.setVisibility(View.GONE);
                    chemCompIV.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        //product detail offers clicklistener
        offerLL1 = findViewById(R.id.offerLL1);
        offerLL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOffersBottomSheetDialog();
            }
        });

        offerLL2 = findViewById(R.id.offerLL2);
        offerLL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOffersBottomSheetDialog();
            }
        });

        ///buttons on product detail page
        try {
            cart_on_product_detail = findViewById(R.id.cart_on_product_detail);
            cart_on_product_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//          api call for add cart
//                    Call<ResponseBody> callC = RetrofitClient.getInstance().getApi().addToCart(token,id,"2");
//                    callC.enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    startActivity(new Intent(ProductDetailActivity.this,MYCartActivity.class));

                }
            });
        }catch (Exception e){

        }
        try{
            buy_on_product_detail = findViewById(R.id.buy_on_product_detail);
            buy_on_product_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ProductDetailActivity.this, MyAddressActivity.class));
                }
            });
        }catch (Exception e){

        }

        //rating layout
        try {
            linearLayout = findViewById(R.id.RatingImage);
            list = new ArrayList<>();
            list.add(R.drawable.img);
            list.add(R.drawable.img);
            list.add(R.drawable.img);
            list.add(R.drawable.img);
            for (int i =0;i<list.size();i++){
                View view = LayoutInflater.from(this).inflate(R.layout.rating_image_layout,linearLayout,false);
                Rating_layout_image = view.findViewById(R.id.Rating_layout_image);
                Rating_layout_image.setImageResource(list.get(i));

                linearLayout.addView(view);
            }
        }catch (Exception e){

        }

        //review layout
        try{
            ReviewRecyclerView = findViewById(R.id.ReviewRecyclerView);
            LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
            layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
            ReviewRecyclerView.setLayoutManager(layoutManagers);

            reviewModelList = new ArrayList<>();
            reviewModelList.add(new ReviewModel("4.4","Cool Product",
                    "hbvashbdjajdvbaschbjjacjacjjascjavscj a cjabcjavdab " +
                            "d acj ajscdja dqochqwdb qjhd jhavc cjhs jaca cjhac dvbasjca " +
                            " cjha sjcajc a cujhascb jc ahcjackwdbiwdb " +
                            " wdi bxbxibISX  sx     djb dbuscbxhhxAXB   DDQ WKJDJBB WDKKw","sachin jha","noia","Jan,2021" ));

            reviewAdapter = new ReviewAdapter(reviewModelList);
            ReviewRecyclerView.setAdapter(reviewAdapter);
            reviewAdapter.notifyDataSetChanged();
        }catch (Exception e){

        }
        try{
           MoreReview = findViewById(R.id.MoreReview);
           MoreReview.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(ProductDetailActivity.this,AllReviewActivity.class));
               }
           });
        }catch (Exception e){

        }

//        call for add product in wishlist.
//        try{
//            wishListProductsDetail = findViewById(R.id.wishListProductsDetail);
//            wishListProductsDetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Call<ResponseBody> call = RetrofitClient.getInstance().getApi().addWishlist(token,id);
//                    call.enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            });
//        }catch (Exception e){
//
//        }

    }

    private void showOffersBottomSheetDialog() {
        final BottomSheetDialog bsd = new BottomSheetDialog(this);
        bsd.setContentView(R.layout.bottom_sheet_dialog_offers_product_detail);
        closeBsdOffers1 = bsd.findViewById(R.id.closeBsdOffers);
        closeBsdOffers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsd.dismiss();
            }
        });
        bsd.show();

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}