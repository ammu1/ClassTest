package com.iitkgp.amritha.classtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ArrayList<Float> floatPointList, floatPointListP, floatPointListG, floatPointListL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button backButton = (Button)this.findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Accelerometer Graph
        floatPointList = new ArrayList<Float>();
        floatPointList = (ArrayList<Float>) getIntent().getSerializableExtra("sensorXData");
        float[] data = new float[floatPointList.size()];
        for (int i = 0; i < floatPointList.size(); i++)
            data[i] = Float.parseFloat(Float.toString(floatPointList.get(i)));
        DataPoint dataPoints[] = new DataPoint[floatPointList.size()];
        for (int i = 0; i < floatPointList.size(); i++) {
            DataPoint point = new DataPoint(i, floatPointList.get(i));
            dataPoints[i] = point;
        }
        GraphView graph = (GraphView) findViewById(R.id.graphA);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        graph.addSeries(series);



        //Pressure Graph
        floatPointListP = new ArrayList<Float>();
        floatPointListP = (ArrayList<Float>) getIntent().getSerializableExtra("sensorZData");
        if(floatPointListP.size()>0){
            float[] dataP = new float[floatPointListP.size()];
            for (int i = 0; i < floatPointListP.size(); i++)
                dataP[i] = Float.parseFloat(Float.toString(floatPointListP.get(i)));

            DataPoint dataPointsP[] = new DataPoint[floatPointListP.size()];
            for (int i = 0; i < floatPointListP.size(); i++) {
                DataPoint point = new DataPoint(i, floatPointListP.get(i));
                dataPointsP[i] = point;
            }
            GraphView graphP = (GraphView) findViewById(R.id.graphP);
            LineGraphSeries<DataPoint> seriesP = new LineGraphSeries<DataPoint>(dataPointsP);
            graph.addSeries(seriesP);
        }


        //Light Graph
        floatPointListL = new ArrayList<Float>();
        floatPointListL = (ArrayList<Float>) getIntent().getSerializableExtra("sensorYData");
        if(floatPointListL.size()>0){
            float[] dataL = new float[floatPointListL.size()];
            for (int i = 0; i < floatPointListL.size(); i++)
                dataL[i] = Float.parseFloat(Float.toString(floatPointListL.get(i)));

            DataPoint dataPointsL[] = new DataPoint[floatPointListL.size()];
            for (int i = 0; i < floatPointListL.size(); i++) {
                DataPoint point = new DataPoint(i, floatPointListL.get(i));
                dataPointsL[i] = point;
            }
            GraphView graphL = (GraphView) findViewById(R.id.graphL);
            LineGraphSeries<DataPoint> seriesL = new LineGraphSeries<DataPoint>(dataPointsL);
            graph.addSeries(seriesL);
        }

        //Temperature Graph
        floatPointListG = new ArrayList<Float>();
        floatPointListG = (ArrayList<Float>) getIntent().getSerializableExtra("sensorGData");
        if(floatPointListG.size()>0){
            float[] dataG = new float[floatPointListG.size()];
            for (int i = 0; i < floatPointListG.size(); i++)
                dataG[i] = Float.parseFloat(Float.toString(floatPointListG.get(i)));

            DataPoint dataPointsG[] = new DataPoint[floatPointListG.size()];
            for (int i = 0; i < floatPointListG.size(); i++) {
                DataPoint point = new DataPoint(i, floatPointListG.get(i));
                dataPointsG[i] = point;
            }
            GraphView graphG = (GraphView) findViewById(R.id.graphG);
            LineGraphSeries<DataPoint> seriesG = new LineGraphSeries<DataPoint>(dataPointsG);
            graph.addSeries(seriesG);
        }
    }
}
