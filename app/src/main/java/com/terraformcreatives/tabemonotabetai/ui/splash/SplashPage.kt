package com.terraformcreatives.tabemonotabetai.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.terraformcreatives.tabemonotabetai.R

class SplashPage : Fragment(R.layout.splash_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val handler = Handler()
        val task = Runnable {
            Navigation.findNavController(view).navigate(R.id.action_go_home)
        }
        handler.removeCallbacks(task)
        handler.postDelayed(task, 1500)
    }

}
