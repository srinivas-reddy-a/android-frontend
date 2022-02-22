package com.example.arraykart.AddressActivity;

import static com.example.arraykart.AddressActivity.MyAddressActivity.SELECTED_ADDRESS;
import static com.example.arraykart.AddressActivity.MyAddressActivity.refreshAddress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.AddressActivity.AddressFormActivity;
import com.example.arraykart.AddressActivity.AddressModel;
import com.example.arraykart.AllApiModels.AddressDeleteRespones;
import com.example.arraykart.AllRetrofit.RetrofitClient;
import com.example.arraykart.AllRetrofit.SharedPrefManager;
import com.example.arraykart.NotificationPage.NotificationAdapter;
import com.example.arraykart.R;
import com.example.arraykart.Signin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<AddressModel> addressModelList;
    private  int ADDRESSMODE ;
    int allReadySelected;
    int pp ;
    Context context;
    SharedPrefManager sharedPrefManager;

    private OnItemClickListener aListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        aListener=listener;
    }


    public AddressAdapter(List<AddressModel> addressModelList,int ADDRESSMODE,Context context) {
        this.addressModelList = addressModelList;
        this.ADDRESSMODE = ADDRESSMODE;
        this.context  = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_addresses_layout,parent,false);
        return new ViewHolder(view,aListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = addressModelList.get(position).getAddress_name();
        String address1 = addressModelList.get(position).getAddress_line1();
        String address2 = addressModelList.get(position).getAddress_line2();
        String state = addressModelList.get(position).getState();
        String city = addressModelList.get(position).getCity();
        String pin = addressModelList.get(position).getPostal_code();
        String phone = addressModelList.get(position).getPhone_number();
        String selected = addressModelList.get(position).getSelected();
        holder.SetData(name,address1,address2,state,city,pin,phone,selected,position);
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fullName;
        private TextView address1;
        private TextView address2;
        private TextView state;
        private TextView city;
        private TextView pinCode;
        private TextView phone;
        private ImageView icon ;
        private Button removeAddress , EditAddress;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            fullName = itemView.findViewById(R.id.personName);
            address1 = itemView.findViewById(R.id.personAddress1);
            address2 = itemView.findViewById(R.id.personAddress2);
            state = itemView.findViewById(R.id.addressState);
            city = itemView.findViewById(R.id.addressCity);
            pinCode = itemView.findViewById(R.id.addressPinCode);
            phone= itemView.findViewById(R.id.addressPhone);
            icon = itemView.findViewById(R.id.addressSelectTick);
            removeAddress = itemView.findViewById(R.id.removeAddress);
            EditAddress = itemView.findViewById(R.id.EditAddress);

            sharedPrefManager = new SharedPrefManager(context);


            pp = getAdapterPosition();

            removeAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = addressModelList.get(getAdapterPosition()).getId();
                    String token = sharedPrefManager.getValue_string("token");

                    Call<AddressDeleteRespones> callDelete = RetrofitClient.getInstance().getApi().deleteAddress(token,id);

                    callDelete.enqueue(new Callback<AddressDeleteRespones>() {
                        @Override
                        public void onResponse(Call<AddressDeleteRespones> call, Response<AddressDeleteRespones> response) {
//                            addressModelList.remove(addressModelList.get(getAdapterPosition()));
//                            notifyDataSetChanged();
//                            notifyItemRemoved(getAdapterPosition());
                            if(response.isSuccessful()) {
                                Toast.makeText(context, "delete successfully", Toast.LENGTH_SHORT).show();
                            }else {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                    Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                } 
                            }

                        }

                        @Override
                        public void onFailure(Call<AddressDeleteRespones> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

            EditAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, AddressEditForm.class);
                    in.putExtra("id",addressModelList.get(getAdapterPosition()).getId());
                    in.putExtra("user_id",addressModelList.get(getAdapterPosition()).getUser_id());
                    in.putExtra("address_name",addressModelList.get(getAdapterPosition()).getAddress_name());
                    in.putExtra("address_line1",addressModelList.get(getAdapterPosition()).getAddress_line1());
                    in.putExtra("address_line2",addressModelList.get(getAdapterPosition()).getAddress_line2());
                    in.putExtra("city",addressModelList.get(getAdapterPosition()).getCity());
                    in.putExtra("postal_code",addressModelList.get(getAdapterPosition()).getPostal_code());
                    in.putExtra("state",addressModelList.get(getAdapterPosition()).getState());
                    in.putExtra("phone_number",addressModelList.get(getAdapterPosition()).getPhone_number());
                    in.putExtra("alt_num",addressModelList.get(getAdapterPosition()).getAlternate_number());
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in);

                }
            });


        }
        private void SetData(String name,String addresses1,String addresses2,String states,String citys,String pin,String phones,String selected,int position){
            fullName.setText(name);
            address1.setText(addresses1);
            address2.setText(addresses2);
            state.setText(states);
            city.setText(citys);
            pinCode.setText(pin);
            phone.setText(phones);

//            boolean s = false;
//
//            if(selected == "1"){
//                s = true;
//            }else if(selected=="0"){
//                s = false;
//            }

            if (ADDRESSMODE == SELECTED_ADDRESS) {
                icon.setImageResource(R.drawable.ic_check);
                if (selected == "1") {
                    icon.setVisibility(View.VISIBLE);
                    allReadySelected = position;
                } else {
                    icon.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = addressModelList.get(position).getId();
                        String token = sharedPrefManager.getValue_string("token");
                        if (allReadySelected != position) {
                            addressModelList.get(position).setSelected("1");
                            addressModelList.get(allReadySelected).setSelected("0");
                            refreshAddress(allReadySelected, position);
                            allReadySelected = position;

                            Call<ResponseBody> callSelect = RetrofitClient.getInstance().getApi().SelectAddress(token,id);
                            callSelect.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.isSuccessful()) {
                                        Toast.makeText(context.getApplicationContext(), "selected successfully", Toast.LENGTH_LONG).show();
                                    }else {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                            Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                        } catch (JSONException | IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

            }

        }
    }
}
