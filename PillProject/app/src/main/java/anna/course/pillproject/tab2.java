package anna.course.pillproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// this class populates the RecyclerView, which is like Listview 2.0
// also ListView is deprecated
// The values are first chugged through CustomAdapter

public class tab2 extends Fragment {

    private static final int DATASET_COUNT = 7;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab3, container, false);

        // initializeRecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set CustomAdapter as the adapter for RecyclerView.
        mAdapter = new CustomAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }


    // Generates Strings for RecyclerView's adapter
    private void initDataset() {
        mDataset = new String[DATASET_COUNT];

        SharedPreferences sharedPref = getActivity().getSharedPreferences("Settings", 0);

        String pill_time0 = sharedPref.getString("Monday", "0 o'clock");
        String pill_time1 = sharedPref.getString("Tuesday", "0 o'clock");
        String pill_time2 = sharedPref.getString("Wednesday", "0 o'clock");
        String pill_time3 = sharedPref.getString("Thursday", "0 o'clock");
        String pill_time4 = sharedPref.getString("Friday", "0 o'clock");
        String pill_time5 = sharedPref.getString("Saturday", "0 o'clock");
        String pill_time6 = sharedPref.getString("Sunday", "0 o'clock");

        for (int i = 0; i < DATASET_COUNT; i++) {

            if (i == 0) {
                mDataset[i] = "Monday: " + "taken " + pill_time0;
            }
            if (i == 1) {
                mDataset[i] = "Tuesday: " + "taken " + pill_time1;
            }
            if (i == 2) {
                mDataset[i] = "Wednesday: " + "taken " + pill_time2;
            }
            if (i == 3) {
                mDataset[i] = "Thursday: " + "taken " + pill_time3;
            }
            if (i == 4) {
                mDataset[i] = "Friday: " + "taken " + pill_time4;
            }
            if (i == 5) {
                mDataset[i] = "Saturday: " + "taken " + pill_time5;
            }
            if (i == 6) {
                mDataset[i] = "Sunday: " + "taken " + pill_time6;
            }
        }

    }
}