package eeb3.anirudhhramesh.com.eeb3.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eeb3.anirudhhramesh.com.eeb3.NotificationDetailActivity;
import eeb3.anirudhhramesh.com.eeb3.R;

public class notificationsAdapter extends RecyclerView.Adapter<notificationsAdapter.ViewHolder> {
    private Context mContext;

    public notificationsAdapter (Context mContext) {
        this.mContext = mContext;
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        TextView notificationTitle;
        TextView notificationDescription;
        TextView notificationDate;
        CardView notificationCard;

        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public ViewHolder(View itemView) {
            super(itemView);

            notificationTitle = (TextView) itemView.findViewById(R.id.notificationTitle);
            notificationDescription = (TextView) itemView.findViewById(R.id.notificationDescription);
            notificationDate = (TextView) itemView.findViewById(R.id.notificationDate);
            notificationCard = (CardView) itemView.findViewById(R.id.notifCard);

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public notificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.notification_layout, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SharedPreferences prefTitle = mContext.getSharedPreferences("notifTitles", Activity.MODE_PRIVATE);
        SharedPreferences prefText = mContext.getSharedPreferences("notifTexts", Activity.MODE_PRIVATE);
        SharedPreferences prefDate = mContext.getSharedPreferences("notifDates", Activity.MODE_PRIVATE);

        String Title = prefTitle.getString("notifTitles", "Error retrieving data");
        String[] Titles = Title.split(";");
        reverseArray(Titles);

        String Text = prefText.getString("notifTexts", "Error retrieving data");
        String[] Texts = Text.split(";");
        reverseArray(Texts);

        String Date = prefDate.getString("notifDates", "Error retrieving data");
        String[] Dates = Date.split(";");
        reverseArray(Dates);

        holder.notificationTitle.setText(Titles[position]);
        holder.notificationDescription.setText(Texts[position]);
        holder.notificationDate.setText(Dates[position]);

        notificationsAdapter.ViewHolder programViewHolder = (notificationsAdapter.ViewHolder) holder;
        programViewHolder.notificationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("pressing on cardview", "the position is" + position);
                Intent intent=new Intent(mContext.getApplicationContext(), NotificationDetailActivity.class);
                intent.putExtra("position", position);
                mContext.getApplicationContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        SharedPreferences prefs = mContext.getSharedPreferences("notifTitles", Activity.MODE_PRIVATE);
        String Title = prefs.getString("notifTitles", "Error retrieving data");
        String[] Titles = Title.split(";");

        return Titles.length;
    }

    public static void reverseArray(String[] array){
        List<String> list = Arrays.asList(array);
        //next, reverse the list using Collections.reverse method
        Collections.reverse(list);

        //next, convert the list back to String array
        array = (String[]) list.toArray();
    }
}