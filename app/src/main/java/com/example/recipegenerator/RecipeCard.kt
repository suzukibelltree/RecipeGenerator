package com.example.recipegenerator.components

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipegenerator.Result

@Composable
fun RecipeCard(
    result: Result,
    modifier: Modifier = Modifier
){
    var isFavorite by remember { mutableStateOf(result.isFavorite) }
    Card(
        colors = CardDefaults.cardColors(),
        modifier = modifier.fillMaxWidth().padding(16.dp)
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
                        text="コスト: ${result.apiData.cost}",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
                    Text(
                        text="所要時間: ${result.apiData.indication}",
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
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = if(isFavorite){
                            Color.Red
                        }else{
                            Color.Gray
                        }
                    )
                }
            }
        }
    }
}