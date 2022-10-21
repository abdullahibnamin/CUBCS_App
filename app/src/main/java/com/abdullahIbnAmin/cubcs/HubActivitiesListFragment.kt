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
import com.abdullahIbnAmin.cubcs.models.Activities
import com.abdullahIbnAmin.cubcs.models.ActivitiesItem
import com.abdullahIbnAmin.cubcs.models.RecyclerList
import com.abdullahIbnAmin.cubcs.recyclerAdapters.RecyclerAdapterForActivities
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import com.facebook.shimmer.ShimmerFrameLayout

class HubActivitiesListFragment : Fragment() {

    private lateinit var recyclerAdapter: RecyclerAdapterForActivities
    private lateinit var shimmerFrameLayout:ShimmerFrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hub_activities_list, container, false)

        shimmerFrameLayout = view.findViewById(R.id.shimmer_list)

        initViewModel(view)
        initFunViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_activities_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerAdapter = RecyclerAdapterForActivities()
        recyclerView.adapter = recyclerAdapter
    }

    private fun initFunViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserverForActivities().observe(this, Observer<Activities> {
                if(it != null ){
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                    recyclerAdapter.setUpdatedData(it)
                }
                else{
                    Toast.makeText(activity, "Data Not found",Toast.LENGTH_SHORT).show()
                }
            })
        viewModel.apiCallForActivities()
    }


//  recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


}