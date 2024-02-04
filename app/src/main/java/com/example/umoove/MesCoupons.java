package com.example.umoove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MesCoupons extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<MesCouponsModel> coupons = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // Fetch Layout
        drawerLayout = findViewById(R.id.coupons_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        //drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setNavigationViewListener();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        setCoupons();


        MesCouponRecyclerViewAdapter adapter = new MesCouponRecyclerViewAdapter(this, coupons);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_store) {
            Intent i = new Intent(MesCoupons.this, StoreActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_home) {
            Intent i = new Intent(MesCoupons.this, HomeActivity.class);
            startActivity(i);
        }

        return false;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setCoupons(){
        coupons.add(new MesCouponsModel(false, "un coupon de 15%", "ABC123", "Shell"));
        coupons.add(new MesCouponsModel(false, "un coupon de 150000%", "ABC124", "Shell"));
        coupons.add(new MesCouponsModel(false, "un fromage gratuit", "COUPON432", "IGA"));
        coupons.add(new MesCouponsModel(false, "un banc dans le bus", "TRANSPORT666", "STM"));
        coupons.add(new MesCouponsModel(false, "un calin", "ITS_A_TRAP", "Bob and co."));

    }
}