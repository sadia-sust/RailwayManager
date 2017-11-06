package com.example.hp.railwaymanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class ToSelectActivity extends ActionBarActivity {


    ListView list1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_select);
        list1 = (ListView) findViewById(R.id.tolist);
        ArrayList<String> items = new ArrayList<String>();

        if(TrackTrainActivity.taken==true)
        {
            items.add("Dhaka");
            if (TrackTrainActivity.frmStation != 3)
            {items.add("Chittagong");
                items.add("Sylhet");
                if (TrackTrainActivity.frmStation == 0)
                    items.add("Rajshahi");
            }


        }
        else
        {
            items.add("Akhaura");
            items.add("Chittagong");
            items.add("Comilla");
            items.add("Dhaka");
            items.add("Feni");
            items.add("Srimangal");
            items.add("Sylhet");
            items.add("Rajshahi");





        }

        list1.setAdapter(new CustomAdapter3(this, items));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_from_select, menu);
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
}
