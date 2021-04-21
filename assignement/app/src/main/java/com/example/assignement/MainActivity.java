package com.example.assignement;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.btn_more {

    private RecyclerView recyclerView;
    private List<Model> datalist;
    private static String JSON_URL = "https://jsonplaceholder.typicode.com/posts";
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_bar_wall);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        datalist = new ArrayList<>();
        extractData();
    }

    private void extractData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject Modelobj = response.getJSONObject(i);
                                Model modelobj = new Model();
                                modelobj.setDescription(Modelobj.getString("body"));
                                datalist.add(modelobj);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        myAdapter = new MyAdapter(datalist, MainActivity.this, MainActivity.this::more);
                        recyclerView.setAdapter(myAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse:" + error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }

    @Override
    public void more(int positon) {
        Intent intent = new Intent(MainActivity.this, Profile.class);
        startActivity(intent);
    }
}