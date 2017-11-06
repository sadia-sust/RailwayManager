package com.example.hp.railwaymanager;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HP on 8/24/2015.
 */
public class CustomAdapter4 extends BaseAdapter {
    Activity context;
    static int flag;
    ArrayList<String>trainName;
    ArrayList<String>trainTime;

    CustomAdapter4(Activity context, ArrayList<String> items, ArrayList<String>items2){
        this.context= context;
        this.trainName = items;
        this.trainTime= items2;
        flag=-1;
    }
    @Override
    public int getCount() {
        return trainName.size();
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
            convertView = context.getLayoutInflater().inflate(R.layout.listitemschedule, parent , false);
            //  convertView = getLayoutInflater().from(context); same thing, works if Context context

            TextView txt =  (TextView) convertView.findViewById(R.id.txt1sc);
            TextView txt2 = (TextView) convertView.findViewById(R.id.txt2sc);
            txt.setText(trainName.get(position));
            txt2.setText("Start Time : "+trainTime.get(position));
            /*
            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, "Button " + position + " clicked", Toast.LENGTH_SHORT).show();
                    TrackTrainActivity.toStation=position;
                  //  ((ToSelectActivity)context).finish();
                }
            });
            */
        }

        return convertView;
    }
}