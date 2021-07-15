package com.terraformcreatives.tabemonotabetai.ui.commons

import android.os.Parcelable
import com.zhuinden.simplestack.navigator.DefaultViewKey
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import com.zhuinden.simplestack.navigator.changehandlers.FadeViewChangeHandler


abstract class BasePath : DefaultViewKey, Parcelable {
    override fun viewChangeHandler(): ViewChangeHandler = FadeViewChangeHandler()
}
