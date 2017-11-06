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
public class CustomAdapter5 extends BaseAdapter {
    Activity context;
    static int flag;
    ArrayList<String>trainName;
    ArrayList<String>shovon;
    ArrayList<String>chair;
    ArrayList<String>ac;


    CustomAdapter5(Activity context, ArrayList<String> items, ArrayList<String>items2, ArrayList<String> items3, ArrayList<String>items4){
        this.context= context;
        this.trainName = items;
        this.shovon= items2;
        this.chair= items3;
        this.ac = items4;
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
            convertView = context.getLayoutInflater().inflate(R.layout.listitemfare, parent , false);
            //  convertView = getLayoutInflater().from(context); same thing, works if Context context

            TextView txt =  (TextView) convertView.findViewById(R.id.txt1fare);
            TextView txt2 = (TextView) convertView.findViewById(R.id.txt2fare);
            TextView txt3 =  (TextView) convertView.findViewById(R.id.txt3fare);
            TextView txt4 = (TextView) convertView.findViewById(R.id.txt4fare);

            txt.setText(trainName.get(position));
            txt.setBackgroundResource(R.color.darkorange);
           // txt2.setBackgroundResource(R.color.orange);
            txt2.setText("SHOVON - "+shovon.get(position)+" BDT" );
            txt3.setText("CHAIR - "+chair.get(position)+" BDT" );
            txt4.setText("AC - "+ac.get(position)+" BDT" );

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