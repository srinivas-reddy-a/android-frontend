package com.example.arraykart.AddressActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arraykart.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<AddressModel> addressModelList;

    public AddressAdapter(List<AddressModel> addressModelList) {
        this.addressModelList = addressModelList;
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
        holder.SetData(name,address,pin);
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fullName;
        private TextView address;
        private TextView pinCode;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.personName);
            address = itemView.findViewById(R.id.personAddress);
            pinCode = itemView.findViewById(R.id.addressPinCode);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        private void SetData(String name,String addresses,String pin){
            fullName.setText(name);
            address.setText(addresses);
            pinCode.setText(pin);
        }
    }
}
