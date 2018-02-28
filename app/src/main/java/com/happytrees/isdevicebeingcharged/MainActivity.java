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
//BROADCAST RECEIVER SHOULD BE UNREGISTERED onPause AND REGISTERED onResume


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        PowerConnectedBCReceiver myPowerConnectedBCReceiver = new PowerConnectedBCReceiver();


        IntentFilter intentPowerOn = new IntentFilter();
        intentPowerOn.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        registerReceiver(myPowerConnectedBCReceiver, intentPowerOn);


        IntentFilter intentPowerOff = new IntentFilter();
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




}

