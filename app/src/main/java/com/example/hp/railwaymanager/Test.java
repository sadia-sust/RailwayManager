package com.example.hp.railwaymanager;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class Test extends ActionBarActivity {
    ListView list;
    ArrayList<String> trainName;
    ArrayList<String> trainTime;
    ArrayList<String> trainShovon;
    ArrayList<String> trainChair;
    ArrayList<String> trainAc;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        list = (ListView) findViewById(R.id.testt);
        trainName = new ArrayList<>();
        trainTime = new ArrayList<>();
        trainShovon = new ArrayList<>();
        trainChair = new ArrayList<>();
        trainAc = new ArrayList<>();
       // trainName.add("name");
      //  trainTime.add("traintime");
       // trainAc.add("ac");
       // trainChair.add("chair");
       // trainShovon.add("shovon");
         // if(ScheduleActivity.schedulecheck==false)
           //     list.setAdapter(new CustomAdapter5(Test.this, trainName,trainShovon,trainChair,trainAc));
             // else
           // list.setAdapter(new CustomAdapter4(Test.this, trainName,trainTime));
        str ="http://192.168.43.65:8080/WebApplication3/Purse?from_st="+ScheduleActivity.from_city+"&to_st="+ScheduleActivity.to_city;
       // str = "http://192.168.0.114:8080/WebApplication3/Purse";
        new loader().start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class loader extends  Thread
    {
        public void run()
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(str);
            try{
                final String response = client.execute(request,new BasicResponseHandler());

                //Log.i("abd",response);
                JSONArray table = new JSONArray(response);
                for(int i =0; i<table.length();++i)
                {
                    JSONObject t =  table.getJSONObject(i);
                    String chair = t.getString("chair");
                    String ac = t.getString("ac");
                    String shovon = t.getString("shovon");
                    String name = t.getString("name");
                    String traintime = t.getString("start_time");
                    trainName.add(name);
                    trainAc.add(ac);
                    trainChair.add(chair);
                    trainShovon.add(shovon);
                    trainTime.add(traintime);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  ArrayAdapter<String> adapter =new ArrayAdapter<String>(Test.this,android.R.layout.simple_list_item_1,trainShovon);
//                        list.setAdapter(adapter);
                         if(ScheduleActivity.schedulecheck==false)
                             list.setAdapter(new CustomAdapter5(Test.this, trainName,trainShovon,trainChair,trainAc));
                        else
                        list.setAdapter(new CustomAdapter4(Test.this, trainName,trainTime));
                    }
                });
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}
