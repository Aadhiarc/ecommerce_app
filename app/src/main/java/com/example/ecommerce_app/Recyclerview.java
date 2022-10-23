package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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

public class Recyclerview extends AppCompatActivity  {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<ProductModel> productList;
    ArrayList<ProductModel>filterList;
    Adapter adapter;
    RequestQueue requestQueue;
    SearchView searchView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        requestQueue= Volley.newRequestQueue(this);
        jsonParse();
        searchView=findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchBar(newText);
                return true;
            }


        });

    }
     // search view bar
    private void searchBar(String newText) {
        List<ProductModel> searchViewList=new ArrayList<>();
        for(ProductModel item:productList){
            if(item.getTitle().toLowerCase().contains(newText.toLowerCase())){
                searchViewList.add(item);
            }
        }adapter.searchView(searchViewList);

    }

    private void initializeRecyclerview() {
        recyclerView=findViewById(R.id.recyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new Adapter(filterList,this);
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
                            productList=new ArrayList<ProductModel>();
                            JSONArray jsonArray=response.getJSONArray("products");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject users = jsonArray.getJSONObject(i);
                                productList.add(new ProductModel(users.getInt("id"),users.getString("title"),users.getString("description"),users.getString("price"),users.getDouble("discountPercentage"),users.getDouble("rating"),users.getInt("stock"),users.getString("brand"),users.getString("category"),users.getString("thumbnail"), (JSONArray) users.get("images")));
                            }
                            Intent intent=getIntent();
                            String category=intent.getStringExtra("cat");
                           filterList=new ArrayList<ProductModel>();
                          for(ProductModel item:productList){
                              if(item.getCategory().equals(category)){
                                  filterList.add(item);
                              }
                              if(category.equals("All_products")){
                                      filterList.add(item);
                              }
                          }
                          initializeRecyclerview();
                        }
                        catch (JSONException e) {
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