package anna.course.pillproject;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;


// this fragment controls the pill-taking screen
// option 1 is the pill
// choice 1, 2 and 3 are the time slots
// they're hard-coded time slots, should think about getting the hour dynamically
// anyway the touch and drag-and-drop listeners are set on option 1 and the 3 choices
// the values are put into the key-value dictionary, sharedpreferences
// those values are later referenced by the history tab to make a list of times taken
// enter button enters values
// reset button resets values <-- basically just for debugging
// I think that's it

public class tab1 extends Fragment {

    // the UI elements don't need to be declared here
    // but I like them because it reminds me of what I'm working with
    private TextView option1, option2, option3, choice1, choice2, choice3;
    private Spinner spinner_day, reset;
    private Button enter;
    private String pill_first = "";
    private String current_day;
    private int flag = 0;

    // this inflates the layout fragment
    // we're inflating the pill, the 3 drag and drop things, and the 2 buttons
    // also initiating the listeners
    // and the enter and reset buttons
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_tab1, container, false);

        //views to drag
        final TextView option1 = (TextView) view.findViewById(R.id.option_1);

        //set touch listeners
        option1.setOnTouchListener(new ChoiceTouchListener());

        //views to drop onto
        final TextView choice1 = (TextView) view.findViewById(R.id.choice_1);
        final TextView choice2 = (TextView) view.findViewById(R.id.choice_2);
        final TextView choice3 = (TextView) view.findViewById(R.id.choice_3);

        //set drag listeners
        choice1.setOnDragListener(new ChoiceDragListener());
        choice2.setOnDragListener(new ChoiceDragListener());
        choice3.setOnDragListener(new ChoiceDragListener());

        // inflate the spinner
        final Spinner spinner_day = (Spinner) view.findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.days_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_day.setAdapter(adapter1);


        // this enter button sends the key-value pairs to the history list
        // I believe it also resets the drag-and-drop
        final Button enter_button = (Button) view.findViewById(R.id.enter);
            enter_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                int selected_day = spinner_day.getSelectedItemPosition();
                spinner_day.setSelection(selected_day);

                SharedPreferences sharedPref = getActivity().getSharedPreferences("Settings", 0);
                final SharedPreferences.Editor editor = sharedPref.edit();

                if (selected_day == 0) {
                    //editor.putString("none", pill_first);
                    current_day = "none-day";
                    editor.putString("current_day", "non-day");
                }
                if (selected_day == 1) {
                    current_day = "Monday";
                    editor.putString("current_day", "Monday");
                }
                if (selected_day == 2) {
                    current_day = "Tuesday";
                    editor.putString("current_day", "Tuesday");
                }
                if (selected_day == 3) {
                    current_day = "Wednesday";
                    editor.putString("current_day", "Wednesday");
                }
                if (selected_day == 4) {
                    current_day = "Thursday";
                    editor.putString("current_day", "Thursday");
                }
                if (selected_day == 5) {
                    current_day = "Friday";
                    editor.putString("current_day", "Friday");
                }
                if (selected_day == 6) {
                    editor.putString("day", "Saturday");
                    current_day = "Saturday";
                    editor.putString("current_day", "Saturday");
                }
                if (selected_day == 7) {
                    current_day = "Sunday";
                    editor.putString("current_day", "Sunday");
                }

                Log.e("logging the adapter", current_day);


                if (current_day .equals("Monday") ) {
                    editor.putString("Monday", pill_first);
                }
                if (current_day .equals("Tuesday") ) {
                    editor.putString("Tuesday", pill_first);
                }
                if (current_day .equals("Wednesday") ) {
                    editor.putString("Wednesday", pill_first);
                }
                if (current_day .equals("Thursday") ) {
                    editor.putString("Thursday", pill_first);
                }
                if (current_day .equals("Friday") ) {
                    editor.putString("Friday", pill_first);
                }
                if (current_day .equals("Saturday") ) {
                    editor.putString("Saturday", pill_first);
                }
                if (current_day .equals("Sunday") ) {
                    editor.putString("Sunday", pill_first);
                }
                if (current_day .equals("none-day") ) {
                    editor.putString("none-day", pill_first);
                }

                Log.e("logging the pill first", pill_first);
                editor.commit();

                // reset Spinners
                spinner_day.setSelection(selected_day);

                // reset flags
                pill_first = "";

                //views to drag

                if (flag == 1) {
                    option1.setVisibility(TextView.VISIBLE);
                }

                flag = 0;

                choice1.setText("8 am");
                choice2.setText("10 am");
                choice3.setText("12 pm");

                choice1.setTypeface(Typeface.DEFAULT);
                choice2.setTypeface(Typeface.DEFAULT);
                choice3.setTypeface(Typeface.DEFAULT);

            }
        });


        // basically resets everything
        // just here for debugging, really
        final Button reset_button = (Button) view.findViewById(R.id.reset);
        reset_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = getActivity().getSharedPreferences("Settings", 0);
                final SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("Monday", "NONE");
                editor.putString("Tuesday", "NONE");
                editor.putString("Wednesday", "NONE");
                editor.putString("Thursday", "NONE");
                editor.putString("Friday", "NONE");
                editor.putString("Saturday", "NONE");
                editor.putString("Saturday", "NONE");
                editor.putString("Sunday", "NONE");

                Log.e("clearing very thing", "done");
                editor.apply();

                spinner_day.setSelection(0);

                // reset flags
                pill_first = "";

                //views to drag
                option1.setVisibility(TextView.VISIBLE);
                flag = 0;

                choice2.setText("8 am");
                choice2.setText("10 am");
                choice3.setText("12 pm");

                choice1.setTypeface(Typeface.DEFAULT);
                choice2.setTypeface(Typeface.DEFAULT);
                choice3.setTypeface(Typeface.DEFAULT);
            }
        });

        return view;

    }

    // default touch listener for option 1
    private final class ChoiceTouchListener implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
            else {
                return false;
            }

        }
    }

    // default listener for the 3 choices
    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);

                    // this flag means that the drag was successful
                    flag = 1;

                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;

                    //view being dragged and dropped
                    TextView dropped = (TextView) view;

                    //set the tag in the target view to the ID of the view being dropped
                    dropTarget.setTag(dropped.getId());

                    //set the tag in the target view to the ID of the view being dropped
                    dropTarget.setTag(dropped.getId());

                    //update the text in the target view to reflect the data being dropped
                    dropTarget.setText(dropTarget.getText() + " " + dropped.getText());

                    //make it bold to highlight the fact that an item has been dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);

                    Log.e("dropTarget1", dropTarget.getText().toString());
                    Log.e("dropTarget2", dropped.getText().toString());

                    // this hard-coded if-else
                    // sends 1 of 3 values to the history list
                    // might want to change it to a dynamic int based on hour
                    String check_dropTarget_text = dropTarget.getText().toString();

                    if (check_dropTarget_text.toLowerCase().contains("8 am")) {
                        pill_first = "8 am";
                        Log.e("yes 8 am ", "to fiber");
                    }
                    if (check_dropTarget_text.toLowerCase().contains("10 am")) {
                        pill_first = "10 am";
                        Log.e("yes 10 am ", "to fiber");
                    }
                    if (check_dropTarget_text.toLowerCase().contains("12 pm")) {
                        pill_first = "12 pm";
                        Log.e("yes 12 pm ", "to fiber");
                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }

            return true;
        }
    }

}

