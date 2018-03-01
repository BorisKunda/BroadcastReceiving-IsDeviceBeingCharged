package com.happytrees.isdevicebeingcharged;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

//IN THIS EXAMPLE BROADCAST RECEIVER REGISTERED THROUGH CODE INSTEAD OF MANIFEST OPTION

public class MainActivity extends AppCompatActivity {

//BROADCAST RECEIVER SHOULD BE UNREGISTERED onPause,if not unregistered it will continue receive broadcasts even when app is no longer active
    //BROADCAST RECEIVER SHOULD BE REGISTERED onResume if was previously unregistered onPause,otherwise it will cease to function after app was brought to background once



    PowerConnectedBCReceiver myPowerConnectedBCReceiver;
    IntentFilter intentPowerOn;
    IntentFilter intentPowerOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myPowerConnectedBCReceiver = new PowerConnectedBCReceiver();


         intentPowerOn = new IntentFilter();
        intentPowerOn.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        registerReceiver(myPowerConnectedBCReceiver, intentPowerOn);


        intentPowerOff = new IntentFilter();
        intentPowerOff.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        registerReceiver(myPowerConnectedBCReceiver,intentPowerOff);





    }



    private class PowerConnectedBCReceiver  extends  BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {


            if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                Toast.makeText(MainActivity.this,"connected to power source",Toast.LENGTH_SHORT).show();
            } else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                Toast.makeText(MainActivity.this,"disconnected from power source",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myPowerConnectedBCReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myPowerConnectedBCReceiver, intentPowerOn);
        registerReceiver(myPowerConnectedBCReceiver,intentPowerOff);
    }
}

//The onPause() and onResume() methods are called when the application is brought to the background and into the foreground again