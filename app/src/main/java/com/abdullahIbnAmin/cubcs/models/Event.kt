package com.abdullahIbnAmin.cubcs.models

class Event : ArrayList<EventItem>()

data class EventItem(
    val event_id: Int,
    val user_email: String,
    val event_uploadedBy: String,
    val event_title: String,
    val event_description: String,
    val event_start_date: String,
    val event_epoch_start_date: String,
    val event_start_time: String,
    val event_end_date: String,
    val event_end_time: String,
    val created_at: String,
    val updated_at: String,
)


