package tk.partofbodyapp.partofbodyapp;

import android.app.ProgressDialog;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FloatingActionButton fbtn_playsound;
    private MediaPlayer mPlayer;
    private final static  String TAG = AboutActivity.class.getCanonicalName();
    private ProgressDialog progressDialog;
    String isPlaying = "true";
    private FloatingActionButton fbtn_stopsound;
//    private TextView tv_buffer;

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
        //play sound
        fbtn_playsound = (FloatingActionButton) findViewById(R.id.fbtn_playsound);
        fbtn_playsound.setOnClickListener(new playSound());
        //stop sound
        fbtn_stopsound = (FloatingActionButton) findViewById(R.id.fbtn_stopsound);
        fbtn_stopsound.setOnClickListener(new stopSound());
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
            mPlayer.stop();
//            this.finish();
        } else if (id == R.id.nav_playgame){
            Intent i = new Intent(getApplicationContext(), GamemainActivity.class);
            startActivity(i);
            mPlayer.stop();

        } else if (id == R.id.nav_kamus){
            Intent i = new Intent(getApplicationContext(), KamusActivity.class);
            startActivity(i);
            mPlayer.stop();
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
            mPlayer.stop();
            super.onBackPressed();
        }
    }

    private class playSound implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            Log.v(TAG, String.valueOf(mPlayer.isPlaying()));
            progressDialog = new ProgressDialog(AboutActivity.this);
            progressDialog.setMessage("Buffered");
            progressDialog.setCancelable(false);
            progressDialog.show();
            mPlayer = new MediaPlayer();
            String urlSound = "http://www.yenyenofficial.tk/sounds/about.m4a";
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mPlayer.setDataSource(urlSound);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(getApplicationContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return false;
                }
            });
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    mPlayer.start();
                    fbtn_playsound.setVisibility(View.GONE);
                    fbtn_stopsound.setVisibility(View.VISIBLE);

                }
            });
            mPlayer.prepareAsync();

        }
    }

    private class stopSound implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mPlayer.stop();
            fbtn_stopsound.setVisibility(View.GONE);
            fbtn_playsound.setVisibility(View.VISIBLE);
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
