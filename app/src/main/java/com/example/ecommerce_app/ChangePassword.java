package com.example.ecommerce_app;

import static com.example.ecommerce_app.DbHelper.COLUMN_EMAIL;
import static com.example.ecommerce_app.DbHelper.COLUMN_PASSWORD;
import static com.example.ecommerce_app.DbHelper.TABLE_USER_DETAILS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import soup.neumorphism.NeumorphButton;

public class ChangePassword extends AppCompatActivity {
    TextView userName;
    TextInputEditText oldPassEdit, newEdit;
    TextInputLayout oldPassLayout, newPassLayout;
    NeumorphButton changeBtn;
    SharedPreferences sharedPreferences;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        userName = findViewById(R.id.chagePasswordUserName);
        oldPassEdit = findViewById(R.id.oldPassword);
        oldPassLayout = findViewById(R.id.OldPasswordLayout);
        newPassLayout = findViewById(R.id.newPasswordLayout);
        newEdit = findViewById(R.id.newPassword);
        changeBtn = findViewById(R.id.changePassword);
        sharedPreferences = getSharedPreferences("loginUser.db", MODE_PRIVATE);
        String loggedUser = sharedPreferences.getString("userEmail", "");
        dbHelper = new DbHelper(ChangePassword.this);
        userModel user = dbHelper.dataGet(loggedUser);
        userName.setText(user.getUserName());


        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getPassword().equals(oldPassEdit.getText().toString())) {
                    SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(COLUMN_PASSWORD, newEdit.getText().toString());
                    sqLiteDatabase.update(TABLE_USER_DETAILS, contentValues, COLUMN_EMAIL + "=?", new String[]{loggedUser});
                    Toast.makeText(ChangePassword.this, "password changed successfully", Toast.LENGTH_SHORT).show();
                    sqLiteDatabase.close();
                    Intent intent = new Intent(ChangePassword.this, login.class);
                    startActivity(intent);
                } else {
                    oldPassLayout.setError("password is not matching");
                }
                if (TextUtils.isEmpty(newEdit.getText().toString())) {
                    newPassLayout.setError("password should not be empty");

                } else if (!TextUtils.isEmpty(newEdit.getText().toString())) {
                    //regex pattern for create password
                    //Minimum eight characters, at least one letter, one number and one special character:
                    Pattern pattern2 = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{5,10}$");
                    Matcher matcher2 = pattern2.matcher(newEdit.getText().toString());
                    boolean regex2 = matcher2.matches();
                    if (!regex2) {
                        newPassLayout.setError("required eight character one letter ,one number and one special character");
                    }
                }
            }
        });
    }
}