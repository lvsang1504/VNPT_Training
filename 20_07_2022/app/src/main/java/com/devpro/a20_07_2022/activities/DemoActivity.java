package com.devpro.a20_07_2022.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.devpro.a20_07_2022.OnClickItemTab1;
import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.fragments.Fragment1;
import com.devpro.a20_07_2022.fragments.Fragment2;
import com.google.android.material.tabs.TabLayout;

public class DemoActivity extends AppCompatActivity {
    ViewPager mViewPager;
    TabLayout mTabLayout;
    OnClickItemTab1 onClickItemTab1;
    Fragment2 fragment2;
    Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        fragment2 = Fragment2.getInstance();
        fragment1 = Fragment1.getInstance();
        fragment1.setOnClickItemTab1(fragment2.getOnClickItemTab1());
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void mapping() {
        mViewPager = findViewById(R.id.viewPager1);
        mTabLayout = findViewById(R.id.tabLayout);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return fragment1;
                case 1:
                    return fragment2;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Tab 1";
                case 1: return "Tab 2";
            }
            return super.getPageTitle(position);
        }
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return new Fragment1();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return new Fragment2();

                default:
                    return new Fragment1();
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

}
