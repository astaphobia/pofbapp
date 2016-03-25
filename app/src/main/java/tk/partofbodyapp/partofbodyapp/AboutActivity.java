package tk.partofbodyapp.partofbodyapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;

public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FloatingActionButton fbtn_playsound;
    private MediaPlayer mPlayer;
    private final static  String TAG = AboutActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //sound
        fbtn_playsound = (FloatingActionButton) findViewById(R.id.fbtn_playsound);
        fbtn_playsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
            }
        });
    }

    private void playSound() {
        String urlSound = "http://www.yenyenofficial.tk/sounds/about.m4a";
        Log.v(TAG, urlSound);
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mPlayer.setDataSource(urlSound);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_about){
//            Intent i = new Intent(getApplicationContext(), AboutActivity.class);
//            startActivity(i);
//            this.finish();
        } else if (id == R.id.nav_learn){
            Intent i = new Intent(getApplicationContext(), LearnActivity.class);
            startActivity(i);
//            this.finish();
        } else if (id == R.id.nav_playgame){
            Intent i = new Intent(getApplicationContext(), GamemainActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_kamus){
            Intent i = new Intent(getApplicationContext(), KamusActivity.class);
            startActivity(i);
//            this.finish();
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
