package com.terraformcreatives.tabemonotabetai.ui.recipe

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.terraformcreatives.tabemonotabetai.R
import com.terraformcreatives.tabemonotabetai.markdown.RecipeMarkdownUtil
import com.terraformcreatives.tabemonotabetai.network.models.Recipe
import com.terraformcreatives.tabemonotabetai.network.respository.Repository
import dagger.hilt.EntryPoints
import kotlinx.coroutines.*
import java.util.*

class DetailedRecipePage : BottomSheetDialogFragment() {
    private lateinit var recipeMarkdownUtil: RecipeMarkdownUtil
    private lateinit var repository: Repository

    private lateinit var toolbar: Toolbar
    private lateinit var bookmarkButton: Button
    private lateinit var visitButton: Button

    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private lateinit var viewModel: DetailedViewModel
    private val args: DetailedRecipePageArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        return super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detailed_recipe_page, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            this.dialog?.dismiss()
        }
        bookmarkButton = view.findViewById(R.id.bookmarkButton)
        visitButton = view.findViewById(R.id.visitButton)

        recipeMarkdownUtil = EntryPoints.get(
            requireContext().applicationContext,
            RecipeMarkdownUtil.Interface::class.java
        ).recipeMarkdownUtil

        repository = EntryPoints.get(
            requireContext().applicationContext,
            Repository.Interface::class.java
        ).repository

        viewModel = DetailedViewModel(repository, args.recipeId!!)

        visitButton.setOnClickListener {

        }
        checkBookmarkRecipe()
        bookmarkButton.setOnClickListener {
            bookmarkRecipe()
        }
        return view
    }


    private fun setupBookmarkButton(isBookmarked: Boolean) {
        val buttonRes =
            if (isBookmarked) R.drawable.coral_rounded_corner else R.drawable.coral_outlined_rounded_corner
        val stringRes = if (isBookmarked) R.string.bookmarked else R.string.bookmark
        val textColor =
            if (isBookmarked) requireContext().resources.getColor(R.color.white, context?.theme) else requireContext().resources.getColor(
                R.color.coral,
                context?.theme
            )
        bookmarkButton.setBackgroundResource(buttonRes)
        bookmarkButton.setText(stringRes)
        bookmarkButton.setTextColor(textColor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchRecipe()
    }

    private fun enableActions(enableActions: Boolean) {
        bookmarkButton.isEnabled = enableActions
        visitButton.isEnabled = true
    }

    private fun fetchRecipe() {
        scope.launch {
            val result = viewModel.getRecipeById()
            if (result.isSuccessful) {
                val recipe = result.body()!![0]
                viewModel.recipe.value = recipe
                displayRecipe(recipe)
                enableActions(true)
            } else {
                TODO("Display error")
            }
        }
    }

    private fun checkBookmarkRecipe() {
        scope.launch {
            val isBookmarked = viewModel.isRecipeBookmarked()
            viewModel.isBookmarked.value = isBookmarked
            setupBookmarkButton(isBookmarked)
        }
    }

    private fun bookmarkRecipe() {
        scope.launch {
            if (viewModel.currentRecipeBookmarked() == true) {
                viewModel.unBookmarked()
                viewModel.isBookmarked.value = false
                setupBookmarkButton(false)
            } else {
                viewModel.isBookmarked.value = true
                viewModel.bookmarkRecipe()
                setupBookmarkButton(true)
            }
        }
    }

    private fun displayRecipe(recipe: Recipe) {
        val title: TextView = toolbar.findViewById(R.id.toolbar_title)
        val subtitle: TextView = toolbar.findViewById(R.id.toolbar_subtitle)
        val recipeMarkdown: TextView = requireView().findViewById(R.id.recipeMarkdown)
        title.text = recipe.label
        subtitle.text = recipe.source.uppercase(Locale.ROOT)

        val markdown = recipeMarkdownUtil.getMarkdownText(recipe)
        recipeMarkdown.text = markdown
    }

    override fun onDestroyView() {
        scope.cancel()
        super.onDestroyView()
    }

}



