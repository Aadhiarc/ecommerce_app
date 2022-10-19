package com.example.ecommerce_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    private List<ProductModel> productlist;

    public Adapter(List<ProductModel> productModellist){this.productlist =productModellist;}
    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_design,parent,false);
         return  new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {
          String product_name= productlist.get(position).getTitle();
          String product_price= String.valueOf(productlist.get(position).getPrice());
          holder.product_Name.setText(productlist.get(position).getTitle());
        holder.product_Price.setText(productlist.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder {


        private TextView  product_Name;
        private TextView  product_Price;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            product_Name=itemView.findViewById(R.id.productName);
            product_Price=itemView.findViewById(R.id.productPrice);
        }
    }
}
