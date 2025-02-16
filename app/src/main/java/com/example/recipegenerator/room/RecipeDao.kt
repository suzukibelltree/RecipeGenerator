package com.example.recipegenerator.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.recipegenerator.FavoriteRecipe

@Dao
interface RecipeDao {

    @Insert
    fun insertRecipe(recipe: FavoriteRecipe)

    @Delete
    fun deleteRecipe(recipe: FavoriteRecipe)

    @Update
    fun updateRecipe(recipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipes")
    fun getAllRecipes(): List<FavoriteRecipe>

}