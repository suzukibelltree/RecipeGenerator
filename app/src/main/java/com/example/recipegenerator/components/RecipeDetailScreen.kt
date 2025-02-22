package com.example.recipegenerator.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.recipegenerator.Result

/**
 * レシピの詳細画面
 *
 * @param recipe レシピの情報
 */
@Composable
fun RecipeDetailScreen(
    recipe: Result
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // レシピ画像
        AsyncImage(
            model = recipe.apiData.foodimage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        //レシピ名
        Text(
            text = recipe.apiData.Title ,
            fontSize = 20.sp ,
            fontWeight = FontWeight.Bold ,
            color = MaterialTheme.colorScheme.primary ,
            modifier = Modifier.padding(all = 8.dp)
        )

        Text(text = recipe.apiData.url,
             modifier = Modifier.fillMaxWidth())
        //調理費用
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .height(50.dp)
        ) {
            Text(
                text = "調理費用" ,
                fontSize = 15.sp ,
                fontWeight = FontWeight.Bold ,
                modifier = Modifier.wrapContentSize(Alignment.TopStart)
            )

            Text(
                text = recipe.apiData.cost ,
                fontSize = 20.sp ,
                fontWeight = FontWeight.Bold ,
                color = MaterialTheme.colorScheme.primary ,
                modifier = Modifier.padding(all = 8.dp)
                    .wrapContentSize(Alignment.TopEnd)
            )
        }
        //調理時間
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .height(50.dp)
        ) {
            Text(
                text = "調理時間" ,
                fontSize = 15.sp ,
                fontWeight = FontWeight.Bold ,
                modifier = Modifier.wrapContentSize(Alignment.TopStart)
            )

            Text(
                text = recipe.apiData.indication ,
                fontSize = 20.sp ,
                fontWeight = FontWeight.Bold ,
                color = MaterialTheme.colorScheme.primary ,
                modifier = Modifier.padding(all = 8.dp)
                    .wrapContentSize(Alignment.TopEnd)
            )
        }
        //材料リスト
        Text(
            text = "材料" ,
            fontSize = 20.sp ,
            fontWeight = FontWeight.Bold ,
            color = MaterialTheme.colorScheme.primary ,
            modifier = Modifier.padding(all = 4.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .height(170.dp)
        ){
            items(recipe.apiData.recipeMaterial) { material ->
                MaterialItem(material)
            }
        }
        //戻る際のボタン
        FilledTonalButton(onClick = {/*画面を戻す*/},
              modifier = Modifier
                  .fillMaxWidth()
                  .height(40.dp),
               shape = CircleShape) {
            Text("Back")
        }


    }
}

@Composable
fun MaterialItem(material: String){
    Box(modifier = Modifier.height(30.dp)){
        Text(material)
    }
}


