package com.example.alarmapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePicker alarmTimer;
    Button btnStartAlarm;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimer = findViewById(R.id.edtSeconds);
        btnStartAlarm = findViewById(R.id.btnStartTimer);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        btnStartAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
    }

    private void setAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, alarmTimer.getHour());
        calendar.set(Calendar.MINUTE, alarmTimer.getMinute());
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(MainActivity.this, Alarm.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "Просыпайся, соня", Toast.LENGTH_SHORT).show();
    }
}