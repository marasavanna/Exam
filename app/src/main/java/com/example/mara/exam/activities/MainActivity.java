package com.example.mara.exam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.mara.exam.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        private TextView ideas, projects;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ideas = findViewById(R.id.ideas);
            projects = findViewById(R.id.projects);
            ideas.setOnClickListener(this);
            projects.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.ideas:{
                    Intent i = new Intent(this,IdeasActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.projects:{
                    Intent i = new Intent(this,ProjectsActivity.class);
                    startActivity(i);
                    break;
                }
            }
        }
    }

