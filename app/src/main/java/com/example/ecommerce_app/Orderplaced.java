package com.example.ecommerce_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import soup.neumorphism.NeumorphButton;

public class Orderplaced extends AppCompatActivity {
    DbHelper dbHelper;
    TextView user_Name,user_HouseNumber,user_Street,user_Pincode,user_State,user_Phonenumber;
    SharedPreferences sharedPreferences;
    NeumorphButton confirmBtn,cancelBtn;
    AlertDialog.Builder builder;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderplaced);
        user_Name=findViewById(R.id.user_name_place);
        user_HouseNumber=findViewById(R.id.house_number_place);
        user_Street=findViewById(R.id.street_place);
        user_Pincode=findViewById(R.id.pincode_place);
        user_State=findViewById(R.id.state_place);
        user_Phonenumber=findViewById(R.id.phone_number_place);
        confirmBtn=findViewById(R.id.confirm_order);
        cancelBtn=findViewById(R.id.cancel_order);
        sharedPreferences = getSharedPreferences("loginUser.db", MODE_PRIVATE);
        String loggedUser = sharedPreferences.getString("userEmail", "");
        dbHelper=new DbHelper(this);
        userDetailsBuyModel user = dbHelper.orderDetails(loggedUser);
        user_Name.setText(user.getUserName()+",");
        user_HouseNumber.setText(user.getUserHouseNumber()+",");
        user_Street.setText(user.getUserAddress()+",");
        user_Pincode.setText(user.getUserPinCode()+",");
        user_State.setText(user.getUserState()+".");
        user_Phonenumber.setText(user.getUserPhoneNumber());
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder=new AlertDialog.Builder(Orderplaced.this);
                builder.setTitle("Confirmation")
                        .setMessage("Do you want to buy the product")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Orderplaced.this, "Your ordered has been placed", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(Orderplaced.this,Filter.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();


            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Orderplaced.this,Filter.class);
                startActivity(intent);
            }
        });

    }
}