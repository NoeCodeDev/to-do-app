package com.noeliacoboaguilar.agenda3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private int day, month, year, week;
    TextView dateToday;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateToday = (TextView) findViewById(R.id.dateToday);
        showDate();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_settings:
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


//    CALENDAR
    public void showDate() {
        Calendar date = Calendar.getInstance();
        day = date.get(Calendar.DAY_OF_MONTH);
        month = (date.get(Calendar.MONTH))+1;
        year = date.get(Calendar.YEAR);
        week = date.get(Calendar.DAY_OF_WEEK);

        String dayWeek = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ROOT).toUpperCase();

        dateToday.setText(dayWeek + ", " + day+ "/" + month+ "/" + year);
    }

//    GO TO ACTIVITIES

    public void go_task (View view) {
        Intent i = new Intent(this, TaskActivity.class);
        startActivity(i);
    }

    public void go_settings (View view) {
        Intent i = new Intent(this,SettingsActivity.class);
        startActivity(i);
    }

    public void go_add_task (View view) {
        Intent i = new Intent(this,AddActivity.class);
        startActivity(i);
    }

}