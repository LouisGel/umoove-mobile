package com.example.umoove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = (ViewPager2)findViewById(R.id.loginPager);
        viewPager.setAdapter(new LoginPagerAdapter(getSupportFragmentManager(), getLifecycle()));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager, ((tab, position) -> {
            switch(position) {
                case 1: tab.setText(R.string.sign_up); break;
                default: tab.setText(R.string.sign_in); break;
            }
        })).attach();
    }

    private class LoginPagerAdapter extends FragmentStateAdapter {

        public LoginPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position) {
                case 0: return SignInFragment.newInstance("SignInFragment, Instance 1");
                case 1: return SignUpFragment.newInstance("SignUpFragment, Instance 2");
                default: return SignInFragment.newInstance("SignInFragment, Default");
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}