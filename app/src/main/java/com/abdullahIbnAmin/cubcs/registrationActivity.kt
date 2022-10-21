package com.abdullahIbnAmin.cubcs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdullahIbnAmin.cubcs.models.SignUp
import com.abdullahIbnAmin.cubcs.models.SignUpResponse
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_registration.*

class registrationActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        signUpBtn.setOnClickListener {
            val name = signUpName.text.toString()
            val email = signUpEmail.text.toString()
            val phnNumber = signUpPhnNumber.text.toString()
            val password = signUpPassword.text.toString()
            val confirmPassword = signUpConfirmPassword.text.toString()

            signUpBtn.text = "Loading..."

            if(password != confirmPassword){
                signUpBtn.text = "Continue"
                Toast.makeText(this, "password and confirm password not matched", Toast.LENGTH_LONG).show()
            }
            if((name.isEmpty() || email.isEmpty() || phnNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())){
                signUpBtn.text = "Continue"
                Toast.makeText(this, "Empty Fields", Toast.LENGTH_LONG).show()
            }

            else{
                viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
                viewModel.getCreateNewUserObserver().observe(this, Observer<SignUpResponse> {
                    if(it == null){
                        signUpBtn.text = "Continue"
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this, "Thank you for join", Toast.LENGTH_LONG).show()
                        signUpBtn.text = "Continue"
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                })
                val info = SignUp(name = name, email = email, phone_number = phnNumber, password = password)
                viewModel.apiCallForCreateUser(info)
                Log.d("testlive", "${viewModel.getCreateNewUserObserver()}")
            }



        }

    }

}