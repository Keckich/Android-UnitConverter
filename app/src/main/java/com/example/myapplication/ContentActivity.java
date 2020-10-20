package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
//import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class ContentActivity extends AppCompatActivity  {
    private static final String LOG_TAG = ContentActivity.class.getSimpleName();
    CurrFragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
    }


/*    @Override
    public void onFragmentInteraction(String link) {
        fragment1 = (CurrFragment1) getSupportFragmentManager().findFragmentById(R.id.currFragment1);
        if (fragment1 != null && fragment1.isInLayout()) {
            fragment1.setEdit(link);
        }
    }*/

}
