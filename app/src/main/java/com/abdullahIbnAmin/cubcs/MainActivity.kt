package com.abdullahIbnAmin.cubcs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

//        nav_drawer.setNavigationItemSelectedListener(this)
//        d = findViewById(R.id.drawer_layout)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.reportFragmentDialog -> {
//                val view: View = layoutInflater.inflate(R.layout.activity_report_dialog, null)
//                val dialog = BottomSheetDialog(this)
//                dialog.setContentView(view)
//                dialog.show()
//            }
//            R.id.appAboutFragment -> {
//                Toast.makeText(applicationContext, "Developed By: Name name", Toast.LENGTH_LONG).show()
//            }
//            R.id.helpFragment -> {
//                val view: View = layoutInflater.inflate(R.layout.activity_help_dialog, null)
//                val dialog = BottomSheetDialog(this)
//                dialog.setContentView(view)
//                dialog.show()
//            }
////            R.id.profileFragment -> {
////                val fm = supportFragmentManager.beginTransaction()
////                fm.replace(R.id.fragmentContainerView, ProfileFragment())
////                fm.commit()
////            }
//        }
//
//        d.closeDrawer(GravityCompat.START)
//        return true
//    }

}

















































