package com.noeliacoboaguilar.agenda3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDataBaseHelper myDB;
    ArrayList<String> task_id, task_name, task_date, task_complete;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                Intent intent = new Intent(TaskActivity.this, AddActivity.class);
                startActivity(intent);
            }

        });

        myDB = new MyDataBaseHelper(TaskActivity.this);
        task_id = new ArrayList<>();
        task_name = new ArrayList<>();
        task_date = new ArrayList<>();
        task_complete = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(TaskActivity.this, this, task_id,task_name,task_date,task_complete);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TaskActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, R.string.no_data, Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                task_id.add(cursor.getString(0));
                task_name.add(cursor.getString(1));
                task_date.add(cursor.getString(2));
                task_complete.add(cursor.getString(3));

            }
        }

    }

//BOTON PARA VOLVER
    public void back_home (View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}