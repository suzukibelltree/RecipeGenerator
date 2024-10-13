package com.example.recipegenerator.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipegenerator.ViewModel.RecipeUiState

@Composable
fun RecipeGenerateApp(
    recipeUiState: RecipeUiState,
    modifier: Modifier = Modifier
){
    when (recipeUiState) {
        is RecipeUiState.Success -> {
            /*
            この中は書き換えてもらって構いません。
            UIを構築する上でResultクラスのインスタンスのリストが必要になると思いますが、
            recipeUiState.resultsで取得できます。
            HomeScreen()みたいな関数を作って、その中でPopularRecipeList()を呼び出すといいと思います。
             */
            PopularRecipeList(recipes = recipeUiState.results, modifier = modifier)
        }
        is RecipeUiState.Error -> {
            Text(text = "Error loading recipe")
        }
        is RecipeUiState.Loading -> {
            Text(text = "Loading...")
        }
    }
}