package com.abdullahIbnAmin.cubcs.models

class Gallery: ArrayList<GalleryItem>()

data class GalleryItem(
    val gallery_id: Int,
    val image_caption: String,
    val username: String,
    val gallery_image_url: String,
    val created_at: String
)























