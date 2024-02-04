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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<CouponModel> coupons = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        setCoupons();


        CouponRecyclerViewAdapter adapter = new CouponRecyclerViewAdapter(this, coupons);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch Layout
        drawerLayout = findViewById(R.id.store_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();
    }

    private void setCoupons(){
        coupons.add(new CouponModel(666, "Coupon -15%", "un coupon douteux...", "IGA", 10));
        coupons.add(new CouponModel(50, "Coupon 10%", "un beau de coupon vert", "IGA", 10));
        coupons.add(new CouponModel(700, "Coupon 151%", "un beau de coupon jaune", "shell", 10));
        coupons.add(new CouponModel(500000, "Fait revenir les Nordiques à Québec", "un beau coupon bleu", "???", 10));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(StoreActivity.this, HomeActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_rewards) {
            // TODO
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}


