package com.example.sleepyflight;

import androidx.appcompat.app.AppCompatActivity;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static MediaPlayer mediaPlayer;
    public static boolean isAirplaneOn = false;
    public static boolean isHeadphonesOn = false;
    private Button playButton;
    private static TextView airplaneStatus, headphonesStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        airplaneStatus = findViewById(R.id.airplaneStatus);
        headphonesStatus = findViewById(R.id.headphonesStatus);

        mediaPlayer = MediaPlayer.create(this, R.raw.white_noise);
        mediaPlayer.setLooping(true);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConditionsAndPlay();
            }
        });

        // מאזינים ל-Broadcasts
        IntentFilter airplaneFilter = new IntentFilter(android.content.Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(new AirplaneReceiver(), airplaneFilter);

        IntentFilter headphonesFilter = new IntentFilter(android.content.Intent.ACTION_HEADSET_PLUG);
        registerReceiver(new HeadphonesReceiver(), headphonesFilter);

        // עדכון UI התחלה
        updateUI();
    }

    public static void checkConditionsAndPlay() {
        if (!isAirplaneOn || !isHeadphonesOn) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        } else {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }
        updateUI();
    }

    public static void updateUI() {
        airplaneStatus.setText("מצב טיסה: " + (isAirplaneOn ? "פעיל" : "כבוי"));
        headphonesStatus.setText("אוזניות: " + (isHeadphonesOn ? "מחוברות" : "מנותקות"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
