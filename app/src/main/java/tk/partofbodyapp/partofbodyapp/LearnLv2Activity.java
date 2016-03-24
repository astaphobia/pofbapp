package tk.partofbodyapp.partofbodyapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class LearnLv2Activity extends AppCompatActivity implements ConsumeResponse{
    final String TAG = LearnLv2Activity.class.getCanonicalName();
    public static String query= "";
    private boolean isFabOpen = false;
    FloatingActionButton language, language_en, language_id;
    Animation fab_open, fab_close, rotate_forward, rotate_backward;
    static ProgressDialog Dialog;
    static String lang = "id";
    TextView lv1_txt1_id, lv1_txt1_en, lv1_txt2_id, lv1_txt2_en, lv1_txt3_id, lv1_txt3_en, lv1_txt4_id, lv1_txt4_en, lv1_txt5_id, lv1_txt5_en, lv1_txt6_id, lv1_txt6_en, lv1_txt7_id, lv1_txt7_en, lv1_txt8_id, lv1_txt8_en, lv1_txt9_id, lv1_txt9_en, lv1_txt10_id, lv1_txt10_en;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_lv2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        nama bagian tubuh
        lv1_txt1_id = (TextView) findViewById(R.id.lv1_txt1_id);
        lv1_txt1_en = (TextView) findViewById(R.id.lv1_txt1_en);
        lv1_txt2_id = (TextView) findViewById(R.id.lv1_txt2_id);
        lv1_txt2_en = (TextView) findViewById(R.id.lv1_txt2_en);
        lv1_txt3_id = (TextView) findViewById(R.id.lv1_txt3_id);
        lv1_txt3_en = (TextView) findViewById(R.id.lv1_txt3_en);
        lv1_txt4_id = (TextView) findViewById(R.id.lv1_txt4_id);
        lv1_txt4_en = (TextView) findViewById(R.id.lv1_txt4_en);
        lv1_txt5_id = (TextView) findViewById(R.id.lv1_txt5_id);
        lv1_txt5_en = (TextView) findViewById(R.id.lv1_txt5_en);
        lv1_txt6_id = (TextView) findViewById(R.id.lv1_txt6_id);
        lv1_txt6_en = (TextView) findViewById(R.id.lv1_txt6_en);
        lv1_txt7_id = (TextView) findViewById(R.id.lv1_txt7_id);
        lv1_txt7_en = (TextView) findViewById(R.id.lv1_txt7_en);
        lv1_txt8_id = (TextView) findViewById(R.id.lv1_txt8_id);
        lv1_txt8_en = (TextView) findViewById(R.id.lv1_txt8_en);
        lv1_txt9_id = (TextView) findViewById(R.id.lv1_txt9_id);
        lv1_txt9_en = (TextView) findViewById(R.id.lv1_txt9_en);
        lv1_txt10_id = (TextView) findViewById(R.id.lv1_txt10_id);
        lv1_txt10_en = (TextView) findViewById(R.id.lv1_txt10_en);

//        language = (FloatingActionButton) findViewById(R.id.language);
//        language_en = (FloatingActionButton) findViewById(R.id.language_en);
//        language_id = (FloatingActionButton) findViewById(R.id.language_id);
//        //animate
//        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
//        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
//        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
//        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
//        language.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                animateFab();
//            }
//        });
//        language_en.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
//                lang = "en";
//                languagetoEN();
//            }
//            private void languagetoEN() {
//                lv1_txt1_id.setVisibility(View.GONE);
//                lv1_txt1_en.setVisibility(View.VISIBLE);
//                lv1_txt2_id.setVisibility(View.GONE);
//                lv1_txt2_en.setVisibility(View.VISIBLE);
//                lv1_txt3_id.setVisibility(View.GONE);
//                lv1_txt3_en.setVisibility(View.VISIBLE);
//                lv1_txt4_id.setVisibility(View.GONE);
//                lv1_txt4_en.setVisibility(View.VISIBLE);
//                lv1_txt5_id.setVisibility(View.GONE);
//                lv1_txt5_en.setVisibility(View.VISIBLE);
//                lv1_txt6_id.setVisibility(View.GONE);
//                lv1_txt6_en.setVisibility(View.VISIBLE);
//                lv1_txt7_id.setVisibility(View.GONE);
//                lv1_txt7_en.setVisibility(View.VISIBLE);
//                lv1_txt8_id.setVisibility(View.GONE);
//                lv1_txt8_en.setVisibility(View.VISIBLE);
//                lv1_txt9_id.setVisibility(View.GONE);
//                lv1_txt9_en.setVisibility(View.VISIBLE);
//                lv1_txt10_id.setVisibility(View.GONE);
//                lv1_txt10_en.setVisibility(View.VISIBLE);
//            }
//        });
//        language_id.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Indonesia", Toast.LENGTH_SHORT).show();
//                lang = "id";
//                languagetoID();
//            }
//
//            private void languagetoID() {
//                lv1_txt1_id.setVisibility(View.VISIBLE);
//                lv1_txt1_en.setVisibility(View.GONE);
//                lv1_txt2_id.setVisibility(View.VISIBLE);
//                lv1_txt2_en.setVisibility(View.GONE);
//                lv1_txt3_id.setVisibility(View.VISIBLE);
//                lv1_txt3_en.setVisibility(View.GONE);
//                lv1_txt4_id.setVisibility(View.VISIBLE);
//                lv1_txt4_en.setVisibility(View.GONE);
//                lv1_txt5_id.setVisibility(View.VISIBLE);
//                lv1_txt5_en.setVisibility(View.GONE);
//                lv1_txt6_id.setVisibility(View.VISIBLE);
//                lv1_txt6_en.setVisibility(View.GONE);
//                lv1_txt7_id.setVisibility(View.VISIBLE);
//                lv1_txt7_en.setVisibility(View.GONE);
//                lv1_txt8_id.setVisibility(View.VISIBLE);
//                lv1_txt8_en.setVisibility(View.GONE);
//                lv1_txt9_id.setVisibility(View.VISIBLE);
//                lv1_txt9_en.setVisibility(View.GONE);
//                lv1_txt10_id.setVisibility(View.VISIBLE);
//                lv1_txt10_en.setVisibility(View.GONE);
//            }
//        });


        LinearLayout lv1_btn1 = (LinearLayout) findViewById(R.id.lv1_btn1);
        lv1_btn1.setClickable(true);
        LinearLayout lv1_btn2 = (LinearLayout) findViewById(R.id.lv1_btn2);
        lv1_btn2.setClickable(true);
        LinearLayout lv1_btn3 = (LinearLayout) findViewById(R.id.lv1_btn3);
        lv1_btn3.setClickable(true);
        LinearLayout lv1_btn4 = (LinearLayout) findViewById(R.id.lv1_btn4);
        lv1_btn4.setClickable(true);
        LinearLayout lv1_btn5 = (LinearLayout) findViewById(R.id.lv1_btn5);
        lv1_btn5.setClickable(true);
        LinearLayout lv1_btn6 = (LinearLayout) findViewById(R.id.lv1_btn6);
        lv1_btn6.setClickable(true);
        LinearLayout lv1_btn7 = (LinearLayout) findViewById(R.id.lv1_btn7);
        lv1_btn7.setClickable(true);
        LinearLayout lv1_btn8 = (LinearLayout) findViewById(R.id.lv1_btn8);
        lv1_btn8.setClickable(true);
        LinearLayout lv1_btn10 = (LinearLayout) findViewById(R.id.lv1_btn10);
        lv1_btn10.setClickable(true);
        LinearLayout lv1_btn9 = (LinearLayout) findViewById(R.id.lv1_btn9);
        lv1_btn9.setClickable(true);
    }

    public void partOnClick(View view){
        switch (view.getId()){
            case R.id.lv1_btn1:
                query = "Leher";
                startDialog();
                break;
            case R.id.lv1_btn2:
                query = "Lengan";
                startDialog();
                break;
            case R.id.lv1_btn3:
                query = "Siku";
                startDialog();
                break;
            case R.id.lv1_btn4:
                query = "Jari";
                startDialog();
                break;
            case R.id.lv1_btn5:
                query = "Kaki";
                startDialog();
                break;
            case R.id.lv1_btn6:
                query = "Tangan";
                startDialog();
                break;
            case R.id.lv1_btn7:
                query = "Lutut";
                startDialog();
                break;
            case R.id.lv1_btn8:
                query = "Otot";
                startDialog();
                break;
            case R.id.lv1_btn9:
                query = "Bahu";
                startDialog();
                break;
            case R.id.lv1_btn10:
                query = "Punggung";
                startDialog();
                break;
        }
    }

    private void startDialog() {
        Dialog = ProgressDialog.show(LearnLv2Activity.this, "", "Connecting..");
        Dialog.setCancelable(false);
        jsonGetpartLv2 getpart = new jsonGetpartLv2();
        getpart.delegated=this;
        getpart.execute();
        Log.v(TAG, "getpart execute: ");
    }

//    private void animateFab() {
//        if (isFabOpen) {
//            language.startAnimation(rotate_forward);
//            language_id.startAnimation(fab_close);
//            language_en.startAnimation(fab_close);
//            language_id.setClickable(false);
//            language_en.setClickable(false);
//            isFabOpen = false;
//        } else {
//            language.startAnimation(rotate_backward);
//            language_id.startAnimation(fab_open);
//            language_en.startAnimation(fab_open);
//            language_id.setClickable(true);
//            language_en.setClickable(true);
//            isFabOpen = true;
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void consumeResponse(JSONObject response) {
        Dialog.dismiss();
        StringBuilder sb = new StringBuilder();
        if(response == null){
            Toast.makeText(LearnLv2Activity.this, "response is NULL", Toast.LENGTH_SHORT).show();
        } else if (response.has("error")){
            try {
                sb.append(response.getJSONObject("error").getString("resource"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Log.v(TAG, "response: "+response);
            LearnViewActivity.response = response;
            Intent i = new Intent(LearnLv2Activity.this, LearnViewActivity.class);
            startActivity(i);
        }
    }
}
