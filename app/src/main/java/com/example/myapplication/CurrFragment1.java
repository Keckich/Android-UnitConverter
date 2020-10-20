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
import java.util.Observable;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrFragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner spinner1, spinner2;
    String unitName;
    String selected1, selected2;
    EditText editText;
    TextView textView;
    DataViewModel model;

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
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CurrFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrFragment1 newInstance(String param1, String param2) {
        CurrFragment1 fragment = new CurrFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_curr1, container, false);
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
                createSpinner(spinner1, unitsUS);
                createSpinner(spinner2, unitsEU);
                break;
            }
            case "Weight": {
                createSpinner(spinner1, unitsUSw);
                createSpinner(spinner2, unitsEUw);
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
        final Observer<String> value = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editText.setText(s);
            }
        };
        model.getSelected().observe(getViewLifecycleOwner(), value);
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
            if (Objects.equals(unitName, "Distance")) {
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
            else if (Objects.equals(unitName, "Temperature")) {
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
            else if (Objects.equals(unitName, "Weight")) {
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

