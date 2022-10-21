package com.abdullahIbnAmin.cubcs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        // animation
        val stb = AnimationUtils.loadAnimation(this.requireContext(), R.anim.stb)
        redBox.startAnimation(stb)

        see_all_hubs_btn.setOnClickListener {
//            Toast.makeText(requireContext(), "Coming soon", Toast.LENGTH_LONG).show()
//            val action = HomeFragmentDirections.actionHomeFragmentToBlogContentFragment()
//            navController.navigate(action)
        }

        about_cs_btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAboutClubFragment()
            navController.navigate(action)
        }

        cp_hub_btn.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToHubActivitiesListFragment()
            navController.navigate(action)
        }


    }
}