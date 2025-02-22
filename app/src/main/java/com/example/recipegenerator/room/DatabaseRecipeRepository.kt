package com.example.recipegenerator.room

import com.example.recipegenerator.FavoriteRecipe

class DatabaseRecipeRepository(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun insertRecipe(recipe: FavoriteRecipe) {
        recipeDao.insertRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: FavoriteRecipe) {
        recipeDao.deleteRecipe(recipe)
    }

    override suspend fun updateRecipe(recipe: FavoriteRecipe) {
        recipeDao.updateRecipe(recipe)
    }

    override fun getAllRecipes() = recipeDao.getAllRecipes()

    override suspend fun searchRecipeByTitle(title: String): FavoriteRecipe? {
        return recipeDao.searchRecipeByTitle(title)
    }
}