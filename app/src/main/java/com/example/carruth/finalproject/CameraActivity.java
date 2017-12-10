package com.example.carruth.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.example.carruth.finalproject.LoginActivity.REQUEST_IMAGE_CAPTURE;

public class CameraActivity extends AppCompatActivity {

    private StorageReference mStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        dispatchTakePictureIntent();
        mStore = FirebaseStorage.getInstance().getReference(getIntent().getStringExtra("user"));
    }

    /**
     * The function that tells the phone to take a picture
     */
    private void dispatchTakePictureIntent() {
        Toast.makeText(getApplicationContext(),"Please take picture landscape!", Toast.LENGTH_LONG).show();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * Requests the code or "bit map" of the image and stores it into a string
     * @param requestCode  the value of REQUEST_IMAGE_CAPTURE -used to compare/verify
     * @param resultCode - used to verify that the operation succeeded.
     * @param data the main data where the image code is added to - connected to current user data
     *             and job
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView)findViewById(R.id.picture);
            mImageView.setImageBitmap(imageBitmap);
            ByteArrayOutputStream baos=new  ByteArrayOutputStream();
            assert imageBitmap != null;
            imageBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
            byte [] b=baos.toByteArray();
            String temp=Base64.encodeToString(b, Base64.DEFAULT);
            new User().updateDataBase(getIntent().getStringExtra("user"),"picture",temp);
        }
    }

    /**
     * Doesn't actually save at the moment, but does say that it does
     * @param view
     */
    public void onSavePicture(View view){
        Toast.makeText(getApplicationContext(),"Picture Saved!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),ServiceActivity.class);
        intent.putExtra("user",getIntent().getStringExtra("user"));
        startActivity(intent);
    }
}
