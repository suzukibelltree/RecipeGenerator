package com.example.recipegenerator.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

// データの仮置き
data class Recipe(val name: String, val imageResId: Int)

val recipedata  = (1..20).map {
    Recipe("recipe$it", android.R.drawable.ic_menu_gallery)
}

@Composable
// 履歴画面のUI
fun HistoryScreen() {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray) // 背景の色
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

        items(recipedata) { recipe ->
            HistoryItemRow(recipe)
        }
    }
}

//履歴の一つのUI
@Composable
fun HistoryItemRow(a: Recipe) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .height(100.dp)
            .clickable { /* アイテムクリック時の処理 */ }
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Gray) // 背景色を指定
    ) {
//        レシピの画像
        Image(
            painter = painterResource(id = a.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .width(120.dp) // 画像のサイズを指定
                .padding(end = 10.dp) // テキストとの間に余白を追加
        )
//        レシピの名前
        Text(text = a.name)
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
