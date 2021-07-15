package com.terraformcreatives.tabemonotabetai.persist

import androidx.room.*

@Entity(tableName = "bookmarked_recipes", indices = [Index(value = ["edamam_id"])])
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "edamam_id") val edamamId: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "label") val label: String,
)

@Dao
interface BookmarkedRecipeDao {
    @Query("SELECT * FROM bookmarked_recipes")
    fun getAll(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: Recipe)

    @Query("DELETE FROM bookmarked_recipes where edamam_id = :id")
    fun delete(id: String)

    @Query("DELETE FROM bookmarked_recipes")
    fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM bookmarked_recipes WHERE edamam_id = :id)")
    fun exist(id: String): Boolean
}