package com.example.recipegenerator.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recipegenerator.ViewModel.RecipeUiState

@Composable
fun RecipeGenerateApp(
    recipeUiState: RecipeUiState,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    when (recipeUiState) {
        is RecipeUiState.Success -> {
            PopularRecipeList(
                recipes = recipeUiState.results,
                modifier = modifier,
                navController = navController
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