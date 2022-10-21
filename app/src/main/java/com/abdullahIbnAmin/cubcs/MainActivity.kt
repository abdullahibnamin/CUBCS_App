package com.abdullahIbnAmin.cubcs

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
//    , NavigationView.OnNavigationItemSelectedListener
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
//    lateinit var d: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FAF8FC")))
        supportActionBar!!.elevation = 1f
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.drawable.ic_launcher_cub)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.blogsFragment,
                R.id.galleryFragment,
                R.id.calenderFragment,
                R.id.bulletin_list_Fragment
            ),
            drawer_layout
        )
        val navHostfragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostfragment.navController

        setupActionBarWithNavController(navController, appBarConfiguration)
        menu_bottom.setupWithNavController(navController)

        nav_drawer.setupWithNavController(navController)

        nav_drawer.menu.findItem(R.id.logout).setOnMenuItemClickListener { MenuItem ->

            val sharedPref = getSharedPreferences("CUBCS_loginPref", Context.MODE_PRIVATE)
            val sharedEditor = sharedPref.edit()
            sharedEditor.remove("JWT").apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
            true
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}

















































