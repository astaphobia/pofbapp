package tk.partofbodyapp.partofbodyapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View;

public class LearnLv1Activity extends AppCompatActivity {

    private static final String url = "";
    private boolean isFabOpen = false;
    private LinearLayout lv1_btn1, lv1_btn2, lv1_btn3, lv1_btn4, lv1_btn5, lv1_btn6, lv1_btn7, lv1_btn8, lv1_btn9, lv1_btn10;
    FloatingActionButton language, language_en, language_id;
    Animation fab_open, fab_close, rotate_forward, rotate_backward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_lv1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        language = (FloatingActionButton) findViewById(R.id.language);
        language_en = (FloatingActionButton) findViewById(R.id.language_en);
        language_id = (FloatingActionButton) findViewById(R.id.language_id);
        //animate
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                animateFab();
            }
        });
        language_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
            }
        });
        language_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Indonesia", Toast.LENGTH_SHORT).show();
            }
        });


        lv1_btn1 = (LinearLayout) findViewById(R.id.lv1_btn1);
        lv1_btn1.setClickable(true);
        lv1_btn2 = (LinearLayout) findViewById(R.id.lv1_btn2);
        lv1_btn2.setClickable(true);
        lv1_btn3 = (LinearLayout) findViewById(R.id.lv1_btn3);
        lv1_btn3.setClickable(true);
        lv1_btn4 = (LinearLayout) findViewById(R.id.lv1_btn4);
        lv1_btn4.setClickable(true);
        lv1_btn5 = (LinearLayout) findViewById(R.id.lv1_btn5);
        lv1_btn5.setClickable(true);
        lv1_btn6 = (LinearLayout) findViewById(R.id.lv1_btn6);
        lv1_btn6.setClickable(true);
        lv1_btn7 = (LinearLayout) findViewById(R.id.lv1_btn7);
        lv1_btn7.setClickable(true);
        lv1_btn8 = (LinearLayout) findViewById(R.id.lv1_btn8);
        lv1_btn8.setClickable(true);
        lv1_btn9 = (LinearLayout) findViewById(R.id.lv1_btn9);
        lv1_btn9.setClickable(true);
        lv1_btn10 = (LinearLayout) findViewById(R.id.lv1_btn10);
        lv1_btn10.setClickable(true);
    }

    private void animateFab() {
        if (isFabOpen) {
            language.startAnimation(rotate_forward);
            language_id.startAnimation(fab_close);
            language_en.startAnimation(fab_close);
            language_id.setClickable(false);
            language_en.setClickable(false);
            isFabOpen = false;
        } else {
            language.startAnimation(rotate_backward);
            language_id.startAnimation(fab_open);
            language_en.startAnimation(fab_open);
            language_id.setClickable(true);
            language_en.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
    }

}
