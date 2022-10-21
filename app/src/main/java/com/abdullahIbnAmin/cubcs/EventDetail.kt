package com.abdullahIbnAmin.cubcs

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_event_detail.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EventDetail : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FAF8FC")))
        supportActionBar!!.elevation = 1f
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.drawable.ic_launcher_cub)
        supportActionBar!!.title = " Event"

        val event_uploaded_by: String = intent.getStringExtra("event_uploaded_by").toString()
        val event_user_email: String = intent.getStringExtra("event_user_email").toString()
        val event_title: String = intent.getStringExtra("event_title").toString()
        val event_description: String = intent.getStringExtra("event_description").toString()
        val event_start_date: String = intent.getStringExtra("event_start_date").toString()
        val event_end_date: String = intent.getStringExtra("event_end_date").toString()
        val event_start_time: String = intent.getStringExtra("event_start_time").toString()
        val event_end_time: String = intent.getStringExtra("event_end_time").toString()
        val event_created_at: String = intent.getStringExtra("event_created_at").toString()
        val event_updated_at: String = intent.getStringExtra("event_updated_at").toString()


        val local = LocalDateTime.parse(event_updated_at)
        val dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a")
        val formattedDateTime = local.format(dateTimeFormat)


        eventUploadedBy.text = event_uploaded_by
        eventDetail_UploadedAt.text = formattedDateTime
        eventDetail_Title.text = event_title
        eventDetail_startDate.text = "Start Date: $event_start_date"
        eventDetail_endDate.text = "End Date: $event_end_date"
        eventDetail_startTime.text = "Start Time: $event_start_time"
        eventDetail_endTime.text = "End Time: $event_end_time"
        eventDetail_Description.text = event_description

        createNotificationChannel()

        eventDetail_RemainderBtn.setOnClickListener {
            eventDetail_RemainderBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_baseline_notifications_active_24, 0,0,0)
            eventDetail_RemainderBtn.text = "Notification on"

            scheduleNotification(event_title, event_start_date, event_start_time, event_description)

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notifi channel"
        val desc = "A description Channal"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channal = NotificationChannel(channelID, name , importance)
        channal.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channal)
    }

    private fun scheduleNotification(event_title: String, event_start_date: String, event_start_time: String, event_description: String) {
        val intent = Intent(applicationContext, Notification::class.java)
        intent.putExtra(titleExtra, event_title)
        intent.putExtra(messageExtra, event_description)

        val pandingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime(event_start_date, event_start_time)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pandingIntent
        )

        showAlert(event_title, event_start_time)
    }

    private fun showAlert(event_title: String, event_start_time: String) {
        AlertDialog.Builder(this)
            .setTitle("CUBCS Notification")
            .setMessage(
                """
                    Title: ${event_title}
                    Start From: ${event_start_time}
                """.trimIndent()
            )
            .setPositiveButton("Okay"){_, _ -> }
            .show()
    }

    private fun getTime(event_start_date: String, event_start_time: String): Long {
        val calender = Calendar.getInstance()
        val(year, month, day) = event_start_date.split("-")
        val(hour, min, sec) = event_start_time.split(":")

        calender.set(
            year.toInt(),
            month.toInt() - 1,
            day.toInt(),
            hour.toInt(),
            min.toInt(),
            sec.toInt()
        )
        Log.d("time", "${month} ${calender.timeInMillis}")
        return calender.timeInMillis
    }

}



























