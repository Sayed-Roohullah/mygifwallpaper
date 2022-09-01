package com.example.mygifwallpaper.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.mygifwallpaper.Adapters.pagerAdapter;
import com.example.mygifwallpaper.Fragments.DFragment;
import com.example.mygifwallpaper.Fragments.TestFragment;
import com.example.mygifwallpaper.Fragments.wallFragment;
import com.example.mygifwallpaper.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    ArrayList<Fragment> fragments;

    LinearLayout linearLayout;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        adView = findViewById(R.id.ad_view);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
        //image = findViewById(R.id.favourite);
        linearLayout = findViewById(R.id.LFav);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,favoriteActivity.class);
                startActivity(intent);

            }
        });
        fragments =new ArrayList<>();

        fragments.add(new wallFragment());
        fragments.add(new DFragment());
        fragments.add(new TestFragment());

        pagerAdapter pagerAdapter = new pagerAdapter(getSupportFragmentManager(), getApplicationContext(), fragments);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Wallpaper").setIcon(R.drawable.ic_baseline_4k_24);
        tabLayout.getTabAt(1).setText("3D").setIcon(R.drawable.ic_baseline_3d_rotation_24);
        tabLayout.getTabAt(2).setText("Gifs").setIcon(R.drawable.ic_baseline_backup_table_24);

        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setTabIconTint(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setSelectedTabIndicatorColor(Color.GREEN);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(android.R.color.holo_green_light));             }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }
}