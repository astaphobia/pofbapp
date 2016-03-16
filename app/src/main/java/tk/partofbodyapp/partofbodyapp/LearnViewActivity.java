package tk.partofbodyapp.partofbodyapp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class LearnViewActivity extends AppCompatActivity {
    final String TAG = LearnLv1Activity.class.getCanonicalName();

    public static JSONObject response;
    TextView txtV_Name, txtV_Detail;
    ImageView imgV_Result;
    private ProgressDialog Dialog;
    private Bitmap bitmap;
    String lang = LearnLv1Activity.lang;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learnview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       collapsingToolbarLayout  = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        txtV_Name = (TextView) findViewById(R.id.txt_resultName);
        txtV_Detail = (TextView) findViewById(R.id.txt_resultDetail);
        imgV_Result = (ImageView) findViewById(R.id.img_result);
        showResult();
    }

    private void showResult() {
        try {
            Log.v(TAG, "lang = "+lang);
//            if (lang == "id") {
//                //user memilih bahasa indonesia
//                String image_url = response.getString("image_url");
//                String name = response.getString("name_indo");
//                String detail = response.getString("detail_indo");
////                txtV_Name.setText(name);
//                collapsingToolbarLayout.setTitle(name);
//                txtV_Detail.setText(detail);
//
//                //setimage
//                new setImg().execute("http://www.yenyenofficial.tk" + image_url);
//            } else {
//                //jika user memilih basah english
//                String image_url = response.getString("image_url");
//                String name = response.getString("name_eng");
//                String detail = response.getString("detail_eng");
////                txtV_Name.setText(name);
//                collapsingToolbarLayout.setTitle(name);
//                txtV_Detail.setText(detail);
//
//                //setimage
//                new setImg().execute("http://www.yenyenofficial.tk" + image_url);
//            }
                String image_url = response.getString("image_url");
                String name_indo = response.getString("name_indo");
                String detail_indo = response.getString("detail_indo");
                String name_eng = response.getString("name_eng");
                String detail_eng = response.getString("detail_eng");
                String nameAll = name_indo+"/"+name_eng;
                String detailAll = detail_indo+"\n\n"+detail_eng;
                txtV_Name.setText(nameAll);
                collapsingToolbarLayout.setTitle(name_indo);
                txtV_Detail.setText(detailAll);

                //setimage
                new setImg().execute("http://www.yenyenofficial.tk" + image_url);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public boolean onOptionsItemSelected(MenuItem item){
        onBackPressed();
        return true;
    }

    //setImage
    private class setImg extends AsyncTask<String, String, Bitmap>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Dialog = ProgressDialog.show(LearnViewActivity.this, "", "Load Image...");
            Dialog.setCancelable(false);
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(params[0]).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap image){
            super.onPostExecute(image);
            if(image != null){
                imgV_Result.setImageBitmap(image);
                Dialog.dismiss();
            }else{
                Dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Ada Kesalahan, Coba Lagi", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
