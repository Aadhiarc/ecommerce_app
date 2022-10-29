package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import soup.neumorphism.NeumorphButton;

public class ConfirmOrder extends AppCompatActivity {
     TextInputEditText userName,userEmail,userHouseNo,userStreet,userPincode,userstate,userPhoneNumber;
     NeumorphButton submit;
     DbHelper dbHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        userName=findViewById(R.id.username_Buy);
        userEmail=findViewById(R.id.userEmail_Buy);
        userHouseNo=findViewById(R.id.HouseNumber);
        userStreet=findViewById(R.id.street_Buy);
        userPincode=findViewById(R.id.pincode_BUY);
        userstate=findViewById(R.id.state_Buy);
        userPhoneNumber=findViewById(R.id.mobileNumber_Buy);
        submit=findViewById(R.id.submitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper=new DbHelper(ConfirmOrder.this);
                dbHelper.productDetails(userName.getText().toString(),userEmail.getText().toString(),userHouseNo.getText().toString(),userStreet.getText().toString(),userPincode.getText().toString(),userstate.getText().toString(),userPhoneNumber.getText().toString());
                userDetailsBuyModel user = dbHelper.orderDetails(userEmail.getText().toString());
                System.out.println(user.getUserEmail());
            }
        });


    }
}