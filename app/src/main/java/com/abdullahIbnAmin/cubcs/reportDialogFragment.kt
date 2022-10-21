package com.abdullahIbnAmin.cubcs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdullahIbnAmin.cubcs.models.*
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import com.auth0.android.jwt.JWT
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_report_dialog.*

class reportDialogFragment : BottomSheetDialogFragment() {

    lateinit var viewModel: MainActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity!!.getSharedPreferences("CUBCS_loginPref", Context.MODE_PRIVATE)
        val jwt = sharedPref.getString("JWT", null)
        val emailSub = JWT(jwt.toString())
        val email = emailSub.subject
//        Log.d("email", "${emailSub.subject}")
        reportSubmitBtn.setOnClickListener {
            val subject = reportSubject.text.toString()
            val description = reportDescription.text.toString()

            reportSubmitBtn.text = "Loading..."

            if((subject.isEmpty() || description.isEmpty() )) {
                reportSubmitBtn.text = "Continue"
                Toast.makeText(requireContext(), "Empty Fields", Toast.LENGTH_LONG).show()
            }

            else{
                viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
                viewModel.getReportObserver().observe(this, Observer<ReportResponse> {
                    if(it == null){
                        reportSubmitBtn.text = "Continue"
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(requireContext(), "Report submitted successfully", Toast.LENGTH_LONG).show()
                        reportSubmitBtn.text = "Continue"
                        this.dismiss()
                    }
                })
                val info = Report(email = email, subject = subject, description = description)
                viewModel.apiCallForUserReport(info)
            }


        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
          return inflater.inflate(R.layout.fragment_report_dialog, container, false)
    }

}