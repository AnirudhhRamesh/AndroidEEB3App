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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class ShortFilmFestival extends AppCompatActivity {

    public static String FACEBOOK_URL = "https://www.facebook.com/EEB3ShortFilmFestival";
    public static String FACEBOOK_PAGE_ID = "EEB3ShortFilmFestival";

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

        setContentView(R.layout.activity_short_film_festival);

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
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        else if(item.getItemId() == R.id.action_contact){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","sffestivaleeb3@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Short Film Fest Contact Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Short Film Fest,\n \n ");
            startActivity(emailIntent);
        }
        else if(item.getItemId() == R.id.action_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, "Want to participate in the EEB3 Short Film Festival? Starting October, we're organising a festival in which you and your crew can send in your short film, to be graded by our independent jury! Best of all, we'll hold a gala in mid-April to give a sweet reward to the winners and share your collective masterpieces with the school community! Interested? Contact us at: sffestivaleeb3@gmail.com");
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
        public static ShortFilmFestival.OverviewFragment newInstance(int sectionNumber) {
            ShortFilmFestival.OverviewFragment fragment = new ShortFilmFestival.OverviewFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.overview_filmfest, container, false);
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
        public static ShortFilmFestival.ParticipateFragment newInstance(int sectionNumber) {
            ShortFilmFestival.ParticipateFragment fragment = new ShortFilmFestival.ParticipateFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.participate_filmfest, container, false);
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
        public static ShortFilmFestival.WebsiteFragment newInstance(int sectionNumber) {
            ShortFilmFestival.WebsiteFragment fragment = new ShortFilmFestival.WebsiteFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.websites_filmfest, container, false);
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
                    return ShortFilmFestival.OverviewFragment.newInstance(position+1);
                case 1:
                    return ShortFilmFestival.ParticipateFragment.newInstance(position+1);
                default:
                    return ShortFilmFestival.WebsiteFragment.newInstance(position+1);
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
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = getFacebookPageURL(this);
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);
        }
        else if (v.getId() == R.id.teacherbutton) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","sffestivaleeb3@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Short Film Fest Participate Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Short Film Fest, \n \n");
            startActivity(emailIntent);
        }
        else if (v.getId() == R.id.scweeblybutton) {
            Intent i = new Intent(this, shortfilmfestweb.class);
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
