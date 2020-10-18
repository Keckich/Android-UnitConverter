package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrFragment1 extends Fragment {
    private static final String LOG_TAG = CurrFragment1.class.getSimpleName();
    String[] units = {"USD", "RUB", "BYN", "EUR"};
    double[] convert = {25.4, 101.6, 304.8, 914.4, 1609000};
    double[] power = {1, 10, 10e1, 10e2, 10e5};
    String selected1, selected2, input;
    EditText editText;
    TextView textView;
    Spinner spinner1, spinner2;
    Button button;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_curr1, container, false);
        Log.d(LOG_TAG, "we're in onCreateView");
        return view;
    }

    public void setEdit(String item) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            EditText editText = Objects.requireNonNull(getView()).findViewById(R.id.editTextNumber);
            editText.setText(item);
        }
    }
}