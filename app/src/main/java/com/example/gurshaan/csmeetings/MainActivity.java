package com.example.gurshaan.csmeetings;

import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.provider.MediaStore;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button welcome_button;
    static final int CAM_REQUEST = 1;
    String CurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        welcome_button = findViewById(R.id.welcome_button);

        welcome_button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.FROYO)
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    File photoFile;
                    try {
                        photoFile = createImageFile();
                    } catch(IOException ex) {return;}
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(MainActivity.this , "com.example.android.fileprovider", photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, CAM_REQUEST);
                    }
                }
            }
        });
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat(getString(R.string.date)).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image;
        image = File.createTempFile(
                imageFileName, ".jpg", storageDir
        );
        CurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Intent i = new Intent(this,menu.class);
            i.putExtra("image",CurrentPhotoPath);
            startActivity(i);
        }
    }
}
