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
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SpringfestActivity extends ActionBarActivity {

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

        setContentView(R.layout.activity_springfest);

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
                    "mailto","info.springfest18@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Springfest Contact Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Springfest Managers, \n \n ");
            startActivity(emailIntent);
        }
        else if(item.getItemId() == R.id.action_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            uriString = "https://eeb3studentcourses.weebly.com";
            i.putExtra(Intent.EXTRA_TEXT, "Want to take part in Springfest? Then send us an email at info.springfest18@gmail.com or check out the roles you can apply for in the EEB3 App!");
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
            View rootView = inflater.inflate(R.layout.overview_springfest, container, false);
            return rootView;
        }
    }

    public static class ParticipateFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        public static ViewPager mViewPager1;
        private static int currentPage = 0;
        private int[] layouts;
        private static int NUM_PAGES = 0;
        private ArrayList<ImageModel> imageModelArrayList;
        private Context context;
        private LinearLayout dotsLayout;
        private TextView[] dots;
        private ChildSectionsPagerAdapter mViewSectionsPagerAdapter;

        private int[] myImageList = new int[]{R.drawable.updates_svg, R.drawable.eebgreen_cabbage,
                R.drawable.sc_instagram_banner_empty,R.drawable.ic_short_film_fest_border
                ,R.drawable.springfest_logo_jan_2018,R.drawable.assignment_shadow};
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
            View rootView = inflater.inflate(R.layout.participate_springfest, container, false);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            context = getContext();
            mViewPager1 = (ViewPager) view.findViewById(R.id.springfestContainer);
            mViewSectionsPagerAdapter = new ChildSectionsPagerAdapter(getChildFragmentManager());
            mViewPager1.setAdapter(mViewSectionsPagerAdapter);
            mViewPager1.addOnPageChangeListener(viewPagerPageChangeListener);
            dotsLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
            layouts = new int[]{
                    R.layout.general_manager_fragment,
                    R.layout.security_fragment,
                    R.layout.crew_fragment,
                    R.layout.fashion_fragment,
                    R.layout.singing_fragment,
                    R.layout.dancing_fragment,
                    R.layout.gymnastics_fragment,
                    R.layout.rhythmic_fragment};
            addBottomDots(0);
        }

        private void addBottomDots(int currentPage) {
            dots = new TextView[layouts.length];

            int[] colorsActive = getResources().getIntArray(R.array.array_springfest_active);
            int[] colorsInactive = getResources().getIntArray(R.array.array_springfest_inactive);

            dotsLayout.removeAllViews();
            for (int i = 0; i < dots.length; i++) {
                dots[i] = new TextView(context);
                dots[i].setText(Html.fromHtml("&#8226;"));
                dots[i].setTextSize(35);
                dots[i].setTextColor(colorsInactive[currentPage]);
                dotsLayout.addView(dots[i]);
            }

            if (dots.length > 0)
                dots[currentPage].setTextColor(colorsActive[currentPage]);
        }

        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };
    }

    public static class ChildSectionsPagerAdapter extends FragmentPagerAdapter {

        public ChildSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return GMFragment.newInstance(position+1);
                case 1:
                    return SecurityFragment.newInstance(position+1);
                case 2:
                    return CrewFragment.newInstance(position+1);
                case 3:
                    return FashionFragment.newInstance(position+1);
                case 4:
                    return SingingFragment.newInstance(position+1);
                case 5:
                    return DancingFragment.newInstance(position+1);
                case 6:
                    return GymnFragment.newInstance(position+1);
                case 7:
                    return RhythmicFragment.newInstance(position+1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "_";
                case 1:
                    return "_";
                case 2:
                    return "_";
                case 3:
                    return "_";
                case 4:
                    return "_";
                case 5:
                    return "_";
                case 6:
                    return "_";
                case 7:
                    return "_";
            }
            return null;
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
            View rootView = inflater.inflate(R.layout.websites_springfest, container, false);
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
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.youtube.com/channel/UCZYYz7ovNEgkS9zXHAiKQEQ"));
            startActivity(intent);
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
        else if (v.getId() == R.id.left_fab) {
            if (ParticipateFragment.mViewPager1.getCurrentItem() == 1) {
                ParticipateFragment.mViewPager1.setCurrentItem(0);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 2) {
                ParticipateFragment.mViewPager1.setCurrentItem(1);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 3) {
                ParticipateFragment.mViewPager1.setCurrentItem(2);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 4) {
                ParticipateFragment.mViewPager1.setCurrentItem(3);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 5) {
                ParticipateFragment.mViewPager1.setCurrentItem(4);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 6) {
                ParticipateFragment.mViewPager1.setCurrentItem(5);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 7) {
                ParticipateFragment.mViewPager1.setCurrentItem(6);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 8) {
                ParticipateFragment.mViewPager1.setCurrentItem(7);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 9) {
                ParticipateFragment.mViewPager1.setCurrentItem(8);
            }
        }
        else if (v.getId() == R.id.right_fab) {
            if (ParticipateFragment.mViewPager1.getCurrentItem() == 0) {
                ParticipateFragment.mViewPager1.setCurrentItem(1);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 1) {
                ParticipateFragment.mViewPager1.setCurrentItem(2);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 2) {
                ParticipateFragment.mViewPager1.setCurrentItem(3);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 3) {
                ParticipateFragment.mViewPager1.setCurrentItem(4);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 4) {
                ParticipateFragment.mViewPager1.setCurrentItem(5);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 5) {
                ParticipateFragment.mViewPager1.setCurrentItem(6);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 6) {
                ParticipateFragment.mViewPager1.setCurrentItem(7);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 7) {
                ParticipateFragment.mViewPager1.setCurrentItem(8);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 8) {
                ParticipateFragment.mViewPager1.setCurrentItem(9);
            }
            else if (ParticipateFragment.mViewPager1.getCurrentItem() == 9) {
                ParticipateFragment.mViewPager1.setCurrentItem(10);
            }
        }
        else if (v.getId() == R.id.gmcard) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","info.springfest18@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Springfest Question Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Springfest Managers, \n \n ");
            startActivity(emailIntent);
        }
        else if (v.getId() == R.id.securitycard) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","secure.sf18@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Security Apply Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Security Managers, \n \n ");
            startActivity(emailIntent);
        }
        else if (v.getId() == R.id.crewcard) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","eeb3.crewsf18@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Crew Apply Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Crew Managers, \n \n ");
            startActivity(emailIntent);
        }
        else if (v.getId() == R.id.fashioncard) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","fashionshowsf18@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Fashion Apply Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Fashion Managers, \n \n ");
            startActivity(emailIntent);
        }
        else if (v.getId() == R.id.singingcard) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","sfocsinging@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Singing Apply Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Singing Managers, \n \n ");
            startActivity(emailIntent);
        }
        else if (v.getId() == R.id.dancingcard) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","ocdance2018@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Dancing Apply Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Dance Managers, \n \n ");
            startActivity(emailIntent);
        }
        else if (v.getId() == R.id.gymnasticscard) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","gymnastics.sp18@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Gymnastics Apply Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Gymnastics Managers, \n \n ");
            startActivity(emailIntent);
        }
        else if (v.getId() == R.id.rhythmiccard) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","rgspringfest2018@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - Rhythmic Gymnastics Apply Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Rhythmic Gymnastics Managers, \n \n ");
            startActivity(emailIntent);
        }

    }


    public static class GMFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public GMFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static GMFragment newInstance(int sectionNumber) {
            GMFragment fragment = new GMFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.general_manager_fragment, container, false);
            return rootView;
        }
    }

    public static class CrewFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public CrewFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static CrewFragment newInstance(int sectionNumber) {
            CrewFragment fragment = new CrewFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.crew_fragment, container, false);
            return rootView;
        }
    }

    public static class SecurityFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public SecurityFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SecurityFragment newInstance(int sectionNumber) {
            SecurityFragment fragment = new SecurityFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.security_fragment, container, false);
            return rootView;
        }
    }

    public static class SingingFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public SingingFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SingingFragment newInstance(int sectionNumber) {
            SingingFragment fragment = new SingingFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.singing_fragment, container, false);
            return rootView;
        }
    }

    public static class DancingFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public DancingFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static DancingFragment newInstance(int sectionNumber) {
            DancingFragment fragment = new DancingFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.dancing_fragment, container, false);
            return rootView;
        }
    }

    public static class GymnFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public GymnFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static GymnFragment newInstance(int sectionNumber) {
            GymnFragment fragment = new GymnFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.gymnastics_fragment, container, false);
            return rootView;
        }
    }

    public static class RhythmicFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public RhythmicFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RhythmicFragment newInstance(int sectionNumber) {
            RhythmicFragment fragment = new RhythmicFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.rhythmic_fragment, container, false);
            return rootView;
        }
    }

    public static class FashionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public FashionFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FashionFragment newInstance(int sectionNumber) {
            FashionFragment fragment = new FashionFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fashion_fragment, container, false);
            return rootView;
        }
    }
}
