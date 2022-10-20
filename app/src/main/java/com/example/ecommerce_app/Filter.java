package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Filter extends AppCompatActivity {
     Button smartPhone,laptops,fragrances,skincare,groceries,homeDecorations;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        smartPhone=findViewById(R.id.smartPhone);
        laptops=findViewById(R.id.laptops);
        fragrances=findViewById(R.id.fragrances);
        skincare=findViewById(R.id.skincare);
        groceries=findViewById(R.id.groceries);
        homeDecorations=findViewById(R.id.homeDecorations);


        smartPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GradientDrawable gradientDrawable = new GradientDrawable();
//                gradientDrawable.setStroke(5,getResources().getColor(R.color.red));
//                smartPhone.setBackground(gradientDrawable);
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("cat","smartphones");
                startActivity(intent);

            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("cat","laptops");
                startActivity(intent);
            }
        });
        fragrances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("cat","fragrances");
                startActivity(intent);
            }
        });
        skincare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("cat","skincare");
                startActivity(intent);
            }
        });
        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("cat","groceries");
                startActivity(intent);
            }
        });
        homeDecorations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("cat","home-decoration");
                startActivity(intent);
            }
        });

    }

}