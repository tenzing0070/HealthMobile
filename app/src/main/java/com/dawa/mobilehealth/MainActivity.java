package com.dawa.mobilehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.dawa.fragment.FirstaidFragment;
import com.dawa.fragment.HomeFragment;
import com.dawa.fragment.NotificationFragment;
import com.dawa.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.main_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectFragment = null;
                    switch (item.getItemId()){

                        case R.id.nav_home:
                            selectFragment = new HomeFragment();
                            break;

                        case R.id.nav_notification:
                            selectFragment = new NotificationFragment();
                            Intent intents = new Intent (MainActivity.this,NotificationActivity.class);
                            startActivity(intents);
                            break;

                        case R.id.nav_firstaid:
                            selectFragment = new FirstaidFragment();
                            Intent intent = new Intent(MainActivity.this, FirstaidActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.nav_profile:
                            selectFragment = new ProfileFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,selectFragment).commit();
                    return true;

                }
            };

}
