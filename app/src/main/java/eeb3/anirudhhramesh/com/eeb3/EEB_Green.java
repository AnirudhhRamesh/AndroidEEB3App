package eeb3.anirudhhramesh.com.eeb3;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class EEB_Green extends AppCompatActivity {

    public static String FACEBOOK_URL = "https://m.facebook.com/profile.php?id=290689687998751";
    public static String FACEBOOK_PAGE_ID = "profile.php?id=290689687998751";
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

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_eeb__green);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartasks);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }
        else if (id == R.id.action_contact) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","eeb.green.info@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - eeb.GREEN Contact Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear eeb.GREEN, \n \n");
            startActivity(emailIntent);
        }
        else if(item.getItemId() == R.id.action_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, "Do you want to make our school greener and cleaner? We're the EEB.GREEN project, and we're planning to make the school a more environmentally friendly place! If you support our cause and want to take part in this initiative, contact us from our Facebook page: https://m.facebook.com/profile.php?id=290689687998751");
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
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
        public static EEB_Green.OverviewFragment newInstance(int sectionNumber) {
            EEB_Green.OverviewFragment fragment = new EEB_Green.OverviewFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.overview_eebgreen, container, false);
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
        public static EEB_Green.ParticipateFragment newInstance(int sectionNumber) {
            EEB_Green.ParticipateFragment fragment = new EEB_Green.ParticipateFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.participate_eebgreen, container, false);
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
        public static EEB_Green.WebsiteFragment newInstance(int sectionNumber) {
            EEB_Green.WebsiteFragment fragment = new EEB_Green.WebsiteFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.websites_eebgreen, container, false);
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
                    return EEB_Green.OverviewFragment.newInstance(position+1);
                case 1:
                    return EEB_Green.ParticipateFragment.newInstance(position+1);
                default:
                    return EEB_Green.WebsiteFragment.newInstance(position+1);
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

    public void onButtonClick(View v) {
        if (v.getId() == R.id.studentbutton) {
            {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "eeb.green.info@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - eeb.GREEN Participate Form");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear eeb.GREEN, \n \n I would love to participate in the eeb.GREEN project... \n \n");
                startActivity(emailIntent);
            }
        } else if (v.getId() == R.id.scweeblybutton) {
            Intent i = new Intent(this, eebgreenweb.class);
            startActivity(i);
        } else if (v.getId() == R.id.scfacebookbutton) {
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = getFacebookPageURL(this);
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);
        }
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
}
