package com.example.mara.exam.activities;

import android.content.Intent;
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
import com.example.mara.exam.R;
import com.example.mara.exam.adapters.ProjectAdapter;
import com.example.mara.exam.domain.Project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends AppCompatActivity implements View.OnClickListener{


    private List<Project> projects;
    private RecyclerView rv;
    private ProjectAdapter adapter;
    private Button promote;
    private static final String ALL_URL = "http://192.168.42.15:4026/projects";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        projects = new ArrayList<>();
        rv  = findViewById(R.id.projects_rv);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(this));
        promote = findViewById(R.id.promote);
        promote.setOnClickListener(this);

        loadProjects();
    }

    private void loadProjects(){
        StringRequest stringrequest = new StringRequest(Request.Method.GET,ALL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray projectsJ = new JSONArray(response);
                    for (int i = 0; i < projectsJ.length(); i++) {
                        JSONObject gameObject = projectsJ.getJSONObject(i);

                        int id = gameObject.getInt("id");
                        String name = gameObject.getString("name");
                        int budget = gameObject.getInt("budget");
                        String type = gameObject.getString("type");
                        String status = gameObject.getString("status");

                        Project project = new Project(id, name,type, status, budget);
                        projects.add(project);

                    }
                    adapter = new ProjectAdapter(ProjectsActivity.this, projects);
                    rv.setAdapter(adapter);
                    Log.i("Projects loaded","Projects loaded");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProjectsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringrequest);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.promote:{
                Intent i = new Intent(this, PromoteIdeaActivity.class);
                startActivity(i);
                break;
            }
        }
    }
}
