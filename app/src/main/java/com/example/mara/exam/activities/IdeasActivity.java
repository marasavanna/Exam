package com.example.mara.exam.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mara.exam.ConnectivityChecker;
import com.example.mara.exam.R;
import com.example.mara.exam.adapters.IdeaAdapter;
import com.example.mara.exam.db.LocalDb;
import com.example.mara.exam.domain.Project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IdeasActivity extends AppCompatActivity implements View.OnClickListener {

    List<Project> projects;
    RecyclerView rv;
    IdeaAdapter adapter;
    Button addIdea, deleteAll;
    LocalDb db;
    private static final String IDEAS_URL = "http://192.168.42.15:4026/ideas";
    private ConnectivityChecker checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);

        projects = new ArrayList<>();
        rv = findViewById(R.id.ideas_rv);

        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(this));



        addIdea = findViewById(R.id.add_idea);
        addIdea.setOnClickListener(this);

        deleteAll = findViewById(R.id.delete_locally);
        deleteAll.setOnClickListener(this);

        db = new LocalDb(this);
        checker = new ConnectivityChecker(this);

        if(checker.isConnected()){
            loadIdeas();
        }else{
            Toast.makeText(this,"You are not connected",Toast.LENGTH_SHORT).show();
            loadIdeasocally();
        }


    }

    public void loadIdeas(){
        StringRequest stringrequest = new StringRequest(Request.Method.GET,IDEAS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray projectsJ = new JSONArray(response);
                   // db.deleteCars();
                    for (int i = 0; i < projectsJ.length(); i++) {
                        JSONObject gameObject = projectsJ.getJSONObject(i);

                        int id = gameObject.getInt("id");
                        String name = gameObject.getString("name");
                        int budget = gameObject.getInt("budget");
                        String type = gameObject.getString("type");
                        String status = gameObject.getString("status");

                        Project project = new Project(id, name,type, status, budget);
                        projects.add(project);
                        db.insertIdea(name,budget,type,status);
                        Log.i("Ideas loaded","Ideas loaded");

                    }
                    adapter = new IdeaAdapter(IdeasActivity.this, projects);
                    rv.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IdeasActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringrequest);
    }

    private void loadIdeasocally() {
        Cursor cursor = db.getAllIdeas();
        if (cursor.moveToFirst()) {
            do {
                projects.add(new Project(0, cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        adapter = new IdeaAdapter(this, projects);
        rv.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_idea:{
                Intent i = new Intent(this, AddActivityActivity.class);
                startActivity(i);
                break;
            }
            case R.id.delete_locally:{
                db.deleteCars();
                break;
            }
        }
    }


}
