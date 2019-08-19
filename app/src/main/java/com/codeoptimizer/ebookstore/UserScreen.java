package com.codeoptimizer.ebookstore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.codeoptimizer.ebookstore.FragmentsForUser.AboutUs;
import com.codeoptimizer.ebookstore.FragmentsForUser.CartFragment;
import com.codeoptimizer.ebookstore.FragmentsForUser.HomeFragment;
import com.codeoptimizer.ebookstore.FragmentsForUser.ContactUs;
import com.codeoptimizer.ebookstore.FragmentsForUser.WishlistFragment;
import com.codeoptimizer.ebookstore.Utilities.Settings;

import android.provider.MediaStore;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class UserScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , HomeFragment.OnFragmentInteractionListener,
        WishlistFragment.OnFragmentInteractionListener,
        CartFragment.OnFragmentInteractionListener,
        ContactUs.OnFragmentInteractionListener ,
        AboutUs.OnFragmentInteractionListener {

   Fragment fragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
   //ImageView imageView;
  //  Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//imageView.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//
//    }
//});
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();


        fragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.testFrame, fragment);
        //ft.addToBackStack(null);
        ft.commit();

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView userE =(TextView)hView.findViewById(R.id.userE);
  //      imageView = (ImageView)hView.findViewById(R.id.UserPhoto);
        //add image on the layout when clicked
//imageView.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//    }
//});

        if(sharedPreferences.contains("userEmail")){
            userE.setText(sharedPreferences.getString("userEmail",""));
        }
        //--------


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }


//    private void SelectImage(){
//        final CharSequence[] items = {"Camera","Galley","Cancel"};
//
//        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(UserScreen.this);
//        builder.setTitle("Add Image");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if (items[i].equals("Camera")){
//                    Intent i1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(i1,REQUEST_CAMERA);
//
//                }else if(items[i].equals("Gallery")) {
//
//                    Intent i2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    i2.setType("image/*");
//                    startActivityForResult(i2.createChooser(i2,"Select File"),SELECT_FILE);
//
//                }else if (items[i].equals("Cancel")){
//
//                    dialogInterface.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
    // getting result on the image icon
//@Override
//public void onActivityResult(int requestCode,int resultCode,Intent data){
//    super.onActivityResult(requestCode,resultCode,data);
//
//    if (resultCode == Activity.RESULT_OK){
//
//
//        if (requestCode == REQUEST_CAMERA){
//
//
//            Bundle bundle = data.getExtras();
//            final Bitmap bmp = (Bitmap) bundle.get("data");
//            imageView.setImageBitmap(bmp);
//
//
//        }else if (requestCode == SELECT_FILE){
//
//            Uri selectImageUri = data.getData();
//            imageView.setImageURI(selectImageUri);
//
//        }
//    }
//}
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
        getMenuInflater().inflate(R.menu.user_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            fragment = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.testFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();

        }
        else if (id == R.id.wishlist) {
            fragment = new WishlistFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.testFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();

        }else if(id == R.id.cart){
            fragment = new CartFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.testFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }else if (id == R.id.Logout){

            new AlertDialog.Builder(this)
                    .setTitle("Logout?")
                    .setMessage("Do you want to Logout this beautiful app?")
                    .setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    editor.remove("userEmail");
                                    editor.remove("userPassword");
                                    editor.apply();
                                    Intent i =new Intent(UserScreen.this,SplashScreen.class);
                                    startActivity(i);
                                    finish();
                                }
                            })
                    .setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            }).show();

        }else if (id == R.id.contactUs){

            fragment = new ContactUs();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.testFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();

        }else if (id == R.id.aboutUs){

            fragment = new AboutUs();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.testFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
