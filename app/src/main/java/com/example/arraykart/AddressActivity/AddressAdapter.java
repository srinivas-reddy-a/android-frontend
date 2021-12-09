package com.example.arraykart.AddressActivity;

import static com.example.arraykart.AddressActivity.MyAddressActivity.SELECTED_ADDRESS;
import static com.example.arraykart.AddressActivity.MyAddressActivity.refreshAddress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<AddressModel> addressModelList;
    private  int ADDRESSMODE ;
    private  int allReadySelected;

    public AddressAdapter(List<AddressModel> addressModelList,int ADDRESSMODE) {
        this.addressModelList = addressModelList;
        this.ADDRESSMODE = ADDRESSMODE;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_addresses_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = addressModelList.get(position).getFullName();
        String address = addressModelList.get(position).getAddress();
        String pin = addressModelList.get(position).getPinCode();
        Boolean selected = addressModelList.get(position).getSelected();
        holder.SetData(name,address,pin,selected,position);
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fullName;
        private TextView address;
        private TextView pinCode;
        private ImageView icon ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.personName);
            address = itemView.findViewById(R.id.personAddress);
            pinCode = itemView.findViewById(R.id.addressPinCode);
            icon = itemView.findViewById(R.id.addressSelectTick);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        private void SetData(String name,String addresses,String pin,boolean selected,int position){
            fullName.setText(name);
            address.setText(addresses);
            pinCode.setText(pin);

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
