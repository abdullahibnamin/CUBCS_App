package com.abdullahIbnAmin.cubcs.models

class Blog : ArrayList<BlogItem>()

data class BlogItem(
    val blog_id: Int,
    val blog_image_url: String,
    val created_at: String,
    val description: String,
    val title: String,
    val updated_at: String,
    val user_email: String,
    val username :String
)






















