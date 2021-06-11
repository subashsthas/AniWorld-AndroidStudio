package com.example.animeworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.nav_bar_buttom);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_id,new HomeFrag()).commit();

        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivity(new Intent(this,Login.class));
            finish();}
    }
    BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFrag = null;

                switch (menuItem.getItemId()){
                    case R.id.home_id:
                        selectedFrag = new HomeFrag();
                        break;
                    case R.id.contact_id:
                        selectedFrag = new ContactFrag();
                        break;
                    case R.id.profile_id:
                        selectedFrag = new ProfileFrag();
                        break;
                }
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.frame_id,selectedFrag).commit();
                return true;
            }
        };
}
