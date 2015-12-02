package anna.course.pillproject;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    //This method returns the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        // if the position is 0, then we show fragment tab 1
        if(position == 0)
        {
            tab1 tab1 = new tab1();
            return tab1;
        }
        // if the position is 1, then we show fragment tab 2
        else if  (position == 2)
        {
            tab2 tab2 = new tab2();
            return tab2;
        }
        // else, we show tab 3. Will have to extend this, if we we want more tabs
        else {
            tab3 tab3 = new tab3();
            return tab3;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}