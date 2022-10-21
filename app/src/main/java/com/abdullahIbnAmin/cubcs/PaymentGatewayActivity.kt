package com.abdullahIbnAmin.cubcs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import com.abdullahIbnAmin.cubcs.models.DeviceInfoModel
import com.abdullahIbnAmin.cubcs.models.PaymentInfo
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_payment_gateway.*

class PaymentGatewayActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_gateway)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FAF8FC")))
        supportActionBar!!.elevation = 1f
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.drawable.ic_launcher_cub)
        supportActionBar!!.title = " Payment"

        val activityId: String = intent.getStringExtra("activityId").toString()
        val custName: String = intent.getStringExtra("custName").toString()
        val custEmail: String = intent.getStringExtra("custEmail").toString()

        Log.d("test", activityId)
        paymentWebview.webViewClient = WebViewClient()

        val url = "http://192.168.0.107:8000/getPaymentInfo"
        paymentWebview.postUrl(url, "activity_id=${activityId}&cus_name=${custName}&cus_email=${custEmail}".toByteArray())
        paymentWebview.settings.javaScriptEnabled = true
        paymentWebview.settings.setSupportZoom(true)

    }

    override fun onBackPressed() {
        if(paymentWebview.canGoBack()){
            paymentWebview.goBack()
        }
        else {
            super.onBackPressed()
        }
    }
}



























