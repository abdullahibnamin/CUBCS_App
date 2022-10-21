package com.abdullahIbnAmin.cubcs.recyclerAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdullahIbnAmin.cubcs.R
import com.abdullahIbnAmin.cubcs.models.BulletinItem
import com.squareup.picasso.Picasso

class RecyclerAdapterForBulletin : RecyclerView.Adapter<RecyclerAdapterForBulletin.MyViewHolder>() {
    var items = ArrayList<BulletinItem>()

    fun setUpdatedData(items: ArrayList<BulletinItem>){
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.img)!!
        val title = view.findViewById<TextView>(R.id.title)!!

        fun bind(data: BulletinItem){
            val imgUrl = data.bulletin_image_url
            val pdfUrl = data.bulletin_pdf_url
            title.text = data.title
            Picasso.get().load(imgUrl).fit().centerCrop().error(R.drawable.cub_cs_logo).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_items_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}