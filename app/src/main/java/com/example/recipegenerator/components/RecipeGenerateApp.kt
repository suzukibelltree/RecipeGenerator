package com.example.recipegenerator.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recipegenerator.ViewModel.RecipeUiState
import com.example.recipegenerator.ViewModel.RecipeViewModel

@Composable
fun RecipeGenerateApp(
    recipeUiState: RecipeUiState,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecipeViewModel
) {
    when (recipeUiState) {
        is RecipeUiState.Success -> {
            PopularRecipeList(
                recipes = recipeUiState.results,
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