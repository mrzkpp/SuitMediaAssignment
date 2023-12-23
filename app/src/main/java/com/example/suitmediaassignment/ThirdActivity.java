package com.example.suitmediaassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

public class ThirdActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {

    private static final String API_URL = "https://reqres.in/api/users?page=1&per_page=10";

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;
    private ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        recyclerView = findViewById(R.id.recyclerView);
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList);
        userAdapter.setOnItemClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);

        fetchDataFromApi();

        backButton = findViewById(R.id.back);

        ImageView backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(); // This will simulate the back button press
            }
        });
    }

    private void fetchDataFromApi() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray dataArray = response.getJSONArray("data");
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject userObject = dataArray.getJSONObject(i);
                                int id = userObject.getInt("id");
                                String email = userObject.getString("email");
                                String firstName = userObject.getString("first_name");
                                String lastName = userObject.getString("last_name");
                                String avatar = userObject.getString("avatar");

                                User user = new User(id, email, firstName, lastName, avatar);
                                userList.add(user);
                            }

                            userAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e("JSON", "Error parsing JSON", e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error fetching data", error);
                    }
                });

        queue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click, get the selected user
        User selectedUser = userList.get(position);

        // Update the selectedUserNameTextView in SecondActivity
        Intent intent = new Intent("updateUserName");
        intent.putExtra("selectedUserName", selectedUser.getFullName());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        // You can also show a toast or perform any other actions as needed
        Toast.makeText(this, "Selected User: " + selectedUser.getFullName(), Toast.LENGTH_SHORT).show();
    }








}
