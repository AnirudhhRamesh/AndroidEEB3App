package eeb3.anirudhhramesh.com.eeb3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Anirudhh on 22/05/2017.
 */

public class credits_button extends ActionBarActivity {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.credits);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartasks);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView creditsBackground = (ImageView) findViewById(R.id.creditsBackground);
        Bitmap resultBmp = BlurBuilder.blur(this, BitmapFactory.decodeResource(getResources(), R.drawable.beach_bg2));
        creditsBackground.setImageBitmap(resultBmp);

        ImageView imageView = (ImageView) findViewById(R.id.profileImage);
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anirudhh_profile_cropped);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //Default
            imageView.setBackgroundResource(R.drawable.anirudhh_profile_cropped);
        } else {
            //RoundCorners
            RoundCornersDrawable round = new RoundCornersDrawable(mBitmap,
                   100, 0); //or your custom radius

            CardView cardView = (CardView) findViewById(R.id.profileCard);
            cardView.setPreventCornerOverlap(false); //it is very important

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                imageView.setBackground(round);
            else
                imageView.setBackgroundDrawable(round);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.credits_menu_licenses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        else if(item.getItemId() == R.id.action_licenses){
            Intent i= new Intent(credits_button.this, Licenses.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}