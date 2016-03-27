package tk.partofbodyapp.partofbodyapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class startgameActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = startgameActivity.class.getCanonicalName();

    private ImageView imgV_Result;
    private EditText input_jawaban;
    private TextView tv_estimate;
    private String level;
    AsyncTask<String, String, JSONObject> startJson;
    private ProgressDialog Dialog;
    String result;
    JSONObject response;
    private  Map<Integer, String> maplist;
    private Bitmap bitmap;
    private ArrayList<String> listarray;

    Integer index = 0;
    private CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

//        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
//        collapsingToolbarLayout.setTitle("");

        imgV_Result = (ImageView) findViewById(R.id.img_result);
        input_jawaban = (EditText) findViewById(R.id.inputJawaban);
        tv_estimate = (TextView) findViewById(R.id.tv_estimate);
        ImageButton btn_checkjawaban = (ImageButton) findViewById(R.id.btnJawab);
        btn_checkjawaban.setOnClickListener(this);

        //Get Level from gamemain
        Bundle bundle = getIntent().getExtras();
        level = bundle.getString("level");
        mulai();
    }

    private void mulai() {
        startJson = new startJson();
        startJson.execute();
    }

    @Override
    public void onClick(View v) {
        String inputresult = input_jawaban.getText().toString().toLowerCase();
        if (!inputresult.equals(listarray.get(index).toLowerCase())){
            Toast.makeText(getApplicationContext(), "Jawaban Salah, Coba Lagi", Toast.LENGTH_SHORT).show();
        } else {
            Log.v(TAG, "if else: index"+index);
            index++;
            if (index < listarray.size()){
                Log.v(TAG, "index: "+index);
                cdt.cancel();
                new setImg().execute();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(startgameActivity.this);
                builder.setCancelable(false)
                        .setMessage("Selamat, Level Ini Selesai!. Coba Level Selanjutnya")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cdt.cancel();
                                startgameActivity.this.finish();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }

    private class startJson extends AsyncTask<String, String, JSONObject>{
        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                String newUrl = "http://www.yenyenofficial.tk/json/jsonrandom.php?level="+level;
                URL fullUrl = new URL(newUrl);
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
                result = sb.toString();
                response = new JSONObject(sb.toString());
                Log.v(TAG, String.valueOf(response));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                response = ThrowException(e);
            }
            return response;
        }

        private JSONObject ThrowException(Exception e) {
            JSONObject error = new JSONObject();
            try {
                error.put("error", true);
                error.put("message",(new JSONObject()).put("resource", e.getMessage()));
                return  error;
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            return null;
        }

        @Override
        public void onPreExecute(){
            super.onPreExecute();
            Dialog = ProgressDialog.show(startgameActivity.this, "", "Connecting..");
            Dialog.setCancelable(false);
        }

        @SuppressLint("UseSparseArrays")
        @Override
        public void onPostExecute(JSONObject response){
            super.onPostExecute(response);
            Dialog.dismiss();
            if (response == null){
                Toast.makeText(getApplicationContext(), "Masalah Koneksi", Toast.LENGTH_SHORT).show();
                startgameActivity.this.finish();
            } else {
                //get name eng level
                if (response.has("error")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(startgameActivity.this);
                    builder.setMessage("Terjadi Masalah Koneksi");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //OK
                            startgameActivity.this.finish();
                        }
                    });
                    builder.create().show();
                } else {
                    try {
                        maplist = new HashMap<Integer, String>();
                        JSONArray resultArr = response.getJSONArray("data"); //buat resultobj menjadi jsonArray
                        for (int i = 0; i < resultArr.length(); i++) {
                            JSONObject finalObj = (JSONObject) resultArr.get(i); //ambli data array ke int i;
                            String result = finalObj.getString("name_indo");
                            maplist.put(i, result);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listarray = new ArrayList<String>(maplist.values());
                    new setImg().execute();
                }
            }
        }
    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(startgameActivity.this);
        builder.setMessage("Yakin Ingin Menutup Game ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startgameActivity.this.finish();
                cdt.cancel();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                // No
            }
        });
        builder.create().show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(startgameActivity.this);
        builder.setMessage("Yakin Ingin Menutup Game ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startgameActivity.this.finish();
                cdt.cancel();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No
                dialog.cancel();
            }
        });
        builder.create().show();
        return true;
    }
    
    private class setImg extends AsyncTask<String, String, Bitmap> {
        protected void onPreExecute(){
            super.onPreExecute();
            Dialog = ProgressDialog.show(startgameActivity.this, "", "Load Image...");
            Dialog.setCancelable(false);
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            String img = listarray.get(index).toLowerCase().replace(" ", "-");
            String urlimg = "http://www.yenyenofficial.tk/append_img/"+img+".png";
            Log.v(TAG, urlimg);
            try {
                URL img_url = new URL(urlimg);
                HttpURLConnection connection = (HttpURLConnection) img_url.openConnection();
                connection.setDoInput(true);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
//                bitmap = BitmapFactory.decodeStream((InputStream) new URL(img_url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            if(bitmap != null){
                imgV_Result.setImageBitmap(bitmap);
//                tv_resultEng.setText(listarray.get(index));
                Dialog.dismiss();
                startTime();
            }else{
                Dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Ada Kesalahan, Coba Lagi", Toast.LENGTH_SHORT).show();
                startgameActivity.this.finish();
            }
        }
    }

    private void startTime() {
        cdt = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_estimate.setText(String.valueOf(millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder builder = new AlertDialog.Builder(startgameActivity.this);
                builder.setMessage("WOW, Jangan Menyerah. Silakan Coba Kembali");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startgameActivity.this.finish();
                    }
                });
//                builder.create().show();
                AlertDialog dialog = builder.create();
                dialog.show();;
            }
        };
        cdt.start();
    }
}