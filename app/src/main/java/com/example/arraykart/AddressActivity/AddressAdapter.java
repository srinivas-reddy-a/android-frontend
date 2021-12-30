package com.example.arraykart.AddressActivity;

import static com.example.arraykart.AddressActivity.MyAddressActivity.SELECTED_ADDRESS;
import static com.example.arraykart.AddressActivity.MyAddressActivity.refreshAddress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.NotificationPage.NotificationAdapter;
import com.example.arraykart.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<AddressModel> addressModelList;
    private  int ADDRESSMODE ;
    int allReadySelected;
    int pp ;

    private OnItemClickListener aListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        aListener=listener;
    }


    public AddressAdapter(List<AddressModel> addressModelList,int ADDRESSMODE) {
        this.addressModelList = addressModelList;
        this.ADDRESSMODE = ADDRESSMODE;
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
        Boolean selected = addressModelList.get(position).getSelected();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            pp = getAdapterPosition();

        }
        private void SetData(String name,String addresses1,String addresses2,String states,String citys,String pin,String phones,boolean selected,int position){
            fullName.setText(name);
            address1.setText(addresses1);
            address2.setText(addresses2);
            state.setText(states);
            city.setText(citys);
            pinCode.setText(pin);
            phone.setText(phones);

            try {
                if (ADDRESSMODE == SELECTED_ADDRESS) {
                    icon.setImageResource(R.drawable.ic_check);
                    if (selected) {
                        icon.setVisibility(View.VISIBLE);
                        allReadySelected = position;
                    } else {
                        icon.setVisibility(View.GONE);
                    }
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (allReadySelected != position) {
                                addressModelList.get(position).setSelected(true);
                                addressModelList.get(allReadySelected).setSelected(false);
                                refreshAddress(allReadySelected, position);
                                allReadySelected = position;
                            }
                        }
                    });

                }
            }catch (Exception e){

            }

        }
    }
}
