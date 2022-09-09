package com.abdullahIbnAmin.cubcs.models

data class RecyclerList(val items: ArrayList<RecyclerData>)

data class RecyclerData(
        val activitieName: String,
        val created_at: String,
        val description: String,
        val endDate: String,
        val endTime: String,
        val hubName: String,
        val id: Int,
        val image_url: String,
        val price: String,
        val startDate: String,
        val startTime: String,
        val updated_at: String
    )
