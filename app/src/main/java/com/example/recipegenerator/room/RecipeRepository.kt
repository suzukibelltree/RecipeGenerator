package com.example.recipegenerator.room

import com.example.recipegenerator.FavoriteRecipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun insertRecipe(recipe: FavoriteRecipe)
    suspend fun deleteRecipe(recipe: FavoriteRecipe)
    suspend fun updateRecipe(recipe: FavoriteRecipe)
    fun getAllRecipes(): Flow<List<FavoriteRecipe>>
}