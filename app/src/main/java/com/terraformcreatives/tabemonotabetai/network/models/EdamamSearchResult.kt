package com.terraformcreatives.tabemonotabetai.network.models

data class EdamamSearchResult(
    val q: String,
    val from: Int,
    val to: Int,
    val more: Boolean,
    val count: Int,
    val hits: List<Hit>
)
