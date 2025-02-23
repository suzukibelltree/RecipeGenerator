package com.example.recipegenerator.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.recipegenerator.FavoriteRecipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe: FavoriteRecipe)

    @Delete
    suspend fun deleteRecipe(recipe: FavoriteRecipe)

    @Update
    suspend fun updateRecipe(recipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipes")
    fun getAllRecipes(): Flow<List<FavoriteRecipe>>

    // レシピタイトルの完全一致で検索
    @Query("SELECT * FROM favorite_recipes WHERE title = :title")
    suspend fun searchRecipeByTitle(title: String): FavoriteRecipe?
}