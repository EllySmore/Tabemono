package com.terraformcreatives.tabemonotabetai.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.terraformcreatives.tabemonotabetai.network.models.EdamamSearchResult
import com.terraformcreatives.tabemonotabetai.network.models.MealType
import com.terraformcreatives.tabemonotabetai.network.respository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DashboardViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val DEFAULT_INCREMENT = 30
    private val DEFAULT_INITIAL = 0
    private val query = MutableLiveData<String>()
    private val mealType = MutableLiveData<MealType>()
    private val fromLiveData = MutableLiveData(DEFAULT_INITIAL)
    private val toLiveData = MutableLiveData(DEFAULT_INCREMENT)

    fun currentQuery(): String? = query.value
    fun currentFrom(): Int? = fromLiveData.value
    fun currentTo(): Int? = toLiveData.value
    fun currentMealType(): MealType? = mealType.value


    fun clear() {
        query.value = ""
        fromLiveData.value = DEFAULT_INITIAL
        toLiveData.value = DEFAULT_INCREMENT
        mealType.value = null
    }

    suspend fun initialQueryRecipe(
        query: String,
        mealType: MealType,
    ): EdamamSearchResult {
        this.mealType.value = mealType
        this.query.value = query
        fromLiveData.value = DEFAULT_INITIAL
        toLiveData.value = DEFAULT_INCREMENT
        return queryRecipe(query, mealType)
    }

    suspend fun queryRecipe(
        query: String = "",
        mealType: MealType = MealType.breakfast,
    ): EdamamSearchResult {
        val result = withContext(Dispatchers.IO) {
            repository.queryRecipe(query, mealType, currentFrom()!!, currentTo()!!).execute()
        }
        if (result.code() == 200) {
            fromLiveData.value = currentFrom()!! + DEFAULT_INCREMENT
            toLiveData.value = currentTo()!! + DEFAULT_INCREMENT
        }
        return result.body()!!
    }

}