package com.example.sleepyflight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            boolean isOn = intent.getBooleanExtra("state", false);
            MainActivity.isAirplaneOn = isOn;

            Toast.makeText(context, isOn ? "מצב טיסה הופעל" : "מצב טיסה כובה", Toast.LENGTH_SHORT).show();

            // עוצר רעש או מעדכן UI
            MainActivity.checkConditionsAndPlay();
        }
    }
}
