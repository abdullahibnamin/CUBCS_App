package com.abdullahIbnAmin.cubcs

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdullahIbnAmin.cubcs.models.login
import com.abdullahIbnAmin.cubcs.models.loginResponse
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.signUpBtn

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sharedPref = getSharedPreferences("CUBCS_loginPref", Context.MODE_PRIVATE)
        val sharedEditor = sharedPref.edit()

        signUpBtn.setOnClickListener {
            val intent = Intent(this, registrationActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginContinueBtn.setOnClickListener {

            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()

            loginContinueBtn.text = "Loading..."

            viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
            viewModel.getLoginUserObserver().observe(this, Observer<loginResponse> {
                if(it == null){
                    loginContinueBtn.text = "Continue"
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                }
                else{

                    sharedEditor?.apply{
                        putString("JWT", it.token)
                        apply()
                    }

                    loginContinueBtn.text = "Continue"
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
            val info = login(email = email, password = password)
            viewModel.apiCallForUserLogin(info)

        }


        forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

    }
}