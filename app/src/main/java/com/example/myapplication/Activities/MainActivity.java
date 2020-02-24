package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.myapplication.Fragments.ButtonsFragment;
import com.example.myapplication.R;
import com.example.myapplication.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity implements ButtonsFragment.Actions {

    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            case (0):
                                tab.setText("Maps");
                                break;
                            case (1):
                                tab.setText("Recycler");
                                break;
                            case (2):
                                tab.setText("Buttons");
                                break;
                        }
                    }
                });
        tabLayoutMediator.attach();
    }


    @Override
    public void delete() {
        viewPagerAdapter.getMapFragment().deleteLocations();
        viewPager.setCurrentItem(0, true);
    }

    @Override
    public void restore() {
        viewPagerAdapter.getMapFragment().restoreLocations();
        viewPager.setCurrentItem(0, true);
    }
}
