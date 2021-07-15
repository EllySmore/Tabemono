package com.terraformcreatives.tabemonotabetai.network.models

data class Hit(
    val recipe: Recipe,
    val bookmarked: Boolean,
    val bought: Boolean
)