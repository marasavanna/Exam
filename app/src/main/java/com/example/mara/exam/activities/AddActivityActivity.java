package com.example.mara.exam.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mara.exam.R;
import com.example.mara.exam.db.BackgroundWorker;

public class AddActivityActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name, type, budget;
    private Button submit;
    private BackgroundWorker bw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);


        name = findViewById(R.id.add_name);
        type = findViewById(R.id.add_type);
        budget = findViewById(R.id.add_budget);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.submit:{
                bw = new BackgroundWorker(this);
                String special = "add";
                String nameI = name.getText().toString();
                String typeI = type.getText().toString();
                String budgetI = budget.getText().toString();

                bw.execute(special,nameI,typeI,budgetI);
                finish();
                break;
            }
        }
    }


}
