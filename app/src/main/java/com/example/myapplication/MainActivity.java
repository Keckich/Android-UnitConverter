package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "HelloWorld");
    }

    public void launchDistanceActivity(View view) {
        Intent intent = new Intent(this, DistanceActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Button clicked");
    }

    public void launchTemperatureActivity(View view) {
        Intent intent = new Intent(this, TemperatureActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Button clicked");
    }

    public void launchWeightActivity(View view) {
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Button clicked");
    }

    public void launchCurrencyActivity(View view) {
        Intent intent = new Intent(this, CurrencyActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Button clicked");
    }
}