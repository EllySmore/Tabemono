package com.terraformcreatives.tabemonotabetai.markdown

import android.text.Spanned
import com.terraformcreatives.tabemonotabetai.network.models.Recipe
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.noties.markwon.Markwon
import javax.inject.Inject

class RecipeMarkdownUtil @Inject constructor(private val markwon: Markwon) {

    @EntryPoint
    @InstallIn(ApplicationComponent::class)
    interface Interface {
        val recipeMarkdownUtil: RecipeMarkdownUtil
    }

    fun getMarkdownText(recipe: Recipe): Spanned {
        var ingredientText = ""
        recipe.ingredientLines.forEach {
            ingredientText += "- $it\n"

        }

        return markwon.toMarkdown(
            "**Servings:** ${recipe.yield}\n" +
                    "\n**Time:** ${recipe.totalTime} minutes" +
                    "\n***\n" +
                    "### Ingredients\n" +
                    ingredientText +
                    "\n"
        )

    }

}