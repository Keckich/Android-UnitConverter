package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchDistanceActivity(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        Button button = (Button)view;
        intent.putExtra("unitName", String.valueOf(button.getText()));
        startActivity(intent);
    }

    public void launchTemperatureActivity(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        Button button = (Button)view;
        intent.putExtra("unitName", String.valueOf(button.getText()));
        startActivity(intent);
    }

    public void launchWeightActivity(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        Button button = (Button)view;
        intent.putExtra("unitName", String.valueOf(button.getText()));
        startActivity(intent);
    }

    public void launchCurrencyActivity(View view) {
        Intent intent = new Intent(this, ContentActivity.class);
        Button button = (Button)view;
        intent.putExtra("unitName", String.valueOf(button.getText()));
        startActivity(intent);
        Log.d(LOG_TAG, "Button clicked");
    }
}