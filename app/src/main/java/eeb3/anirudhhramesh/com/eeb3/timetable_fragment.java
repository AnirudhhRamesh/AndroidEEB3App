package eeb3.anirudhhramesh.com.eeb3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Anirudhh on 21/05/2017.
 */

public class timetable_fragment extends Fragment {
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/EEB3App/";
    Context context;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ImageView mItemImage;
    private CardView scheduleDescript;
    public Display display;
    public timetable_fragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SpringfestActivity.ParticipateFragment newInstance(int sectionNumber) {
        SpringfestActivity.ParticipateFragment fragment = new SpringfestActivity.ParticipateFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timetable_fragment_layout, container, false);
        mItemImage = (ImageView) rootView.findViewById(R.id.ScheduleView);
        scheduleDescript = (CardView) rootView.findViewById(R.id.scheduleDescript);
        scheduleDescript.setVisibility(View.VISIBLE);
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        if (MainActivity.fileNames != "") {
            String photoPath = path + MainActivity.fileNames;
            Log.e("fileName", MainActivity.fileNames);
            BitmapFactory.Options options = new BitmapFactory.Options();
            scheduleDescript.setVisibility(View.GONE);
            //options.inSampleSize = 8;
            //final Bitmap b = BitmapFactory.decodeFile(photoPath, options);
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
            display = view.getDisplay();

            int height = display.getHeight();// ((display.getHeight()*30)/100)
            mItemImage.setImageBitmap(bitmap);
            mItemImage.getLayoutParams().height = height;
            mItemImage.requestLayout();
        }
        else {

        }
    }


}

