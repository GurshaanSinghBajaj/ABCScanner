package com.example.gurshaan.csmeetings;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Button;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Toast;
import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class menu extends AppCompatActivity {

    Bitmap myBitmap;
    Bitmap anImage;
    Bitmap overlayBitmap;
    ImageView image_menu;
    Button cam_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        Intent i = getIntent();
        Bundle image_data = i.getExtras();
        if(image_data != null) {

            String image = i.getStringExtra("image");
            File imgFile = new File(image);

            if (imgFile.exists()) {
                myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                image_menu = (ImageView) findViewById(R.id.image_menu);
                image_menu.setImageBitmap(myBitmap);
                image_menu.setRotation(90);
            }
        }
    }

    public void new_activity1(View view){
        Drawable myDrawable = getResources().getDrawable(R.drawable.cs);
        anImage = ((BitmapDrawable) myDrawable).getBitmap();
        int bitmap1Width = myBitmap.getWidth();
        int bitmap1Height = myBitmap.getHeight();
        int bitmap2Width = anImage.getWidth();
        int bitmap2Height = anImage.getHeight();
        float marginLeft = (float) (bitmap1Width * 0.5 - bitmap2Width * 0.5);
        float marginTop = (float) (bitmap1Height * 0.5 - bitmap2Height * 0.5);
        overlayBitmap = Bitmap.createBitmap(bitmap1Width, bitmap1Height, myBitmap.getConfig());
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.drawBitmap(myBitmap, new Matrix(), null);
        canvas.drawBitmap(anImage, marginLeft, marginTop, null);
        image_menu.setImageBitmap(overlayBitmap);
    }

    public void new_activity2(View view){
        Drawable myDrawable = getResources().getDrawable(R.drawable.images);
        anImage = ((BitmapDrawable) myDrawable).getBitmap();
        int bitmap1Width = myBitmap.getWidth();
        int bitmap1Height = myBitmap.getHeight();
        int bitmap2Width = anImage.getWidth();
        int bitmap2Height = anImage.getHeight();
        float marginLeft = (float) (bitmap1Width * 0.5 - bitmap2Width * 0.5);
        float marginTop = (float) (bitmap1Height * 0.5 - bitmap2Height * 0.5);
        overlayBitmap = Bitmap.createBitmap(bitmap1Width, bitmap1Height, myBitmap.getConfig());
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.drawBitmap(myBitmap, new Matrix(), null);
        canvas.drawBitmap(anImage, marginLeft, marginTop, null);
        image_menu.setImageBitmap(overlayBitmap);
    }

    public void new_activity3(View view){
        Drawable myDrawable = getResources().getDrawable(R.drawable.googleg_standard_color_128dp);
        anImage = ((BitmapDrawable) myDrawable).getBitmap();
        int bitmap1Width = myBitmap.getWidth();
        int bitmap1Height = myBitmap.getHeight();
        int bitmap2Width = anImage.getWidth();
        int bitmap2Height = anImage.getHeight();
        float marginLeft = (float) (bitmap1Width * 0.5 - bitmap2Width * 0.5);
        float marginTop = (float) (bitmap1Height * 0.5 - bitmap2Height * 0.5);
        overlayBitmap = Bitmap.createBitmap(bitmap1Width, bitmap1Height, myBitmap.getConfig());
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.drawBitmap(myBitmap, new Matrix(), null);
        canvas.drawBitmap(anImage, marginLeft, marginTop, null);
        image_menu.setImageBitmap(overlayBitmap);
    }

    public void new_activity4(View view){
        Drawable myDrawable = getResources().getDrawable(R.drawable.google);
        anImage = ((BitmapDrawable) myDrawable).getBitmap();
        int bitmap1Width = myBitmap.getWidth();
        int bitmap1Height = myBitmap.getHeight();
        int bitmap2Width = anImage.getWidth();
        int bitmap2Height = anImage.getHeight();
        float marginLeft = (float) (bitmap1Width * 0.5 - bitmap2Width * 0.5);
        float marginTop = (float) (bitmap1Height * 0.5 - bitmap2Height * 0.5);
        overlayBitmap = Bitmap.createBitmap(bitmap1Width, bitmap1Height, myBitmap.getConfig());
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.drawBitmap(myBitmap, new Matrix(), null);
        canvas.drawBitmap(anImage, marginLeft, marginTop, null);
        image_menu.setImageBitmap(overlayBitmap);
    }

    public void save(View view){
        saveImageToExternalStorage(overlayBitmap);
        Toast.makeText(menu.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
        new_act();
    }

    private void saveImageToExternalStorage(Bitmap finalBitmap) {
        String root = getExternalStoragePublicDirectory(DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/saved_images_1");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 0;
        n++;
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            MediaStore.Images.Media.insertImage(getContentResolver(),
                    file.getAbsolutePath(), file.getName(), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });

    }

    protected void new_act(){
        Intent j = new Intent(this,MainActivity.class);
        startActivity(j);
    }

}
