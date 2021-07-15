package com.terraformcreatives.tabemonotabetai.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.terraformcreatives.tabemonotabetai.R


enum class BottomNavigationTabType() {
    DASHBOARD,
    BOOKMARK,
    SETTINGS;
}

class HomePage : Fragment() {
    private lateinit var adapter: HomeAdapter
    private lateinit var viewPager: ViewPager2
    private val viewModel = HomeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_page, container, false)
        viewPager = view.findViewById(R.id.viewpager)
        setupAdapter()
        view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> {
                        updateTab(BottomNavigationTabType.DASHBOARD)
                        true
                    }
                    R.id.navigation_bookmark -> {
                        updateTab(BottomNavigationTabType.BOOKMARK)
                        true
                    }
                    R.id.navigation_settings -> {
                        updateTab(BottomNavigationTabType.SETTINGS)
                        true
                    }
                    else -> false
                }
            }
        return view
    }

    private fun setupAdapter() {
        adapter = HomeAdapter(parentFragmentManager, requireActivity().lifecycle)
        viewPager.isUserInputEnabled = false
        viewPager.adapter = adapter
    }

    private fun updateTab(type: BottomNavigationTabType) {
        viewPager.setCurrentItem(type.ordinal, false)
    }


}
