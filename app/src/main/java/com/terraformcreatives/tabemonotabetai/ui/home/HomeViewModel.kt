package com.terraformcreatives.tabemonotabetai.ui.home

import androidx.lifecycle.*
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {
    private val currentNavigation = MutableLiveData<BottomNavigationTabType>()

    fun currentNavigation(): BottomNavigationTabType? = currentNavigation.value

    fun setCurrentNavigation(bottomNavigationTabType: BottomNavigationTabType){
        currentNavigation.value = bottomNavigationTabType
    }

}
