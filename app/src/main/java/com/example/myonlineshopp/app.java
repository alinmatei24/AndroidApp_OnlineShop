package com.example.myonlineshopp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class app extends AppCompatActivity  {
    public static ArrayList<Product> chart=new ArrayList<Product>();
    String User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        User= getIntent().getStringExtra("User");
        System.out.println(User);
        BottomNavigationView botNav = findViewById(R.id.bottom_nav);
        botNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
          new BottomNavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  Fragment selectedFragment = null;

                  switch (item.getItemId()){
                      case R.id.nav_home:
                          selectedFragment = new HomeFragment();
                          break;
                      case R.id.nav_chart:
                          selectedFragment = new ChartFragment();
                          break;
                      case R.id.nav_profile:
                          selectedFragment = new ProfileFragment();

                          break;
                  }
                  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                  return true;
              }
          };

}