package com.ishu.stopwatch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    Button start;
    Button pause;
    Button reset;

    long MilliSecondTime, StartTime, BufferTime, UpdateTime = 0L;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;

    @SuppressLint({"SetTextI18n", "SuspiciousIndentation", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.tvTimer);
        start = findViewById(R.id.btStart);
        pause = findViewById(R.id.btPause);
        reset = findViewById(R.id.btReset);

        handler = new Handler();
        start.setOnClickListener(v -> {
            StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable ,0);
                reset.setEnabled(false);
        });

        pause.setOnClickListener(v -> {

            BufferTime+=MilliSecondTime;
            handler.removeCallbacks(runnable);
            reset.setEnabled(true);
        });

        reset.setOnClickListener(v -> {
            MilliSecondTime = 0L;
            StartTime = 0L;
            BufferTime = 0L;
            UpdateTime =0L;
            Seconds =0;
            Minutes = 0;
            MilliSeconds = 0;
                timer.setText("00:00:00");
        });




    }

        public Runnable runnable = new Runnable() {

            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            public void run() {

                MilliSecondTime = SystemClock.uptimeMillis() - StartTime;

                UpdateTime = BufferTime + MilliSecondTime;

                Seconds = (int) (UpdateTime / 1000);

                Minutes = Seconds / 60;

                Seconds = Seconds % 60;

                MilliSeconds = (int) (UpdateTime % 1000);

                timer.setText("" + Minutes + ":"
                        + String.format("%02d", Seconds) + ":"
                        + String.format("%02d", MilliSeconds));

                handler.postDelayed(this, 0);
            }
        };
}