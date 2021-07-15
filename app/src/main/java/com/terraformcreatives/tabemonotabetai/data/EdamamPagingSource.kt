package com.terraformcreatives.tabemonotabetai.data

import android.util.Log
import androidx.paging.PagingSource
import com.terraformcreatives.tabemonotabetai.network.models.EdamamSearchResult
import com.terraformcreatives.tabemonotabetai.network.models.Hit
import com.terraformcreatives.tabemonotabetai.network.models.MealType
import com.terraformcreatives.tabemonotabetai.network.models.Recipe
import com.terraformcreatives.tabemonotabetai.network.respository.Repository
import com.terraformcreatives.tabemonotabetai.network.services.EdamamApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val EDAMAM_STARTING_PAGE_INDEX = 0



class EdamamApi (
    private val query: String,
    private val mealType: MealType?,
    private val repository: Repository
) : PagingSource<Int, Hit>() {

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
            return try {
                val data = repository.queryRecipe(
                    q = query,
                    mealType = mealType,
                    from = if (params is LoadParams.Append) params.key else 0,
                    to = if (params is LoadParams.Prepend) params.key else 30,
                ).execute()

                LoadResult.Page(
                    data = data.body()!!.hits,
                    prevKey = data.body()!!.from,
                    nextKey =data.body()!!.to
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            }
        }
}