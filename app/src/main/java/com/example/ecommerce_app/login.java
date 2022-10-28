package com.example.ecommerce_app;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {
    TextInputEditText userEmailEdit, passwordEdit;
    TextInputLayout userEmailLayout, passwordLayout;
    Button loginBtn;
    SharedPreferences sharedPreferences;
    DbHelper dbHelper;
    userModel userModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmailEdit = findViewById(R.id.login_username);
        userEmailLayout = findViewById(R.id.login_usernameLayout);
        passwordEdit = findViewById(R.id.login_accountPassword);
        passwordLayout = findViewById(R.id.login_accountPasswordLayout);
        loginBtn = findViewById(R.id.loginbtn);
        //creating a shared preferences to store the user details until logout
        sharedPreferences = getSharedPreferences("loginUser.db", MODE_PRIVATE);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String user_Email = userEmailEdit.getText().toString();
                String user_Password = passwordEdit.getText().toString();
                editor.putString("userEmail", user_Email);
                editor.commit();
                String loggedUser = sharedPreferences.getString("userEmail", "");
                dbHelper = new DbHelper(login.this);
                userModel user = dbHelper.dataGet(loggedUser);
                try {
                    String userEmail = user.getUserEmail();
                    String userPassword = user.getPassword();
                    //validation for login page
                    if (userEmail.equals(user_Email) && userPassword.equals(user_Password)) {
                        Toast.makeText(login.this, "login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, Filter.class);
                        startActivity(intent);
                    } else if (!userEmail.equals(user_Email)) {
                        userEmailLayout.setError("invalid email address");
                    } else if (!userPassword.equals(user_Password)) {
                        passwordLayout.setError("invalid password");
                    } else if (!userEmail.equals(user_Email) && !userPassword.equals(user_Password)) {
                        Toast.makeText(login.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(userEmailEdit.getText().toString())) {
                        userEmailLayout.setError("email field is empty");
                    } else if (TextUtils.isEmpty(passwordEdit.getText().toString())) {
                        passwordLayout.setError("password field is empty");
                    } else if (TextUtils.isEmpty(userEmailEdit.getText().toString()) && TextUtils.isEmpty(passwordEdit.getText().toString())) {
                        userEmailLayout.setError("email field is empty");
                        passwordLayout.setError("password field is empty");
                    }

                } catch (Exception e) {
                    Toast.makeText(login.this, "user not found", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}