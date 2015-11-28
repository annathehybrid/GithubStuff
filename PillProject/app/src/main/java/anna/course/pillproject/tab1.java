package anna.course.pillproject;

//package samples.exoguru.materialtabs;

        import android.content.ClipData;
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
        import android.widget.ImageButton;
        import android.widget.TextView;


public class tab1 extends Fragment {

    private TextView option1, option2, option3, choice1, choice2, choice3;
    private ImageButton pill_blue, pill_green, pill_red;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab1, container, false);


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


        // Assign the touch listener to your view which you want to move
        view.findViewById(R.id.pill_blue).setOnTouchListener(new MyTouchListener());
        view.findViewById(R.id.pill_green).setOnTouchListener(new MyTouchListener());
        view.findViewById(R.id.pill_red).setOnTouchListener(new MyTouchListener());

        return view;

    }


    // This defines your touch listener
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
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

