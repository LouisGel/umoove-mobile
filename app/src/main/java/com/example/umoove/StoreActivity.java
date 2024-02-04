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
        coupons.add(new CouponModel(666, "Coupon -15%", "un coupon douteux...", "IGA", 10));
        coupons.add(new CouponModel(50, "Coupon 10%", "un beau de coupon vert", "IGA", 10));
        coupons.add(new CouponModel(700, "Coupon 151%", "un beau de coupon jaune", "shell", 10));
        coupons.add(new CouponModel(500000, "Fait revenir les Nordiques à Québec", "un beau coupon bleu", "???", 10));

    }
}


