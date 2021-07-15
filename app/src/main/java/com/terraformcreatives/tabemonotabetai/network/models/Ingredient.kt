package com.terraformcreatives.tabemonotabetai.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val text: String,
    val quantity: Double,
    val measure: String,
    val food: String,
    val weight: Double
) : Parcelable
