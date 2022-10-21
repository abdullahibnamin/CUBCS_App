package com.abdullahIbnAmin.cubcs.recyclerAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdullahIbnAmin.cubcs.EventDetail
import com.abdullahIbnAmin.cubcs.R
import com.abdullahIbnAmin.cubcs.models.EventItem
import kotlinx.android.synthetic.main.recycler_list_items_event_calender.view.*

class RecyclerAdapterForEvents(private val eventList: ArrayList<EventItem>): RecyclerView.Adapter<RecyclerAdapterForEvents.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_items_event_calender, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = eventList[position]
        val context = holder.itemView.context
        holder.eventTitle.text = currentItem.event_title
        holder.itemView.setOnClickListener {

            val intent = Intent(context, EventDetail::class.java)
            intent.putExtra("event_user_email", currentItem.user_email)
            intent.putExtra("event_uploaded_by", currentItem.event_uploadedBy)
            intent.putExtra("event_title", currentItem.event_title)
            intent.putExtra("event_description", currentItem.event_description)
            intent.putExtra("event_start_date", currentItem.event_start_date)
            intent.putExtra("event_end_date", currentItem.event_end_date)
            intent.putExtra("event_start_time", currentItem.event_start_time)
            intent.putExtra("event_end_time", currentItem.event_end_time)
            intent.putExtra("event_created_at", currentItem.created_at)
            intent.putExtra("event_updated_at", currentItem.updated_at)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = eventList.size


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val eventTitle = itemView.eventCardViewTitle
    }
}