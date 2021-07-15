package com.terraformcreatives.tabemonotabetai.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.terraformcreatives.tabemonotabetai.ui.bookmark.BookmarkPage
import com.terraformcreatives.tabemonotabetai.ui.dashboard.DashboardPage
import com.terraformcreatives.tabemonotabetai.ui.settings.SettingsPage

class HomeAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return BottomNavigationTabType.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DashboardPage()
            1 -> BookmarkPage()
            2 -> SettingsPage()
            else -> throw IllegalStateException("Missing fragment")
        }
    }
}