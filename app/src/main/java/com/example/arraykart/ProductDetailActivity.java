package com.example.arraykart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.arraykart.AddressActivity.AddressModel;
import com.example.arraykart.AddressActivity.MyAddressActivity;
import com.example.arraykart.AllApiModels.CartAddRespones;
import com.example.arraykart.AllApiModels.ProductDetailPageRespones;
import com.example.arraykart.AllApiModels.WishListAddRespones;
import com.example.arraykart.AllApiModels.deleteWishListRespones;
import com.example.arraykart.AllApiModels.getSelectedAddressRespones;
import com.example.arraykart.AllApiModels.getWishListRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.DeliveryPage.DeliveryActivity;
import com.example.arraykart.MyCart.MYCartActivity;
import com.example.arraykart.ProductDetailAboutListing.ProductDetailListingAdapter;
import com.example.arraykart.ProductDetailAboutListing.ProductDetailListingModel;
import com.example.arraykart.ProductDetailAboutListing.ProductDetailPageModel;
import com.example.arraykart.ProductDetailAboutListing.ProductTableDetailPage;
import com.example.arraykart.RatingReviewPage.AllReviewActivity;
import com.example.arraykart.RatingReviewPage.ReviewAdapter;
import com.example.arraykart.RatingReviewPage.ReviewModel;
import com.example.arraykart.SearchPage.SearchPageActivity;
import com.example.arraykart.WishList.WishListActivity;
import com.example.arraykart.WishList.WishListAdapter;
import com.example.arraykart.WishList.WishListModel;
import com.example.arraykart.homeCategoryProduct.HAdapter;
import com.example.arraykart.homeCategoryProduct.MainModel;
import com.example.arraykart.homeCategoryProduct.allItemOfSingleProduct.ItemsForSingleProduct;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    private CarouselView carouselView;
    private TextView striketextView;
    List<ProductDetailPageModel> product;
    String im;
    String[] si;
//    private int[] sampleImages = {R.drawable.img, R.drawable.img, R.drawable.img, R.drawable.img};
    ///rating layout
    private LinearLayout linearLayout,MoreDetailButton;
    private List<Integer> list;
    private ImageView Rating_layout_image;
    private String yo;


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
    private TextView textView11,searchProductDetail;

    //productdetail chemicalComposition
    private LinearLayout chemCompLL;
    private ImageView chemCompIV,close_address_Prouct_detail_page;
    private TextView textView25;

    //product detail offers
    private LinearLayout offerLL1;
    private ImageView closeBsdOffers1;
    private LinearLayout offerLL2;
    private LinearLayout ProductDetailPageAddressShow;
    private ImageView closeBsdOffers2;

    ///buttons on product detail page
    private Button cart_on_product_detail,buy_on_product_detail,changeAddress,delivery_continue_btn;

    //review for this page
    private RecyclerView ReviewRecyclerView;
    private List<ReviewModel> reviewModelList ;
    private ReviewAdapter reviewAdapter;
    private TextView MoreReview,pdProductName,productDetailPagePrice,listDetail,listDetail1,listDetail2;

    private CheckBox wishListProductsDetail;
    private List<WishListModel> wishListModelList ;
    SharedPrefManager sharedPrefManager;



    ImageView Add_quantity_product_page,mini_quantity_product_page,productImageQualityLayout;
    TextView product_quantity_text_product_detail_page;
    ///shipping address
    TextView shoppingDetailName,shoppingDetailAddress,textView6;
    List<AddressModel> addressModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        String id = getIntent().getStringExtra("id");
        String qlty = getIntent().getStringExtra("qlt");
        String imgs = getIntent().getStringExtra("image");


        ProductDetailPageAddressShow = findViewById(R.id.ProductDetailPageAddressShow);
        close_address_Prouct_detail_page = findViewById(R.id.close_address_Prouct_detail_page);
        changeAddress = findViewById(R.id.changeAddress);
        delivery_continue_btn = findViewById(R.id.delivery_continue_btn);
        buy_on_product_detail = findViewById(R.id.buy_on_product_detail);
        MoreDetailButton = findViewById(R.id.MoreDetailButton);
        carouselView = findViewById(R.id.carouselViewProductDetail);

        pdProductName=findViewById(R.id.pdProductName);
        productDetailPagePrice = findViewById(R.id.productDetailPagePrice);
        listDetail = findViewById(R.id.listDetail);
        listDetail1= findViewById(R.id.listDetail1);
        listDetail2 = findViewById(R.id.listDetail2);

        Add_quantity_product_page = findViewById(R.id.Add_quantity_product_page);
        mini_quantity_product_page = findViewById(R.id.mini_quantity_product_page);
        product_quantity_text_product_detail_page = findViewById(R.id.product_quantity_text_product_detail_page);
        productImageQualityLayout = findViewById(R.id.productImageQualityLayout);
        wishListProductsDetail = findViewById(R.id.wishListProductsDetail);

        ///shipping address
        shoppingDetailName = findViewById(R.id.shoppingDetailName);
        shoppingDetailAddress = findViewById(R.id.shoppingDetailAddress);
        textView6 = findViewById(R.id.textView6);
        final String[] add1 = new String[1];
        final String[] add2 = new String[1];
        final String[] city = new String[1];
        final String[] state = new String[1];

        SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
        if(userToken.contains("token")) {
            Call<getSelectedAddressRespones> callSelectedAddress = RetrofitClient.getInstance().getApi().getSelectedAddress(token);
            callSelectedAddress.enqueue(new Callback<getSelectedAddressRespones>() {
                @Override
                public void onResponse(Call<getSelectedAddressRespones> call, Response<getSelectedAddressRespones> response) {
                    getSelectedAddressRespones getSelectedAddressRespones = response.body();
                    addressModels = getSelectedAddressRespones.getAddress();
                    if(!addressModels.isEmpty()) {
                        shoppingDetailName.setText(addressModels.get(0).getAddress_name());
                        add1[0] = addressModels.get(0).getAddress_line1();
                        add2[0] = addressModels.get(0).getAddress_line2();
                        state[0] = addressModels.get(0).getState();
                        city[0] = addressModels.get(0).getCity();
                        textView6.setText(addressModels.get(0).getPostal_code());
                        shoppingDetailAddress.setText(add1[0] + "," + add2[0] + "," + state[0] + "," + city[0]);
                    }else {
                        shoppingDetailName.setText("Full Name");
                        textView6.setText("PinCode");
                        shoppingDetailAddress.setText("Full Address");
                    }

                }

                @Override
                public void onFailure(Call<getSelectedAddressRespones> call, Throwable t) {
                    Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
        }

        if(qlty!=null) {
            product_quantity_text_product_detail_page.setText(qlty);
        }else{
            product_quantity_text_product_detail_page.setText("1");
        }

        if (imgs != null) {
            Glide.with(ProductDetailActivity.this)
                    .load(imgs)
                    .into(productImageQualityLayout);
        } else {
            productImageQualityLayout.setImageResource(R.drawable.img);
        }
        ///qty update

        Add_quantity_product_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String Atext = product_quantity_text_product_detail_page.getText().toString();
                    int Aqlt = Integer.parseInt(Atext);
                    Aqlt += 1;
                    String nATxt = Integer.toString(Aqlt);
                    product_quantity_text_product_detail_page.setText(nATxt);
                }catch (Exception e){
                    Toast.makeText(ProductDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mini_quantity_product_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String stext = product_quantity_text_product_detail_page.getText().toString();
                    int sqlt = Integer.parseInt(stext);
                    if (sqlt == 1) {
                        String nSTxt = Integer.toString(sqlt);
                        product_quantity_text_product_detail_page.setText(nSTxt);
                    } else if (sqlt > 1) {
                        sqlt -= 1;
                        String nSTxt = Integer.toString(sqlt);
                        product_quantity_text_product_detail_page.setText(nSTxt);
                    }
                }catch (Exception e){
                    Toast.makeText(ProductDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        //String qty = product_quantity_text_product_detail_page.getText().toString();


        ////////////////
       ImageView li = findViewById(R.id.listImage);
       TextView lt = findViewById(R.id.listText);
       TextView ld = findViewById(R.id.listDetail);
       LinearLayout descriptionLL = findViewById(R.id.descriptionLL);

        ImageView li1 = findViewById(R.id.listImage1);
        TextView lt1 = findViewById(R.id.listText1);
        TextView ld1= findViewById(R.id.listDetail1);
        LinearLayout brandLL = findViewById(R.id.brandLL);

        ImageView li2 = findViewById(R.id.listImage2);
        TextView lt2 = findViewById(R.id.listText2);
        TextView ld2= findViewById(R.id.listDetail2);
        LinearLayout categoryLL = findViewById(R.id.categoryLL);


       //////////
        descriptionLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ld.getVisibility() == View.GONE)) {
                    ld.setVisibility(View.VISIBLE);
                    li.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    ld.setVisibility(View.GONE);
                    li.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        brandLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ld1.getVisibility() == View.GONE)) {
                    ld1.setVisibility(View.VISIBLE);
                    li1.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    ld1.setVisibility(View.GONE);
                    li1.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        categoryLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ld2.getVisibility() == View.GONE)) {
                    ld2.setVisibility(View.VISIBLE);
                    li2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    ld2.setVisibility(View.GONE);
                    li2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });





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
                SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
                if(userToken.contains("token")) {
                    Intent in = new Intent(ProductDetailActivity.this, MYCartActivity.class);
                    startActivity(in);
                }else{
                    Toast.makeText(ProductDetailActivity.this, "SignUp First", Toast.LENGTH_LONG).show();
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
        cart_on_product_detail = findViewById(R.id.cart_on_product_detail);
        cart_on_product_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty = product_quantity_text_product_detail_page.getText().toString();
                // api call for add cart
                SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
                if(userToken.contains("token")) {
                    Call<CartAddRespones> callC = RetrofitClient.getInstance().getApi().addToCart(token, id, qty);
                    callC.enqueue(new Callback<CartAddRespones>() {
                        @Override
                        public void onResponse(Call<CartAddRespones> call, Response<CartAddRespones> response) {
                            if (response.isSuccessful()) {
                                CartAddRespones cartAddRespones = response.body();
                                Toast.makeText(ProductDetailActivity.this, cartAddRespones.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ProductDetailActivity.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CartAddRespones> call, Throwable t) {
                            Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                    startActivity(new Intent(ProductDetailActivity.this,MYCartActivity.class));
                }else{
                    Toast.makeText(ProductDetailActivity.this, "SignUp First", Toast.LENGTH_SHORT).show();
                }
            }
        });


        try{
            buy_on_product_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
                    if(userToken.contains("token")) {
                        changeAddress.setVisibility(View.VISIBLE);
                        ProductDetailPageAddressShow.setVisibility(View.VISIBLE);
                        buy_on_product_detail.setVisibility(View.GONE);
                        delivery_continue_btn.setVisibility(View.VISIBLE);
//                    startActivity(new Intent(ProductDetailActivity.this, MyAddressActivity.class));
                    }else {
                        Toast.makeText(ProductDetailActivity.this, "SignUp First", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }catch (Exception e){

        }
        try {
            close_address_Prouct_detail_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailPageAddressShow.setVisibility(View.GONE);
                    delivery_continue_btn.setVisibility(View.GONE);
                    buy_on_product_detail.setVisibility(View.VISIBLE);

                }
            });
        }catch (Exception e){

        }

        try {
            changeAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ProductDetailActivity.this, MyAddressActivity.class));
                }
            });
        }catch (Exception e){

        }

        //searchview
        try {
            searchProductDetail = findViewById(R.id.searchProductDetail);
            searchProductDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ProductDetailActivity.this, SearchPageActivity.class));
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


//    call for add product in wishlist
        wishListProductsDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty = product_quantity_text_product_detail_page.getText().toString();
                SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
                if(userToken.contains("token")) {

                    Call<WishListAddRespones> call = RetrofitClient.getInstance().getApi().addWishlist(token, id, qty);
                    call.enqueue(new Callback<WishListAddRespones>() {
                        @Override
                        public void onResponse(Call<WishListAddRespones> call, Response<WishListAddRespones> response) {
                            WishListAddRespones wishListAddRespones = response.body();
                            if (response.isSuccessful()) {
                                String msg = wishListAddRespones.getMessage();

                                if(msg.contains("Product already exists in wish list!")){
                                    //delete products from wishlist
                                    wishListProductsDetail.setChecked(false);
                                    Call<deleteWishListRespones> callD = RetrofitClient.getInstance().getApi().deleteWishList("application/json",token,id);
                                    callD.enqueue(new Callback<deleteWishListRespones>() {
                                        @Override
                                        public void onResponse(Call<deleteWishListRespones> call, Response<deleteWishListRespones> response) {
                                            deleteWishListRespones deleteWishListRespones = response.body();
                                            if (response.isSuccessful()){
                                                Toast.makeText(ProductDetailActivity.this, deleteWishListRespones.getMessage(), Toast.LENGTH_LONG).show();
                                            }else {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                                    Toast.makeText(ProductDetailActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();

                                                } catch (Exception e) {
                                                    Toast.makeText(ProductDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<deleteWishListRespones> call, Throwable t) {
                                            Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                } else {
                                    Toast.makeText(ProductDetailActivity.this, wishListAddRespones.getMessage(), Toast.LENGTH_LONG).show();
                                    wishListProductsDetail.setChecked(true);
                                }

                            } else {
                                Toast.makeText(ProductDetailActivity.this, "error", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<WishListAddRespones> call, Throwable t) {
                            Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

//
                }else{
                    Toast.makeText(ProductDetailActivity.this, "SignUp First", Toast.LENGTH_SHORT).show();
                    wishListProductsDetail.setChecked(false);
                }
            }
        });

//        //productDetailListing
//        try {
//            productDetailListing = findViewById(R.id.productDetailListing);
//            LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
//            layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
//            productDetailListing.setLayoutManager(layoutManagers);
//
//            productDetailListingModels = new ArrayList<>();
//            productDetailListingModels.add(new ProductDetailListingModel("Description",R.drawable.ic_baseline_keyboard_arrow_up_24,"The Indian rupee sign (₹) is the currency symbol for the Indian rupee, the official currency of India. Designed by Udaya Kumar, it was presented to the public by the Government of India on 15 July 2010, following its selection through an open competition among Indian residents. The Indian rupee sign (₹) is the currency symbol for the Indian rupee, the official currency of India. Designed by Udaya Kumar, it was presented to the public by the Government of India on 15 July 2010, following its selection through an open competition among Indian residents. The Indian rupee sign (₹) is the currency symbol for the Indian rupee, the official currency of India. Designed by Udaya Kumar, it was presented to the public by the Government of India on 15 July 2010, following its selection through an open competition among Indian residents. The Indian rupee sign (₹) is the currency symbol for the Indian rupee, the official currency of India. Designed by Udaya Kumar, it was presented to the public by the Government of India on 15 July 2010, following its selection through an open competition among Indian residents"));
//            productDetailListingModels.add(new ProductDetailListingModel("Chemical composition",R.drawable.ic_baseline_keyboard_arrow_up_24,"Mancozeb 63% + Carbendazim 12% WP"));
//
//            productDetailListingAdapter = new ProductDetailListingAdapter(productDetailListingModels,ProductDetailActivity.this);
//            productDetailListing.setAdapter(productDetailListingAdapter);
//        }catch (Exception e){
//
//        }


        ///product detail page call

        String url = "/api/product/"+id;
        Call<ProductDetailPageRespones> CallDetail = RetrofitClient.getInstance().getApi().getDetail(url);
        CallDetail.enqueue(new Callback<ProductDetailPageRespones>() {
            @Override
            public void onResponse(Call<ProductDetailPageRespones> call, Response<ProductDetailPageRespones> response) {

                try {
                    product = response.body().getProduct();
                    pdProductName.setText(product.get(0).getName());
                    productDetailPagePrice.setText(product.get(0).getPrice());
                    listDetail.setText(product.get(0).getDescription());
                    listDetail1.setText(product.get(0).getBrand());
                    listDetail2.setText(product.get(0).getCategory());
                    yo= product.get(0).getImage();
                    si = yo.split(",");
                    carouselView.setPageCount(si.length);

                }catch (Exception e){
                    Toast.makeText(ProductDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductDetailPageRespones> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        try{
            MoreDetailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(ProductDetailActivity.this, ProductTableDetailPage.class);
                    in.putExtra("id",id);
                    startActivity(in);
                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        carouselView.setImageListener(imageListener);


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
            Glide.with(ProductDetailActivity.this)
                    .load("https://arraykartandroid.s3.ap-south-1.amazonaws.com/"+si[position])
                    .centerCrop()
                    .into(imageView);
        }
    };

    @Override
    public boolean supportShouldUpRecreateTask(@NonNull Intent targetIntent) {
        return super.supportShouldUpRecreateTask(targetIntent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        ///shipping address
        shoppingDetailName = findViewById(R.id.shoppingDetailName);
        shoppingDetailAddress = findViewById(R.id.shoppingDetailAddress);
        textView6 = findViewById(R.id.textView6);
        final String[] add1 = new String[1];
        final String[] add2 = new String[1];
        final String[] city = new String[1];
        final String[] state = new String[1];

        SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
        if(userToken.contains("token")) {
            Call<getSelectedAddressRespones> callSelectedAddress = RetrofitClient.getInstance().getApi().getSelectedAddress(token);
            callSelectedAddress.enqueue(new Callback<getSelectedAddressRespones>() {
                @Override
                public void onResponse(Call<getSelectedAddressRespones> call, Response<getSelectedAddressRespones> response) {
                    getSelectedAddressRespones getSelectedAddressRespones = response.body();
                    addressModels = getSelectedAddressRespones.getAddress();
                    if(!addressModels.isEmpty()) {
                        shoppingDetailName.setText(addressModels.get(0).getAddress_name());
                        add1[0] = addressModels.get(0).getAddress_line1();
                        add2[0] = addressModels.get(0).getAddress_line2();
                        state[0] = addressModels.get(0).getState();
                        city[0] = addressModels.get(0).getCity();
                        textView6.setText(addressModels.get(0).getPostal_code());
                        shoppingDetailAddress.setText(add1[0] + "," + add2[0] + "," + state[0] + "," + city[0]);
                    }else {
                        shoppingDetailName.setText("Full Name");
                        textView6.setText("PinCode");
                        shoppingDetailAddress.setText("Full Address");
                    }
                }

                @Override
                public void onFailure(Call<getSelectedAddressRespones> call, Throwable t) {
                    Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
        }

    }
}