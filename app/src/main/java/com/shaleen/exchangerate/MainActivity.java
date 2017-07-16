package com.shaleen.exchangerate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   ForexRateWorker.AsyncResponse,
                    Spinner.OnItemSelectedListener{

    ForexRateData ratesData = new ForexRateData();
    ArrayList<String> currList = ratesData.currList;
    ArrayAdapter<String> rateListAdapter ;
    ListView forexRateListView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rateListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currList);
        forexRateListView = (ListView) findViewById(R.id.forexratelist);
        forexRateListView.setAdapter(rateListAdapter);

        // Set Base Value Spinner
        Spinner baseValueSpinner = (Spinner) findViewById(R.id.spinner_base);
        baseValueSpinner.setAdapter(rateListAdapter);

        // TODO: 6/26/17 Get Currency from previous session's last selected currency.
        createForexRateWorker("USD");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        // TODO: 6/26/17 Save current base currency for next instance on this activity (for next time we open app).
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void ratesFetchingComplete() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rateListAdapter.notifyDataSetChanged();
            }
        });

        Log.d("Testing", "Response In Main Thread ::" + currList.toString());
    }

    private void createForexRateWorker(String base){
        if(base == null || base.length() == 0){
            Log.e("Exception", "Base Currency Exception: No Base currency found");
            return;
            // TODO: 6/26/17 Create exception classes for different kind of exceptions like base currency exception.
        }
        new ForexRateWorker(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, base, ratesData);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Update Base Currency on option select.
        // TODO: 6/26/17 1. Get from adapter the rate selected. 2. Update the base rate variable. 3. Call create worker with new base
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Nothing required here for our use cases.
    }
}
