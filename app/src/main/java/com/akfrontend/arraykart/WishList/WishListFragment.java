package com.akfrontend.arraykart.WishList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akfrontend.arraykart.AllApiModels.getWishListRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.AllRetrofit.SharedPrefManager;
import com.akfrontend.arraykart.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link WishListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class WishListFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public WishListFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment WishListFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static WishListFragment newInstance(String param1, String param2) {
//        WishListFragment fragment = new WishListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    private RecyclerView wishListRecyclerView;
    private WishListAdapter wishListAdapter;
    private List<WishListModel> wishListModelList ;
    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPrefManager = new SharedPrefManager(getContext());
        String token = sharedPrefManager.getValue_string("token");
        SharedPreferences user_token = getContext().getSharedPreferences("arraykartuser", Activity.MODE_PRIVATE);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        wishListRecyclerView = view.findViewById(R.id.wishList_RecyclerView);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishListRecyclerView.setLayoutManager(linearLayoutManager);

        wishListModelList = new ArrayList<>();
//        wishListModelList.add(new WishListModel("1",R.drawable.img,"Product Name ",2,"4.5","Rs.4999/-","Rs.5999/-","Cash on delivery available"));
//        wishListModelList.add(new WishListModel("2",R.drawable.img,"Product Name ",0,"4.5","Rs.4999/-","Rs.5999/-","Cash on delivery not available"));
//        wishListModelList.add(new WishListModel("3",R.drawable.img,"Product Name ",1,"3.3","Rs.4999/-","Rs.5999/-","Cash on delivery available"));
//        wishListModelList.add(new WishListModel("4",R.drawable.img,"Product Name ",4,"2.5","Rs.4999/-","Rs.5999/-","Cash on delivery not available"));
//        wishListModelList.add(new WishListModel("5",R.drawable.img,"Product Name ",2,"3.7","Rs.4999/-","Rs.5999/-","Cash on delivery available"));
//        wishListModelList.add(new WishListModel("6",R.drawable.img,"Product Name ",5,"3.3","Rs.4999/-","Rs.5999/-","Cash on delivery not available"));
//        wishListModelList.add(new WishListModel("7",R.drawable.img,"Product Name ",0,"5","Rs.4999/-","Rs.5999/-","Cash on delivery available"));

//        wishListAdapter = new WishListAdapter(wishListModelList,getContext());
//        wishListRecyclerView.setAdapter(wishListAdapter);
//
//        wishListAdapter.notifyDataSetChanged();

      //call wishlist products
    if(user_token.contains("token")) {
        Call<getWishListRespones> call = RetrofitClient.getInstance().getApi().getWishList(token);
        call.enqueue(new Callback<getWishListRespones>() {
            @Override
            public void onResponse(Call<getWishListRespones> call, Response<getWishListRespones> response) {
                getWishListRespones getWishListRespones = response.body();
                if(response.isSuccessful()) {
                    wishListModelList = getWishListRespones.getProducts();
                    wishListAdapter = new WishListAdapter(wishListModelList, getContext());
                    wishListRecyclerView.setAdapter(wishListAdapter);
                    wishListAdapter.notifyDataSetChanged();
                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        //Toast.makeText(getContext(), jsonObject.getString("message  "), Toast.LENGTH_SHORT).show();
                        view.findViewById(R.id.ContinueShopping).setVisibility(View.VISIBLE);


                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<getWishListRespones> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }else{
        Toast.makeText(getContext(), "SignUp first", Toast.LENGTH_SHORT).show();
    }

        view.findViewById(R.id.ContinueShopping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });

//        wishListAdapter = new WishListAdapter(wishListModelList,getContext());
//        wishListRecyclerView.setAdapter(wishListAdapter);
//        wishListAdapter.setOnItemClickListener(new WishListAdapter.onItemClickListener() {
//            @Override
//            public void onDeleteClick(int position) {
//                String id = wishListModelList.get(position).getId();
////                remove wishlist call
//
//                Call<deleteWishListRespones> callD = RetrofitClient.getInstance().getApi().deleteWishList("application/json",token,id);
//                callD.enqueue(new Callback<deleteWishListRespones>() {
//                    @Override
//                    public void onResponse(Call<deleteWishListRespones> call, Response<deleteWishListRespones> response) {
//                        deleteWishListRespones deleteWishListRespones = response.body();
//                        if (response.isSuccessful()){
//                            Toast.makeText(getContext(), deleteWishListRespones.getMessage(), Toast.LENGTH_LONG).show();
//                        }else {
//                            try {
//                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
//                                Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
//
//                            } catch (Exception e) {
//                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<deleteWishListRespones> call, Throwable t) {
//                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
////                Intent re = new Intent(getContext(),WishListFragment.class);
////                re.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                getContext().startActivity(re);
////                ((Activity)getContext()).finish();
//            }
//        });

        return view;
    }

}