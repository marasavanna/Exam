package com.example.mara.exam.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class PromoteIdeaActivity extends AppCompatActivity {

    private List<Project> projects;
    private RecyclerView rv;
    private Button promote;
    private ProjectAdapter adapter;
    private static final String PROMOTED_URL = "http://192.168.42.15:4026/ideas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote_idea);

        projects = new ArrayList<>();
        rv = findViewById(R.id.promoted_rv);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(this));
        loadIdeas();
    }

    private void loadIdeas() {
        StringRequest stringrequest = new StringRequest(Request.Method.GET, PROMOTED_URL, new Response.Listener<String>() {
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

                        Project project = new Project(id, name, type, status, budget);
                        projects.add(project);

                    }
                    adapter = new ProjectAdapter(PromoteIdeaActivity.this, projects);
                    rv.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PromoteIdeaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringrequest);
    }
}