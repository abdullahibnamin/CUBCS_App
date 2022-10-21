package com.abdullahIbnAmin.cubcs


import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdullahIbnAmin.cubcs.models.Bulletin
import com.abdullahIbnAmin.cubcs.recyclerAdapters.RecyclerAdapterForBulletin
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import java.io.File
import java.util.*

class BulletinFragment : Fragment() {

    private lateinit var recyclerAdapterForBulletin: RecyclerAdapterForBulletin
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bulletin_list, container, false)
        shimmerFrameLayout = view.findViewById(R.id.shimmer_list)
        initViewModel(view)
        initFunViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        recyclerAdapterForBulletin = RecyclerAdapterForBulletin()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_bulletin_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recyclerAdapterForBulletin

        val itemSwipe = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                showDialog(viewHolder)
            }

            fun showDialog(viewHolder: RecyclerView.ViewHolder) {
                val alertBuilder = AlertDialog.Builder(activity)
                alertBuilder.setTitle("Download")
                alertBuilder.setMessage("Would you like to download this bulletin?")
                alertBuilder.setPositiveButton("Yes"){dialog, which ->

                    val position = viewHolder.adapterPosition
                    val pdfUrl = recyclerAdapterForBulletin.items[position].bulletin_pdf_url
                    recyclerAdapterForBulletin.notifyItemChanged(position)

                    val fileName = pdfUrl.split("/").last()
                    val uuid = UUID.randomUUID().toString()

                    val request = DownloadManager.Request(
                        Uri.parse(pdfUrl))
                        .setTitle(uuid+fileName)
                        .setDescription("CUBCS Bulletin Downloading")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                        .setAllowedOverRoaming(false)
                        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uuid+fileName)
                    val downloadManager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    downloadManager.enqueue(request)
                }
                alertBuilder.setNegativeButton("No"){dialog, which ->
                    val position = viewHolder.adapterPosition
                    recyclerAdapterForBulletin.notifyItemChanged(position)
                }

                alertBuilder.show()
            }

        }
        val swap = ItemTouchHelper(itemSwipe)
        swap.attachToRecyclerView(recyclerView)

    }


    private fun initFunViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserverForBulletin().observe(this, Observer<Bulletin>{
            if(it != null){
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.GONE
                recyclerAdapterForBulletin.setUpdatedData(it)
            }
            else{
                Toast.makeText(activity, "Not Found", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.apiCallForBulletin()
    }
}