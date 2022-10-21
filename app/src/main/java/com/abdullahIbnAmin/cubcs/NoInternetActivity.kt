package com.abdullahIbnAmin.cubcs

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_no_internet.*

class NoInternetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)
        supportActionBar?.hide()

        val sharedPref = getSharedPreferences("CUBCS_loginPref", Context.MODE_PRIVATE)
        val jwt = sharedPref.getString("JWT", null)

        tryAgainBtn.setOnClickListener {
            val connectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectionManager.activeNetworkInfo
            if(networkInfo != null && networkInfo.isConnected == true){
                if(jwt != null){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            else{
                Toast.makeText(this, "Try again", Toast.LENGTH_LONG).show()
            }
        }

    }
}