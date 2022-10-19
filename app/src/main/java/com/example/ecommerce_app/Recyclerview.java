package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        requestQueue= Volley.newRequestQueue(this);
        jsonParse();

    }

    private void initializeData() {
        productList=new ArrayList<>();
        productList.add(new ProductModel("machine","200"));
        productList.add(new ProductModel("book","400"));
        productList.add(new ProductModel("perfume","500"));
        productList.add(new ProductModel("power","600"));
    }

    private void initializeRecyclerview() {
        recyclerView=findViewById(R.id.recyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new Adapter(productList);
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
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject users=jsonArray.getJSONObject(i);
                                System.out.println(users.get("title"));
                                System.out.println(users.get("price"));
                                productList.add(new ProductModel(users.getString("title"),users.getString("price")));
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