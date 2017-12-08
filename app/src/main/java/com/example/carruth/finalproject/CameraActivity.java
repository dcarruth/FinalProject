package com.example.carruth.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.carruth.finalproject.LoginActivity.REQUEST_IMAGE_CAPTURE;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Toast.makeText(getApplicationContext(),"Please take picture landscape!", Toast.LENGTH_LONG).show();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView)findViewById(R.id.picture);
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    public void onSavePicture(View view){

        Toast.makeText(getApplicationContext(),"Picture Saved!", Toast.LENGTH_SHORT).show();
    }
}
