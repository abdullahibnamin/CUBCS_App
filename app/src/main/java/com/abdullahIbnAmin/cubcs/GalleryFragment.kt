package com.abdullahIbnAmin.cubcs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.abdullahIbnAmin.cubcs.models.Blog
import com.abdullahIbnAmin.cubcs.models.Gallery
import com.abdullahIbnAmin.cubcs.recyclerAdapters.RecyclerAdapterForBlogs
import com.abdullahIbnAmin.cubcs.recyclerAdapters.RecyclerAdapterForGallery
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import com.facebook.shimmer.ShimmerFrameLayout

class GalleryFragment : Fragment() {

    private lateinit var recyclerAdapterForGallery: RecyclerAdapterForGallery
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        shimmerFrameLayout = view.findViewById(R.id.shimmer_list)

        initViewModel(view)
        initFunViewModel()
        return view
    }

    fun initViewModel(view: View) {
        recyclerAdapterForGallery = RecyclerAdapterForGallery()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_gallery_images)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = recyclerAdapterForGallery
    }


    fun initFunViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserverForGallery().observe(this, Observer<Gallery> {
            if(it != null ){
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.GONE
                recyclerAdapterForGallery.setUpdatedData(it)
            }
            else{
                Toast.makeText(activity, "Not found", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.apiCallForGallery()
    }

}