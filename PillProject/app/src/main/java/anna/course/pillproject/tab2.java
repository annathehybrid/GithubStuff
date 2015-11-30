package anna.course.pillproject;

//package samples.exoguru.materialtabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class tab2 extends Fragment implements SensorEventListener{

    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab2,container,false);


        // Assign TextView
        TextView xText = (TextView)view.findViewById(R.id.xText);
        TextView yText = (TextView)view.findViewById(R.id.yText);
        TextView zText = (TextView)view.findViewById(R.id.zText);


        // Create our Sensor Manager
        //SM = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        SM = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        // Accelerometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);


        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        try {
            TextView xText = (TextView) getView().findViewById(R.id.xText);
            xText.setText("X: " + event.values[0]);

            TextView yText = (TextView) getView().findViewById(R.id.yText);
            yText.setText("Y: " + event.values[1]);

            TextView zText = (TextView) getView().findViewById(R.id.zText);
            zText.setText("Z: " + event.values[2]);
        } catch(NullPointerException e) {
            System.out.println(e.toString());
        }




    }


}
