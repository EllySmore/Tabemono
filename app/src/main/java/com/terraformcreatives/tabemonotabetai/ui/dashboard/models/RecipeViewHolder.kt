package com.terraformcreatives.tabemonotabetai.ui.dashboard.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.terraformcreatives.tabemonotabetai.R

class FooterViewHolder(view: View) : BaseViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): RecipeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.dashboard_footer_viewholder, parent, false)
            return RecipeViewHolder(view)
        }
    }
}

class RecipeViewHolder(
    private val view: View
) : BaseViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.image)

    fun bind(recipe: DashboardItem.Recipe) {
        Glide.with(this.view)
            .load(recipe.imageUrl)
            .transform(CenterCrop(), RoundedCorners(8))
            .into(image)
    }

    companion object {
        fun create(parent: ViewGroup): RecipeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.dashboard_recipe_viewholder, parent, false)
            return RecipeViewHolder(view)
        }
    }
}

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)