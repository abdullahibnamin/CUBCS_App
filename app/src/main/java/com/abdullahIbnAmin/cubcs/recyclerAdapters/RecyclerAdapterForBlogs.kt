package com.abdullahIbnAmin.cubcs.recyclerAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdullahIbnAmin.cubcs.BlogContentActivity
import com.abdullahIbnAmin.cubcs.R
import com.abdullahIbnAmin.cubcs.models.BlogItem
import com.squareup.picasso.Picasso

class RecyclerAdapterForBlogs : RecyclerView.Adapter<RecyclerAdapterForBlogs.MyViewHolder>() {

    var items = ArrayList<BlogItem>()

    fun setUpdatedData(items: ArrayList<BlogItem>){
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.img)!!
        var title = view.findViewById<TextView>(R.id.title)!!
        var description = view.findViewById<TextView>(R.id.description)!!

        fun bind(data: BlogItem) {
            val imgUrl = data.blog_image_url
            title.text = data.title
            description.text = data.description
            Picasso.get().load(imgUrl).fit().centerCrop().error(R.drawable.cub_cs_logo).into(image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_items_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        val context = holder.itemView.context
        holder.itemView.setOnClickListener{
            val intent = Intent(context, BlogContentActivity::class.java)
            intent.putExtra("blogImage", items[position].blog_image_url)
            intent.putExtra("blogTitle", items[position].title)
            intent.putExtra("blogDescription", items[position].description)
            intent.putExtra("blogDateTime", items[position].created_at)
            intent.putExtra("blogAuthor", items[position].username)
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int = items.size

}