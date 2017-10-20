package com.example.jesus.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import source.Brain;
import source.functions.Test;

import static android.widget.AdapterView.*;

/**
 * Created by Jesus on 10/15/2017.
 */

public class FunctionTab extends Fragment {

    Boolean changed = false;

    public Boolean areThereAChanged(){
        return changed;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.function_tab, container, false);

        configureFuncSpinner(rootView);
        configurePhiSpinner(rootView);
        configureFuncViewButton(rootView);
        configurePhiViewButton(rootView);

        return rootView;
    }
    private void configureFuncSpinner(View rootView){
        final Spinner funcSpinner = (Spinner) rootView.findViewById(R.id.function_spinner);
        funcSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2,
                                       long arg3) {
                int position = funcSpinner.getSelectedItemPosition();
                switch (position){
                    case 1:
                        Brain.setFunction(new Test());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }
    private void configurePhiSpinner(View rootView){

    }
    private void configureFuncViewButton(View rootView){

    }
    private void configurePhiViewButton(View rootView){

    }
}
