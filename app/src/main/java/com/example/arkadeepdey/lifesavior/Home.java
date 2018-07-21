package com.example.arkadeepdey.lifesavior;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.emergency);
        Button button3 = findViewById(R.id.request);
        Button button4 = findViewById(R.id.video);
        Button button5 = findViewById(R.id.info);
        ViewPager mViewPager = findViewById(R.id.viewPage);
        ImageAdapter1 adapterView = new ImageAdapter1(this);
        mViewPager.setAdapter(adapterView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Home.this,Emergency.class);
                startActivity(intent);
                Toast.makeText(Home.this,"wait",Toast.LENGTH_LONG).show();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Home.this,Request.class);
                startActivity(intent);
                Toast.makeText(Home.this,"wait",Toast.LENGTH_LONG).show();

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Home.this,mVideo.class);
                startActivity(intent);
                Toast.makeText(Home.this,"wait",Toast.LENGTH_LONG).show();

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Home.this,Info.class);
                startActivity(intent);
                Toast.makeText(Home.this,"wait",Toast.LENGTH_LONG).show();

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        getMenuInflater().inflate(R.menu.home, menu);
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
            Intent intent = new Intent(Home.this, CountDonar.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        User user=new User(Home.this);
        if (id == R.id.nav_home) {
            if(!user.getName().contains("@"))
            Toast.makeText(Home.this,"null",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_profile) {
            if(user.getName().contains("@")) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(Home.this,"Not logged in!!!\n\nRegister/Log In first",Toast.LENGTH_LONG).show();
            }


        } else if (id == R.id.nav_login) {
            if(!user.getName().contains("@")) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }
            else if (user.getName().contains("@")){
                Toast.makeText(Home.this,"Already logged in  :"+user.getName(),Toast.LENGTH_SHORT).show();
            }
        }
        else if (id == R.id.nav_logout) {
            if (user.getName().contains("@")) {
                Toast.makeText(Home.this, "Successfully logout  :" + user.getName(), Toast.LENGTH_SHORT).show();
                user.removeuser();
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }else {
                Toast.makeText(Home.this, "not logged in" + user.getName(), Toast.LENGTH_SHORT).show();
            }
            }



        else if (id == R.id.nav_aboutus) {

        }
        else if (id == R.id.nav_about_Dev) {
            Intent intent =new Intent(Home.this,AboutDev.class);
            startActivity(intent);

        }else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareSub = "Download the app";
            String shareBody = "Download the app LIVE_SAVIOR from the given link and Share this application with your family and friends....\n www.google.com";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } else if (id == R.id.nav_send) {

            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "lifesavior@hotmail.com" });
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
            startActivity(Intent.createChooser(Email, "Send Feedback:"));
            return true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
