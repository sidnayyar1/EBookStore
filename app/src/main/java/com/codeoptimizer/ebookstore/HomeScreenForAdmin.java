package com.codeoptimizer.ebookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codeoptimizer.ebookstore.Adapters.ViewPagerAdapterForAdmin;
import com.codeoptimizer.ebookstore.Fragments.AddBook;
import com.codeoptimizer.ebookstore.Fragments.DeleteBook;
import com.codeoptimizer.ebookstore.Fragments.UpdateBook;
import com.codeoptimizer.ebookstore.Fragments.ViewFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeScreenForAdmin extends AppCompatActivity implements
        ViewFragment.OnFragmentInteractionListener,
        AddBook.OnFragmentInteractionListener,
        DeleteBook.OnFragmentInteractionListener,
        UpdateBook.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_for_admin);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("View"));
        tabLayout.addTab(tabLayout.newTab().setText("Add"));
        tabLayout.addTab(tabLayout.newTab().setText("Delete"));
       // tabLayout.addTab(tabLayout.newTab().setText("Update"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new ViewPagerAdapterForAdmin
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}