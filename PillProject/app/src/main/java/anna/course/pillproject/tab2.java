package anna.course.pillproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class tab2 extends Fragment {

    private static final int DATASET_COUNT = 7;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab3,container,false);

        // BEGIN_INCLUDE(initializeRecyclerView)
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


    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */

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
                String pill_day = sharedPref.getString("Monday", "missing");
                mDataset[i] = "Monday: " + "fiber taken at " + pill_time0;
            }
            if (i == 1) {
                String pill_day = sharedPref.getString("Tuesday", "missing");
                mDataset[i] = "Tuesday: " + "fiber at " + pill_time1;
            }
            if (i == 2) {
                String pill_day = sharedPref.getString("Wednesday", "missing");
                mDataset[i] = "Wednesday: " + "fiber at " + pill_time2;
            }
            if (i == 3) {
                String pill_day = sharedPref.getString("Thursday", "missing");
                mDataset[i] = "Thursday: " + "fiber at " + pill_time3;
            }
            if (i == 4) {
                String pill_day = sharedPref.getString("Friday", "missing");
                mDataset[i] = "Friday: " + "fiber at " + pill_time4;
            }
            if (i == 5) {
                String pill_day = sharedPref.getString("Saturday", "missing");
                mDataset[i] = "Saturday: " + "fiber at " + pill_time5;
            }
            if (i == 6) {
                String pill_day = sharedPref.getString("Sunday", "missing");
                mDataset[i] = "Sunday: " + "fiber at " + pill_time6;
            }
        }


    }
}