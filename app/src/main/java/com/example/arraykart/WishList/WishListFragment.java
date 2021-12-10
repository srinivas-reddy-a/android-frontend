package com.example.arraykart.WishList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.arraykart.R;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        wishListRecyclerView = view.findViewById(R.id.wishList_RecyclerView);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishListRecyclerView.setLayoutManager(linearLayoutManager);

        wishListModelList = new ArrayList<>();
        wishListModelList.add(new WishListModel(R.drawable.img,"Product Name ",2,"4.5","Rs.4999/-","Rs.5999/-","Cash on delivery available"));
        wishListModelList.add(new WishListModel(R.drawable.img,"Product Name ",0,"4.5","Rs.4999/-","Rs.5999/-","Cash on delivery not available"));
        wishListModelList.add(new WishListModel(R.drawable.img,"Product Name ",1,"3.3","Rs.4999/-","Rs.5999/-","Cash on delivery available"));
        wishListModelList.add(new WishListModel(R.drawable.img,"Product Name ",4,"2.5","Rs.4999/-","Rs.5999/-","Cash on delivery not available"));
        wishListModelList.add(new WishListModel(R.drawable.img,"Product Name ",2,"3.7","Rs.4999/-","Rs.5999/-","Cash on delivery available"));
        wishListModelList.add(new WishListModel(R.drawable.img,"Product Name ",5,"3.3","Rs.4999/-","Rs.5999/-","Cash on delivery not available"));
        wishListModelList.add(new WishListModel(R.drawable.img,"Product Name ",0,"5","Rs.4999/-","Rs.5999/-","Cash on delivery available"));

        wishListAdapter = new WishListAdapter(wishListModelList);
        wishListRecyclerView.setAdapter(wishListAdapter);

        wishListAdapter.notifyDataSetChanged();

        wishListAdapter.setOnItemClickListener(new WishListAdapter.onItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                wishListModelList.remove(position);
                wishListAdapter.notifyItemRemoved(position);
            }
        });

        return view;
    }
}