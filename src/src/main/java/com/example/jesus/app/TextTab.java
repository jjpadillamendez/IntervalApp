package com.example.jesus.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Stack;

import source.Brain;
import source.functions.Function;
import source.math.IVector;

/**
 * Created by Jesus on 10/15/2017.
 */

public class TextTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.text_tab, container, false);
        updateTextDetails(rootView);
        return rootView;
    }
    private void updateTextDetails(View rootView){
        TextView solutionText = (TextView) rootView.findViewById(R.id.solutiontext);

        Stack<IVector> solution = Brain.getSolution();
        Function function = Brain.getFunction();

        solutionText.setText("Output\n" + "Solutions: "+solution.size()+"\n\n"+"Function:     \n");
        solutionText.append(function.toString());
        solutionText.append("Initial Box\n");
        solutionText.append("xi = [-5, 5] \n\n");
        for(int i=0; i < solution.size(); i++){
            solutionText.append("Box"+(i+1)+"\n");
            IVector box = solution.get(i);
            for(int j=0; j < box.length(); j++) {
                solutionText.append("\t"+function.getVarAsString(j)+"="+box.get(j)+"\n");
            }
        }

        solutionText.setMovementMethod(new ScrollingMovementMethod());
    }
}
