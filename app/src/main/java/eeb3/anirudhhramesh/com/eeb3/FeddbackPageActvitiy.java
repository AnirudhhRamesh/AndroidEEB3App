package eeb3.anirudhhramesh.com.eeb3;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Anirudhh on 08/07/2017.
 */

public class FeddbackPageActvitiy extends ActionBarActivity {

    public static String EEB3Appfb_PAGE_ID = "EEB3-App-154990461721033/";
    public static String EEB3Appfb_URL = "https://www.facebook.com/EEB3-App-154990461721033/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.feedback_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartasks);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View v){
        if (v.getId() == R.id.btnSelectImage) {
            PrefManager prefManager = new PrefManager(getApplicationContext());

            // make first time launch TRUE
            prefManager.setFirstTimeLaunch(true);

            startActivity(new Intent(FeddbackPageActvitiy.this, WelcomeActivity.class));
            finish();
        } else if (v.getId() == R.id.pixabaycard) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","gameratramesh@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EEB3 App Feedback Form");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Developer, \n \nI just had some thoughts on your Application...\n \n");
            startActivity(emailIntent);
        }
        else if (v.getId() == R.id.googleplaycard) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=eeb3.anirudhhramesh.com.eeb3" + appPackageName)));
            }
        }
        else if (v.getId() == R.id.FacebookCard) {
            Intent facebookintent = new Intent(Intent.ACTION_VIEW);
            String EEB3Appfb = getEEB3PageURL(FeddbackPageActvitiy.this);
            facebookintent.setData(Uri.parse(EEB3Appfb));
            startActivity(facebookintent);
        }
        else if (v.getId() == R.id.donatecard) {
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
}
