package com.example.recipegenerator.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recipegenerator.Result
import com.example.recipegenerator.ViewModel.RecipeUiState
import com.example.recipegenerator.ViewModel.RecipeViewModel


@Composable
fun ShowFavoriteRecipesList(
    recipeUiState: RecipeUiState,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecipeViewModel
) {
    when (recipeUiState) {
        is RecipeUiState.Success -> {
            FavoriteRecipesList(
                results = recipeUiState.results,
                modifier = modifier,
                navController = navController,
                viewModel = viewModel
            )
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
    modifier: Modifier,
    navController: NavController,
    viewModel: RecipeViewModel
) {
    val favoriteRecipes = remember {
        mutableStateListOf<Result>().apply {
            addAll(results.filter { it.isFavorite })
        }
    }
    LazyColumn(modifier = modifier) {
        itemsIndexed(favoriteRecipes) { index, recipe ->
            RecipeCard(
                result = recipe,
                index = index,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
