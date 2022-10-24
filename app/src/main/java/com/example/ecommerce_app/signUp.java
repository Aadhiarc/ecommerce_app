package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signUp extends AppCompatActivity {
    TextInputEditText userNameEdit,emailEdit,passwordCreate,passwordEdit;
    TextInputLayout usernameLayout,emailLayout,passwordLayout,confirmPasswordLayout;
    Button registerBtn;
    DbHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userNameEdit=findViewById(R.id.username);
        usernameLayout=findViewById(R.id.usernameLayout);
        emailEdit=findViewById(R.id.signUpEmail);
        emailLayout=findViewById(R.id.signUp_email_Layout);
        passwordEdit=findViewById(R.id.confirm_password);
        passwordCreate=findViewById(R.id.signUpPassword);
        passwordLayout=findViewById(R.id.signUp_password_layout);
        confirmPasswordLayout=findViewById(R.id.confirm_password_layout);
        registerBtn=findViewById(R.id.signUpRegisterBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   if(dataWrite()==true){
                       dbHelper=new DbHelper(signUp.this);
                       dbHelper.dataWrite(userNameEdit.getText().toString(),emailEdit.getText().toString(),passwordEdit.getText().toString());
                       Toast.makeText(signUp.this, "registration done successfully", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(signUp.this,login.class);
                       startActivity(intent);
                   }
            }
        });

    }

    boolean  dataWrite() {
        if(userNameEdit.hasFocus()){
            usernameLayout.setErrorEnabled(true);
            if (TextUtils.isEmpty(userNameEdit.getText().toString())) {
                usernameLayout.setError("username should not be empty");
                return false;
            } else if(!TextUtils.isEmpty(userNameEdit.getText().toString())) {
                //regex pattern for userName
                Pattern pattern = Pattern.compile("^[A-Za-z]{5,29}$");
                Matcher matcher = pattern.matcher(userNameEdit.getText().toString());
                boolean regex = matcher.matches();
                if (!regex) {
                    usernameLayout.setError("minimum five character required(do not use digits or special character; )");
                    return false;
                }
            }
        }
        if (TextUtils.isEmpty(emailEdit.getText().toString())) {
            emailLayout.setError("email should not be empty");
            return false;
        } else if(!TextUtils.isEmpty(emailEdit.getText().toString())) {
            //regex pattern for email
            Pattern pattern1 = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
            Matcher matcher1 = pattern1.matcher(emailEdit.getText().toString());
            boolean regex1 = matcher1.matches();
            if (!regex1) {
                emailLayout.setError("email format is wrong");
                return false;
            }
        }
        if (TextUtils.isEmpty(passwordCreate.getText().toString())) {
            passwordLayout.setError("password should not be empty");
            return false;
        } else if(!TextUtils.isEmpty(passwordCreate.getText().toString())) {
            //regex pattern for create password
            //Minimum eight characters, at least one letter, one number and one special character:
            Pattern pattern2 = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{5,10}$");
            Matcher matcher2 = pattern2.matcher(passwordCreate.getText().toString());
            boolean regex2 = matcher2.matches();
            if (!regex2) {
                passwordLayout.setError("required eight character one letter ,one number and one special character");
                return false;
            }
        }
        if (TextUtils.isEmpty(passwordEdit.getText().toString())) {
            confirmPasswordLayout.setError("confirm password should not be empty");
            return false;
        } else if(!TextUtils.isEmpty(passwordEdit.getText().toString())){
            //regex pattern for confirm password
            Pattern pattern3 = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{5,10}$");
            Matcher matcher3 = pattern3.matcher(passwordEdit.getText().toString());
            boolean regex3 = matcher3.matches();
            if (!regex3) {
                confirmPasswordLayout.setError("required five character one letter ,one number and one special character");
                return false;
            }
        }
        if (passwordCreate.getText().toString().equals(passwordEdit.getText().toString())) {
            return true;
        } else {
            Toast.makeText(this, "pin and confirm is not matching", Toast.LENGTH_SHORT).show();
            return false;
        }


    }

}