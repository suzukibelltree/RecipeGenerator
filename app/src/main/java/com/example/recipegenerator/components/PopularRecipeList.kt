package com.example.recipegenerator.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipegenerator.Result

@Composable
fun PopularRecipeList(
    recipes:List<Result>,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
    ) {
        items(recipes) { recipe ->
            RecipeCard(result = recipe)
        }
    }
}