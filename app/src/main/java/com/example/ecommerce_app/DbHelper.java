package com.example.ecommerce_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TABLE_USER_DETAILS = "userDetails";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_NAME = "userName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_MOBILE_NUMBER = "mobileNumber";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_CART = "cart";
    userModel userModel;

    public DbHelper(Context context) {
        super(context, "USER.DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable= "CREATE TABLE " + TABLE_USER_DETAILS + "(" + COLUMN_ID + " integer primary key autoincrement not null ," + COLUMN_USER_NAME + " text not null," + COLUMN_EMAIL + " text not null," + COLUMN_PASSWORD + " text not null," + COLUMN_MOBILE_NUMBER + " text not null," + COLUMN_ADDRESS + " text not null," + COLUMN_CART + " text not null)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
        //This method is used to insert data into database from signUp page
       public void dataWrite(String userName,String email,String password) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_USER_NAME,userName);
        contentValues.put(COLUMN_EMAIL,email);
        contentValues.put(COLUMN_PASSWORD,password);
        long result=sqLiteDatabase.insert(TABLE_USER_DETAILS,null,contentValues);
    }
        //This method is used to get data from database and initialize in pojo class

    public userModel dataGet(String user_Email){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
         Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABLE_USER_DETAILS +"  where " +COLUMN_EMAIL+"=?",new String[]{user_Email});
         while(cursor.moveToNext()){
             userModel=new userModel(cursor.getInt(0), cursor.getString(1),cursor.getString(2), cursor.getString(3),  cursor.getString(4), cursor.getString(5), cursor.getString(6));
         }

        return userModel;
    }

}
