package co.za.mtn.academy.itsgotime

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class TakePictureActivity : AppCompatActivity() {
    private val REQUEST_CODE = 100
    private val REQUEST_CODE_VIDEO = 101

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_picture)

        val button = findViewById<Button>(R.id.button)
        imageView = findViewById<ImageView>(R.id.imageView)

        button.setOnClickListener {
            this.captureVideo()
            //this.capturePhoto()
        }
    }

    private fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    private fun captureVideo() {
        val cameraIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE_VIDEO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            imageView.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }

}