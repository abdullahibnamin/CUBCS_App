package com.abdullahIbnAmin.cubcs

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_blog_content.*
import kotlinx.android.synthetic.main.activity_blog_content.blogDetailActivityImage
import kotlinx.android.synthetic.main.activity_gallery_image_view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GalleryImageViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_image_view)

        supportActionBar!!.hide()

        val galleryImageUrl: String =  intent.getStringExtra("galleryImageUrl").toString()
        val galleryImageTitle: String =  intent.getStringExtra("galleryImageTitle").toString()
        val galleryImageDateTime: String =  intent.getStringExtra("galleryImageDateTime").toString()
        val galleryImageUploaderName: String =  intent.getStringExtra("galleryImageUploaderName").toString()

//        val local = LocalDateTime.parse(blogDateTime)
//        val dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a")
//        val formattedDateTime = local.format(dateTimeFormat)

        Picasso.get().load(galleryImageUrl).error(R.drawable.cub_cs_logo).into(galleryImageView)


        galleryImageInfoBtn.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(R.layout.fragment_help)

            val heading = bottomSheetDialog.findViewById<TextView>(R.id.heading)
            heading?.text = "Info"

            val description = bottomSheetDialog.findViewById<TextView>(R.id.description)
            description?.text = """
                Uploaded By: $galleryImageUploaderName
                Caption: $galleryImageTitle
                Date: $galleryImageDateTime
            """.trimIndent()

            bottomSheetDialog.show()
        }


        // https://www.youtube.com/watch?v=81gJ8MB25yw

        galleryImageShareBtn.setOnClickListener {
            val bitmapDraw = galleryImageView.drawable as BitmapDrawable
            val imgBitmap = bitmapDraw.bitmap
            val path = MediaStore.Images.Media.insertImage(contentResolver, imgBitmap, "", null)

            val bitmapUri = Uri.parse(path)
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)

            startActivity(Intent.createChooser(intent, "Share: "))

        }


    }
}































