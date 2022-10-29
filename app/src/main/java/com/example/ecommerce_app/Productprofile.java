package com.example.ecommerce_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;


import soup.neumorphism.NeumorphButton;

public class Productprofile extends AppCompatActivity {
    TextView name, price, category, brand, description, percentage, ratings, stock;
    ViewPager viewPager;
    NeumorphButton addToCart,buyBtn;
    SharedPreferences sharedPreferences;
    DbHelper dbHelper;
    AlertDialog.Builder builder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productprofile);
        Intent intent = getIntent();
        String title = intent.getStringExtra("product_Title");
        String price_intent = intent.getStringExtra("product_Price");
        String stringimages = intent.getStringExtra("product_Images");
        String category_intent = intent.getStringExtra("product_Category");
        String brand_intent = intent.getStringExtra("product_Brand");
        String description_intent = intent.getStringExtra("product_Description");
        String percentage_intent = intent.getStringExtra("discount_Percentage");
        String ratings_intent = intent.getStringExtra("ratings");
        String stock_intent = intent.getStringExtra("stock");
        String thumb_intent = intent.getStringExtra("thumbnail");
        name = findViewById(R.id.productName_profile);
        price = findViewById(R.id.price_profile);
        category = findViewById(R.id.category_profile);
        brand = findViewById(R.id.brand_profile);
        description = findViewById(R.id.productDescription_profile);
        percentage = findViewById(R.id.dicountpercentage_profile);
        ratings = findViewById(R.id.rating_profile);
        stock = findViewById(R.id.stock_profile);
        addToCart = findViewById(R.id.addtocartbtn);
        viewPager = findViewById(R.id.viewPager);
        buyBtn=findViewById(R.id.Buy);
        try {
            JSONArray images = new JSONArray(stringimages);
            imageViewAadpter imageViewAadpter = new imageViewAadpter(this, images);
            viewPager.setAdapter(imageViewAadpter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        name.setText(title);
        price.setText(price_intent);
        category.setText(category_intent);
        brand.setText(brand_intent);
        description.setText(description_intent);
        percentage.setText(percentage_intent);
        ratings.setText(ratings_intent);
        stock.setText(stock_intent);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(Productprofile.this);
                builder.setTitle("Alert")
                        .setMessage("Do you want to add the product to cart")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent1 = new Intent(Productprofile.this, Addcart.class);
                                intent1.putExtra("productTitle", title);
                                intent1.putExtra("productPrice", price_intent);
                                intent1.putExtra("productThumbnail", thumb_intent);
                                sharedPreferences = getSharedPreferences("loginUser.db", MODE_PRIVATE);
                                String loggedUser = sharedPreferences.getString("userEmail", "");
                                dbHelper = new DbHelper(Productprofile.this);
                                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("email", loggedUser);
                                contentValues.put("product_name", title);
                                contentValues.put("product_price", price_intent);
                                contentValues.put("product_thumbnail", thumb_intent);
                                sqLiteDatabase.insert("cartTable", null, contentValues);
                                startActivity(intent1);
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();


            }
        });
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Productprofile.this,ConfirmOrder.class);
                startActivity(intent1);

            }
        });

    }


}