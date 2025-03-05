package com.example.recipegenerator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.recipegenerator.FavoriteRecipeCard
import com.example.recipegenerator.ViewModel.RecipeViewModel
import kotlinx.coroutines.launch

/**
 * 履歴を表示する画面のUI
 * @param viewModel レシピのViewModel
 */
@Composable
fun HistoryScreen(
    viewModel: RecipeViewModel
) {
    // お気に入りレシピの情報をローカルデータベースからとってきている
    val favoriteRecipes by viewModel.favoriteRecipes.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.getAllFavoriteRecipes()
    }
    LaunchedEffect(favoriteRecipes) {
        // お気に入りレシピの情報が変更されたときに、再度データベースから取得する
        viewModel.getAllFavoriteRecipes()
    }
    // ここからUIを記述
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "作った回数が多い順に表示しています",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyColumn {
            items(favoriteRecipes.sortedByDescending { it.makeCount }) { recipe ->
                FavoriteRecipeCard(
                    recipe = recipe,
                    onMakeCountAddClick = {
                        scope.launch {
                            viewModel.updateFavoriteRecipe(recipe.copy(makeCount = recipe.makeCount + 1))
                        }
                    },
                    onMakeCountMinusClick = {
                        scope.launch {
                            viewModel.updateFavoriteRecipe(recipe.copy(makeCount = recipe.makeCount - 1))
                        }
                    }
                )
            }
        }
    }
}
