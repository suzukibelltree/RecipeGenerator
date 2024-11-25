package com.example.recipegenerator.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.recipegenerator.ViewModel.RecipeUiState
import com.example.recipegenerator.Result


@Composable
fun showFavoriteRecipesList(
    recipeUiState: RecipeUiState ,
    modifier: Modifier = Modifier
) {
    when (recipeUiState) {
        is RecipeUiState.Success -> {
            FavoriteRecipesList(results = recipeUiState.results , modifier = modifier)
        }

        is RecipeUiState.Error -> {
            Text(text = "Error loading recipe")
        }

        is RecipeUiState.Loading -> {
            Text(text = "Loading...")
        }
    }
}


@Composable
fun FavoriteRecipesList(
    results: List<Result>,
    modifier: Modifier

) {
    val favoriteRecipes = remember {
        mutableStateListOf<Result>().apply {
            addAll(results.filter { it.isFavorite })
        }
    }
    LazyColumn {
        items(favoriteRecipes) { recipe ->
            RecipeCard(result = recipe)
        }
    }
}
