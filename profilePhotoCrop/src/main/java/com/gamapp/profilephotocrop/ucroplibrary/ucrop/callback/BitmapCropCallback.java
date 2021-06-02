package com.gamapp.profilephotocrop.ucroplibrary.ucrop.callback;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

public interface BitmapCropCallback {

    void onBitmapCropped(@NonNull Uri resultUri, Bitmap mResultBitmap, int offsetX, int offsetY, int imageWidth, int imageHeight);

    void onCropFailure(@NonNull Throwable t);

}