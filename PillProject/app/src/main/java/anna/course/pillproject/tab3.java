
package anna.course.pillproject;

//package samples.exoguru.materialtabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Handler;
import android.view.ViewGroup;


import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class tab3 extends Fragment implements SensorEventListener{

    private TextView xText, yText, zText;
    private Sensor mySensor_Accelerometer, mySensor_Pedometer;
    private SensorManager SM;

    private TextView count;

    // Steps counted in current session
    private int mSteps = 0;
    // Value of the step counter sensor when the listener was registered.
    // (Total steps are calculated from this value.)
    private int mCounterSteps = 0;
    // Steps counted by the step counter previously. Used to keep counter consistent across rotation
    // changes
    private int mPreviousCounterSteps = 0;

    int flag = 0;

    int eight_oclock = 0;
    int nine_oclock = 0;
    int ten_oclock = 0;
    int eleven_oclock = 0;
    int twelve_oclock = 0;
    int thirteen_oclock = 0;
    int fourteen_oclock = 0;
    int fifteen_oclock = 0;


    @Override
    public void onResume() {
        super.onResume();
        // BIND sensor here with mActivity,
        // could also be done in other fragment lifecycle events,
        // depends on how you handle configChanges

        // Create our Sensor Manager
        //SM = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        //sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        // Accelerometer Sensor
        //mySensor_Accelerometer = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Register sensor Listener
        //SM.registerListener(this, mySensor_Accelerometer, SensorManager.SENSOR_DELAY_UI);


        // Pedometer Sensor
        mySensor_Pedometer = SM.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        SM.registerListener(this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_UI);
        //mPreviousCounterSteps = mSteps;

        SharedPreferences sharedPref_pedometer = getActivity().getSharedPreferences("Settings_Pedometer", 0);
        final SharedPreferences.Editor editor = sharedPref_pedometer.edit();

        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        Integer current_hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format


        Integer eight_oclock = sharedPref_pedometer.getInt("eight_oclock", 0);
        Integer nine_oclock = sharedPref_pedometer.getInt("nine_oclock", 0);
        Integer ten_oclock = sharedPref_pedometer.getInt("ten_oclock", 0);
        Integer eleven_oclock = sharedPref_pedometer.getInt("eleven_oclock", 0);
        Integer twelve_oclock = sharedPref_pedometer.getInt("twelve_oclock", 0);
        Integer thirteen_oclock = sharedPref_pedometer.getInt("thirteen_oclock", 0);
        Integer fourteen_oclock = sharedPref_pedometer.getInt("fourteen_oclock", 0);
        Integer fifteen_oclock = sharedPref_pedometer.getInt("fifteen_oclock", 0);

        if (current_hour == 8) {
            mSteps = eight_oclock;
            mPreviousCounterSteps = mSteps;
        } else if (current_hour == 9) {
            mSteps = nine_oclock;
            mPreviousCounterSteps = mSteps;
        } else if (current_hour == 10) {
            mSteps = ten_oclock;
            mPreviousCounterSteps = mSteps;
        } else if (current_hour == 11) {
            mSteps = eleven_oclock;
            mPreviousCounterSteps = mSteps;
        } else if (current_hour == 12) {
            mSteps = twelve_oclock;
            mPreviousCounterSteps = mSteps;
        } else if (current_hour == 13) {
            mSteps = thirteen_oclock;
            mPreviousCounterSteps = mSteps;
        } else if (current_hour == 14) {
            mSteps = fourteen_oclock;
            mPreviousCounterSteps = mSteps;
        } else if (current_hour == 15) {
            mSteps = fifteen_oclock;
            mPreviousCounterSteps = mSteps;
        }

        editor.apply();

        TextView count = (TextView) getView().findViewById(R.id.your_analysis);

        //count.setText(String.valueOf(event.values[0]));
        count.setText("You have taken " + String.valueOf(mSteps) + " steps!");


        editor.putString("current_day", "Sunday");

        // make a graph
        // find the graph on the layout
        GraphView graph = (GraphView) getView().findViewById(R.id.graph);



    }

    @Override
    public void onPause() {
        super.onPause();
        // UNBIND sensor here from mActivity,
        // could also be done in other fragment lifecycle events,
        // depends on how you handle configChanges

        SM.unregisterListener(this);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab2,container,false);


        // Create our Sensor Manager
        SM = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        // Pedometer Sensor
        mySensor_Pedometer = SM.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        SM.registerListener(this, mySensor_Pedometer, SensorManager.SENSOR_DELAY_UI);
        mPreviousCounterSteps = mSteps;



        flag = 0;


        final Button reset_button = (Button) view.findViewById(R.id.reset_pedometer);
        reset_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                mSteps = 0;
                mCounterSteps = 0;
                mPreviousCounterSteps = 0;
                TextView count = (TextView) getView().findViewById(R.id.your_analysis);
                count.setText("You have taken " + String.valueOf(mSteps) + " steps!");

                SharedPreferences sharedPref_pedometer = getActivity().getSharedPreferences("Settings_Pedometer", 0);
                final SharedPreferences.Editor editor = sharedPref_pedometer.edit();

                editor.putInt("eight_oclock", 0);
                editor.putInt("nine_oclock", 0);
                editor.putInt("ten_oclock", 0);
                editor.putInt("eleven_oclock", 0);
                editor.putInt("twelve_oclock", 0);
                editor.putInt("thirteen_oclock", 0);
                editor.putInt("fourteen_oclock", 0);
                editor.putInt("fifteen_oclock", 0);

                editor.commit();

                flag = 1;
            }
        });

        return view;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAccuracyChanged (Sensor sensor,int accuracy){
        // Not in use
    }



    @Override
    public void onSensorChanged (SensorEvent event) {

        SharedPreferences sharedPref_pedometer = getActivity().getSharedPreferences("Settings_Pedometer", 0);
        final SharedPreferences.Editor editor = sharedPref_pedometer.edit();

        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                if (mCounterSteps < 1) {
                    // initial value
                    mCounterSteps = (int) event.values[0];
                }

                if (flag == 1) {
                    // initial value
                    mCounterSteps = (int) event.values[0];
                    flag = 0;
                }

                // Calculate steps taken based on first counter value received.
                mSteps = (int) event.values[0] - mCounterSteps;

                // Add the number of steps previously taken, otherwise the counter would start at 0.
                // This is needed to keep the counter consistent across rotation changes.
                mSteps = mSteps + mPreviousCounterSteps;


                Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                Integer current_hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format

                // check if it is not the current hour
                Integer past_hour = sharedPref_pedometer.getInt("past hour", 0);

                if (current_hour == past_hour) {
                    // yay everything is the same, as you were
                    editor.putInt("past hour", current_hour);
                }
                else {
                    mSteps = 0;
                    mCounterSteps = 0;
                    mPreviousCounterSteps = 0;
                    TextView count = (TextView) getView().findViewById(R.id.your_analysis);
                    count.setText("You have taken " + String.valueOf(mSteps) + " steps!");
                }

                editor.putInt("past hour", current_hour);



                if (current_hour == 8) {
                    editor.putInt("eight_oclock", mSteps);
                    editor.putInt("past hour", 8);
                } else if (current_hour == 9) {
                    editor.putInt("nine_oclock", mSteps);
                    editor.putInt("past hour", 9);
                } else if (current_hour == 10) {
                    editor.putInt("ten_oclock", mSteps);
                    editor.putInt("past hour", 10);
                } else if (current_hour == 11) {
                    editor.putInt("eleven_oclock", mSteps);
                    editor.putInt("past hour", 11);
                } else if (current_hour == 12) {
                    editor.putInt("twelve_oclock", mSteps);
                    editor.putInt("past hour", 12);
                } else if (current_hour == 13) {
                    editor.putInt("thirteen_oclock", mSteps);
                    editor.putInt("past hour", 13);
                } else if (current_hour == 14) {
                    editor.putInt("fourteen_oclock", mSteps);
                    editor.putInt("past hour", 14);
                } else if (current_hour == 15) {
                    editor.putInt("fifteen_oclock", mSteps);
                    editor.putInt("past hour", 15);
                }


                TextView count = (TextView) getView().findViewById(R.id.your_analysis);

                //count.setText(String.valueOf(event.values[0]));
                count.setText("You have taken " + String.valueOf(mSteps) + " steps!");


                editor.putString("current_day", "Sunday");

                // make a graph
                // find the graph on the layout
                GraphView graph = (GraphView) getView().findViewById(R.id.graph);

                graph.setTitle("number of steps per hour");
                graph.getGridLabelRenderer().setVerticalAxisTitle("number of steps");
                graph.getGridLabelRenderer().setHorizontalAxisTitle("o'clock, military time");



                Integer eight_oclock = sharedPref_pedometer.getInt("eight_oclock", 0);
                Integer nine_oclock = sharedPref_pedometer.getInt("nine_oclock", 0);
                Integer ten_oclock = sharedPref_pedometer.getInt("ten_oclock", 0);
                Integer eleven_oclock = sharedPref_pedometer.getInt("eleven_oclock", 0);
                Integer twelve_oclock = sharedPref_pedometer.getInt("twelve_oclock", 0);
                Integer thirteen_oclock = sharedPref_pedometer.getInt("thirteen_oclock", 0);
                Integer fourteen_oclock = sharedPref_pedometer.getInt("fourteen_oclock", 0);
                Integer fifteen_oclock = sharedPref_pedometer.getInt("fifteen_oclock", 0);


                BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(8, eight_oclock),
                        new DataPoint(9, nine_oclock),
                        new DataPoint(10, ten_oclock),
                        new DataPoint(11, eleven_oclock),
                        new DataPoint(12, twelve_oclock),
                        new DataPoint(13, thirteen_oclock),
                        new DataPoint(14, fourteen_oclock),
                        new DataPoint(15, fifteen_oclock)
                });


                graph.addSeries(series);
                graph.removeAllSeries();

                series.resetData(new DataPoint[]{
                });

                graph.removeAllSeries();
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(8, eight_oclock),
                        new DataPoint(9, nine_oclock),
                        new DataPoint(10, ten_oclock),
                        new DataPoint(11, eleven_oclock),
                        new DataPoint(12, twelve_oclock),
                        new DataPoint(13, thirteen_oclock),
                        new DataPoint(14, fourteen_oclock),
                        new DataPoint(15, fifteen_oclock)
                });

                graph.addSeries(series2);
                editor.commit();

            }

        }


    }}