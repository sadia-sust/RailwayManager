package com.example.hp.railwaymanager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
/**
 * Created by HP on 8/25/2015.
 */

public class SMSReceiver extends BroadcastReceiver
{
    static String pursedStation;
    @Override
    public void onReceive(Context context, Intent intent)
    {
//---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "" ;
        if (bundle != null)
        {
//---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                if (i==0) {

                }
//---get the message body---
                str += msgs[i].getMessageBody().toString();
            }
//---display the new SMS message---

            try {

                SMSActivity  .getInstace().setTEXT(getStation(str));
                SMSActivity  .getInstace().setTEXT1(getDelay(str));

            } catch (Exception e) {

            }
            //Toast.makeText(context, getStation(str), Toast.LENGTH_SHORT).show();
            Log.d("SMSReceiver", str);

        }
    }
    String getStation(String ms)
    {
        String temp,ans;
        temp ="";
        ans="Next Station: ";

        int len = ms.length();
        for(int i=0;i<len-8;i++)
        {
            if(ms.charAt(i)=='N' && ms.charAt(i+1)=='e' &&ms.charAt(i+2)=='x' &&ms.charAt(i+3)=='t')
            {
                if(ms.charAt(i+5)=='s' && ms.charAt(i+6)=='t' && ms.charAt(i+7)=='o' && ms.charAt(i+8)=='p')
                {
                    int j=i+10;
                    while(++j<len)
                    {


                        if(ms.charAt(j)>='0' && ms.charAt(j)<='9' )
                        {
                            break;
                        }
                        temp+=ms.charAt(j);

                    }
                    pursedStation="";
                    for(int I=0;I<temp.length()-4;I++)
                    {
                        ans+=temp.charAt(I);
                          pursedStation+=temp.charAt(I);

                    }


                }




            }

        }

        return ans;
    }
    String getDelay(String ms)
    {
    String temp,ans;
    temp ="";
    ans="Current Delay: ";

    int len = ms.length();
    for(int i=0;i<len-6;i++)
    {
        if(ms.charAt(i)=='D' && ms.charAt(i+1)=='e' &&ms.charAt(i+2)=='l' &&ms.charAt(i+3)=='a' && ms.charAt(i+4)=='y')
        {

                for(int I=i+7;I<i+12;I++)
                    ans+=ms.charAt(I);







        }

    }
    return ans;
}


}