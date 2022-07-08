package com.cigrastudio.booklibrary;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.cigrastudio.booklibrary.fragment.bookFragment;
import com.cigrastudio.booklibrary.fragment.favouriteFragment;
import com.cigrastudio.booklibrary.fragment.logoutFragment;
import com.cigrastudio.booklibrary.fragment.profileFragment;
import com.cigrastudio.booklibrary.fragment.uploadFragment;

public class Bottom_navigation_activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    final Fragment fragment1 = new bookFragment();
    final Fragment fragment2 = new favouriteFragment();
    final Fragment fragment3 = new profileFragment();
    final Fragment fragment4 = new logoutFragment();
    final Fragment fragment5 = new uploadFragment();
    final FragmentManager frmnt = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        frmnt.beginTransaction().add(R.id.frameLayout, fragment5, "5").hide(fragment5).commit();
        frmnt.beginTransaction().add(R.id.frameLayout, fragment4, "4").hide(fragment4).commit();
        frmnt.beginTransaction().add(R.id.frameLayout, fragment3, "3").hide(fragment3).commit();
        frmnt.beginTransaction().add(R.id.frameLayout, fragment2, "2").hide(fragment2).commit();
        frmnt.beginTransaction().add(R.id.frameLayout, fragment1, "1").commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.books:
                    frmnt.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.favourite:
                    frmnt.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.profile:
                    frmnt.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;

                case R.id.logout:
                    frmnt.beginTransaction().hide(active).show(fragment4).commit();
                    active = fragment4;
                    return true;

                case R.id.upload:
                    frmnt.beginTransaction().hide(active).show(fragment5).commit();
                    active = fragment5;
                    return true;

            }
            return false;
        }
    };
}