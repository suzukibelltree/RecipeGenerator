package com.example.recipegenerator.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.recipegenerator.R

// データの仮置き
data class Recipe(val name: String, val imageResId: Int)

val initialRecipeData = (1..20).map {
    Recipe("recipe$it", android.R.drawable.ic_menu_gallery)
}
//これはよくわからない
@SuppressLint("MutableCollectionMutableState")
@Composable
// 履歴画面のUI
fun HistoryScreen() {
    // 状態管理
    var recipes by remember { mutableStateOf(initialRecipeData.toMutableList()) }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(10.dp)
            ) {
                Text(
                    text = "履歴",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        items(recipes) { recipe ->
            HistoryItemRow(recipe) {
                // アイテム削除の処理
                recipes = recipes.filter { it != recipe }.toMutableList()
            }
        }
    }
}

// 履歴の一つのUI
@Composable
fun HistoryItemRow(recipe: Recipe, onDelete: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Gray)
    ) {
        // レシピの画像
        Image(
            painter = painterResource(id = recipe.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .size(120.dp)
                .padding(end = 10.dp)
        )
        // レシピの名前
        Text(text = recipe.name,fontSize =20.sp)

        // ゴミ箱アイコン
        Image(
            painter = painterResource(id = R.drawable.baseline_delete_24),
            contentDescription = "Delete",
            modifier = Modifier
                .size(60.dp)
                .clickable {
                    onDelete() // ゴミ箱アイコンクリック時の削除処理
                }
                .padding(start = 10.dp)
        )
    }
}

@Composable
fun MyApp() {
    MaterialTheme {
        Surface {
            HistoryScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistoryScreen() {
    MyApp()
}
