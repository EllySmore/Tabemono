package com.terraformcreatives.tabemonotabetai.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.terraformcreatives.tabemonotabetai.network.models.Recipe
import com.terraformcreatives.tabemonotabetai.network.respository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DetailedViewModel @Inject constructor(val repository: Repository, val id: String) :
    ViewModel() {

    private val recipeId = MutableLiveData<String>()
    val recipe = MutableLiveData<Recipe>()
    val isBookmarked = MutableLiveData<Boolean>()

    fun currentRecipeId() = recipeId.value
    fun currentRecipe() = recipe.value
    fun currentRecipeBookmarked() = isBookmarked.value

    init {
        isBookmarked.value = false
    }

    suspend fun getRecipeById(): Response<List<Recipe>> {
        recipeId.value = id
        return withContext(Dispatchers.IO) {
            repository.getRecipeById(id).execute()
        }
    }

    suspend fun isRecipeBookmarked(): Boolean {
        return withContext(Dispatchers.IO) {
            repository.isRecipeBookmarked(id)
        }
    }

    suspend fun bookmarkRecipe() {
        recipe.value?.let { recipe ->
            withContext(Dispatchers.IO) {
                repository.saveRecipe(recipe)
            }
        }
    }

    suspend fun unBookmarked() {
        withContext(Dispatchers.IO) {
            repository.deleteRecipe(id)
        }

    }
}