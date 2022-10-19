package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recyclerview extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<ProductModel> productList;
    Adapter adapter;
    RequestQueue requestQueue;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        requestQueue= Volley.newRequestQueue(this);
        jsonParse();

    }

    private void initializeData() {
        productList=new ArrayList<>();
       // productList.add(new ProductModel("machine","200"));
       // productList.add(new ProductModel("book","400"));
        //productList.add(new ProductModel("perfume","500"));
        //productList.add(new ProductModel("power","600"));
    }

    private void initializeRecyclerview() {
        recyclerView=findViewById(R.id.recyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new Adapter(productList,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void jsonParse() {
        String url="https://dummyjson.com/products";
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            productList=new ArrayList<>();
                            JSONArray jsonArray=response.getJSONArray("products");
                            String num;
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject users=jsonArray.getJSONObject(i);
                                intent =new Intent() ;
                                Intent intent=getIntent();
                                num= intent.getStringExtra("smartPhones");
                                num= intent.getStringExtra("laptops");
                                num= intent.getStringExtra("fragrances");
                                num= intent.getStringExtra("skincare");
                                num= intent.getStringExtra("groceries");
                                num=  intent.getStringExtra("homedecorations");
                                System.out.println(num);
                                if(num.equals("PASS")){
                                           for(int a=0;a<=5;a++){
                                               productList.add(new ProductModel(users.getString("title"),users.getString("price"),users.getString("thumbnail")));
                                           }
                                    }else  if(num.equals("PASS")){
                                    for(int a=6;a<=10;a++){
                                        productList.add(new ProductModel(users.getString("title"),users.getString("price"),users.getString("thumbnail")));
                                    }
                                    }else if (num.equals("PASS")){
                                    for(int a=11;a<=15;a++){
                                        productList.add(new ProductModel(users.getString("title"),users.getString("price"),users.getString("thumbnail")));
                                    }
                                } else if (num.equals("PASS")){
                                    for(int a=16;a<=20;a++){
                                        productList.add(new ProductModel(users.getString("title"),users.getString("price"),users.getString("thumbnail")));
                                    }
                                }else if (num.equals("PASS")){
                                    for(int a=21;a<=25;a++){
                                        productList.add(new ProductModel(users.getString("title"),users.getString("price"),users.getString("thumbnail")));
                                    }
                                }else if (num.equals("PASS")){
                                    for(int a=26;a<=30;a++){
                                        productList.add(new ProductModel(users.getString("title"),users.getString("price"),users.getString("thumbnail")));
                                    }
                                }
                            }
                            initializeRecyclerview();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

}