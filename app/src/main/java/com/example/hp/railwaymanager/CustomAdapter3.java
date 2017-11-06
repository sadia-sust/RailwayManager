package com.example.hp.railwaymanager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HP on 5/22/2015.
 */
public class CustomAdapter3 extends BaseAdapter {
    Activity context;
    static int flag;
    ArrayList<String> items;
    CustomAdapter3(Activity context, ArrayList<String> items){
        this.context= context;
        this.items = items;
        flag=-1;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView = context.getLayoutInflater().inflate(R.layout.listitem, parent , false);
            //  convertView = getLayoutInflater().from(context); same thing, works if Context context

            //TextView txt =  (TextView) convertView.findViewById(R.id.txt1);
            Button butt = (Button) convertView.findViewById(R.id.butt1);
            butt.setText(items.get(position));
            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  Toast.makeText(context, "Button " + position + " clicked", Toast.LENGTH_SHORT).show();
                    TrackTrainActivity.toStation=position;
                    try {
                        ScheduleActivity  .getInstace().setTEXT2(items.get(position));


                    } catch (Exception e) {

                    }
                    try {
                        TrackTrainActivity  .getInstace().setTEXT2(items.get(position));


                    } catch (Exception e) {

                    }
                    try {
                        TicketFareActivity  .getInstance().setTEXT2(items.get(position));


                    } catch (Exception e) {

                    }
                    ((ToSelectActivity)context).finish();
                }
            });
        }

        return convertView;
    }
}