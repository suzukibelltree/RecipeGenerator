package com.example.recipegenerator.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recipegenerator.Result
import com.example.recipegenerator.ViewModel.RecipeViewModel

@Composable
fun PopularRecipeList(
    recipes: List<Result>,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecipeViewModel
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(recipes) { index, recipe ->
            RecipeCard(
                result = recipe,
                navController = navController,
                index = index,
                viewModel = viewModel
            )
        }
    }
}