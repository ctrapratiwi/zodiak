package com.example.com.zodiak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {
    private static final String TAG = "GalleryActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();

    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if(getIntent().hasExtra("image_url")&& getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");
            String date = getIntent().getStringExtra("date");
            String description = getIntent().getStringExtra("description");
            setImage(imageUrl, imageName, date, description);

        }
    }

    private void setImage(String imageUrl, String imageName, String date, String description){
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        TextView name = findViewById(R.id.image_name);
        name.setText(imageName);

        TextView dates = findViewById(R.id.date);
        dates.setText(date);

        TextView descriptions = findViewById(R.id.description);
        descriptions.setText(description);

        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }
}

