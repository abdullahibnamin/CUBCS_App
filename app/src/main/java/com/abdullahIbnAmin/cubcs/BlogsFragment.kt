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
import com.abdullahIbnAmin.cubcs.models.Blog
import com.abdullahIbnAmin.cubcs.recyclerAdapters.RecyclerAdapterForBlogs
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import com.facebook.shimmer.ShimmerFrameLayout

class BlogsFragment : Fragment(){

    private lateinit var recyclerAdapterForBlogs: RecyclerAdapterForBlogs
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_blogs, container, false)
        shimmerFrameLayout = view.findViewById(R.id.shimmer_list)
        initViewModel(view)
        initFunViewModel()
        return view
    }

    fun initViewModel(view: View){
        recyclerAdapterForBlogs = RecyclerAdapterForBlogs()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_blogs_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recyclerAdapterForBlogs

//        val itemSwipe = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                TODO("Not yet implemented")
//            }
//        }
//        val swap = ItemTouchHelper(itemSwipe)
//        swap.attachToRecyclerView(recyclerView)
    }

    fun initFunViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserverForBlog().observe(this, Observer<Blog> {
            if(it != null ){
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.GONE
                recyclerAdapterForBlogs.setUpdatedData(it)
            }
            else{
                Toast.makeText(activity, "Not found", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.apiCallForBlogs()
    }

}


























































