package eeb3.anirudhhramesh.com.eeb3;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BISSCActivity extends AppCompatActivity {

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

        setContentView(R.layout.activity_bissc);

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
                    "mailto","gameratramesh@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - BISSC NEWS Contact form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear BISSC, \n \n");
            startActivity(emailIntent);
        }
        else if(item.getItemId() == R.id.action_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, "Are you updated on global and local news? Do you want to find out more about what's going on at school? Just check out our BISSC NEWS page, where you can get updates on everything from world politics to school events! Read our articles on: http://www.bissc.net");
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
        public static BISSCActivity.OverviewFragment newInstance(int sectionNumber) {
            BISSCActivity.OverviewFragment fragment = new BISSCActivity.OverviewFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.overview_bissc, container, false);
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
        public static BISSCActivity.ParticipateFragment newInstance(int sectionNumber) {
            BISSCActivity.ParticipateFragment fragment = new BISSCActivity.ParticipateFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.participate_bissc, container, false);
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
        public static BISSCActivity.WebsiteFragment newInstance(int sectionNumber) {
            BISSCActivity.WebsiteFragment fragment = new BISSCActivity.WebsiteFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.website_bissc, container, false);
            WebView mWebView = (WebView) rootView.findViewById(R.id.BISSCWeb);
            mWebView.loadUrl("http://www.bissc.net/");

            // Enable Javascript
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            // Force links and redirects to open in the WebView instead of in a browser
            final ProgressDialog progress = ProgressDialog.show(getContext(), "Loading articles",
                    "Please wait... ", true);
            mWebView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    if (progress != null)
                        progress.dismiss();
                }
            });
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
                    return BISSCActivity.OverviewFragment.newInstance(position+1);
                case 1:
                    return BISSCActivity.ParticipateFragment.newInstance(position+1);
                default:
                    return BISSCActivity.WebsiteFragment.newInstance(position+1);
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
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","gameratramesh@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - BISSC NEWS New Writer Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear BISSC, \n \n I would love to become a writer, I'm interested in writing for {enter news topic(s)}");
            startActivity(emailIntent);
        }
        if (v.getId() == R.id.fullscreen) {
            Intent i = new Intent(BISSCActivity.this, BISSC.class);
            startActivity(i);
        }
    }
}
