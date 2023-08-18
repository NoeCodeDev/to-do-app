package com.noeliacoboaguilar.agenda3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList task_id, task_name, task_date, task_complete;


    CustomAdapter(Activity activity, Context context, ArrayList task_id, ArrayList task_name, ArrayList task_date, ArrayList task_complete) {
        this.activity = activity;
        this.context = context;
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_date = task_date;
        this.task_complete = task_complete;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.task_id_txt.setText(String.valueOf(task_id.get(position)));
        holder.task_name_txt.setText(String.valueOf(task_name.get(position)));
        holder.task_date_txt.setText(String.valueOf(task_date.get(position)));
        holder.task_complete_txt.setText(String.valueOf(task_complete.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id", String.valueOf(task_id.get(position)));
                intent.putExtra("name", String.valueOf(task_name.get(position)));
                intent.putExtra("date", String.valueOf(task_date.get(position)));
                intent.putExtra("complete", String.valueOf(task_complete.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView task_id_txt,task_name_txt, task_date_txt, task_complete_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_id_txt = itemView.findViewById(R.id.task_id_txt);
            task_name_txt = itemView.findViewById(R.id.task_name_txt);
            task_date_txt = itemView.findViewById(R.id.task_date_txt);
            task_complete_txt = itemView.findViewById(R.id.task_complete_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
