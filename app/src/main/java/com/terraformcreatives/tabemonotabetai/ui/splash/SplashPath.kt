package com.terraformcreatives.tabemonotabetai.ui.splash

import com.terraformcreatives.tabemonotabetai.R
import com.terraformcreatives.tabemonotabetai.ui.commons.BasePath
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import com.zhuinden.simplestack.navigator.changehandlers.SegueViewChangeHandler
import kotlinx.parcelize.Parcelize


@Parcelize
data class SplashPath(val placeholder: String = "") : BasePath() {
    override fun layout(): Int {
        return R.layout.splash_page
    }

    override fun viewChangeHandler(): ViewChangeHandler {
        return SegueViewChangeHandler()
    }
}
