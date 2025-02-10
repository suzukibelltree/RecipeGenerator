package com.example.recipegenerator.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.recipegenerator.Result

/**
 * レシピの詳細画面
 * @param recipe レシピの情報
 */
@Composable
fun RecipeDetailScreen(
    recipe: Result
) {
    //テスト用テキスト(消しておいてください)
    Text(text = recipe.apiData.Title)
}