package com.terraformcreatives.tabemonotabetai

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

}
