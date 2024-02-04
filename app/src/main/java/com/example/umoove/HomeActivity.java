package com.example.umoove;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    // Layout
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    // Table Entities
    private User currentUser;

    // Elements
    private Button startButton;

    // Values
    private boolean moving = false;
    private Location startLocation;
    private Date startDateTime;
    private final double maxMeanSpeed = 15.0;
    private final double maxAbsoluteSpeed = 40.0;

    // Listeners
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            double speedKmH = Float.valueOf(location.getSpeed()*3.6f).doubleValue();
            if (speedKmH > maxAbsoluteSpeed) {
                toggleMove(location);
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

        // Fetch entities
        currentUser = new User();

        // Set Managers
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationProvider locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);


        // Set Elements
        TextView points = (TextView) findViewById(R.id.pointsNb);
        points.setText(String.valueOf(currentUser.getPoints()));

        startButton = (Button)findViewById(R.id.startMoveButton);

        startButton.setOnClickListener((view) -> {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            toggleMove(location);
        });
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
            Toast toast = Toast.makeText(this, String.valueOf(meanSpeed) + "km/h", Toast.LENGTH_LONG);
            toast.show();

            startButton.setText("Start");
        } else {
            moving = true;
            startLocation = location;
            startDateTime = Calendar.getInstance().getTime();
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
}