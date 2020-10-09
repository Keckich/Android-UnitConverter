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
    String selectedUS, selectedEU;
    EditText editText;
    TextView textView;
    Spinner spinnerUS, spinnerEU;
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
/*        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inType = editText.getInputType();
                editText.setInputType(InputType.TYPE_NULL);
                editText.onTouchEvent(event);
                editText.setInputType(inType);
                return true;
            }
        });*/
    }

    public void convert(View view) {
        selectedUS = spinnerUS.getSelectedItem().toString();
        selectedEU = spinnerEU.getSelectedItem().toString();
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