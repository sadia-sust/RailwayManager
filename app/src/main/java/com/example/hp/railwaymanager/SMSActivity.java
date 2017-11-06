package com.example.hp.railwaymanager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;



public class SMSActivity extends ActionBarActivity  {

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 15; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000*60; // in Milliseconds

    protected LocationManager locationManager;

    static double curLatitude,curLongitude;
    double Latitude,Longitude;

    TextView txt,txt2;
    String message;
    String phone;
    String msgTxt;
    Context context;
    Intent intent;
    static double station1;
    static double station2;


    SharedPreferences sharedPreferences;

    private static SMSActivity ins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Map <String , String> m1 = new HashMap<String, String>();
        ins = this;
        initTrainList(m1);
        /*
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("BhoirabBazarJn", "Bhairab+Railway");
        editor.commit();
        */
        sharedPreferences = getSharedPreferences("shamim", MODE_PRIVATE);
        txt = (TextView)findViewById(R.id.sms1);
        txt2 = (TextView)findViewById(R.id.sms2);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Bhoirob Bazar Jn", "Bhairab+railway");

        editor.putString("Shahji Bazar", "Fenchuganj+railway");
        editor.putString("BrahmanBaria stn", "Brahmanbaria+railway");

        editor.commit();
        txt.setText("Loading.....");

        msgTxt = m1.get(TrackTrainActivity.sercStation);
        message = msgTxt;
        phone = "16318";
        //sendSMS(phone, message);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sm, menu);
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
    private void sendSMS(String phoneNumber, String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));
        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }




    public void onReceive(Context context, Intent intent)
    {
//---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "SMS from" ;
        if (bundle != null)
        {
//---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                if (i==0) {
//---get the sender address/phone number---
                    str += msgs[i].getOriginatingAddress();
                    str += ": ";
                }
//---get the message body---
                str += msgs[i].getMessageBody().toString();
            }
//---display the new SMS message---

            //Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            Log.d("SMSReceiver", str);
            // txt.setText(str);

        }
    }

    public void initTrainList(Map m1){

        m1.put("parabot","TR 709");
        m1.put("kalni","TR 773");
        m1.put("joyontica","TR 717");
        m1.put("upaban","TR 739");

        m1.put("Parabot","TR 710");
        m1.put("Kalni","TR 774");
        m1.put("Joyontica","TR 718");
        m1.put("Upaban","TR 740");

        m1.put("mahanagar provati","TR 704");
        m1.put("shuborno express","TR 702");
        m1.put("mohanagar godhuli","TR 722");
        m1.put("turna nishitha","TR 742");

        m1.put("Mahanagar Provati","TR 721");
        m1.put("Shuborno Express","TR 701");
        m1.put("Mohanagar Godhuli","TR 703");
        m1.put("Turna Nishitha","TR 741");

        m1.put("udayan","TR 723");
        m1.put("paharika","TR 719");

        m1.put("Udayan","TR 724");
        m1.put("Paharika","TR 720");

        m1.put("silkCity express","TR 753");
        m1.put("padma express","TR 759");
        m1.put("dhumkatu express","TR 769");

        m1.put("SilkCity Express","TR 754");
        m1.put("Padma Express","TR 760");
        m1.put("Dhumkatu Express","TR 770");

    }
    public void setTEXT(String str){
        txt.setTextColor(this.getResources().getColor(R.color.orange));
        txt.setText( str );
    }public void setTEXT1(String str){
        txt2.setTextColor(this.getResources().getColor(R.color.orange));
        txt2.setText( str );
    }

    public static SMSActivity  getInstace(){
        return ins;
    }
    public void goToGoogleMapButtonClicked(View v){
        String selectionStart= "";

        selectionStart = sharedPreferences.getString(SMSReceiver.pursedStation,"1");
  ///
     Toast.makeText(getApplicationContext(), selectionStart+ "vua" + SMSReceiver.pursedStation, Toast.LENGTH_SHORT).show();
        FindLocationByAdderess adrs = new FindLocationByAdderess(selectionStart);
        try
        {
            adrs.t.join();

        }catch (Exception e){};
        areaFind(adrs.lat,adrs.lon);

        if(TrackTrainActivity.toStation==0)
       adrs = new FindLocationByAdderess("kamalapur+railway");
        else
          adrs = new FindLocationByAdderess("Sylhet+Railway+Station");

        try
        {
            adrs.t.join();

        }catch (Exception e){};
        curAreaFind(adrs.lat,adrs.lon);
        //Toast.makeText(SMSActivity.this,curLatitude +" "+ curLongitude ,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void contributeButtonClicked(View v){
        Intent intent = new Intent(this, ContributeActivity.class);
        startActivity(intent);
    }


    public void areaFind(double passedStation,double nextStation){


        station1 = passedStation;
        station2 = nextStation;
        //getLocation();

//        Toast.makeText(SMSActivity.this,curLatitude +" "+ curLongitude ,Toast.LENGTH_SHORT).show();


    }
    public void curAreaFind(double passedStation,double nextStation){


        curLatitude = passedStation;
        curLongitude = nextStation;


    }
    public void getLocation(){

        locationManager = (LocationManager)context. getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MyLocationListener()

        );



    }
    class MyLocationListener implements LocationListener{

        @Override

        public void onLocationChanged(Location location) {

            Longitude=location.getLongitude();
            Latitude=location.getLatitude();
            locationManager.removeUpdates(MyLocationListener.this);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }


}
