package com.example.recipegenerator.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.recipegenerator.FavoriteRecipe
import com.example.recipegenerator.Result
import com.example.recipegenerator.network.ApiClient
import com.example.recipegenerator.room.DatabaseRecipeRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface RecipeUiState {
    data class Success(var results: List<Result>) : RecipeUiState
    object Error : RecipeUiState
    object Loading : RecipeUiState
}

class RecipeViewModel(private val repository: DatabaseRecipeRepository) : ViewModel() {
    var recipeUiState: RecipeUiState by mutableStateOf(RecipeUiState.Loading)
        private set

    init {
        getRecipeList()
    }

    fun getRecipeList() {
        viewModelScope.launch {
            recipeUiState = RecipeUiState.Loading
            recipeUiState = try {
                val listResult = ApiClient().fetchResults()
                if (listResult.isSuccessful) {
                    val recipeResponse = listResult.body()
                    if (recipeResponse != null) {
                        val resultList = recipeResponse.result.map { apiData ->
                            Result(
                                apiData = apiData,
                                isFavorite = false
                            ) // Resultを作成、isFavoriteはデフォルトでfalse
                        }
                        RecipeUiState.Success(resultList)
                    } else {
                        RecipeUiState.Loading
                    }
                } else {
                    RecipeUiState.Error
                }
            } catch (e: IOException) {
                RecipeUiState.Error
            } catch (e: HttpException) {
                RecipeUiState.Error
            }
        }
    }

    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe) {
        repository.insertRecipe(recipe)
    }

    suspend fun deleteFavoriteRecipe(recipe: FavoriteRecipe) {
        repository.deleteRecipe(recipe)
    }

    suspend fun updateFavoriteRecipe(recipe: FavoriteRecipe) {
        repository.updateRecipe(recipe)
    }

    fun getAllFavoriteRecipes() = repository.getAllRecipes()

    suspend fun searchRecipeByTitle(title: String): FavoriteRecipe? {
        return repository.searchRecipeByTitle(title)
    }
}