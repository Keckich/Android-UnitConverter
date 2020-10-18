package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

public class ContentActivity extends AppCompatActivity implements CurrFragment2.OnFragmentInteractionListener {
    private static final String LOG_TAG = ContentActivity.class.getSimpleName();
    String[] unitsCurr = {"USD", "RUB", "BYN", "EUR"};
    double[] fromUSD = {1, 77.96, 2.57, 0.85};
    double[] inUSD = {1, 0.013, 0.39, 1.17};

    String[] unitsUS = {"inch", "hand", "foot", "yard", "mile"};
    String[] unitsEU = {"mm", "sm", "dm", "m", "km"};
    double[] convert = {25.4, 101.6, 304.8, 914.4, 1609000};
    double[] power = {1, 10, 10e1, 10e2, 10e5};

    String[] unitsTemp = {"C", "F", "K"};

    String[] unitsUSw = {"ounce", "lb", "pd"};
    String[] unitsEUw = {"g", "kg", "ton"};
    double[] convertW = {29.896, 410, 16380};
    double[] powerW = {1, 10e2, 10e5};

    String selected1, selected2;
    EditText editText;
    TextView textView;
    Spinner spinner1, spinner2;
    CurrFragment1 fragment1;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            switch (Objects.requireNonNull(getIntent().getStringExtra("unitName"))) {
                case "Distance": {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, unitsUS);
                    spinner1.setAdapter(adapter1);

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, unitsEU);
                    spinner2.setAdapter(adapter2);
                    break;
                }
                case "Weight": {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, unitsUSw);
                    spinner1.setAdapter(adapter1);

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, unitsEUw);
                    spinner2.setAdapter(adapter2);
                    break;
                }
                case "Currency": {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, unitsCurr);
                    spinner1.setAdapter(adapter1);

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, unitsCurr);
                    spinner2.setAdapter(adapter2);
                    break;
                }
                case "Temperature": {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, unitsTemp);
                    spinner1.setAdapter(adapter1);

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, unitsTemp);
                    spinner2.setAdapter(adapter2);
                    break;
                }
            }
        }
        editText = findViewById(R.id.editTextNumber);
        textView = findViewById(R.id.textView3);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inType = editText.getInputType();
                editText.setInputType(InputType.TYPE_NULL);
                editText.onTouchEvent(event);
                editText.setInputType(inType);
                return true;
            }
        });
        fragment1 = (CurrFragment1) getSupportFragmentManager().findFragmentById(R.id.currFragment1);
    }

    @Override
    public void onFragmentInteraction(String link) {
        if (fragment1 != null && fragment1.isInLayout()) {
            fragment1.setEdit(link);
        }
    }

    public void convert(View view) {
        selected1 = spinner1.getSelectedItem().toString();
        selected2 = spinner2.getSelectedItem().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(getIntent().getStringExtra("unitName"), "Distance")) {
                if (!editText.getText().toString().equals("")) {
                    float number = Float.parseFloat(editText.getText().toString());
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (selected1.equals(unitsUS[i]) && selected2.equals(unitsEU[j])) {
                                textView.setText(String.valueOf(number * (convert[i] / power[j])));
                            }
                        }
                    }
                }
            }
            else if (Objects.equals(getIntent().getStringExtra("unitName"), "Temperature")) {
                if (!editText.getText().toString().equals("")) {
                    float number = Float.parseFloat(editText.getText().toString());
                    if (selected1.equals(unitsTemp[0]) && selected2.equals(unitsTemp[1])) {
                        textView.setText(String.valueOf(1.8 * number + 32));
                    }
                    else if (selected1.equals(unitsTemp[0]) && selected2.equals(unitsTemp[2])) {
                        textView.setText(String.valueOf(number + 274.15));
                    }
                    else if (selected1.equals(unitsTemp[1]) && selected2.equals(unitsTemp[0])) {
                        textView.setText(String.valueOf((number - 32) * 5 / 9));
                    }
                    else if (selected1.equals(unitsTemp[1]) && selected2.equals(unitsTemp[2])) {
                        textView.setText(String.valueOf((number + 459.67) * 5 / 9));
                    }
                    else if (selected1.equals(unitsTemp[2]) && selected2.equals(unitsTemp[0])) {
                        textView.setText(String.valueOf(number - 274.15));
                    }
                    else if (selected1.equals(unitsTemp[2]) && selected2.equals(unitsTemp[1])) {
                        textView.setText(String.valueOf(number * 1.8 - 459.67));
                    }
                    else {
                        textView.setText(String.valueOf(number));
                    }
                }
            }
            else if (Objects.equals(getIntent().getStringExtra("unitName"), "Weight")) {
                if (!editText.getText().toString().equals("")) {
                    float number = Float.parseFloat(editText.getText().toString());
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (selected1.equals(unitsUSw[i]) && selected2.equals(unitsEUw[j])) {
                                textView.setText(String.valueOf(number * (convertW[i] / powerW[j])));
                            }
                        }
                    }
                }
            }
            else {
                if (!editText.getText().toString().equals("")) {
                    float number = Float.parseFloat(editText.getText().toString());
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (selected1.equals(unitsCurr[i]) && selected2.equals(unitsCurr[j])) {
                                if (i == j) {
                                    textView.setText(String.valueOf(number));
                                    continue;
                                }
                                textView.setText(String.valueOf(number * inUSD[i] * fromUSD[j]));
                            }
                        }
                    }
                }
            }
        }
    }
}