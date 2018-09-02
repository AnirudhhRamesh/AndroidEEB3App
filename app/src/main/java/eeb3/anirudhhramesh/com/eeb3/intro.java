package eeb3.anirudhhramesh.com.eeb3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by Anirudhh on 19/05/2017.
 */

public class intro extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.intro_page_layout);
    }

    public void IntroButtonClick(View v) {
        if (v.getId() == R.id.GoToApp) {
            Intent addTaskIntent = new Intent(this, MainActivity.class);
            startActivity(addTaskIntent);
        }
    }
}
