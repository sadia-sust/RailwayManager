package com.example.hp.railwaymanager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {
    Button  buton1,buton4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Log.i("MainActivity", "log");
        buton1= (Button)findViewById(R.id.button1);
        buton1.setBackgroundResource(R.drawable.buton);
        //buton4.setTextColor(this.getResources().getColor(R.color.darkred));

        buton4= (Button)findViewById(R.id.button4);
        buton4.setBackgroundResource(R.drawable.buton);
        //buton4.setTextColor(this.getResources().getColor(R.color.blue));

        buton1= (Button)findViewById(R.id.button2);
        buton1.setBackgroundResource(R.drawable.buton);
        //buton1.setTextColor(this.getResources().getColor(R.color.orange));

        buton4= (Button)findViewById(R.id.button3);
        buton4.setBackgroundResource(R.drawable.buton);
        //buton4.setTextColor(this.getResources().getColor(R.color.orange));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void trackTrainClicked(View v)
    {
        Intent intent = new Intent(this,TrackTrainActivity.class);
        startActivity(intent);


    }
    public void ticketFareClicked(View v)
    {
        Intent intent = new Intent(this,TicketFareActivity.class);
        startActivity(intent);


    }
    public void timeScheduleClicked(View v)
    {
        Intent intent = new Intent(this,ScheduleActivity.class);
        startActivity(intent);


    }
    public void purchaseTicketClicked(View v)
    {
        Intent intent = new Intent(this,PurchaseTicketActivity.class);
        startActivity(intent);


    }
    public void aboutusClicked(View v)
    {
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);


    }


}
