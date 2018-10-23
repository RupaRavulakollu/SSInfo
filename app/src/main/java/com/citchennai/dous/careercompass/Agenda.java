package com.citchennai.dous.careercompass;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class Agenda extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_agenda, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */


        TextView day111,day112,day121,day122,day131,day132;
        TextView day211,day212,day221,day222,day231,day232;
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;

                if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
                {
                    rootView = inflater.inflate(R.layout.fragment_agenda, container, false);
                    day111= rootView.findViewById(R.id.day1first1);
                    day112 = rootView.findViewById(R.id.day1firs2);

                    day121 = rootView.findViewById(R.id.day1second1);
                    day122=rootView.findViewById(R.id.day1second2);

                    day131=rootView.findViewById(R.id.day1third1);
                    day132=rootView.findViewById(R.id.day1third2);

                    day111.setOnClickListener(this);
                    day112.setOnClickListener(this);
                    day121.setOnClickListener(this);
                    day122.setOnClickListener(this);
                    day131.setOnClickListener(this);
                    day132.setOnClickListener(this);
                    Log.e("0","came0");


                }
                if(getArguments().getInt(ARG_SECTION_NUMBER)==2)
                {
                   rootView= inflater.inflate(R.layout.fragment_agenda2, container, false);
                    day211= rootView.findViewById(R.id.day2first1);
                    day212 = rootView.findViewById(R.id.day2first2);

                    day221 = rootView.findViewById(R.id.day2second1);
                    day222=rootView.findViewById(R.id.day2second2);

                    day231=rootView.findViewById(R.id.day2third1);
                    day232=rootView.findViewById(R.id.day2third2);

                    day211.setOnClickListener(this);
                    day212.setOnClickListener(this);
                    day221.setOnClickListener(this);
                    day222.setOnClickListener(this);
                    day231.setOnClickListener(this);
                    day232.setOnClickListener(this);

                    Log.e("1","came1");
                }

            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.day1first1:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day1first1.htm"));
                    break;
                case R.id.day1firs2:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day1firs2.htm"));
                    break;
                case R.id.day1second1:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day1second1.htm"));
                    break;
                case R.id.day1second2:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day1second2.htm"));
                    break;
                case R.id.day1third1:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day1third1.htm"));
                    break;
                case R.id.day1third2:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day2third2.htm"));
                    break;

    //day2
                case R.id.day2first1:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day2first1.htm"));
                    break;
                case R.id.day2first2:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day2first2.htm"));
                    break;
                case R.id.day2second1:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day2second1.htm"));
                    break;
                case R.id.day2second2:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day2second2.htm"));
                    break;
                case R.id.day2third1:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day2third1.htm"));
                    break;
                case R.id.day2third2:
                    startActivity(new Intent(getContext(),AgendaActivity.class).putExtra("url","https://cittechfest.000webhostapp.com/careercompass/html/day2third2.htm"));
                    break;


            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}
