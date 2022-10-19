package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("smartPhones","PASS");
                startActivity(intent);
            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("laptops","PASS");
                startActivity(intent);
            }
        });
        fragrances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("fragrances","PASS");
                startActivity(intent);
            }
        });
        skincare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("skincare","PASS");
                startActivity(intent);
            }
        });
        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("groceries","PASS");
                startActivity(intent);
            }
        });
        homeDecorations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Filter.this,Recyclerview.class);
                intent.putExtra("homedecorations","PASS");
                startActivity(intent);
            }
        });

    }
}