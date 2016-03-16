package tk.partofbodyapp.partofbodyapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class KamusActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    SearchView searchInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //search function
        searchInput = (SearchView) findViewById(R.id.searchInput);
        searchInput.setOnQueryTextListener(new onQueryTextLiestener());
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_about:
                Intent iAbout = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(iAbout);
                this.finish();
                break;
            case R.id.nav_learn:
                Intent iLearn = new Intent(getApplicationContext(), LearnActivity.class);
                startActivity(iLearn);
                this.finish();
                break;
            case R.id.nav_playgame:
                //not yet
                break;
            case R.id.nav_kamus:
                Intent iKamus = new Intent(getApplicationContext(), KamusActivity.class);
                startActivity(iKamus);
                this.finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private class onQueryTextLiestener implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
