package tk.partofbodyapp.partofbodyapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class GamemainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private FloatingActionButton floatinginfo;
//    private Button btn_gamelv1, btn_gamelv2, btn_gamelv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //tombol info dibawah kanan
        floatinginfo = (FloatingActionButton) findViewById(R.id.fbtn_infogame);
        floatinginfo.setOnClickListener(new showTutor());

        Button btn_gamelv1 = (Button) findViewById(R.id.btn_gamelv1);
        Button btn_gamelv2 = (Button) findViewById(R.id.btn_gamelv2);
        Button btn_gamelv3 = (Button) findViewById(R.id.btn_gamelv3);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Intent i = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_learn) {
            Intent i = new Intent(getApplicationContext(), LearnActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_playgame) {

        } else if (id == R.id.nav_kamus){
            Intent i = new Intent(getApplicationContext(), KamusActivity.class);
            startActivity(i);
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    //memilih level
    public void startgamelvOnclick(View view){
        int id = view.getId();
        Bundle bundle = new Bundle();
        switch (id) {
            case R.id.btn_gamelv1:
                Intent ilv1 = new Intent(getApplicationContext(), startgameActivity.class);
                bundle.putString("level", "lv1");
                ilv1.putExtras(bundle);
                startActivity(ilv1);
                Toast.makeText(getApplicationContext(), "lv1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_gamelv2:
                Intent ilv2 = new Intent(getApplicationContext(), startgameActivity.class);
                bundle.putString("level", "lv2");
                ilv2.putExtras(bundle);
                startActivity(ilv2);
                Toast.makeText(getApplicationContext(), "lv2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_gamelv3:
                Intent ilv3 = new Intent(getApplicationContext(), startgameActivity.class);
                bundle.putString("level", "lv3");
                ilv3.putExtras(bundle);
                startActivity(ilv3);
                Toast.makeText(getApplicationContext(), "lv3", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //menampilkan cara bermain
    private class showTutor implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), TutorgameActivity.class);
            startActivity(i);
        }
    }
}
