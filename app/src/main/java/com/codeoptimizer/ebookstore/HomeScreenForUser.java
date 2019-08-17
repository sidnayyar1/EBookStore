package com.codeoptimizer.ebookstore;

import android.content.Intent;
import android.os.Bundle;

import com.codeoptimizer.ebookstore.FragmentsForUser.HomeFragment;
import com.codeoptimizer.ebookstore.FragmentsForUser.ShopByCategoryFragment;
import com.codeoptimizer.ebookstore.FragmentsForUser.WishlistFragment;
import com.codeoptimizer.ebookstore.Utilities.Settings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class HomeScreenForUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_for_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        updateNavigationHeader();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen_for_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){


            case  R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.BasicFrameLayout,new HomeFragment()).commit();
                break;
            // Handle the camera action
            case R.id.wishlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.BasicFrameLayout,new WishlistFragment()).commit();
                break;

            case R.id.shopbycategory:
                getSupportFragmentManager().beginTransaction().replace(R.id.BasicFrameLayout,new ShopByCategoryFragment()).commit();
                break;

            case R.id.Setting:
                Intent i = new Intent(HomeScreenForUser.this,Settings.class);
                startActivity(i);
            case R.id.Logout:
                Intent i2 = new Intent(HomeScreenForUser.this,LoginScreen.class);
                startActivity(i2);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateNavigationHeader(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView Email = headerView.findViewById(R.id.txtEmail);

        Intent intent = getIntent();
        String email =  intent.getStringExtra("email");
        Email.setText(email);

    }
}
