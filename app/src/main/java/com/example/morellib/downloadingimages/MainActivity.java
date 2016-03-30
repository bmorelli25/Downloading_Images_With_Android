package com.example.morellib.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //Our imageView that will show the image
    ImageView downloadedImg;

    //Function that runs when our button is clicked
    public void downloadImage(View view) {

        //Testing the button to make sure it works
        Log.i("Test?", "Button Clicked");

        //Creating a new ImageDownloader Task
        ImageDownloader task = new ImageDownloader();

        //Making a bitmap
        Bitmap myImage;

        //Assigning the URL of the image to the myImage variable - This code runs the ImageDownloader Class
        try {
            myImage = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();

            //Once the image is returned, we set it to our ImageView
            downloadedImg.setImageBitmap(myImage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //IMG DOWNLOADER CLASS
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                //Our url equals the input URL from above.
                URL url = new URL(urls[0]);

                //Open a connection and connect
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                //Get the input stream and decode it into a Bitmap
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                //return the Bitmap
                return myBitmap;

                //error catching
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
            //return null if it didn't work
            return null;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiate our ImageView
        downloadedImg = (ImageView) findViewById(R.id.imageView);

        //Nothing below this line is used in this tutorial. This is all standard code.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
