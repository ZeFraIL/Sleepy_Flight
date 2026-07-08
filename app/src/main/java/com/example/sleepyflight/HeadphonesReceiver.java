package com.example.sleepyflight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class HeadphonesReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            switch (state) {
                case 0:
                    Toast.makeText(context, "אוזניות נותקו", Toast.LENGTH_SHORT).show();
                    MainActivity.isHeadphonesOn = false;
                    MainActivity.checkConditionsAndPlay();
                    break;
                case 1:
                    Toast.makeText(context, "אוזניות חוברו", Toast.LENGTH_SHORT).show();
                    MainActivity.isHeadphonesOn = true;
                    MainActivity.checkConditionsAndPlay();
                    break;
            }
        }
    }
}
