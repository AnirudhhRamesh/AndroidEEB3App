package eeb3.anirudhhramesh.com.eeb3;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotificationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        int position = getIntent().getIntExtra("position", -1);

        TextView notifTitle = (TextView) findViewById(R.id.notifTitleDetail);
        TextView notifText = (TextView) findViewById(R.id.notifTextDetail);
        TextView notifDate = (TextView) findViewById(R.id.notifDateDetail);
        TextView notifSender = (TextView) findViewById(R.id.notifSenderDetail);

        SharedPreferences prefs1 = getSharedPreferences("notifTitles", Activity.MODE_PRIVATE);
        String Title = prefs1.getString("notifTitles", "Error retrieving data");
        String[] Titles = Title.split(";");
        reverseArray(Titles);

        toolbar.setTitle("Notification " + (Titles.length - position));

        SharedPreferences prefs2 = getSharedPreferences("notifTexts", Activity.MODE_PRIVATE);
        String Text = prefs2.getString("notifTexts", "Error retrieving data");
        String[] Texts = Text.split(";");
        reverseArray(Texts);

        SharedPreferences prefs3 = getSharedPreferences("notifDates", Activity.MODE_PRIVATE);
        String Date = prefs3.getString("notifDates", "Error retrieving data");
        String[] Dates = Date.split(";");
        reverseArray(Dates);

        SharedPreferences prefs4 = getSharedPreferences("notifSenders", Activity.MODE_PRIVATE);
        String Sender = prefs4.getString("notifSenders", "Error retrieving data");
        String[] Senders = Sender.split(";");
        reverseArray(Senders);

        if (Titles.length != 0) {
            notifTitle.setText(Titles[position]);
            notifText.setText(Texts[position]);
            notifDate.setText("This message was sent on " + Dates[position]);
            notifSender.setText("Sent by " + Senders[position]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

        }
        return super.onOptionsItemSelected(item);
    }

    public static void reverseArray(String[] array){
        List<String> list = Arrays.asList(array);
        //next, reverse the list using Collections.reverse method
        Collections.reverse(list);

        //next, convert the list back to String array
        array = (String[]) list.toArray();
    }
}
