package com.example.umoove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MesCoupons extends AppCompatActivity {

    ArrayList<MesCouponsModel> coupons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        setCoupons();


        MesCouponRecyclerViewAdapter adapter = new MesCouponRecyclerViewAdapter(this, coupons);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setCoupons(){
        coupons.add(new MesCouponsModel(false, "un coupon de 15%", "ABC123", "Shell"));
        coupons.add(new MesCouponsModel(false, "un coupon de 150000%", "ABC124", "Shell"));
        coupons.add(new MesCouponsModel(false, "un fromage gratuit", "COUPON432", "IGA"));
        coupons.add(new MesCouponsModel(false, "un banc dans le bus", "TRANSPORT666", "STM"));
        coupons.add(new MesCouponsModel(false, "un calin", "ITS_A_TRAP", "Bob and co."));

    }
}