package com.terraformcreatives.tabemonotabetai.network.services

import com.terraformcreatives.tabemonotabetai.network.models.EdamamSearchResult
import com.terraformcreatives.tabemonotabetai.network.models.MealType
import com.terraformcreatives.tabemonotabetai.network.models.Recipe
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface EdamamApiService {
    @GET("search")
    fun getResult(
        @Query(
            "q",
            encoded = true
        ) q: String?,
        @Query(
            "mealType",
            encoded = true
        ) mealType: MealType?,
        @Query(
            "from",
            encoded = true
        ) from: Int,
        @Query(
            "to",
            encoded = true
        ) to: Int,
        @QueryMap appConfig: Map<String, String>
    ): Call<EdamamSearchResult>

    @GET("search")
    fun getRecipeById(
        @Query(value = "r", encoded = true) r: String,
        @QueryMap appConfig: Map<String, String>
    ): Call<List<Recipe>>

}
