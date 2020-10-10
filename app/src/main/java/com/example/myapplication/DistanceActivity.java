package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DistanceActivity extends AppCompatActivity {
    private static final String LOG_TAG = DistanceActivity.class.getSimpleName();
    String[] unitsUS = {"inch", "hand", "foot", "yard", "mile"};
    String[] unitsEU = {"mm", "sm", "dm", "m", "km"};
    double[] convert = {25.4, 101.6, 304.8, 914.4, 1609000};
    double[] power = {1, 10, 10e1, 10e2, 10e5};
    String selectedUS, selectedEU, input;
    EditText editText;
    TextView textView;
    Spinner spinnerUS, spinnerEU;
    Boolean dot = false;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        spinnerUS = (Spinner) findViewById(R.id.spinnerUS);
        ArrayAdapter<String> adapterUS = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, unitsUS);
        spinnerUS.setAdapter(adapterUS);
        spinnerEU = (Spinner) findViewById(R.id.spinnerEU);
        ArrayAdapter<String> adapterEU = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, unitsEU);
        spinnerEU.setAdapter(adapterEU);
        editText = findViewById(R.id.editTextNumber);
        textView = findViewById(R.id.textView3);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inType = editText.getInputType();
                editText.setInputType(InputType.TYPE_NULL);
                editText.onTouchEvent(event);
                editText.setInputType(inType);
                return true;
            }
        });
    }

    public void convert(View view) {
        selectedUS = spinnerUS.getSelectedItem().toString();
        selectedEU = spinnerEU.getSelectedItem().toString();
        if (!editText.getText().toString().equals("")) {
            float number = Float.parseFloat(editText.getText().toString());
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (selectedUS.equals(unitsUS[i]) && selectedEU.equals(unitsEU[j])) {
                        textView.setText(String.valueOf(number * (convert[i] / power[j])));
                    }
                }
            }
        }
    }


    public void b1Click(View view) {
        if (input == null) {
            input = "1";
        }
        else {
            input += "1";
        }

        editText.setText(input);
    }

    public void b0Click(View view) {
        if (input == null) {
            input = "0";
        }
        else {
            input += "0";
        }

        editText.setText(input);
    }

    public void b_Click(View view) {
        if (!dot) {
            if (input == null) {
                input = ".";
            } else {
                input += ".";
            }
        }
        dot = true;
        editText.setText(input);
    }

    public void b9Click(View view) {
        if (input == null) {
            input = "9";
        }
        else {
            input += "9";
        }

        editText.setText(input);
    }

    public void b8Click(View view) {
        if (input == null) {
            input = "8";
        }
        else {
            input += "8";
        }

        editText.setText(input);
    }

    public void b7Click(View view) {
        if (input == null) {
            input = "7";
        }
        else {
            input += "7";
        }

        editText.setText(input);
    }

    public void b6Click(View view) {
        if (input == null) {
            input = "6";
        }
        else {
            input += "6";
        }

        editText.setText(input);
    }

    public void b5Click(View view) {
        if (input == null) {
            input = "5";
        }
        else {
            input += "5";
        }

        editText.setText(input);
    }

    public void b4Click(View view) {
        if (input == null) {
            input = "4";
        }
        else {
            input += "4";
        }

        editText.setText(input);
    }

    public void b2Click(View view) {
        if (input == null) {
            input = "2";
        }
        else {
            input += "2";
        }

        editText.setText(input);
    }

    public void b3Click(View view) {
        if (input == null) {
            input = "3";
        }
        else {
            input += "3";
        }

        editText.setText(input);
    }

    public void delete(View view) {
        if (input != null && !input.equals("")) {
            input = input.substring(0, input.length() - 1);
            if (!input.contains(".")) {
                dot = false;
            }
            editText.setText(input);
        }
    }
}