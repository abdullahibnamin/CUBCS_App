package com.abdullahIbnAmin.cubcs.recyclerAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdullahIbnAmin.cubcs.BlogContentActivity
import com.abdullahIbnAmin.cubcs.GalleryImageViewActivity
import com.abdullahIbnAmin.cubcs.R
import com.abdullahIbnAmin.cubcs.models.BlogItem
import com.abdullahIbnAmin.cubcs.models.GalleryItem
import com.squareup.picasso.Picasso

class RecyclerAdapterForGallery : RecyclerView.Adapter<RecyclerAdapterForGallery.MyViewHolder>() {

    var items = ArrayList<GalleryItem>()

    fun setUpdatedData(items: ArrayList<GalleryItem>){
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.galleyImage)!!

        fun bind(data: GalleryItem) {
            val imgUrl = data.gallery_image_url
            Picasso.get().load(imgUrl).fit().centerCrop()
                .placeholder(R.drawable.cub_cs_logo)
                .error(R.drawable.cub_cs_logo).into(image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_items_for_gallery, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        val context = holder.itemView.context
        holder.itemView.setOnClickListener{
            val intent = Intent(context, GalleryImageViewActivity::class.java)
            intent.putExtra("galleryImageUrl", items[position].gallery_image_url)
            intent.putExtra("galleryImageTitle", items[position].image_caption)
            intent.putExtra("galleryImageDateTime", items[position].created_at)
            intent.putExtra("galleryImageUploaderName", items[position].username)
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int = items.size

}