package com.example.aleksejs.gotosleep;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GoToSleep extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_go_to_sleep);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        new DownloadImageFromInternet((ImageButton) findViewById(R.id.imageButton3))
                .execute("http://www.clker.com/cliparts/5/f/9/7/11971487051948962354zeratul_Help.svg.med.png");

        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                TextView area = (TextView) findViewById(R.id.textView2);
                //   area.setText(" 10:00 PM \n 11:30 PM \n 02:00 AM \n 03:30 AM");

                TimePicker curTime = ((TimePicker) findViewById(R.id.timePicker));

                int hour = curTime.getCurrentHour();
                int minutes = curTime.getCurrentMinute();
                int tempH = hour - 3;
                if (tempH < 0) {
                    tempH = 24 + tempH;
                }

                int tempM = minutes;

                String [] times = new String[5];

                times[0] = (convert24To12System(hour, minutes));

                for (int i = 0; i < 5; i++)
                {
                    tempM = tempM - 30;

                    tempH = tempH - 1;

                    if (tempM < 0)
                    {
                        tempM = 60 + tempM;
                        tempH = tempH - 1;
                    }

                    if (tempH < 0) {
                        tempH = 24 + tempH;
                    }

                    switch (i) {
                        case 0:
                            times[i] = "<font color=#ff3333>" + convert24To12System(tempH, tempM) + "</font color=#ff3333><br>";

                            break;
                        case 1:
                            times[i] = "<font color=#ff6433>" + convert24To12System(tempH, tempM) + "</font color=#ff6433><br>";

                            break;
                        case 2:
                            times[i] = "<font color=#73cc00>" + convert24To12System(tempH, tempM) + "</font color=#73cc00><br>";

                            break;
                        case 3:
                            times[i] = "<font color=#73cc00>" + convert24To12System(tempH, tempM) + "</font color=#73cc00><br>";

                            break;
                        case 4:
                            times[i] = "<font color=#28cc00>" + convert24To12System(tempH, tempM) + "</font color=#28cc00><br>";

                            break;
                    }
                }

                area.setText(TextUtils.concat(Html.fromHtml(times[0]),Html.fromHtml(times[1]),Html.fromHtml(times[2]),Html.fromHtml(times[3]),Html.fromHtml(times[4])));

                /*
                    Date curTime2 = new Date();
                    curTime2.setHours(hour);
                    curTime2.setMinutes(minutes);
                */


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageButton imageView;

        public DownloadImageFromInternet(ImageButton imageView) {
            this.imageView = imageView;
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

    public static String convert24To12System (int hour, int minute) {
        String time = "";
        String am_pm = "";
        if (hour < 12 ) {
            if (hour == 0) hour = 12;
            am_pm = "AM";
        }
        else {
            if (hour != 12)
                hour-=12;
            am_pm = "PM";
        }
        String h = hour+"", m = minute+"";
        if(h.length() == 1) h = "0"+h;
        if(m.length() == 1) m = "0"+m;
        time = h+":"+m+" "+am_pm;
        return time;
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("GoToSleep Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
