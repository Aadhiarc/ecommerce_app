package com.example.ecommerce_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {


    private List<ProductModel> productlist;
    public recyclerViewInterface recyclerViewInterface;
    private Context context;


    public Adapter(List<ProductModel> productModellist, Context context, recyclerViewInterface recyclerViewInterface) {
        this.productlist = productModellist;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;

    }


    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_design, parent, false);
        return new viewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {
        holder.product_Name.setText(productlist.get(position).getTitle());
        holder.product_Price.setText("$" + " " + productlist.get(position).getPrice());
        Picasso.with(context)
                .load(productlist.get(position).getThumbnail())
                .into(holder.product_Image);


    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }

    public void searchView(List<ProductModel> searchViewList) {
        this.productlist = searchViewList;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView product_Name;
        private TextView product_Price;
        private ImageView product_Image;


        public viewHolder(@NonNull View itemView, com.example.ecommerce_app.recyclerViewInterface recyclerViewInterface) {
            super(itemView);
            product_Name = itemView.findViewById(R.id.productName);
            product_Price = itemView.findViewById(R.id.productPrice);
            product_Image = itemView.findViewById(R.id.productImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        recyclerViewInterface.onItemClick(getAdapterPosition());
                    }

                }
            });
        }


    }

}
