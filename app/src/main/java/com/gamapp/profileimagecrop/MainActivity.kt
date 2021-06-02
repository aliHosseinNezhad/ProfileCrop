package com.gamapp.profileimagecrop

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.gamapp.profilephotocrop.ucroplibrary.ucrop.UCrop
import java.io.File
import java.io.FileInputStream
import java.lang.Exception
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    val GET_IMAGE_REQUEST = 23423756
    val IMAGE_CROP_REQUEST  = 2436443
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.open_btn).setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/jpg"

            startActivityForResult(intent, GET_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            if(requestCode == IMAGE_CROP_REQUEST){
                Executors.newSingleThreadExecutor().execute{
                    val imageView = findViewById<ImageView>(R.id.image_capture)

                    val file = File(cacheDir,"profileTemp.jpg")
                    try {
                        val bitmap = BitmapFactory.decodeStream(FileInputStream(file))
                        runOnUiThread {
                            bitmap?.let {
                                imageView.setImageBitmap(it)
                            }
                        }
                    }catch (e: Exception){

                    }
                }
            } else if (requestCode == GET_IMAGE_REQUEST){
                data.data?.let {
                    val file = File(cacheDir,"profileTemp.jpg")
                    UCrop.of(it, Uri.fromFile(file))
                        .withAspectRatio(1f,1f)
                        .start(this,IMAGE_CROP_REQUEST)

                }
            }
        }


    }
}

//        <activity android:name="com.gamapp.profilephotocrop.ucroplibrary.ucrop.UCropActivity"
//        android:screenOrientation="portrait"
//        android:theme="@style/Theme.MaterialComponents.DayNight.NoActionBar"
//        />