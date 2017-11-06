package com.example.hp.railwaymanager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TrackTrainActivity extends ActionBarActivity
{

    TextView txt;
    public static  TrackTrainActivity ins;
    static int frmStation,toStation;
    static boolean  taken;
    static String sercStation;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frmStation=-1;
        taken =true;
        ins = this;
        toStation =-1;
        setContentView(R.layout.activity_track_train);
        b1= (Button)findViewById(R.id.bt5);
        b2= (Button)findViewById(R.id.bt6);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_track_train, menu);
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
    public void fromStationClicked(View v)
    {
        Intent intent = new Intent(this,FromSelectActivity.class);
        startActivity(intent);
        //frmStation = CustomAdapter.flag;
    }
    public void toStationClicked(View v)
    {
        Intent intent = new Intent(this,ToSelectActivity.class);
        startActivity(intent);
    //    toStation = CustomAdapter3.flag;
    }
    public void searchClicked(View v)
    {
        if(TrackTrainActivity.frmStation!= -1 && TrackTrainActivity.toStation != -1) {
            if(TrackTrainActivity.frmStation == TrackTrainActivity.toStation)
            {

                Toast.makeText(getApplicationContext(), "From and To station cannot be same!", Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent = new Intent(this, TrainSearchActivity.class);
                startActivity(intent);

            }


        }
        else if(TrackTrainActivity.frmStation == -1 || TrackTrainActivity.toStation == -1)
        {

            Toast.makeText(getApplicationContext(), "Select both From and To station Please!", Toast.LENGTH_LONG).show();
        }



    }
    public void setTEXT1(String str){
        b1.setText( str );
    }
    public void setTEXT2(String str){
        b2.setText( str );
    }
    public static TrackTrainActivity  getInstace(){
        return ins;
    }


}
