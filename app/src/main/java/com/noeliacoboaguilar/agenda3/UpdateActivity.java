package com.noeliacoboaguilar.agenda3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, date_input, complete_input;
    Button update_button, delete_button;

    String id, name, date, complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_task_input2);
        date_input = findViewById(R.id.date_task_input2);
        complete_input = findViewById(R.id.complete_task_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //Primero llamamos al método
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tras llamarlo
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateActivity.this);
                name=name_input.getText().toString().trim();
                date=date_input.getText().toString().trim();
                complete=complete_input.getText().toString().trim();
                myDB.updateData(id, name, date, complete);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")
                && getIntent().hasExtra("date") && getIntent().hasExtra("complete")) {
            //Get los datos del Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            date = getIntent().getStringExtra("date");
            complete = getIntent().getStringExtra("complete");

            //Set los datos del intent
            name_input.setText(name);
            date_input.setText(date);
            complete_input.setText(complete);

        }else{
            Toast.makeText(this,R.string.no_data, Toast.LENGTH_SHORT).show();

            }
        }

        void confirmDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.delete_button);
            builder.setMessage(R.string.confirm_delete);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateActivity.this);
                    myDB.deleteOneRow(id);
                    finish();
                }
            });
            
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    
                }
            });
            builder.create().show();
        }



    //BOTON PARA VOLVER
    public void back_task_list (View view) {
        Intent i = new Intent(this, TaskActivity.class);
        startActivity(i);
    }
    }
