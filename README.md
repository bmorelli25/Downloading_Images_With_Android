# Downloading_Images_With_Android
###Simple Android Tutorial showing you how to use AsyncTask to download images in the background.The code is well documented and should walk you through every step of the process. If you have any questions, don't hesitate to email me!
##------------------------------------------
**I have also included a copy of the MainActivity.Java Class below so you don't have to download it. To use this code, simply copy and paste it into your Andorid project. Then follow these steps:**

1) Replace the URL in my code with the URL of your own image.

2) Go to Res/Layout/Activity_Main: Create a button with "@+id/button" and an ImageView with "@+id/imageView"

3) Run the app and test it out!
##------------------------------------------
```
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
            }
        });
    }
```
