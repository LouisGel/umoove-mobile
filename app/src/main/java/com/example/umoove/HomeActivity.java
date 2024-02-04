package com.example.umoove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Layout
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    // Table Entities
    private User currentUser;

    // Elements
    private Button startButton;
    private TextView pointsText;
    private TextView currentDistance;

    // Values
    private boolean moving = false;
    private Location startLocation;
    private Date startDateTime;
    private final double maxMeanSpeed = 15.0;
    private final double maxAbsoluteSpeed = 40.0;

    // Services
    private HttpService httpService;

    // Listeners
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            double speedKmH = Float.valueOf(location.getSpeed()*3.6f).doubleValue();
            if (moving) {
                if (speedKmH > maxAbsoluteSpeed) {
                    toggleMove(location);
                }
                double distance = calcDistance(startLocation, location);
                currentDistance.setText(String.format("%.2f", distance) + " km");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Fetch Layout
        drawerLayout = findViewById(R.id.home_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();

        // Fetch entities
        currentUser = new User();

        // Set Managers
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationProvider locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        httpService = new HttpService();

        // Set Elements
        pointsText = (TextView) findViewById(R.id.pointsNb);
        pointsText.setText(String.valueOf(currentUser.getPoints()));
        currentDistance = (TextView)findViewById(R.id.currentDistance);
        TextView welcome = (TextView)findViewById(R.id.welcomeText);
        welcome.setText("Hello " + currentUser.getFirstname() + "!");

        startButton = (Button)findViewById(R.id.startMoveButton);

        // Fetch activity details
        try {
            List<Activity> activities = httpService.getActivities();
            Toast toast = Toast.makeText(this, activities.toString(), Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        startButton.setOnClickListener((view) -> {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            toggleMove(location);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void enableLocationSettings() {
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

    private void toggleMove(Location location) {
        if (moving) {
            moving = false;

            Date currentDateTime = Calendar.getInstance().getTime();
            double distance = calcDistance(startLocation, location);
            double seconds = Long.valueOf((currentDateTime.getTime() - startDateTime.getTime())/ 1000).doubleValue();

            double meanSpeed = distance/(seconds/3600.0);

            // Distribute reward
            if (meanSpeed <= maxMeanSpeed) {
                int reward = (int)(100*distance);
                currentUser.setPoints(currentUser.getPoints() + reward);
                pointsText.setText(String.valueOf(currentUser.getPoints()));

                Toast toast = Toast.makeText(this, Integer.toString(reward) + " points received \uD83D\uDCAA", Toast.LENGTH_LONG);
                toast.show();
            }

            currentDistance.setVisibility(View.INVISIBLE);
            startButton.setText("Start");
        } else {
            moving = true;
            startLocation = location;
            startDateTime = Calendar.getInstance().getTime();
            currentDistance.setText("0.0 km");
            currentDistance.setVisibility(View.VISIBLE);
            startButton.setText("Arrived");
        }
    }

    private double calcDistance(Location loc1, Location loc2) {
        // It works ; trust me bro
        return Math.acos((Math.sin(Math.toRadians(loc1.getLatitude()))
                * Math.sin(Math.toRadians(loc2.getLatitude())))
                + (Math.cos(Math.toRadians(loc1.getLatitude()))
                * Math.cos(Math.toRadians(loc2.getLatitude())))
                * (Math.cos(Math.toRadians(loc2.getLongitude())
                - Math.toRadians(loc1.getLongitude())))) * 6371;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_store) {
            Intent i = new Intent(HomeActivity.this, StoreActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_rewards) {
            Intent i = new Intent(HomeActivity.this, MesCoupons.class);
            startActivity(i);
        }

        return false;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}