package com.example.recipegenerator.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipegenerator.FavoriteRecipe
import com.example.recipegenerator.Result
import com.example.recipegenerator.ViewModel.RecipeViewModel
import kotlinx.coroutines.launch


@Composable
fun RecipeCard(
    result: Result,
    navController: NavController,
    index: Int,
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel
) {
    var isFavorite by remember { mutableStateOf(result.isFavorite) }
    val scope = rememberCoroutineScope()
    Card(
        colors = CardDefaults.cardColors(),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navController.navigate("detail/$index") // ここで渡された index を使う
            }
    ) {
        Column {
            Text(
                text = result.apiData.Title,
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
            ) {
                AsyncImage(
                    model = result.apiData.foodimage,
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .height(90.dp)
                        .padding(8.dp)
                )
                Column {
                    Text(
                        text = "コスト: ${result.apiData.cost}",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
                    Text(
                        text = "所要時間: ${result.apiData.indication}",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    //お気に入り登録ボタンを押した時にisFavoriteの値を変更する
                    onClick = {
                        isFavorite = !isFavorite
                        result.isFavorite = isFavorite
                        scope.launch {
                            if (isFavorite) {
                                viewModel.insertFavoriteRecipe(
                                    FavoriteRecipe(
                                        id = 0,
                                        title = result.apiData.Title,
                                        url = result.apiData.url,
                                        cost = result.apiData.cost,
                                        indication = result.apiData.indication,
                                        nickname = result.apiData.nickname,
                                        recipeDescription = result.apiData.recipeDescription,
                                        recipeMaterial = result.apiData.recipeMaterial,
                                        registerDate = "2021-10-01",
                                        makeCount = 0
                                    )
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = if (isFavorite) {
                            Color.Red
                        } else {
                            Color.Gray
                        }
                    )
                }
            }
        }
    }
}