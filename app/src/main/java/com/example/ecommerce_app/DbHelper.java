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
    public static final String BUY_TABLE_Buy = "buyTable";
    public static final String USER_NAME_Buy = "user_name";
    public static final String USER_EMAIL_Buy = "user_email";
    public static final String USER_HOUSE_NO_Buy = "user_houseNo";
    public static final String USER_ADDRESS_Buy = "user_address";
    public static final String USER_PINCODE_Buy = "user_pincode";
    public static final String USER_STATE_Buy = "user_state";
    public static final String USER_PHONE_NUMBER_Buy = "user_phoneNumber";

    userModel userModel;
    userDetailsBuyModel userDetailsBuyModel;


    public DbHelper(Context context) {
        super(context, "USER.DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_USER_DETAILS + "(" + COLUMN_ID + " integer primary key autoincrement  ," + COLUMN_USER_NAME + " text ," + COLUMN_EMAIL + " text ," + COLUMN_PASSWORD + " text ," + COLUMN_MOBILE_NUMBER + " text ," + COLUMN_ADDRESS + " text ," + COLUMN_CART + " text )";
        sqLiteDatabase.execSQL(createTable);
        sqLiteDatabase.execSQL("create table cartTable(email text,product_name text,product_price,product_thumbnail text,id integer primary key autoincrement)");
        sqLiteDatabase.execSQL("create table " + BUY_TABLE_Buy + "(" + USER_NAME_Buy + " text," + USER_EMAIL_Buy + " text," + USER_HOUSE_NO_Buy + " text," + USER_ADDRESS_Buy + " text," + USER_PINCODE_Buy + " text," + USER_STATE_Buy + " text," + USER_PHONE_NUMBER_Buy + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //This method is used to insert data into database from signUp page
    public void dataWrite(String userName, String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, userName);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = sqLiteDatabase.insert(TABLE_USER_DETAILS, null, contentValues);
    }
    //This method is used to get data from database and initialize in pojo class

    public userModel dataGet(String user_Email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_USER_DETAILS + "  where " + COLUMN_EMAIL + "=?", new String[]{user_Email});
        while (cursor.moveToNext()) {
            userModel = new userModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        }

        return userModel;
    }

    //This method is used get the information product that user buy
    public userDetailsBuyModel productDetails(String user_name, String user_Email,String user_houseNO,String user_address,String user_pinCode,String user_state,String user_phoneNumber ){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME_Buy,user_name);
        values.put(USER_EMAIL_Buy,user_Email);
        values.put(USER_HOUSE_NO_Buy,user_houseNO);
        values.put(USER_ADDRESS_Buy,user_address);
        values.put(USER_PINCODE_Buy,user_pinCode);
        values.put(USER_STATE_Buy,user_state);
        values.put(USER_PHONE_NUMBER_Buy,user_phoneNumber);
        sqLiteDatabase.insert(BUY_TABLE_Buy,null,values);
        return userDetailsBuyModel;

    }
    //This method is used to get userDetails
    public  userDetailsBuyModel orderDetails(String user_Email){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
      Cursor cursor=  sqLiteDatabase.rawQuery("select * from "+BUY_TABLE_Buy+ " where "+ USER_EMAIL_Buy+ " =? ",new String[]{user_Email});
        while (cursor.moveToNext()){
            userDetailsBuyModel=new userDetailsBuyModel(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getString(6) );
        }
        return userDetailsBuyModel;
    }



}
