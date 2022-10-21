package com.abdullahIbnAmin.cubcs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_blog_content.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BlogContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_content)

        val blogTitle: String =  intent.getStringExtra("blogTitle").toString()

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FAF8FC")))
        supportActionBar!!.elevation = 1f
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.drawable.ic_launcher_cub)
        supportActionBar!!.title = " $blogTitle"

        val blogImage: String =  intent.getStringExtra("blogImage").toString()
        val blogDescription: String =  intent.getStringExtra("blogDescription").toString()
        val blogDateTime: String =  intent.getStringExtra("blogDateTime").toString()
        val blogAuthor: String =  intent.getStringExtra("blogAuthor").toString()

        val local = LocalDateTime.parse(blogDateTime)
        val dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a")
        val formattedDateTime = local.format(dateTimeFormat)

        Picasso.get().load(blogImage).error(R.drawable.cub_cs_logo).into(blogDetailActivityImage)
        blogDetailActivityAuthorName.text = blogAuthor
        blogDetailActivityDateTime.text = formattedDateTime
        blogDetailActivityTitle.text = blogTitle
        blogDetailActivityDescription.text = blogDescription


    }
}