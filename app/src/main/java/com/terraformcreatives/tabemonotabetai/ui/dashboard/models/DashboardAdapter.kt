package com.terraformcreatives.tabemonotabetai.ui.dashboard.models

import android.os.Parcelable
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize
import java.lang.IllegalStateException
import kotlin.collections.ArrayList


@Parcelize
data class DashboardItem(val recipe: Recipe?, val type: Type) : Parcelable {
    constructor(recipe: Recipe?) : this(recipe, Type.recipe)
    constructor() : this(null, Type.recipe)


    @Parcelize
    enum class Type : Parcelable {
        recipe,
        footer
    }

    @Parcelize
    data class Recipe(
        val label: String,
        val imageUrl: String,
        val id: String,
        val isBookedmarked: Boolean
    ) : Parcelable
}


class DashboardAdapter(
    private val onItemClicked: (DashboardItem) -> Unit
) : PagingDataAdapter<DashboardItem, BaseViewHolder>(POST_COMPARATOR) {

    var dataSet: List<DashboardItem> = ArrayList(0)
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            0 -> RecipeViewHolder.create(parent)
            1 -> FooterViewHolder.create(parent)
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> {
                val viewHolder: RecipeViewHolder = holder as RecipeViewHolder
                viewHolder.bind(dataSet[position].recipe!!)
                viewHolder.itemView.setOnClickListener {
                    onItemClicked(dataSet[position])
                }
            }
            1 -> {
                val viewHolder: FooterViewHolder = holder as FooterViewHolder
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].type.ordinal
    }

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<DashboardItem>() {
            override fun areContentsTheSame(
                oldItem: DashboardItem,
                newItem: DashboardItem
            ): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: DashboardItem, newItem: DashboardItem): Boolean =
                if (oldItem.type == DashboardItem.Type.recipe && newItem.type == DashboardItem.Type.recipe)
                    oldItem.recipe!!.id == newItem.recipe!!.id
                else {
                    false
                }

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}