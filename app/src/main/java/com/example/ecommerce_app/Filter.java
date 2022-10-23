package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Timer;
import java.util.TimerTask;

public class Filter extends AppCompatActivity {
     Button smartPhone,laptops,fragrances,skincare,groceries,homeDecorations;
     ChipNavigationBar chipNavigationBar;
     Button bot_all_pro,bot_pro,bot_cart;
     Timer timer;
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
        chipNavigationBar=findViewById(R.id.chip_btm_nav_bar);
        timer=new Timer();
        smartPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch(i){
                    case R.id.bot_nav_all_products:
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(Filter.this,Recyclerview.class);
                                intent.putExtra("cat","All_products");
                                startActivity(intent);
                            }
                        },2000);

                        break;
                    case R.id.bot_nav_profile:
                        break;
                    case R.id.bot_nav_add_cart:
                        break;

                }
            }
        });


    }

    }