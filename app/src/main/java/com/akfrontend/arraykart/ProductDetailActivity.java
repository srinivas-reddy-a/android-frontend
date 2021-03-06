package com.akfrontend.arraykart;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.akfrontend.arraykart.AddressActivity.AddressModel;
import com.akfrontend.arraykart.AddressActivity.MyAddressActivity;
import com.akfrontend.arraykart.AllApiModels.CartAddRespones;
import com.akfrontend.arraykart.AllApiModels.CartUPdateRespones;
import com.akfrontend.arraykart.AllApiModels.ProductDetailPageRespones;
import com.akfrontend.arraykart.AllApiModels.WishListAddRespones;
import com.akfrontend.arraykart.AllApiModels.deleteWishListRespones;
import com.akfrontend.arraykart.AllApiModels.getSelectedAddressRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.AllRetrofit.SharedPrefManager;
import com.akfrontend.arraykart.MyCart.MYCartActivity;
import com.akfrontend.arraykart.MyOrder.OrderPlacedPage;
import com.akfrontend.arraykart.ProductDetailAboutListing.ProductDetailPageModel;
import com.akfrontend.arraykart.ProductDetailAboutListing.ProductTableDetailPage;
import com.akfrontend.arraykart.R;
import com.akfrontend.arraykart.RatingReviewPage.AllReviewActivity;
import com.akfrontend.arraykart.RatingReviewPage.ReviewAdapter;
import com.akfrontend.arraykart.RatingReviewPage.ReviewModel;
import com.akfrontend.arraykart.SearchPage.SearchPageActivity;
import com.akfrontend.arraykart.WishList.WishListModel;
import com.akfrontend.arraykart.homeCategoryProduct.HAdapter;
import com.akfrontend.arraykart.homeCategoryProduct.MainModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@SuppressWarnings("unchecked")
public class ProductDetailActivity extends AppCompatActivity {

    private CarouselView carouselView,carouselViews;
    private TextView striketextView;
    private List<ProductDetailPageModel> product;

    private String[] si;
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
    private Button cart_on_product_detail,buy_on_product_detail,changeAddress,delivery_continue_btn,go_cart_on_product_detail;

    //review for this page
    private RecyclerView ReviewRecyclerView;
    private List<ReviewModel> reviewModelList ;
    private ReviewAdapter reviewAdapter;
    private TextView MoreReview,pdProductName,productDetailPagePrice,listDetail,listDetail1,listDetail2;

    private CheckBox wishListProductsDetail;
    private List<WishListModel> wishListModelList ;
    SharedPrefManager sharedPrefManager;



    private ImageView Add_quantity_product_page,mini_quantity_product_page,productImageQualityLayout;
    private TextView product_quantity_text_product_detail_page;
    ///shipping address
    private TextView shoppingDetailName,shoppingDetailAddress,textView6,selected_volume;
    private List<AddressModel> addressModels;

    private Spinner spinner;
    private String AddId ;
    private String vl;
    private String costs;
    private String[] price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        String id = getIntent().getStringExtra("id");
        String qlty = getIntent().getStringExtra("qlt");
        String imgs = getIntent().getStringExtra("image");
        String valume = getIntent().getStringExtra("volume");


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
        cart_on_product_detail = findViewById(R.id.cart_on_product_detail);
        go_cart_on_product_detail= findViewById(R.id.go_cart_on_product_detail);
        spinner = findViewById(R.id.spinner);
        selected_volume = findViewById(R.id.selected_volume);

        ////ShippingAddress

        ShippingAddress();

        //////////



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
                    Toast.makeText(ProductDetailActivity.this, e.getMessage(), LENGTH_SHORT).show();
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
                    Toast.makeText(ProductDetailActivity.this, e.getMessage(), LENGTH_SHORT).show();
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

        ImageView li3 = findViewById(R.id.listImage3);
        TextView lt3 = findViewById(R.id.listText3);
        TextView ld3= findViewById(R.id.listDetail3);
        LinearLayout showingTimeLL = findViewById(R.id.showingTimeLL);

        ImageView li4 = findViewById(R.id.listImage4);
        TextView lt4 = findViewById(R.id.listText4);
        TextView ld4= findViewById(R.id.listDetail4);
        LinearLayout seedRateLL = findViewById(R.id.seedRateLL);

        ImageView li5 = findViewById(R.id.listImage5);
        TextView lt5 = findViewById(R.id.listText5);
        TextView ld5 = findViewById(R.id.listDetail5);
        LinearLayout maturityDurationLL = findViewById(R.id.maturityDurationLL);

        ImageView li6 = findViewById(R.id.listImage6);
        TextView lt6 = findViewById(R.id.listText6);
        TextView ld6 = findViewById(R.id.listDetail6);
        LinearLayout colourLL = findViewById(R.id.colourLL);

        ImageView li7 = findViewById(R.id.listImage7);
        TextView lt7 = findViewById(R.id.listText7);
        TextView ld7 = findViewById(R.id.listDetail7);
        LinearLayout uspLL = findViewById(R.id.uspLL);

        ImageView li8 = findViewById(R.id.listImage8);
        TextView lt8 = findViewById(R.id.listText8);
        TextView ld8 = findViewById(R.id.listDetail8);
        LinearLayout NumberofSeedsLL = findViewById(R.id.NumberofSeedsLL);

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
        showingTimeLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ld3.getVisibility() == View.GONE)) {
                    ld3.setVisibility(View.VISIBLE);
                    li3.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    ld3.setVisibility(View.GONE);
                    li3.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        seedRateLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ld4.getVisibility() == View.GONE)) {
                    ld4.setVisibility(View.VISIBLE);
                    li4.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    ld4.setVisibility(View.GONE);
                    li4.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        maturityDurationLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ld5.getVisibility() == View.GONE)) {
                    ld5.setVisibility(View.VISIBLE);
                    li5.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    ld5.setVisibility(View.GONE);
                    li5.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        colourLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ld6.getVisibility() == View.GONE)) {
                    ld6.setVisibility(View.VISIBLE);
                    li6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    ld6.setVisibility(View.GONE);
                    li6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        uspLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ld7.getVisibility() == View.GONE)) {
                    ld7.setVisibility(View.VISIBLE);
                    li7.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    ld7.setVisibility(View.GONE);
                    li7.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        NumberofSeedsLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ld8.getVisibility() == View.GONE)) {
                    ld8.setVisibility(View.VISIBLE);
                    li8.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    ld8.setVisibility(View.GONE);
                    li8.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
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

       /////cart ////
        CartAll();
        /////cart///

        ///WishLIst///
        WishList();
        ///WishList///

        try{
            buy_on_product_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
                    if(userToken.contains("token")) {
//                        Toast.makeText(ProductDetailActivity.this, "coming soon", Toast.LENGTH_SHORT).show();
                        changeAddress.setVisibility(View.VISIBLE);
                        ProductDetailPageAddressShow.setVisibility(View.VISIBLE);
                        buy_on_product_detail.setVisibility(View.GONE);
                        delivery_continue_btn.setVisibility(View.VISIBLE);
                    }else {
                        startActivity(new Intent(ProductDetailActivity.this,SignUP.class));
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

        ////review page

        reviewAll();

        /////////

////

//        //productDetailListing
//        try {
//            productDetailListing = findViewById(R.id.productDetailListing);
//            LinearLayoutManager layoutManagers = new LinearLayoutManager(this);
//            layoutManagers.setOrientation(LinearLayoutManager.VERTICAL);
//            productDetailListing.setLayoutManager(layoutManagers);
//
//            productDetailListingModels = new ArrayList<>();
//            productDetailListingModels.add(new ProductDetailListingModel("Description",R.drawable.ic_baseline_keyboard_arrow_up_24,"The Indian rupee sign (???) is the currency symbol for the Indian rupee, the official currency of India. Designed by Udaya Kumar, it was presented to the public by the Government of India on 15 July 2010, following its selection through an open competition among Indian residents. The Indian rupee sign (???) is the currency symbol for the Indian rupee, the official currency of India. Designed by Udaya Kumar, it was presented to the public by the Government of India on 15 July 2010, following its selection through an open competition among Indian residents. The Indian rupee sign (???) is the currency symbol for the Indian rupee, the official currency of India. Designed by Udaya Kumar, it was presented to the public by the Government of India on 15 July 2010, following its selection through an open competition among Indian residents. The Indian rupee sign (???) is the currency symbol for the Indian rupee, the official currency of India. Designed by Udaya Kumar, it was presented to the public by the Government of India on 15 July 2010, following its selection through an open competition among Indian residents"));
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
                    String p = product.get(0).getPrice();
                    price = p.split(",");
                    if(p.toUpperCase().contains("NA") || p.isEmpty() || p == null){
                        productDetailPagePrice.setText("out of stock");
                    }else {
                        productDetailPagePrice.setText(price[0] + "/---");
                    }
//                    productDetailPagePrice.setText("Price coming soon");
                    listDetail.setText(product.get(0).getDescription());
                    listDetail1.setText(product.get(0).getBrand());
                    listDetail2.setText(product.get(0).getCategory());
                    String showing = product.get(0).getSowing_time();
                    String seedRate = product.get(0).getSeed_Rate();
                    String maturity_duration = product.get(0).getMaturity_duration();
                    String colour = product.get(0).getColour();
                    String usp = product.get(0).getUsp();
                    String NumberofSeeds = product.get(0).getNumberOfSeeds();
                    if(showing.contains("NA") || showing.isEmpty() || showing == null){
                        showingTimeLL.setVisibility(View.GONE);
                    }else {
                        ld3.setText(showing);
                    }
                    if(seedRate.contains("NA") || seedRate.isEmpty() || seedRate == null){
                        seedRateLL.setVisibility(View.GONE);
                    }else {
                        ld4.setText(seedRate);
                    }
                    if(maturity_duration.contains("NA") || maturity_duration.isEmpty() || maturity_duration == null){
                        maturityDurationLL.setVisibility(View.GONE);
                    }else {
                        ld5.setText(maturity_duration);
                    }
                    if(colour.contains("NA") || colour.isEmpty() || colour == null){
                        colourLL.setVisibility(View.GONE);
                    }else {
                        ld6.setText(colour);
                    }
                    if(usp.contains("NA") || usp.isEmpty() || usp == null){
                        uspLL.setVisibility(View.GONE);
                    }else {
                        ld7.setText(usp);
                    }
                    if(NumberofSeeds.contains("NA") || NumberofSeeds.isEmpty() || NumberofSeeds == null){
                        NumberofSeedsLL.setVisibility(View.GONE);
                    }else {
                        ld8.setText(NumberofSeeds);
                    }

                    yo= product.get(0).getImage();
                    si = yo.split(",");
                    carouselView.setPageCount(si.length);
                    String sp = product.get(0).getVolume();
                    String[] volume;
                    volume = sp.split(",");
                    volume(volume,price);

                }catch (Exception e){
                    Toast.makeText(ProductDetailActivity.this, e.getMessage(), LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductDetailPageRespones> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();
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
            Toast.makeText(this, e.getMessage(), LENGTH_SHORT).show();
        }

        carouselView.setImageListener(imageListener);



//        carouselView.setImageClickListener(new ImageClickListener() {
//            @Override
//            public void onClick(int position) {
//                ShowDialog(url);
//            }
//        });


        delivery_continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AddId.contains("null")){
                    Toast.makeText(ProductDetailActivity.this, "Please Add Address First", LENGTH_SHORT).show();
                }else{
                   orederPlaced(AddId,token,id,imgs);
                }
            }
        });



    }
    private void volume(String[] volume,String[] price){
         String is = volume[0];
        if (is.toUpperCase().contains("NA") || is == null || is.isEmpty()) {
            findViewById(R.id.spinnerLayout).setVisibility(View.GONE);
        }

        ArrayAdapter adapter = new ArrayAdapter(ProductDetailActivity.this,android.R.layout.simple_spinner_item,volume);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vl = (String) parent.getItemAtPosition(position);
                int cost = spinner.getSelectedItemPosition();
                costs = price[cost];
                if(price[0].toUpperCase().contains("NA") || price[0].isEmpty() ||price[0] == null){
                    productDetailPagePrice.setText("out of stock");
                }else {
                    costs = price[cost];
                    productDetailPagePrice.setText(costs + "/---");
                }
//               Toast.makeText(ProductDetailActivity.this,costs, Toast.LENGTH_SHORT).show();
                selected_volume.setText(vl);
                String token = sharedPrefManager.getValue_string("token");

                String ids= getIntent().getStringExtra("id");
                Call<CartUPdateRespones> callStatus = RetrofitClient.getInstance().getApi().getStatusCart(token,ids,selected_volume.getText().toString());
                callStatus.enqueue(new Callback<CartUPdateRespones>() {
                    @Override
                    public void onResponse(Call<CartUPdateRespones> call, Response<CartUPdateRespones> response) {
                        CartUPdateRespones cartUPdateRespones = response.body();
                        if (response.isSuccessful()) {
                            String str = cartUPdateRespones.getMessage();
                            if (str.contains("Product already exists in cart!")) {
                                cart_on_product_detail.setVisibility(View.GONE);
                                go_cart_on_product_detail.setVisibility(View.VISIBLE);
                                go_cart_on_product_detail.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(ProductDetailActivity.this,MYCartActivity.class));
                                    }
                                });

                            }
                        }else {
                            cart_on_product_detail.setVisibility(View.VISIBLE);
                            go_cart_on_product_detail.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<CartUPdateRespones> call, Throwable t) {
                        Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    private void ShowDialog(String url) {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.open_image_product_detail);
        carouselViews = dialog.findViewById(R.id.carouselViewProductDetail);
        Call<ProductDetailPageRespones> CallDetail = RetrofitClient.getInstance().getApi().getDetail(url);
        CallDetail.enqueue(new Callback<ProductDetailPageRespones>() {
            @Override
            public void onResponse(Call<ProductDetailPageRespones> call, Response<ProductDetailPageRespones> response) {

                try {
                    product = response.body().getProduct();

                    yo= product.get(0).getImage();
                    si = yo.split(",");
                    carouselViews.setPageCount(si.length);
                }catch (Exception e){
                    Toast.makeText(ProductDetailActivity.this, e.getMessage(), LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductDetailPageRespones> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();
            }
        });
        carouselViews.setImageListener(imageListener);
        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(ProductDetailActivity.this)
                    .load("https://arraykartandroid.s3.ap-south-1.amazonaws.com/"+si[position])
                    .placeholder(R.drawable.placeholder)
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

        ///shipping address
        ShippingAddress();

        /////cart page
        CartAll();

    }

    private  void CartAll(){
        ///cart status

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

        String id = getIntent().getStringExtra("id");
        String valume = getIntent().getStringExtra("volume");

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
                    startActivity(new Intent(ProductDetailActivity.this,SignUP.class));
                }
            }
        });

        SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);

        if(userToken.contains("token")) {

            Call<CartUPdateRespones> callStatus = RetrofitClient.getInstance().getApi().getStatusCart(token, id,selected_volume.getText().toString());
            callStatus.enqueue(new Callback<CartUPdateRespones>() {
                @Override
                public void onResponse(Call<CartUPdateRespones> call, Response<CartUPdateRespones> response) {
                    CartUPdateRespones cartUPdateRespones = response.body();
                    if (response.isSuccessful()) {
                        String str = cartUPdateRespones.getMessage();
                        if (str.contains("Product already exists in cart!")) {
                            cart_on_product_detail.setVisibility(View.GONE);
                            go_cart_on_product_detail.setVisibility(View.VISIBLE);
                            go_cart_on_product_detail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(ProductDetailActivity.this,MYCartActivity.class));
                                }
                            });

                        }
                    }else {
                        cart_on_product_detail.setVisibility(View.VISIBLE);
                        go_cart_on_product_detail.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<CartUPdateRespones> call, Throwable t) {
                    Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();
                }
            });
        }

        ///buttons on product detail page

        cart_on_product_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty = product_quantity_text_product_detail_page.getText().toString();
                // api call for add cart
                SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);
                if(!productDetailPagePrice.getText().toString().contains("out of stock")) {
                    if (userToken.contains("token")) {
                        Call<CartAddRespones> callC = RetrofitClient.getInstance().getApi().addToCart(token, id, qty, selected_volume.getText().toString(),costs);
                        callC.enqueue(new Callback<CartAddRespones>() {
                            @Override
                            public void onResponse(Call<CartAddRespones> call, Response<CartAddRespones> response) {
                                if (response.isSuccessful()) {
                                    CartAddRespones cartAddRespones = response.body();
                                    Toast.makeText(ProductDetailActivity.this, cartAddRespones.getMessage(), Toast.LENGTH_SHORT).show();
                                    String str = cartAddRespones.getMessage();
                                    if (str.contains("Successfully added!")) {
                                        cart_on_product_detail.setVisibility(View.GONE);
                                        go_cart_on_product_detail.setVisibility(View.VISIBLE);
                                        go_cart_on_product_detail.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                startActivity(new Intent(ProductDetailActivity.this, MYCartActivity.class));
                                            }
                                        });
                                    }
                                } else {
                                    Toast.makeText(ProductDetailActivity.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<CartAddRespones> call, Throwable t) {
                                Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        startActivity(new Intent(ProductDetailActivity.this, SignUP.class));
                    }
                }else {
                    Toast.makeText(ProductDetailActivity.this,"product out of stock", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void WishList(){

        String id = getIntent().getStringExtra("id");

        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");
        SharedPreferences userToken = getSharedPreferences("arraykartuser",MODE_PRIVATE);

//        wishlist status
        if(userToken.contains("token")){
            Call<CartUPdateRespones> callWishStatus = RetrofitClient.getInstance().getApi().getStatusWishList(token,id);
            callWishStatus.enqueue(new Callback<CartUPdateRespones>() {
                @Override
                public void onResponse(Call<CartUPdateRespones> call, Response<CartUPdateRespones> response) {
                    CartUPdateRespones cartUPdateRespones = response.body();
                    if(response.isSuccessful()){
                        String wstr = cartUPdateRespones.getMessage();
                        if(wstr.contains("Product already exists in wishlist!")){
                            wishListProductsDetail.setChecked(true);
                        }
                    }else {
                        wishListProductsDetail.setChecked(false);
                    }
                }

                @Override
                public void onFailure(Call<CartUPdateRespones> call, Throwable t) {
                    Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();

                }
            });
        }


//    call for add product in wishlist
        wishListProductsDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty = product_quantity_text_product_detail_page.getText().toString();
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
                                                    Toast.makeText(ProductDetailActivity.this, jsonObject.getString("msg"), LENGTH_SHORT).show();

                                                } catch (Exception e) {
                                                    Toast.makeText(ProductDetailActivity.this, e.getMessage(), LENGTH_SHORT).show();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<deleteWishListRespones> call, Throwable t) {
                                            Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();
                                        }
                                    });


                                } else {
                                    Toast.makeText(ProductDetailActivity.this, wishListAddRespones.getMessage(), Toast.LENGTH_LONG).show();
                                    wishListProductsDetail.setChecked(true);
                                }

                            } else {
                                Toast.makeText(ProductDetailActivity.this, "error", LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<WishListAddRespones> call, Throwable t) {
                            Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();
                        }
                    });

//
                }else{
                    wishListProductsDetail.setChecked(false);
                    startActivity(new Intent(ProductDetailActivity.this,SignUP.class));
                }
            }
        });
    }

    private void reviewAll(){
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
    }

    private void ShippingAddress(){
        ///shipping address


        sharedPrefManager = new SharedPrefManager(this);
        String token = sharedPrefManager.getValue_string("token");

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
                        AddId = addressModels.get(0).getId();
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
                        AddId = "null";
                    }

                }

                @Override
                public void onFailure(Call<getSelectedAddressRespones> call, Throwable t) {
                    Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();
                }
            });
        }else {
        }
    }

    private void  orederPlaced(String AddIds,String token,String id,String imgs){
        String qty = product_quantity_text_product_detail_page.getText().toString();
        String price = costs;
        if(!price.contains("out of stock")) {
            int t = Integer.parseInt(qty) * Integer.parseInt(price);
            String total = Integer.toString(t);
            if (t >= 1000) {
                if (!AddIds.contains("null")) {
                    Call<deleteWishListRespones> callOrder = RetrofitClient.getInstance().getApi().orderAdd(token, total, AddIds);
                    callOrder.enqueue(new Callback<deleteWishListRespones>() {
                        @Override
                        public void onResponse(Call<deleteWishListRespones> call, Response<deleteWishListRespones> response) {
                            deleteWishListRespones deleteWishListRespones = response.body();
                            if (response.isSuccessful()) {
                                String order_id = deleteWishListRespones.getMessage();
                                Call<ResponseBody> callDetail = RetrofitClient.getInstance().getApi().OrderDetail(order_id, id, qty, selected_volume.getText().toString());
                                callDetail.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()) {
                                            Intent in = new Intent(ProductDetailActivity.this, OrderPlacedPage.class);
                                            in.putExtra("page", "pd");
                                            in.putExtra("id", id);
                                            in.putExtra("qlt", qty);
                                            in.putExtra("image", imgs);
                                            in.putExtra("total", total);
                                            in.putExtra("order_id", order_id);
                                            in.putExtra("Add", AddIds);
                                            in.putExtra("volume",selected_volume.getText().toString());
                                            in.putExtra("name", pdProductName.getText().toString());
                                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(in);
                                            ProductDetailPageAddressShow.setVisibility(View.GONE);
                                            delivery_continue_btn.setVisibility(View.GONE);
                                            buy_on_product_detail.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<deleteWishListRespones> call, Throwable t) {
                            Toast.makeText(ProductDetailActivity.this, t.getMessage(), LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(this, "Please Add you Address First", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "minimum order value should be 1000", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "product out of stock", Toast.LENGTH_LONG).show();
        }

    }
}
