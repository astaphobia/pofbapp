package tk.partofbodyapp.partofbodyapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class KamusActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String TAG = KamusActivity.class.getCanonicalName();

    DrawerLayout drawer;
    SearchView searchInput;
    Button searchBtn;
    private ProgressDialog dialog;
    CharSequence querySearch;
    String url = "http://www.yenyenofficial.tk/json/json.php?name=";
    private JSONObject response;
    AsyncTask<String, String, JSONObject> startJson;
    TextView txt_resultVal, txt_kamusResult;
    ImageButton btn_kamusdetail;
    LinearLayout layoutKamusDetail;
    //    public ConsumeResponse delegated = null;

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
        searchInput.setOnQueryTextListener(new onQueryTextListener());
        searchBtn = (Button) findViewById(R.id.btnSearch);
        searchBtn.setOnClickListener(new submitSearch());
        txt_resultVal = (TextView) findViewById(R.id.txt_validatesearch);
        txt_kamusResult = (TextView) findViewById(R.id.txt_kamusResult);
        layoutKamusDetail = (LinearLayout) findViewById(R.id.layoutKamusDetail);
//        btn_kamusdetail = (ImageButton) findViewById(R.id.btn_detailKamus);
//        btn_kamusdetail.setOnClickListener(new kamusDetail());
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_about:
                Intent iAbout = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(iAbout);
//                this.finish();
                break;
            case R.id.nav_learn:
                Intent iLearn = new Intent(getApplicationContext(), LearnActivity.class);
                startActivity(iLearn);
//                this.finish();
                break;
            case R.id.nav_playgame:
                Intent iPlaygamem = new Intent(getApplicationContext(), GamemainActivity.class);
                startActivity(iPlaygamem);
                break;
            case R.id.nav_kamus:
//                Intent iKamus = new Intent(getApplicationContext(), KamusActivity.class);
//                startActivity(iKamus);
//                this.finish();
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

    private class onQueryTextListener implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();
            querySearch = query;
            if(querySearch.length() != 0){
                startJson = new startJson();
                startJson.execute();
            } else {
                Toast.makeText(getApplicationContext(), "Input Query", Toast.LENGTH_SHORT).show();
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
//            Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private class submitSearch implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            querySearch = searchInput.getQuery();
            if(querySearch.length() != 0){
                startJson = new startJson();
                startJson.execute();
            } else {
                Toast.makeText(getApplicationContext(), "Input Query", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class startJson extends AsyncTask<String, String, JSONObject>{
        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                URL fullUrl = new URL(url+querySearch);
                HttpURLConnection connection = (HttpURLConnection) fullUrl.openConnection();
                connection.setRequestProperty("Content-Type", "application/json");
                connection.connect();

                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String json;
                while ((json = br.readLine()) != null){
                    sb.append(json);
                }
                br.close();
                connection.disconnect();

                response = new JSONObject(sb.toString());
            } catch (JSONException | IOException e) {
//                e.printStackTrace();
                response = throwException(e);
            }

            return response;
        }
        @Override
        public void onPreExecute(){
            super.onPreExecute();
            dialog = ProgressDialog.show(KamusActivity.this, "", "Connecting..");
            dialog.setCancelable(false);
        }
        @Override
        public void onPostExecute(JSONObject response){
            super.onPostExecute(response);
            dialog.dismiss();
            StringBuilder sb = new StringBuilder();
            if(response == null){
                txt_resultVal.setVisibility(View.VISIBLE);
                layoutKamusDetail.setVisibility(View.GONE);
                Toast.makeText(KamusActivity.this, "response is NULL", Toast.LENGTH_SHORT).show();
            } else if (response.has("error")){
                Log.v(TAG, "response: " + response);
                txt_resultVal.setVisibility(View.VISIBLE);
                layoutKamusDetail.setVisibility(View.GONE);
                try {
                    sb.append(response.getJSONObject("error").getString("resource"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                Log.v(TAG, "response: " + response);
                try {
                    String name_indo = response.getString("name_indo");
                    String name_eng = response.getString("name_eng");
                    txt_kamusResult.setText(name_indo + "/" + name_eng);
                    layoutKamusDetail.setVisibility(View.VISIBLE);
                    txt_resultVal.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                LearnViewActivity.response = response;
//                Intent i = new Intent(KamusActivity.this, LearnViewActivity.class);
//                startActivity(i);
            }

        }

    }
    private JSONObject throwException(Exception e) {
        JSONObject error = new JSONObject();
        try {
            error.put("error", true);
            error.put("message",(new JSONObject()).put("resource", e.getMessage()));
            return error;
        } catch (JSONException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    private class kamusDetail implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }

//    @Override
//    public void consumeResponse(JSONObject response) {
//        dialog.dismiss();
//        StringBuilder sb = new StringBuilder();
//        if(response == null){
//            Toast.makeText(KamusActivity.this, "response is NULL", Toast.LENGTH_SHORT).show();
//        } else if (response.has("error")){
//            try {
//                sb.append(response.getJSONObject("error").getString("resource"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }else{
//            Log.v(TAG, "response: " + response);
//            LearnViewActivity.response = response;
//            Intent i = new Intent(KamusActivity.this, LearnViewActivity.class);
//            startActivity(i);
//        }
//    }
}
