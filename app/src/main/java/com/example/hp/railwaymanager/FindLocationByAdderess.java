package com.example.hp.railwaymanager;

/**
 * Created by HP on 9/9/2015.
 */
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class FindLocationByAdderess implements Runnable {
    double  lat,lon;
    String youraddress;
    Thread t;
    String uri;
    HttpGet httpGet;
    HttpClient client;
    HttpResponse response;
    StringBuilder stringBuilder;
    public FindLocationByAdderess(String youraddress) {
        this.youraddress=youraddress;
        t=new Thread(this,"Thread");
        t.start();
    }

    public void run() {


        try {
            uri = "http://maps.google.com/maps/api/geocode/json?address=" +
                    youraddress + "&sensor=false";
            httpGet = new HttpGet(uri);
            client = new DefaultHttpClient();

            stringBuilder = new StringBuilder();
        } catch(Exception e){}
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject = new JSONObject(stringBuilder.toString());

            lon = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");

            lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");

            Log.d("latitude", "" + lat);
            Log.d("longitude", "" + lon);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }








}
