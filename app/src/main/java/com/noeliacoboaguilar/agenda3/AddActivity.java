package com.noeliacoboaguilar.agenda3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input, date_input, complete_input;
    Button add_new_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_task_input);
        date_input = findViewById(R.id.date_task_input);
        complete_input = findViewById(R.id.complete_task_input);
        add_new_task = findViewById(R.id.add_new_task);
        add_new_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(AddActivity.this);
                myDB.addTask(name_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        complete_input.getText().toString().trim());
            }
        });

    }


    //BOTON PARA VOLVER
    public void back_home (View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void back_task_list (View view) {
        Intent i = new Intent(this, TaskActivity.class);
        startActivity(i);
    }
}

