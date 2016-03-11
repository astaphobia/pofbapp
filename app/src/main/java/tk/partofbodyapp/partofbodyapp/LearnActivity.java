package tk.partofbodyapp.partofbodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LearnActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout drawer;
    private Button btn_lv1, btn_lv2, btn_lv3;

    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_learn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btn_lv1 = (Button) findViewById(R.id.btn_lv1);
        btn_lv1.setOnClickListener(this);
        btn_lv2 = (Button) findViewById(R.id.btn_lv2);
        btn_lv2.setOnClickListener(this);
        btn_lv3 = (Button) findViewById(R.id.btn_lv3);
        btn_lv3.setOnClickListener(this);
    }

    //Button Level
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.btn_lv1){
            Intent i = new Intent(this, LearnLv1Activity.class);
            startActivity(i);
        } else if (id == R.id.btn_lv2){

        } else if (id == R.id.btn_lv3){

        }
    }

    //Button Drawable
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_about){
            Intent i = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(i);
            this.finish();
        } else if (id == R.id.nav_learn){
            Intent i = new Intent(getApplicationContext(), LearnActivity.class);
            startActivity(i);
            this.finish();
        } else if (id == R.id.nav_playgame){

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

//    @Override
//    public boolean onCreateOptionsMenu (Menu menu){
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        return id == R.id.action_settings || super.onOptionsItemSelected(item);
//    }
}
