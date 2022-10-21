package com.abdullahIbnAmin.cubcs.recyclerAdapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdullahIbnAmin.cubcs.ActivityDetails
import com.abdullahIbnAmin.cubcs.R
import com.abdullahIbnAmin.cubcs.models.ActivitiesItem
import com.abdullahIbnAmin.cubcs.models.RecyclerData
import com.squareup.picasso.Picasso

class RecyclerAdapterForActivities: RecyclerView.Adapter<RecyclerAdapterForActivities.MyViewHolder>() {

    var items = ArrayList<ActivitiesItem>()
    fun setUpdatedData(items: ArrayList<ActivitiesItem>){
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.img)!!
        var title = view.findViewById<TextView>(R.id.title)!!
        var description = view.findViewById<TextView>(R.id.description)!!

        fun bind(data: ActivitiesItem) {
            val imgUrl = data.activity_image_url
            title.text = data.activity_name
            description.text = data.description

            Picasso.get().load(R.drawable.cub_cs_logo).fit().centerCrop().error(R.drawable.cub_cs_logo).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_list_items_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])

        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ActivityDetails::class.java)
            intent.putExtra("activityId", items[position].activites_id)
            Log.d("test", items[position].activites_id)
            intent.putExtra("activityImage", items[position].activity_image_url)
            intent.putExtra("activityName", items[position].activity_name)
            intent.putExtra("activityDescription", items[position].description)
            intent.putExtra("activityCreateAt", items[position].created_at)
            intent.putExtra("activityUpdateAt", items[position].updated_at)
            intent.putExtra("activityHub", items[position].hub)
            intent.putExtra("activityAmount", items[position].amount)
            intent.putExtra("activityUploadedBy", items[position].user_email)
            intent.putExtra("activityStartDate", items[position].start_date)
            intent.putExtra("activityStartTime", items[position].start_time)
            intent.putExtra("activityEndDate", items[position].end_date)
            intent.putExtra("activityEndTime", items[position].end_time)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = items.size
}