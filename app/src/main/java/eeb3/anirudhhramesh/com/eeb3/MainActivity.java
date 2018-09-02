package eeb3.anirudhhramesh.com.eeb3;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    String uriString;
    Switch switch1;
    Switch switch2;

    private ArrayList<String> notifTitles;
    private ArrayList<String> notifTexts;
    private ArrayList<String> notifDates;
    private ArrayList<String> notifSenders;


public static String EEB3Appfb_PAGE_ID = "EEB3-App-154990461721033/";
    public static String EEB3Appfb_URL = "https://www.facebook.com/EEB3-App-154990461721033/";
    public static String FACEBOOK_URL = "https://www.facebook.com/search/pages/?q=eeb3";
    public static String FACEBOOK_PAGE_ID = "search/pages/?q=eeb3";
    private static final int CAMERA_REQUEST = 1888;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/EEB3App";

    CoordinatorLayout coordinatorLayout;
    public int i;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private int totalCount;

    SharedPreferences sharedPref;

    timetable_fragment scheduleFragment;
    homework_fragment homeworkFragment;
    home_fragment homeFragment;
    websites_fragment websitesFragment;
    settings_fragment settingsFragment;
    public static final int PICK_IMAGE = 1;
    public String office365;
    public static String fileNames;
    public static String[] fileValues;
    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    MenuItem prevMenuItem;

    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        isStoragePermissionGranted();
        isCameraPermissionGranted();

        notifTitles = new ArrayList<String>();
        notifTexts = new ArrayList<String>();
        notifDates = new ArrayList<String>();
        notifSenders = new ArrayList<String>();

        viewPager.setCurrentItem(2);
        bottomNavigationView.getMenu().performIdentifierAction(R.id.homeItem, 0);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("/");

        ref.child("Notifications").addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to Firebase
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    String notifKey = childSnapshot.getKey();
                    String notifValue = childSnapshot.getValue().toString();

                    if (notifKey.contains("title")){
                        notifTitles.add(notifValue);
                    }
                    else if (notifKey.contains("text")) {
                        notifTexts.add(notifValue);
                    }
                    else if (notifKey.contains("date")){
                        notifDates.add(notifValue);
                    }
                    else if (notifKey.contains("sender")){
                        notifSenders.add(notifValue);
                    }
                    else{
                        Log.e("error", "Firebase has additional key");
                    }
                }

                if (notifTitles.size() != 0) {
                    stringArray(notifTitles, "notifTitles");
                }
                if (notifTexts.size() != 0) {
                    stringArray(notifTexts, "notifTexts");
                }
                if (notifDates.size() != 0) {
                    stringArray(notifDates, "notifDates");
                }
                if (notifSenders.size() != 0) {
                    stringArray(notifSenders, "notifSenders");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //Log.e("Firebase link", dataSnapshot.getValue().toString());
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    String notifKey = childSnapshot.getKey();
                    String notifValue = childSnapshot.getValue().toString();

                    if (notifKey.contains("title")){
                        notifTitles.add(notifValue);
                    }
                    else if (notifKey.contains("text")) {
                        notifTexts.add(notifValue);
                    }
                    else if (notifKey.contains("date")){
                        notifDates.add(notifValue);
                    }
                    else if (notifKey.contains("sender")){
                        notifSenders.add(notifValue);
                    }
                    else{
                        Log.e("error", "Firebase has additional key");
                    }
                }

                if (notifTitles.size() != 0) {
                    stringArray(notifTitles, "notifTitles");
                }
                if (notifTexts.size() != 0) {
                    stringArray(notifTexts, "notifTexts");
                }
                if (notifDates.size() != 0) {
                    stringArray(notifDates, "notifDates");
                }
                if (notifSenders.size() != 0) {
                    stringArray(notifSenders, "notifSenders");
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //Log.e("Firebase link", dataSnapshot.getValue().toString());
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    String notifKey = childSnapshot.getKey();
                    String notifValue = childSnapshot.getValue().toString();

                    if (notifKey.contains("title")){
                        notifTitles.add(notifValue);
                    }
                    else if (notifKey.contains("text")) {
                        notifTexts.add(notifValue);
                    }
                    else if (notifKey.contains("date")){
                        notifDates.add(notifValue);
                    }
                    else if (notifKey.contains("sender")){
                        notifSenders.add(notifValue);
                    }
                    else{
                        Log.e("error", "Firebase has additional key");
                    }
                }

                if (notifTitles.size() != 0) {
                    stringArray(notifTitles, "notifTitles");
                }
                if (notifTexts.size() != 0) {
                    stringArray(notifTexts, "notifTexts");
                }
                if (notifDates.size() != 0) {
                    stringArray(notifDates, "notifDates");
                }
                if (notifSenders.size() != 0) {
                    stringArray(notifSenders, "notifSenders");
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //Log.e("Firebase link", dataSnapshot.getValue().toString());
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    String notifKey = childSnapshot.getKey();
                    String notifValue = childSnapshot.getValue().toString();

                    if (notifKey.contains("title")){
                        notifTitles.add(notifValue);
                    }
                    else if (notifKey.contains("text")) {
                        notifTexts.add(notifValue);
                    }
                    else if (notifKey.contains("date")){
                        notifDates.add(notifValue);
                    }
                    else if (notifKey.contains("sender")){
                        notifSenders.add(notifValue);
                    }
                    else{
                        Log.e("error", "Firebase has additional key");
                    }
                }

                if (notifTitles.size() != 0) {
                    stringArray(notifTitles, "notifTitles");
                }
                if (notifTexts.size() != 0) {
                    stringArray(notifTexts, "notifTexts");
                }
                if (notifDates.size() != 0) {
                    stringArray(notifDates, "notifDates");
                }
                if (notifSenders.size() != 0) {
                    stringArray(notifSenders, "notifSenders");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase link", databaseError.toString());
            }

        });

        prefs = getPreferences(Context.MODE_PRIVATE);
        editor = prefs.edit();
        totalCount = prefs.getInt("counter", 0);
        totalCount++;
        editor.putInt("counter", totalCount);
        editor.commit();

        sharedPref= getSharedPreferences("myPref", Context.MODE_PRIVATE);
        office365 = sharedPref.getString("office_enabled", "http://bit.ly/Office365SMS");
        boolean checkIfWhole = (totalCount % 3 == 0);
        boolean checkIfFour = (totalCount % 4 == 0);

        if(totalCount / 3 == 1){
            if (checkIfWhole) {
                Log.e("totalCount", "Total count is 3");
                rateUs();
            }
        }

        if(totalCount / 3 == 2){
            if (checkIfWhole) {
                joinCommunity();
            }
        }

        if(totalCount / 3 == 3){
            if (checkIfWhole) {
                donate();
            }
        }

        if(totalCount / 3 == 4){
            if (checkIfWhole) {
                shareFriends();
            }
        }
        if(totalCount / 3 == 5){
            if (checkIfWhole) {
                joinCommunity();
            }
        }
        if(totalCount / 3 == 6){
            if (checkIfWhole) {
                rateUs();
            }
        }
        if(totalCount / 3 == 7){
            if (checkIfWhole) {
                donate();
            }
        }
        if(totalCount / 3 == 8){
            if (checkIfWhole) {
                rateUs();}
        }
        if(totalCount / 3 == 9){
            if (checkIfWhole) {
            leaveSuggestion();}
        }
        if(totalCount / 3 == 10){
            if (checkIfWhole) {
            shareFriends();}
        }
        if(totalCount / 3 == 11){
            if (checkIfWhole) {
            donate();}
        }
        if(totalCount / 3 == 12){
            if (checkIfWhole) {
            joinCommunity();}
        }
        if(totalCount / 3 == 13){
            if (checkIfWhole) {
            donate();}
        }
        if(totalCount / 3 == 14){
            if (checkIfWhole) {
            rateUs();}
        }
        if(totalCount / 3 == 15){
            if (checkIfWhole) {
            shareFriends();}
        }
        if(totalCount / 3 == 16){
            if (checkIfWhole) {
            joinCommunity();}
        }
        if(totalCount / 3 == 17){
            if (checkIfWhole) {
            leaveSuggestion();}
        }
        if(totalCount / 3 == 18){
            if (checkIfWhole) {
            donate(); }
        }
        if(totalCount / 3 == 19){
            if (checkIfWhole) {
            shareFriends();}
        }
        if(totalCount / 3 == 20){
            if (checkIfWhole) {
            joinCommunity();}
        }
        if(totalCount / 3 == 21){
            if (checkIfWhole) {
            rateUs();}
        }
        if(totalCount / 3 == 22){
            if (checkIfWhole) {
            shareFriends();}
        }
        if(totalCount / 3 == 23){
            if (checkIfWhole) {
            donate();}
        }
        if(totalCount / 3 == 24){
            if (checkIfWhole) {
            joinCommunity();}
        }
        if(totalCount / 3 == 25){
            if (checkIfWhole) {
                rateUs();}
        }
        if(totalCount / 3 == 26){
            if (checkIfWhole) {
                leaveSuggestion();}
        }
        if(totalCount / 3 == 27){
            if (checkIfWhole) {
                joinCommunity();}
        }if(totalCount / 3 == 28){
            if (checkIfWhole) {
                rateUs();}
        }if(totalCount / 3 == 29){
            if (checkIfWhole) {
                shareFriends();}
        }
        if(totalCount / 3 == 30){
            if (checkIfWhole) {
                rateUs();}
        }
        if(totalCount / 3 == 31){
            if (checkIfWhole) {
                joinCommunity();}
        }
        if(totalCount / 3 == 32){
            if (checkIfWhole) {
                donate();}
        }

        if(totalCount / 4 == 25){
            if (checkIfFour) {
            thankYou();}
        }
        i=0;
        switch2 = (Switch) findViewById(R.id.list_item_switch2);

        File file=new File(path);
        File[] list = file.listFiles();

        if (list != null) {
            readFiles();
        }
        else{

        }

        fileNames = prefs.getString("fileName", "");
        Log.e("fileName", MainActivity.fileNames);


        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.timetableItem:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.homeworkItem:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.homeItem:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.worldItem:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.settingsItem:
                                viewPager.setCurrentItem(4);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe
       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
        */

        setupViewPager(viewPager);
        bottomNavigationView.getMenu().performIdentifierAction(R.id.homeItem, 0);
    }

    public void stringArray(ArrayList<String> stringArray, String arrayListName){

        prefs = getApplicationContext().getSharedPreferences(arrayListName, Activity.MODE_PRIVATE);
        editor = prefs.edit();
        editor.commit();

        StringBuilder sb = new StringBuilder();
        for (String arrayString: stringArray){
            sb.append(arrayString).append(";");
        }
        editor.putString(arrayListName, sb.toString()).apply();
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("","Permission is granted");
                return true;
            } else {

                Log.v("","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("","Permission is granted");
            return true;
        }
    }

    public  boolean isCameraPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Log.e("", "Permission granted");
                return true;
            } else {
                Log.v("","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("","Permission is granted");
            return true;
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        scheduleFragment=new timetable_fragment();
        homeworkFragment=new homework_fragment();
        homeFragment=new home_fragment();
        websitesFragment=new websites_fragment();
        settingsFragment=new settings_fragment();

        adapter.addFragment(scheduleFragment);
        adapter.addFragment(homeworkFragment);
        adapter.addFragment(homeFragment);
        adapter.addFragment(websitesFragment);
        adapter.addFragment(settingsFragment);
        viewPager.setAdapter(adapter);
    }


    public void onButtonClick(View v){
        if (v.getId()==R.id.EEB3button){
            Intent i = new Intent(this, EEB3.class);
            startActivity(i);
        }
        if (v.getId()==R.id.buttonSTEM){
            Intent i = new Intent(this, STEMActivity.class);
            startActivity(i);
        }
        else if (v.getId()==R.id.SMSbutton){
            office365 = sharedPref.getString("office_enabled", "http://bit.ly/Office365SMS");
            if (!Objects.equals(office365, "http://bit.ly/Office365SMS")){
                office365 = sharedPref.getString("office_enabled", "http://bit.ly/Office365SMS");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://sms.eursc.eu/login.php"));
                startActivity(intent);
            }
            else {
                office365 = sharedPref.getString("office_enabled", "http://bit.ly/Office365SMS");
                Intent i = new Intent(this, smsjava.class);
                startActivity(i);
            }
        }
        else if (v.getId()==R.id.ECbutton){
            Intent i = new Intent(this, EventsCommittee.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.CDEbutton) {
            Intent i = new Intent(this, ComiteEleves.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.BISSCbutton) {
            Intent i = new Intent(this, BISSC.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.Creds) {
            Intent i = new Intent(this, credits_button.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.GoToApp) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.AssignButton) {
            Intent i = new Intent(this, AssignmentsActivity.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.AbsenceButton) {
            office365 = sharedPref.getString("office_enabled", "http://bit.ly/Office365SMS");
            if (!Objects.equals(office365, "http://bit.ly/Office365SMS")){
                office365 = sharedPref.getString("office_enabled", "http://bit.ly/Office365SMS");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://sms.eursc.eu/login.php"));
                startActivity(intent);
            }
            else {
                office365 = sharedPref.getString("office_enabled", "http://bit.ly/Office365SMS");
                Intent i = new Intent(this, smsjava.class);
                startActivity(i);
            }
        }
        else if (v.getId() == R.id.list_item_switch2) {
            switch (i) {
                case 0:
                    Toast.makeText(this, R.string.switch_doesnt_work, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 1:
                    Toast.makeText(this, R.string.switch_no_work, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 2:
                    Toast.makeText(this, R.string.stop_it, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 3:
                    Toast.makeText(this, R.string.code_run_out, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 4:
                    Toast.makeText(this, R.string.spam_clicking, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 5:
                    Toast.makeText(this, R.string.maybe_I_was, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 6:
                    Toast.makeText(this, R.string.dev_spent_too_long, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 7:
                    Toast.makeText(this, R.string.make_into_easter_egg, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 8:
                    Toast.makeText(this, R.string.original_right, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 9:
                    Toast.makeText(this, R.string.since_youre_still_here, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 10:
                    Toast.makeText(this, R.string.id_like_to_ask, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 11:
                    Toast.makeText(this, R.string.my_question_is, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 12:
                    Toast.makeText(this, R.string.why_are_you_still_pressing_me, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 13:
                    Toast.makeText(this, R.string.am_i_funny, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 14:
                    Toast.makeText(this, R.string.probably_is, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 15:
                    Toast.makeText(this, R.string.cant_contradict_me, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 16:
                    Toast.makeText(this, R.string.im_a_switch, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 17:
                    Toast.makeText(this, R.string.argue_with_switch, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 18:
                    Toast.makeText(this, R.string.lets_just_say, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 19:
                    Toast.makeText(this, R.string.ppl_question_sanity, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 20:
                    Toast.makeText(this, R.string.you_still_here, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 21:
                    Toast.makeText(this, R.string.too_much_free_time, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 22:
                    Toast.makeText(this, R.string.not_spending_it, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 23:
                    Toast.makeText(this, R.string.on_this_fabulous_app, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 24:
                    Toast.makeText(this, R.string.im_just_saying, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 25:
                    Toast.makeText(this, R.string.write_some_fab_feedback, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 26:
                    Toast.makeText(this, R.string.rate_fantastic_app, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 27:
                    Toast.makeText(this, R.string.award_dedication, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 28:
                    Toast.makeText(this, R.string.now_officially, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 29:
                    Toast.makeText(this, R.string.dedicated_eeb3_app, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 30:
                    Toast.makeText(this, R.string.this_amazing_title, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 31:
                    Toast.makeText(this, R.string.rate_app_five_stars, Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    break;
                case 32:
                    Toast.makeText(this, R.string.leave_a_comment_easter_egg, Toast.LENGTH_LONG).show();
                    i = i + 1;
                    break;
                case 33:
                    rateUsEasterEgg();
                    break;
            }
        }
        else if (v.getId() == R.id.buttonSC) {
            Intent i = new Intent(this, StudentCoursesTabbed.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.buttonSF) {
            Intent i = new Intent(this, SpringfestActivity.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.buttonTEDx) {
            Intent i = new Intent(this, TedxEEB3.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.passwordscredentials) {
            Intent i = new Intent(this, SMSCredentialsActivity.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.buttonGreen) {
            Intent i = new Intent(this, EEB_Green.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.helpbutton) {
            Intent i = new Intent(this, FeddbackPageActvitiy.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.studentbutton) {
            Intent i = new Intent(this, SCCoursesWeb.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.aboutbutton) {
            Intent i = new Intent(this, AboutPage.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.buttonBISSC) {
            Intent i = new Intent(this, BISSCActivity.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.buttonXlweekly) {
            Intent i = new Intent(this, XLWeekly.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.buttonShortFF) {
            Intent i = new Intent(this, ShortFilmFestival.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.ShareButton) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            uriString = "http://bit.ly/EEB3App";
            i.putExtra(Intent.EXTRA_TEXT, getString(R.string.have_you_already_installed_the_new_eeb3_app));
            startActivity(i);
        }
        else if (v.getId() == R.id.EventButton) {
            Intent i = new Intent(MainActivity.this, BarcodeReader.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.btnSelectImage) {
            uploadImageDialog();
        }else if (v.getId() == R.id.scheduleDescript) {
            uploadImageDialog();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.do_you_wish_to_leave);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton(R.string.im_sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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

    public String getEEB3PageURL(Context context){
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + EEB3Appfb_URL;
            } else {
                return "fb://page/" + EEB3Appfb_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return EEB3Appfb_URL;
        }
    }

    public void rateUs(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.rate_us);
        builder.setIcon(R.drawable.ic_star);
        builder.setMessage(R.string.rate_us_if_you_enjoy);
        builder.setPositiveButton(getString(R.string.rate_us), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=eeb3.anirudhhramesh.com.eeb3" + appPackageName)));
                }
            }
        });
        builder.setNegativeButton(getString(R.string.maybe_later), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void rateUsEasterEgg(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getString(R.string.rate_us));
        builder.setIcon(R.drawable.ic_star);
        builder.setMessage(R.string.dedicated_eeb3_app_user);
        builder.setPositiveButton(getString(R.string.rate_us), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=eeb3.anirudhhramesh.com.eeb3" + appPackageName)));
                }
            }
        });
        builder.setNegativeButton(R.string.maybe_later, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void uploadImageDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Upload an image of your schedule");
        builder.setIcon(R.drawable.ic_file_upload);
        builder.setMessage("Select where you want to import your schedule from");
        builder.setPositiveButton("Take a photo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        builder.setNegativeButton("Import from gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE);
                Log.d("Ani", "Fab 2");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        String scanContent;
        String scanFormat;

        scanContent = "";
        scanFormat = "";
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK && isStoragePermissionGranted() && isCameraPermissionGranted()) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            saveImageToExternalStorage(photo);
            updateApp();
        }
        else if (requestCode == PICK_IMAGE&& resultCode == Activity.RESULT_OK && isStoragePermissionGranted() && isCameraPermissionGranted()) {
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

            // Do something with the bitmap

            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            saveImageToExternalStorage(bitmap);
            updateApp();

            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();
        }
        else if (!isStoragePermissionGranted()){
            isStoragePermissionGranted();
        }
        else if (!isCameraPermissionGranted()){
            isCameraPermissionGranted();
        }
        else if (!isStoragePermissionGranted() && !isCameraPermissionGranted()){
            isStoragePermissionGranted();
            isCameraPermissionGranted();
        }
        else if (requestCode == 1) {
            isCameraPermissionGranted();
        }
        else if (requestCode == 2){
            isStoragePermissionGranted();
        }
    }

    public void updateApp(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Restart app");
        builder.setIcon(R.drawable.ic_resetvsg);
        builder.setMessage("You'll have to restart to see your new schedule");
        builder.setPositiveButton("Restart app", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Don't restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void readFiles(){
        File file=new File(path);
        File[] list = file.listFiles();
        int count = 0;
        for (File f: list){
            String name = f.getName();
            Log.e("File names", name);
            if (name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg"))
                count++;
            fileNames = name;
            editor.putString("fileName", fileNames);
            editor.commit();
            System.out.println("170 " + count);
            Log.e("fileNames", fileNames);
        }
    }

    private void saveImageToExternalStorage(Bitmap finalBitmap) {
        File myDir = new File(path);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".png";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[] { file.toString() }, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });


        readFiles();
    }

    public void joinCommunity(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.join_the_community);
        builder.setIcon(R.drawable.ic_facebook_blue);
        builder.setMessage(R.string.follow_us_on_facebook);
        builder.setPositiveButton(R.string.follow, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent facebookintent = new Intent(Intent.ACTION_VIEW);
                String EEB3Appfb = getEEB3PageURL(MainActivity.this);
                facebookintent.setData(Uri.parse(EEB3Appfb));
                startActivity(facebookintent);
            }
        });
        builder.setNegativeButton(getString(R.string.maybe_later), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void leaveSuggestion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.leave_suggestion);
        builder.setIcon(R.drawable.ic_lightbulbsvg);
        builder.setMessage(R.string.counting_on_your_feedback);
        builder.setPositiveButton(R.string.give_suggestion, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","gameratramesh@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App Suggestion Form");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Developer,\n \n I have a suggestion for the EEB3 App... \n \n");
                startActivity(emailIntent);
            }
        });
        builder.setNegativeButton(R.string.remind_me_later, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void shareFriends(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.tell_your_friends);
        builder.setMessage(R.string.share_the_fun_of_this_app);
        builder.setPositiveButton(getString(R.string.share), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getString(R.string.have_you_already_installed_the_new_eeb3_app));
                startActivity(i);
            }
        });
        builder.setNegativeButton(R.string.I_have_no_friends, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void thankYou(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.thank_you);
        builder.setIcon(R.drawable.ic_favorite_red);
        builder.setMessage(R.string.weve_noticed_100);
        builder.setPositiveButton(R.string.yes_please, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","gameratramesh@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App 100 Times Opener");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Developer,\n \n I love your app, and since I have opened it 100 times, I want to feature on the EEB3 Facebook page! \n \n");
                startActivity(emailIntent);
            }
        });
        builder.setNegativeButton(R.string.no_thanks, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void donate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Got a question?");
        builder.setIcon(R.drawable.ic_helpvsg);
        builder.setMessage("If you have any questions, or you're running into any problems in the app, contact us and we'll help you out!");
        builder.setPositiveButton(R.string.contact_us, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","gameratramesh@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App - User Question");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Developer,\n \n I have a question related to the EEB3 App... \n \n");
                startActivity(emailIntent);
            }
        });
        builder.setNegativeButton(getString(R.string.no_thanks), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
