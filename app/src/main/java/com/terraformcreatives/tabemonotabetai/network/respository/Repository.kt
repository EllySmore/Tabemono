package com.terraformcreatives.tabemonotabetai.network.respository

import com.terraformcreatives.tabemonotabetai.network.models.EdamamSearchResult
import com.terraformcreatives.tabemonotabetai.network.models.MealType
import com.terraformcreatives.tabemonotabetai.network.models.Recipe
import com.terraformcreatives.tabemonotabetai.network.services.EdamamApiService
import com.terraformcreatives.tabemonotabetai.persist.AppDatabase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject
import com.terraformcreatives.tabemonotabetai.persist.Recipe as PersistRecipe

class Repository @Inject constructor(
    private val service: EdamamApiService,
    private val configs: Map<String, String>,
    private val appDatabase: AppDatabase
) {

    @EntryPoint
    @InstallIn(ApplicationComponent::class)
    interface Interface {
        val repository: Repository
    }

    fun queryRecipe(
        q: String,
        mealType: MealType?,
        from: Int,
        to: Int,
    ): Call<EdamamSearchResult> {
        return service.getResult(q, mealType, from, to, configs)
    }

    fun getRecipeById(id: String): Call<List<Recipe>> {
        return service.getRecipeById(id, configs)
    }

    fun saveRecipe(recipe: Recipe) {
        val persistRecipe = PersistRecipe(
            id = 0,
            edamamId = recipe.uri,
            imageUrl = recipe.image,
            label = recipe.label
        )
        appDatabase.bookmarkedRecipeDao().insert(persistRecipe)
    }

    fun isRecipeBookmarked(id: String): Boolean {
        return appDatabase.bookmarkedRecipeDao().exist(id)
    }

    fun deleteRecipe(id: String) {
        appDatabase.bookmarkedRecipeDao().delete(id)
    }

    fun deleteAll() {
        appDatabase.bookmarkedRecipeDao().deleteAll()
    }

    fun queryAll(): List<com.terraformcreatives.tabemonotabetai.persist.Recipe> {
        return appDatabase.bookmarkedRecipeDao().getAll()
    }
}
