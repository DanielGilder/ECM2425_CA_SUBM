package com.example.finaleattempt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finaleattempt.databinding.ActivityMainBinding;

import java.io.InputStream;

/*
  This Activity is for the user to view their saved recipe in more detail,
  the page displays the recipe title, image, description and linked button to the full recipe.
 */
public class RecipePage extends AppCompatActivity {

    ActivityMainBinding binding;


    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_recipe_page);

        binding = ActivityMainBinding.inflate(getLayoutInflater());



        Intent intent = getIntent(); //Stores an incoming intent object

        //Access the extra data from the incoming intent object
        String nameTxt = intent.getStringExtra("name");
        String imageTxt = intent.getStringExtra("image");
        String recipeTxt = intent.getStringExtra("recipe");
        String descTxt = intent.getStringExtra("description");

        //Creates view objects for the corresponding layout file
        TextView name = findViewById(R.id.recipeName);
        ImageView imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.linkedButton);
        TextView description = findViewById(R.id.descriptionView);



        new DownloadImageTask(imageView).execute(imageTxt); //Creates a new thread to offload image fetching of the UI thread
        name.setText(nameTxt); //Displays the recipe name to the activity
        description.setText(descTxt); //Displays the recipe description to the the activity


        //The following block triggers an onClickListener which initiaties an implicit intent to the web browser
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToUrl(recipeTxt);
                    }
                }
        );






    }

    //This method creates an Implicit intent to open the given URL in the devices web browser
    private void goToUrl(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);

        startActivity(intent);


    }

    //The following class is used to offload the image fetching from the URL of the UI thread
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        //Takes the URI and performs the network operation
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        //Displays the final result image to the UI
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}