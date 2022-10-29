package com.example.ecommerce_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.SQLOutput;
import java.util.ArrayList;

import soup.neumorphism.NeumorphButton;

public class Addcart extends AppCompatActivity {
    ListView listView;
    DbHelper dbHelper;
    String val;
    SharedPreferences sharedPreferences;
    ArrayList<CartviewModel> cartviewModels;
    AlertDialog.Builder builder;
    NeumorphButton proceedBuy;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcart);
        Intent intent = getIntent();
        String product_title = intent.getStringExtra("productTitle");
        String product_price = intent.getStringExtra("productPrice");
        String product_image = intent.getStringExtra("productThumbnail");
        sharedPreferences = getSharedPreferences("loginUser.db", MODE_PRIVATE);
        String loggedUser = sharedPreferences.getString("userEmail", "");
        dbHelper = new DbHelper(this);
        cartviewModels = new ArrayList<>();
        listView = findViewById(R.id.cartlistview);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM cartTable WHERE email=?", new String[]{loggedUser});
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String images = cursor.getString(3);
            System.out.println(cursor.getString(4));
            cartviewModels.add(new CartviewModel(name, price, images));

        }
        for (CartviewModel item : cartviewModels) {

            CustomcartAdapter adapter = new CustomcartAdapter(cartviewModels, this, item.getTitle(), item.getPrice(), item.getImage());
            listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String position = String.valueOf(i);
                val = cartviewModels.get(i).getTitle();
                System.out.println(cartviewModels.get(i).getTitle());
                builder = new AlertDialog.Builder(Addcart.this);
                builder.setTitle("Alert")
                        .setMessage("Do you want to delete the product")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                                sqLiteDatabase.delete("cartTable", "product_name=?", new String[]{val});
                                Intent intent1 = new Intent(Addcart.this, Addcart.class);
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
        proceedBuy=findViewById(R.id.proceed_Buy_btn);
        proceedBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Addcart.this,ConfirmOrder.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Addcart.this, Filter.class);
        startActivity(intent);
    }
}