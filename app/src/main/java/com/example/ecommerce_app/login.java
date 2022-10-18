package com.example.ecommerce_app;

import static com.example.ecommerce_app.DbHelper.COLUMN_EMAIL;
import static com.example.ecommerce_app.DbHelper.TABLE_USER_DETAILS;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {
    TextInputEditText userEmailEdit,passwordEdit;
    TextInputLayout userEmailLayout,passwordLayout;
    Button loginBtn;
    SharedPreferences sharedPreferences;
    DbHelper dbHelper;
    userModel userModel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmailEdit=findViewById(R.id.login_username);
        userEmailLayout=findViewById(R.id.login_usernameLayout);
        passwordEdit=findViewById(R.id.login_accountPassword);
        passwordLayout=findViewById(R.id.login_accountPasswordLayout);
        loginBtn=findViewById(R.id.loginbtn);
        //creating a shared preferences to store the user details until logout
        sharedPreferences=getSharedPreferences("loginUser.db",MODE_PRIVATE);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String user_Email=userEmailEdit.getText().toString();
                editor.putString("userEmail",user_Email);
                editor.commit();
               String loggedUser=sharedPreferences.getString("userEmail","");
                dbHelper=new DbHelper(login.this);
                userModel=new userModel();
                SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
                Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLE_USER_DETAILS +"  where " +COLUMN_EMAIL+"=?",new String[]{loggedUser});
                






            }
        });
    }
}