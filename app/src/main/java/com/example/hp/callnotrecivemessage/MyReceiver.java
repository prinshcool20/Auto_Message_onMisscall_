package com.example.hp.callnotrecivemessage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver {
    static boolean ring=false;
   static boolean callReceived=false;
  // String mMessage;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // String action = intent.getAction();
        //String message = intent.getExtras().getString("extra");

        //  if(action.equals("com.example.hp.callnotrecivemessage")){
        //String message = intent.getExtras().getString("extra");
// System.out.println("message received");
        //}
        // String message=intent.getStringExtra("Message");
        //  RequiresPermission.Read more: http://mrbool.com/android-sharedpreferences-how-to-share-data-in-android/30793#ixzz56WMwZHq3

        //throw new UnsupportedOperationException("Not yet implemented");
        try {
            // System.out.println("Receiver start");
            ///Toast.makeText(context,"Receiver Start",Toast.LENGTH_SHORT).show();
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                ring = true;
                Toast.makeText(context, "Ringing state Number is-" + incomingNumber, Toast.LENGTH_SHORT).show();
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                callReceived = true;
                Toast.makeText(context, "Ringing State", Toast.LENGTH_SHORT).show();

            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {

                Toast.makeText(context, "Call Ended", Toast.LENGTH_SHORT).show();
                if (ring == true && callReceived == false) {
                    Toast.makeText(context, "Miss Call", Toast.LENGTH_SHORT).show();

                        SharedPreferences gp=context.getSharedPreferences("myfile1",Context.MODE_PRIVATE);
                        String message=gp.getString("message","");
                    SmsManager manager = SmsManager.getDefault();
                    manager.sendTextMessage(incomingNumber, null, message, null, null);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    }











