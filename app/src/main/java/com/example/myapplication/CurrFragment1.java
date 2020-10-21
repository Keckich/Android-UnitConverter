package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class CurrFragment1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner spinner1, spinner2;
    String unitName;
    String selected1, selected2;
    EditText editText;
    TextView textView;
    DataViewModel model;

    String[] unitsCurr = {"USD", "RUB", "BYN", "EUR"};
    double[] toUSD = {1, 0.013, 0.39, 1.17};

    String[] unitsDist = {"inch", "foot", "mile", "mm", "m", "km"};
    double[] toMM = {25.4, 304.8, 1609000, 1, 10e2, 10e5};

    String[] unitsTemp = {"C", "F", "K"};

    String[] unitsWt = {"ounce", "lb", "pd", "g", "kg", "ton"};
    double[] toGr = {29.896, 410, 16380, 1, 10e2, 10e5};

    private String mParam1;
    private String mParam2;

    public CurrFragment1() {
        // Required empty public constructor
    }

/*    public static CurrFragment1 newInstance(String param1, String param2) {
        CurrFragment1 fragment = new CurrFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public int getLayout() {
        return R.layout.fragment_curr1;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void content(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            unitName = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("unitName");
        }
        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        editText = view.findViewById(R.id.editTextNumber);
        model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
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
        textView = view.findViewById(R.id.textView3);
        Button button = view.findViewById(R.id.buttonConvert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
        switch (unitName) {
            case "Distance": {
                createSpinner(spinner1, unitsDist);
                createSpinner(spinner2, unitsDist);
                break;
            }
            case "Weight": {
                createSpinner(spinner1, unitsWt);
                createSpinner(spinner2, unitsWt);
                break;
            }
            case "Currency": {
                createSpinner(spinner1, unitsCurr);
                createSpinner(spinner2, unitsCurr);
                break;
            }
            case "Temperature": {
                createSpinner(spinner1, unitsTemp);
                createSpinner(spinner2, unitsTemp);
                break;
            }
        }
        final Observer<String> valueInput = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editText.setText(s);
            }
        };
        final Observer<String> valueOutput = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.equals("")) {
                    textView.setText(s);
                }
            }
        };
        model.getInput().observe(getViewLifecycleOwner(), valueInput);
        model.getOutput().observe(getViewLifecycleOwner(), valueOutput);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getLayout(), container, false);
        content(view);
        return view;
    }

    public void createSpinner(Spinner spinner, String[] units) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }


    public void convert() {
        selected1 = spinner1.getSelectedItem().toString();
        selected2 = spinner2.getSelectedItem().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!editText.getText().toString().equals("")) {
                if (Objects.equals(unitName, "Distance")) {
                    float number = Float.parseFloat(editText.getText().toString());
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 6; j++) {
                            if (selected1.equals(unitsDist[i]) && selected2.equals(unitsDist[j])) {
                                if (i == j) {
                                    textView.setText(String.valueOf(number));
                                    continue;
                                }
                                textView.setText(String.valueOf(number * toMM[i] * (1 / toMM[j])));
                            }
                        }
                    }
                }
                else if (Objects.equals(unitName, "Temperature")) {
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
                else if (Objects.equals(unitName, "Weight")) {
                    float number = Float.parseFloat(editText.getText().toString());
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 6; j++) {
                            if (selected1.equals(unitsWt[i]) && selected2.equals(unitsWt[j])) {
                                if (i == j) {
                                    textView.setText(String.valueOf(number));
                                    continue;
                                }
                                textView.setText(String.valueOf(number * toGr[i] * (1 / toGr[j])));
                            }
                        }
                    }
                }
                else {
                    float number = Float.parseFloat(editText.getText().toString());
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (selected1.equals(unitsCurr[i]) && selected2.equals(unitsCurr[j])) {
                                if (i == j) {
                                    textView.setText(String.valueOf(number));
                                    continue;
                                }
                                textView.setText(String.valueOf(number * toUSD[i] * (1 / toUSD[j])));
                            }
                        }
                    }
                }
            }
            else {
                textView.setText("");
            }
            model.setOutput(textView.getText().toString());
        }
    }
}

