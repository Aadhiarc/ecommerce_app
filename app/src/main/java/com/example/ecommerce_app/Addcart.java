package com.example.ecommerce_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class Addcart extends AppCompatActivity {
    ListView listView;
    DbHelper dbHelper;
    SharedPreferences sharedPreferences;
    ArrayList<CartviewModel> cartviewModels;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcart);
        Intent intent=getIntent();
      String product_title= intent.getStringExtra("productTitle");
      String product_price= intent.getStringExtra("productPrice");
      String product_image=intent.getStringExtra("productThumbnail");
        sharedPreferences=getSharedPreferences("loginUser.db",MODE_PRIVATE);
        String loggedUser=sharedPreferences.getString("userEmail","");
        dbHelper=new DbHelper(this);
        cartviewModels=new ArrayList<>();
        listView=findViewById(R.id.cartlistview);
        SQLiteDatabase database=dbHelper.getReadableDatabase();
       Cursor cursor= database.rawQuery("SELECT * FROM cartTable WHERE email=?",new String[]{loggedUser});
        while(cursor.moveToNext()){
            String name=cursor.getString(1);
            String price=cursor.getString(2);
            String images=cursor.getString(3);
           cartviewModels.add(new CartviewModel(name,price,images));

        }for(CartviewModel item:cartviewModels) {

            CustomcartAdapter adapter=new CustomcartAdapter(cartviewModels,this,item.getTitle(),item.getPrice(),item.getImage());
            listView.setAdapter(adapter);
        }
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 String position=String.valueOf(i);
                 builder=new AlertDialog.Builder(Addcart.this);
                 builder.setTitle("Alert")
                         .setMessage("Do you want to delete the product")
                         .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                                 SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
                                 sqLiteDatabase.delete("cartTable","id=?",new String[]{position});
                             }
                         }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                                 dialogInterface.cancel();
                             }
                         }).show();
             }
         });


    }
}