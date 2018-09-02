package eeb3.anirudhhramesh.com.eeb3;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class StudentCoursesTabbed extends ActionBarActivity {

    public static String FACEBOOK_URL = "https://www.facebook.com/eeb3studentcourses";
    public static String FACEBOOK_PAGE_ID = "eeb3studentcourses";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    String uriString;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_student_courses_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartasks);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_courses_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        else if(item.getItemId() == R.id.action_contact){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","eeb3devs@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Student Courses Contact Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Student Courses, \n \n ");
            startActivity(emailIntent);
        }
        else if(item.getItemId() == R.id.action_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            uriString = "https://eeb3studentcourses.weebly.com";
            i.putExtra(Intent.EXTRA_TEXT, "Would you like to join courses taught by your fellow students? Here at the Student Courses, we have a range of diverse courses that you can join, for free! These courses will be during lunchtime at EEB3. For more info, check out https://eeb3studentcourses.weebly.com");
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public String getFacebookPageURL(Context context){
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class OverviewFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public OverviewFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static OverviewFragment newInstance(int sectionNumber) {
            OverviewFragment fragment = new OverviewFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.overview_student_courses, container, false);
            return rootView;
        }
    }

    public static class ParticipateFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public ParticipateFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ParticipateFragment newInstance(int sectionNumber) {
            ParticipateFragment fragment = new ParticipateFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.participate_student_courses, container, false);
            return rootView;
        }
    }

    public static class WebsiteFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public WebsiteFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static WebsiteFragment newInstance(int sectionNumber) {
            WebsiteFragment fragment = new WebsiteFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.websites_student_courses, container, false);
            return rootView;
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
            switch (position){
                case 0:
                    return OverviewFragment.newInstance(position+1);
                case 1:
                    return ParticipateFragment.newInstance(position+1);
                default:
                    return WebsiteFragment.newInstance(position+1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.overview);
                case 1:
                    return getString(R.string.participate);
                case 2:
                    return getString(R.string.websites);
            }
            return null;
        }
    }

    public void onButtonClick(View v){
        if (v.getId() == R.id.studentbutton) {
            Intent i = new Intent(this, SCCoursesWeb.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.teacherbutton) {
            Intent i = new Intent(this, SCTeachersWeb.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.scweeblybutton) {
            Intent i = new Intent(this, scweeblyweb.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.scfacebookbutton) {
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = getFacebookPageURL(this);
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);
        }
        else if (v.getId() == R.id.scinstagrambutton) {
            Uri uri = Uri.parse("http://instagram.com/_u/eeb3studentcourses");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/eeb3studentcourses")));
            }
        }


    }
}
