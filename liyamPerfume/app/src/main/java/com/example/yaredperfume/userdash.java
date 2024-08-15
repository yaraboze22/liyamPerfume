package com.example.yaredperfume;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.Fragment;

import com.example.yaredperfume.fragment.PerfumeFragment;
import com.example.yaredperfume.fragment.aboutus;
import com.example.yaredperfume.fragment.PerfumeFragment;
import com.example.yaredperfume.fragment.home;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class userdash extends AppCompatActivity {

    private RecyclerView rvBestSellers;
    private RecyclerView rvJustArrived;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdash);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);



        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.navigation_home){
                    selectedFragment = new home();
                    Toast.makeText(userdash.this, "home", Toast.LENGTH_SHORT).show();
                }else if(item.getItemId() == R.id.navigation_cart){
                    selectedFragment = new PerfumeFragment();
                    Toast.makeText(userdash.this, "favorits", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.navigation_about) {
                    selectedFragment = new aboutus();
                }else {}

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }


        });

        // Set default fragment
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }}
