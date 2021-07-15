package com.terraformcreatives.tabemonotabetai.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.tabs.TabLayout
import com.terraformcreatives.tabemonotabetai.R
import com.terraformcreatives.tabemonotabetai.network.models.EdamamSearchResult
import com.terraformcreatives.tabemonotabetai.network.models.MealType
import com.terraformcreatives.tabemonotabetai.network.models.Recipe
import com.terraformcreatives.tabemonotabetai.network.respository.Repository
import com.terraformcreatives.tabemonotabetai.ui.commons.GridSpacingItemDecoration
import com.terraformcreatives.tabemonotabetai.ui.dashboard.models.DashboardAdapter
import com.terraformcreatives.tabemonotabetai.ui.dashboard.models.DashboardItem
import dagger.hilt.EntryPoints
import kotlinx.coroutines.*

class DashboardPage : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoriesTabLayout: TabLayout
    private lateinit var categoriesShimmer: View
    private lateinit var errorPage: View
    private lateinit var retryButton: Button
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var pullToRefresh: SwipeRefreshLayout

    private val categories: Array<MealType> = MealType.values()
    private lateinit var viewModel: DashboardViewModel
    private lateinit var photoAdapter: DashboardAdapter
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dashboard_page, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        categoriesTabLayout = view.findViewById(R.id.categoriesTabLayout)
        shimmerFrameLayout = view.findViewById(R.id.shimmerViewContainer)
        categoriesShimmer = view.findViewById(R.id.categoriesShimmer)
        errorPage = view.findViewById(R.id.error_page)
        retryButton = errorPage.findViewById(R.id.retryButton)
        pullToRefresh = view.findViewById(R.id.swipeContainer)

        val repository: Repository = EntryPoints.get(
            requireContext().applicationContext,
            Repository.Interface::class.java
        ).repository

        viewModel = DashboardViewModel(repository)
        setup()
        buildCategories()
        return view
    }

    private fun getCurrentMealType(): MealType =
        MealType.values()[categoriesTabLayout.selectedTabPosition]

    /***
     * Setup listeners and adapter
     */
    private fun setup() {
        categoriesTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //do stuff here
                val mealType = MealType.valueOf(tab.text.toString())
                queryRecipe("", mealType)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        retryButton.setOnClickListener {
            queryRecipe("", getCurrentMealType(), true)
        }

        pullToRefresh.setOnRefreshListener {
            queryRecipe(
                "",
                getCurrentMealType(),
                true
            )
        }

        photoAdapter = DashboardAdapter(this.onRecipeSelect)
        val manager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = manager
        recyclerView.adapter = photoAdapter
        recyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                context.let {
                    resources.getDimensionPixelSize(R.dimen.photo_spacing)
                },
                true
            )
        )
    }

    private val onRecipeSelect: (DashboardItem) -> Unit = {
        //TODO
    }

    private fun buildCategories() {
        categories.forEach { mealType ->
            val tab = categoriesTabLayout.newTab()
            tab.apply {
                text = mealType.name
                contentDescription = mealType.name
            }
            categoriesTabLayout.addTab(tab)
        }
        categoriesTabLayout.isVisible = true
        categoriesShimmer.isVisible = false
    }

    private fun queryRecipe(
        query: String,
        mealType: MealType,
        isInitial: Boolean = true
    ) {
        scope.launch {
            toggleErrorPage(false)
            showLoading(true)
            try {
                val result = if (isInitial) initialFetchRecipe(query, mealType) else fetchRecipe()
                setupSearchResults(result)
            } catch (e: Exception) {
                toggleErrorPage(true, isInitial)
            }
            showLoading(false)
        }
    }

    private suspend fun initialFetchRecipe(query: String, mealType: MealType): EdamamSearchResult =
        viewModel.initialQueryRecipe(query, mealType)


    private suspend fun fetchRecipe(): EdamamSearchResult = viewModel.queryRecipe()

    private fun setupSearchResults(result: EdamamSearchResult) {
        photoAdapter.dataSet = result.hits.map {
            DashboardItem(
                DashboardItem.Recipe(
                    it.recipe.label,
                    it.recipe.image,
                    it.recipe.uri,
                    it.bookmarked
                ), DashboardItem.Type.recipe
            )
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            shimmerFrameLayout.startShimmer()
        } else {
            shimmerFrameLayout.stopShimmer()
        }
        shimmerFrameLayout.isVisible = isLoading
        recyclerView.isVisible = !isLoading
        pullToRefresh.isRefreshing = isLoading
    }

    private fun toggleErrorPage(isError: Boolean, isInitial: Boolean = false) {
        errorPage.isVisible = isError && isInitial

        if (isError && !isInitial) {
            Toast.makeText(context, "Something went wrong! Please try again.", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onDestroyView() {
        viewModel.clear()
        scope.cancel()
        super.onDestroyView()
    }
}
