package com.terraformcreatives.tabemonotabetai.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    val uri: String,
    val label: String,
    val image: String,
    val source: String,
    val url: String,
    val shareAs: String,
    val yield: Int,
    val dietLabels: List<Label>,
    val healthLabels: List<Label>,
    val cautions: List<String>,
    val ingredientLines: List<String>,
    val ingredients: List<Ingredient>,
    val calories: Double,
    val totalWeight: Double,
    val totalTime: Double,
) : Parcelable
