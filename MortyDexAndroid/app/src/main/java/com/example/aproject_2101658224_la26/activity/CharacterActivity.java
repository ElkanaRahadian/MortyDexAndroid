package com.example.aproject_2101658224_la26.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aproject_2101658224_la26.adapter.CharacterAdapter;
import com.example.aproject_2101658224_la26.R;
import com.example.aproject_2101658224_la26.model.CharacterModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {
    RecyclerView characterRV;
    CharacterAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.itemHeadquarters) {
            Intent intent = new Intent(CharacterActivity.this, HeadquartersActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.itemLogout) {
            Intent intent = new Intent(CharacterActivity.this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        characterRV = findViewById(R.id.recyclerView);
        adapter = new CharacterAdapter(this);
        String url = "https://rickandmortyapi.com/api/character";
        ArrayList<CharacterModel> dariDB = new ArrayList<>();

        if (dariDB.isEmpty()) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            ArrayList<CharacterModel> characterModels = new ArrayList<>();
                            try {
                                JSONArray array = response.getJSONArray("results");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    String name = object.getString("name");
                                    String image = object.getString("image");
                                    characterModels.add(new CharacterModel(name, image));
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            ArrayList<CharacterModel> data = new ArrayList<>();
                            adapter.setData(characterModels);
                            adapter.notifyDataSetChanged();
                            characterRV.setAdapter(adapter);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);
        } else {
            adapter.setData(dariDB);
            adapter.notifyDataSetChanged();
        }
        characterRV.setAdapter(adapter);
        characterRV.setLayoutManager(new LinearLayoutManager(this));
    }
}