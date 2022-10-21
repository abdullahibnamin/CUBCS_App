package com.abdullahIbnAmin.cubcs.models

class Activities: ArrayList<ActivitiesItem>()

data class ActivitiesItem(
    val activites_id: String,
    val user_email: String,
    val activity_name: String,
    val start_date: String,
    val end_date: String,
    val start_time: String,
    val end_time: String,
    val description: String,
    val hub: String,
    val amount: String,
    val activity_image_url: String,
    val created_at: String,
    val updated_at: String,
)





































