package com.terraformcreatives.tabemonotabetai.ui.commons

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.terraformcreatives.tabemonotabetai.R

class BottomNavigatorTab(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.ui_bottom_navbar, this)
    }


}