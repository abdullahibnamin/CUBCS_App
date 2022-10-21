package com.abdullahIbnAmin.cubcs

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import com.abdullahIbnAmin.cubcs.models.useremail
import com.auth0.android.jwt.JWT


class Splash_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val connectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo

        val sharedPref = getSharedPreferences("CUBCS_loginPref", Context.MODE_PRIVATE)
        val sharedEditor = sharedPref.edit()
        var jwt = sharedPref.getString("JWT", null)

        if (jwt != null){
            val jwtObj = JWT(jwt.toString())
            val isJWTExpired = jwtObj.isExpired(10)

            if(isJWTExpired){
                sharedEditor.remove("JWT").apply()
                jwt = null
            }
        }


        if(networkInfo != null && networkInfo.isConnected == true){
            Handler().postDelayed({
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
            },2000)
        }
        else{
            val intent = Intent(this, NoInternetActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}