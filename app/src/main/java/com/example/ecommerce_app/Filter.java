package com.example.ecommerce_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Timer;
import java.util.TimerTask;

public class Filter extends AppCompatActivity {
     Button smartPhone,laptops,fragrances,skincare,groceries,homeDecorations;
     ChipNavigationBar chipNavigationBar;
     Timer timer;
     SharedPreferences sharedPreferences;
     DbHelper dbHelper;
     AlertDialog.Builder builder;
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
                        },1000);
                        break;
                    case R.id.bot_nav_profile:
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                sharedPreferences=getSharedPreferences("loginUser.db",MODE_PRIVATE);
                                String loggedUser=sharedPreferences.getString("userEmail","");
                                dbHelper=new DbHelper(Filter.this);
                                try{
                                    userModel user = dbHelper.dataGet(loggedUser);
                                    if(loggedUser.equals(user.getUserEmail())){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(Filter.this, "profile", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Filter.this,UserProfile.class);
                                                startActivity(intent);
                                            }
                                        });

                                    }
                                }catch (Exception e){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent=new Intent(Filter.this,MainActivity.class);
                                            Toast.makeText(Filter.this, "create an account or login to see profile", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }
                        },1000);
                        break;
                    case R.id.bot_nav_add_cart:
                        break;

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        builder=new AlertDialog.Builder(this);
        builder.setTitle("Alert")
                .setMessage("Do you want to exist the app")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }
}