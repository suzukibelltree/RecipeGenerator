package com.example.recipegenerator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun FavoriteRecipeCard(
    recipe: FavoriteRecipe,
    onMakeCountAddClick: (FavoriteRecipe) -> Unit = {},
    onMakeCountMinusClick: (FavoriteRecipe) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = recipe.title,
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp
            )
            Row {
                AsyncImage(
                    model = recipe.foodImageUrl,
                    contentDescription = "food image",
                    modifier = Modifier
                        .width(120.dp)
                        .height(90.dp)
                        .padding(8.dp)
                )
                Column {
                    Text(
                        text = "コスト：${recipe.cost}",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "所要時間：${recipe.indication}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "料理回数"
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ー",
                            modifier = Modifier.clickable {
                                onMakeCountMinusClick(recipe)
                            },
                            fontSize = 16.sp
                        )
                        Text(
                            text = recipe.makeCount.toString(),
                            fontSize = 24.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = "＋",
                            modifier = Modifier.clickable {
                                onMakeCountAddClick(recipe)
                            },
                            fontSize = 16.sp
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteRecipeCardPreview() {
    FavoriteRecipeCard(
        FavoriteRecipe(
            title = "title",
            url = "url",
            foodImageUrl = "foodImageUrl",
            cost = "cost",
            indication = "indication",
            nickname = "nickname",
            recipeDescription = "recipeDescription",
            recipeMaterial = listOf("material1", "material2"),
            registerDate = "registerDate",
            makeCount = 0
        )
    )
}