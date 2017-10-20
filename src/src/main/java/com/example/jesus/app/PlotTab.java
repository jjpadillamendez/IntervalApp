package com.example.jesus.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import source.Brain;


/**
 * Created by Jesus on 10/15/2017.
 */

public class PlotTab extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plot_tab, container, false);

        configurePlot(rootView);
        updateRuntime(rootView);
        updateIterations(rootView);

        return rootView;
    }
    private void configurePlot(View rootView){
        DataPoint[] points = new DataPoint[100];
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(i, Math.sin(i*0.5) * 20*(Math.random()*10+1));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-150);
        graph.getViewport().setMaxY(150);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling

        graph.setKeepScreenOn(true);
        graph.addSeries(series);

    }
    private void updateRuntime(View rootview){
        TextView value = (TextView) rootview.findViewById(R.id.runtime_value);
        TextView unit = (TextView) rootview.findViewById(R.id.runtime_unit);

        double num = Brain.getRuntime();

        if(num < 60000){
            value.setText(""+(num/60000));
            unit.setText("seconds");
        }else if(num < 3600000){
            value.setText(""+num/3600000);
            unit.setText("minutes");
        }else{
            value.setText(""+num);
            unit.setText(" hours ");
        }

    }
    private void updateIterations(View rootview){
        TextView value = (TextView) rootview.findViewById(R.id.iterations_value);

        double num = Brain.getIterations();

        value.setText(""+num);

    }
}