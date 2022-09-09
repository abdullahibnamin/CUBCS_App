package com.abdullahIbnAmin.cubcs.models

class Bulletin: ArrayList<BulletinItem>()

data class BulletinItem(
    val bulletin_id: Int,
    val title: String,
    val bulletin_image_url: String,
    val bulletin_pdf_url: String,
    val created_at: String,
    val updated_at: String
)
