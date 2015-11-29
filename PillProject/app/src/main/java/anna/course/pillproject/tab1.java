package anna.course.pillproject;

//package samples.exoguru.materialtabs;

        import android.content.ClipData;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
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
        import android.widget.Toast;


public class tab1 extends Fragment {

    private TextView option1, option2, option3, choice1, choice2, choice3;
    private ImageButton pill_blue, pill_green, pill_red;
    private Spinner spinner_month, spinner_day;
    private Button enter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_tab1, container, false);


        //views to drag
        TextView option1 = (TextView) view.findViewById(R.id.option_1);
        TextView option2 = (TextView) view.findViewById(R.id.option_2);
        TextView option3 = (TextView) view.findViewById(R.id.option_3);

        //set touch listeners
        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());

        //views to drop onto
        TextView choice1 = (TextView) view.findViewById(R.id.choice_1);
        TextView choice2 = (TextView) view.findViewById(R.id.choice_2);
        TextView choice3 = (TextView) view.findViewById(R.id.choice_3);

        //set drag listeners
        choice1.setOnDragListener(new ChoiceDragListener());
        choice2.setOnDragListener(new ChoiceDragListener());
        choice3.setOnDragListener(new ChoiceDragListener());


        final Spinner spinner_month = (Spinner) view.findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.months_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_month.setAdapter(adapter1);

        final Spinner spinner_day = (Spinner) view.findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.day_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_day.setAdapter(adapter2);

        final Button enter_button = (Button) view.findViewById(R.id.enter);



        enter_button.setOnClickListener(new View.OnClickListener() {

            Spinner spinner_month = (Spinner) view.findViewById(R.id.spinner1);
            Spinner spinner_day = (Spinner) view.findViewById(R.id.spinner2);

            @Override
            public void onClick(View v) {


                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();

                int selected_month_int = spinner_month.getSelectedItemPosition();
                int selected_day = spinner_day.getSelectedItemPosition();

                String selected_month = "A random month";

                if (selected_month_int == 1) {
                    selected_month = "November";
                }
                if (selected_month_int == 2) {
                    selected_month = "December";
                }

                // reset Spinnersg
                spinner_month.setSelection(0);
                spinner_day.setSelection(0);

                String both = selected_month + "_" + selected_day;

                editor.putInt(both, selected_day);
                Log.e("logging the adapter", both);
                editor.apply();


            }
        });

        int pill_first = 0;
        int pill_second = 0;
        int pill_third = 0;


        return view;

    }




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

