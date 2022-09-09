package com.abdullahIbnAmin.cubcs

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ActivityDetails : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val activityName: String =  intent.getStringExtra("activityName").toString()

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FAF8FC")))
        supportActionBar!!.elevation = 1f
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.drawable.ic_launcher_cub)
        supportActionBar!!.title = " $activityName"

        val activityId: String =  intent.getStringExtra("activityId").toString()
        val activityImage: String =  intent.getStringExtra("activityImage").toString()
        val activityDescription: String =  intent.getStringExtra("activityDescription").toString()
        val activityCreateAt: String =  intent.getStringExtra("activityCreateAt").toString()
        val activityUpdateAt: String =  intent.getStringExtra("activityUpdateAt").toString()
        val activityHub: String =  intent.getStringExtra("activityHub").toString()
        val activityUploadedBy: String =  intent.getStringExtra("activityUploadedBy").toString()
        val activityStartDate: String =  intent.getStringExtra("activityStartDate").toString()
        val activityStartTime: String =  intent.getStringExtra("activityStartTime").toString()
        val activityEndDate: String =  intent.getStringExtra("activityEndDate").toString()
        val activityEndTime: String =  intent.getStringExtra("activityEndTime").toString()
        val activityAmount: String =  intent.getStringExtra("activityAmount").toString()


        val local = LocalDateTime.parse(activityUpdateAt)
        val dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a")
        val formattedDateTime = local.format(dateTimeFormat)


//        ActivityUploadedBy.text = activityUploadedBy
        ActivityUploadedBy.text = "Name"
        ActivityDateTime.text = formattedDateTime
        ActivityName.text = activityName
        startDate.text = "Start Date: $activityStartDate"
        endDate.text = "End Date: $activityEndDate"
        startTime.text = "Start Time: $activityStartTime"
        endTime.text = "End Time: $activityEndTime"
        ActivityAmount.text = "Tk: $activityAmount /-"
        ActivityDescription.text = activityDescription

        Picasso.get().load(activityImage).error(R.drawable.cub_cs_logo).into(ActivityImage)

        ActivityJoinBtn.setOnClickListener {
            if(activityAmount.toFloat() > 0.00){
                val intent = Intent(this, PaymentGatewayActivity::class.java)
                intent.putExtra("activityId", activityId)
                intent.putExtra("custName", "Abdulla")
                intent.putExtra("custEmail", "email@gmail.com")
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Free", Toast.LENGTH_SHORT).show()
            }
        }

    }
}