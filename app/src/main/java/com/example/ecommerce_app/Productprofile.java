package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Productprofile extends AppCompatActivity {
    RequestQueue requestQueue;
    TextView name,price,category,brand,description,percentage,ratings,stock;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productprofile);
                Intent intent = getIntent();
                String title=intent.getStringExtra("product_Title");
                String price_intent=intent.getStringExtra("product_Price");
                String images=intent.getStringExtra("product_Images");
                String category_intent=intent.getStringExtra("product_Category");
                String brand_intent=intent.getStringExtra("product_Brand");
                String description_intent=intent.getStringExtra("product_Description");
                String percentage_intent=intent.getStringExtra("discount_Percentage");
                String ratings_intent=intent.getStringExtra("ratings");
                String stock_intent=intent.getStringExtra("stock");

                name=findViewById(R.id.productName_profile);
                price=findViewById(R.id.price_profile);
                category=findViewById(R.id.category_profile);
                brand=findViewById(R.id.brand_profile);
                description=findViewById(R.id.productDescription_profile);
                percentage=findViewById(R.id.dicountpercentage_profile);
                ratings=findViewById(R.id.rating_profile);
                stock=findViewById(R.id.stock_profile);

                name.setText(title);
                price.setText(price_intent);
                category.setText(category_intent);
                brand.setText(brand_intent);
                description.setText(description_intent);
                percentage.setText(percentage_intent);
                ratings.setText(ratings_intent);
                stock.setText(stock_intent);



    }





}