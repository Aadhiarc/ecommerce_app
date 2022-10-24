package com.example.ecommerce_app;

import static com.example.ecommerce_app.DbHelper.COLUMN_EMAIL;
import static com.example.ecommerce_app.DbHelper.TABLE_USER_DETAILS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import soup.neumorphism.NeumorphButton;

public class UserProfile extends AppCompatActivity {
       TextView user_name,user_email;
       Button change_password;
       NeumorphButton logout_btn;
       SharedPreferences sharedPreferences;
       DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        user_name=findViewById(R.id.userName);
        user_email=findViewById(R.id.userEmail);
        change_password=findViewById(R.id.changePassword);
        logout_btn=findViewById(R.id.profileLogout);
        sharedPreferences=getSharedPreferences("loginUser.db",MODE_PRIVATE);
        String loggedUser=sharedPreferences.getString("userEmail","");
        dbHelper=new DbHelper(UserProfile.this);
        userModel user = dbHelper.dataGet(loggedUser);
        user_name.setText(user.getUserName());
        user_email.setText(user.getUserEmail());
         logout_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 sharedPreferences=getSharedPreferences("loginUser.db",MODE_PRIVATE);
                 String loggedUser=sharedPreferences.getString("userEmail","");
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.remove("userEmail");
                    editor.commit();
                    Intent intent=new Intent(UserProfile.this,Filter.class);
                    Toast.makeText(UserProfile.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

             }
         });

    }
}