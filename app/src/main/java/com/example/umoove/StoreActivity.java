package com.example.umoove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    ArrayList<CouponModel> coupons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        setCoupons();


        CouponRecyclerViewAdapter adapter = new CouponRecyclerViewAdapter(this, coupons);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setCoupons(){
        coupons.add(new CouponModel(500, "Coupon 15%", "un criss de coupon", "shell", 10));
        coupons.add(new CouponModel(500, "Coupon 15%", "un criss de coupon", "shell", 10));

    }
}


