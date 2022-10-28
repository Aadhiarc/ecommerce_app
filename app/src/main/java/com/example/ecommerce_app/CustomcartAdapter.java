package com.example.ecommerce_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomcartAdapter extends BaseAdapter {
    Context context;
    private String product_name;
    private String product_price;
    private String product_image;
    LayoutInflater layoutInflater;
    ArrayList<CartviewModel> cartviewModels;

    public CustomcartAdapter( ArrayList<CartviewModel> cartviewModels,Context context,String product_name, String product_price, String product_image) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
        this.context=context;
        this.cartviewModels=cartviewModels;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cartviewModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=layoutInflater.inflate(R.layout.activity_customcartview,null);
        TextView product_Name=view.findViewById(R.id.productName_cart);
        TextView product_Price=view.findViewById(R.id.productPrice_cart);
       ImageView product_Image= view.findViewById(R.id.productImage_cart);
      product_Name.setText(cartviewModels.get(i).getTitle());
      product_Price.setText("$"+" "+cartviewModels.get(i).getPrice());
      Picasso.with(context).load(cartviewModels.get(i).getImage()).into(product_Image);
        return view;
    }
}
