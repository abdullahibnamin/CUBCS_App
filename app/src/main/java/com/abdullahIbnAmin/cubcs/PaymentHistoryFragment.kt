package com.abdullahIbnAmin.cubcs

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdullahIbnAmin.cubcs.models.PaymentHistory
import com.abdullahIbnAmin.cubcs.recyclerAdapters.RecyclerAdapterForPaymentHistory
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import com.auth0.android.jwt.JWT
import com.facebook.shimmer.ShimmerFrameLayout

class PaymentHistoryFragment : Fragment() {
    lateinit var recyclerAdapterForPaymentHistory: RecyclerAdapterForPaymentHistory
    lateinit var shimmerFrameLayout: ShimmerFrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_payment_history, container, false)

        val sharedPref = activity?.getSharedPreferences("CUBCS_loginPref", Context.MODE_PRIVATE)
        val jwt = sharedPref?.getString("JWT", null)
        val jwtObj = JWT(jwt.toString())
        val email = jwtObj.subject.toString()

        shimmerFrameLayout = view.findViewById(R.id.shimmer_list)
        recyclerAdapterForPaymentHistory = RecyclerAdapterForPaymentHistory()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_paymentHistory_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recyclerAdapterForPaymentHistory

        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getRecyclerListObserverForPaymentHistory().observe(this, Observer<PaymentHistory> {
            if(it != null){
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.GONE
                recyclerAdapterForPaymentHistory.setUpdatedData(it)
            } else {
                Toast.makeText(requireContext(), "Not Found", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.apiCallForPaymentHistory(email)


        return view
    }
}