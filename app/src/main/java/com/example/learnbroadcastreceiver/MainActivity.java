package com.example.learnbroadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private LocalBroadcastManager mLocalBroadcastManager;
    private MyReceiver myReceiver;
    private MyReceiverLowPriority myReceiverLowPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.BtnSendMsg);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        myReceiver= new MyReceiver();
        myReceiverLowPriority = new MyReceiverLowPriority();
        IntentFilter intentFilter_main = new IntentFilter("com.example.learnbroadcastreceiver");
        intentFilter_main.setPriority(100);
        IntentFilter intentFilter_low = new IntentFilter("com.example.learnbroadcastreceiver");
        intentFilter_low.setPriority(99);
        mLocalBroadcastManager.registerReceiver(myReceiver, intentFilter_main);
        mLocalBroadcastManager.registerReceiver(myReceiverLowPriority, intentFilter_low);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.learnbroadcastreceiver");
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(myReceiver);
        mLocalBroadcastManager.unregisterReceiver(myReceiverLowPriority);
    }
}
