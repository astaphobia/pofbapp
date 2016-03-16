package tk.partofbodyapp.partofbodyapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class jsonGetpart extends AsyncTask<String, Void, JSONObject>{
    final String TAG = jsonGetpart.class.getCanonicalName();
    final String url = "http://www.yenyenofficial.tk/json/json.php?name=";
    public ConsumeResponse delegated = null;
    String urlQuery = LearnLv1Activity.query;
    public static JSONObject response;
    @Override
    protected JSONObject doInBackground(String... params) {
        startJson();
        return response;
    }
    @Override
    protected void onPostExecute(JSONObject response){
        if (delegated == null){
            Log.v(TAG,"Start onPostExecute, response: "+response);
            Log.e(getClass().toString(), "the task response handler is not defined");
            return;
        }else{
            Log.v(TAG,"Start onPostExecute, response: "+response);
            delegated.consumeResponse(response);
        }
    }

    private JSONObject startJson() {
        try {
            URL fullUrl = new URL(url+urlQuery);
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

        } catch (IOException | JSONException e) {
            //IOException Failed connection
            //JSONException failed JSON
            //e.printStackTrace();
            response = throwException(e);
        }
        return response;
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
}
