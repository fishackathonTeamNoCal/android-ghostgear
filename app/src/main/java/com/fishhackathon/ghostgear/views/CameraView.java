package com.fishhackathon.ghostgear.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fishhackathon.ghostgear.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gemma on 4/23/16.
 */
public class CameraView extends RelativeLayout {
    @Bind(R.id.bvTakeBigPicture)
    Button bvTakeBigPicture;

    @Bind(R.id.bvTakeSmallPicture)
    Button bvTakeSmallPicture;

    @Bind(R.id.ivBigPicture)
    ImageView ivBigPicture;

    @Bind(R.id.ivSmallPicture)
    ImageView ivSmallPicture;

    Context context;
    public final static int CAPTURE_BIG_NET_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public final static int CAPTURE_SMALL_NET_IMAGE_ACTIVITY_REQUEST_CODE = 1035;
    public String bigPhotoFileName = "big.jpg";
    public String smallPhotoFileName = "small.jpg";
    public final String APP_TAG = "GhostGear";

    public CameraView (Context context) {
        super(context);
        this.context = context;

        inflate(getContext(), R.layout.view_camera, this);
        ButterKnife.bind(this);
        bvTakeBigPicture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchCamera(bigPhotoFileName, CAPTURE_BIG_NET_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        bvTakeSmallPicture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchCamera(smallPhotoFileName, CAPTURE_SMALL_NET_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void setImage(int code) {
        String photoName;
        ImageView ivPreview;
        if (code == CAPTURE_BIG_NET_IMAGE_ACTIVITY_REQUEST_CODE) {
            photoName = bigPhotoFileName;
            ivPreview = ivBigPicture;
        } else {
            photoName = smallPhotoFileName;
            ivPreview = ivSmallPicture;
        }
        Uri takenPhotoUri = getPhotoFileUri(photoName);
        // by this point we have the camera photo on disk
        Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
        // Load the taken image into a preview
        ivPreview.setImageBitmap(takenImage);
    }

    public void onLaunchCamera(String photoFileName, int code) {
        Activity activity = (Activity) context;
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName)); // set the image file name

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            // Start the image capture intent to take photo
            activity.startActivityForResult(intent, code);
        }
    }

    public Uri getPhotoFileUri(String fileName) {
        // Only continue if the SD Card is mounted
        if (isExternalStorageAvailable()) {
            // Get safe storage directory for photos
            // Use `getExternalFilesDir` on Context to access package-specific directories.
            // This way, we don't need to request external read/write runtime permissions.
            File mediaStorageDir = new File(
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                Log.d(APP_TAG, "failed to create directory");
            }

            // Return the file target for the photo based on filename
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    // Returns true if external storage for photos is available
    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
