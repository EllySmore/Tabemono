package com.terraformcreatives.tabemonotabetai.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
enum class LabelType : Parcelable {
    diet, health
}

@Parcelize
enum class Label(val displayName: String, val apiParameter: String, val labelType: LabelType) :
    Parcelable {

    balanced("Balanced", "balanced", LabelType.diet),

    @SerializedName("High-Fiber")
    high_fiber("High-Fiber", "high-fiber", LabelType.diet),

    @SerializedName("High-Protein")
    high_protein("High-Protein", "high-protein", LabelType.diet),

    @SerializedName("Low-Carb")
    low_carb("Low-Carb", "low-carb", LabelType.diet),

    @SerializedName("Low-Fat")
    low_fat("Low-Fat", "low-fat", LabelType.diet),

    @SerializedName("Low-Sodium")
    low_sodium("Low-Sodium", "low-sodium", LabelType.diet),

    @SerializedName("Alcohol-Free")
    alcohol_free("Alcohol-free", "alcohol-free", LabelType.health),

    @SerializedName("Immuno-Supportive")
    immuno_supportive("Immune-Supportive", "immuno-supportive", LabelType.health),

    @SerializedName("Celery-Free")
    celery_free("Celery-free", "celery-free", LabelType.health),

    @SerializedName("Crustacean-Free")
    crustacean_free("Crustacean-free", "crustcean-free", LabelType.health),

    @SerializedName("Dairy-Free")
    dairy_free("Dairy", "dairy-free", LabelType.health),

    @SerializedName("Egg-Free")
    egg_free("Eggs", "egg-free", LabelType.health),

    @SerializedName("Fish-Free")
    fish_free("Fish", "fish-free", LabelType.health),

    @SerializedName("Fodmap-Free")
    fodmap_free("FODMAP free", "fodmap-free", LabelType.health),

    @SerializedName("Gluten-Free")
    gluten_free("Gluten", "gluten-free", LabelType.health),

    @SerializedName("Keto-Friendly")
    keto_friendly("Keto", "keto-friendly", LabelType.health),

    @SerializedName("Kidney-Friendly")
    kidney_friendly("Kidney friendly", "kidney-friendly", LabelType.health),

    @SerializedName("Kosher")
    kosher("Kosher", "kosher", LabelType.health),

    @SerializedName("Low-Potassium")
    low_potassium("Low potassium", "low-potassium", LabelType.health),

    @SerializedName("Lupine-Free")
    lupine_free("Lupine-free", "lupine-free", LabelType.health),

    @SerializedName("Mustard-Free")
    mustard_free("Mustard-free", "mustard-free", LabelType.health),

    @SerializedName("No oil added")
    no_oil_added("No oil added", "no-oil-added", LabelType.health),

    @SerializedName("Low-Sugar")
    low_sugar("No-sugar", "low-sugar", LabelType.health),

    @SerializedName("Paleo")
    paleo("Paleo", "paleo", LabelType.health),

    @SerializedName("Peanut-Free")
    peanut_free("Peanuts", "peanut-free", LabelType.health),

    @SerializedName("Pescatarian")
    pecatarian("Pescatarian", "pecatarian", LabelType.health),

    @SerializedName("Pork-Free")
    pork_free("Pork-free", "pork-free", LabelType.health),

    @SerializedName("Red-Meat-Free")
    red_meat_free("Red meat-free", "red-meat-free", LabelType.health),

    @SerializedName("Mollusk-Free")
    mollusk_free("Mollusk Free", "mollusk-free", LabelType.health),

    @SerializedName("Sesame-Free")
    sesame_free("Sesame-free", "sesame-free", LabelType.health),

    @SerializedName("Shellfish-Free")
    shellfish_free("Shellfish", "shellfish-free", LabelType.health),

    @SerializedName("Soy-Free")
    soy_free("Soy Free", "soy-free", LabelType.health),

    @SerializedName("Sugar-conscious")
    sugar_conscious("Sugar-conscious", "sugar-conscious", LabelType.health),

    @SerializedName("Tree-Nut-Free")
    tree_nut_free("Tree Nuts", "tree-nut-free", LabelType.health),

    @SerializedName("Vegan")
    vegan("Vegan", "vegan", LabelType.health),

    @SerializedName("Vegetarian")
    vegetarian("Vegetarian", "vegetarian", LabelType.health),

    @SerializedName("Wheat-Free")
    wheat_free("Wheat-free", "wheat-free", LabelType.health),

    none("none", "none", LabelType.diet);

}
