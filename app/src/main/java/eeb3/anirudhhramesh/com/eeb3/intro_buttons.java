package eeb3.anirudhhramesh.com.eeb3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Anirudhh on 30/05/2017.
 */

public class intro_buttons extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_page_layout);
    }

    public void IntroButtonClick(View v) {
        if (v.getId() == R.id.GoToApp) {
            Intent addTaskIntent = new Intent(this, MainActivity.class);
            startActivity(addTaskIntent);
        }
    }
}