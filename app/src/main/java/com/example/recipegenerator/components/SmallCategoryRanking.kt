package com.example.recipegenerator.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.recipegenerator.ViewModel.CategoryViewModel
import com.example.recipegenerator.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun SmallCategoryRanking(
    selectedCategoryName: String,
    parentCategoryId: String,
    viewModel: CategoryViewModel,
    apiClient: ApiClient,
    coroutineScope: CoroutineScope,
) {
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val smallRankingDeferred = async { apiClient.fetchRecipeRanking(parentCategoryId) }
            val smallRankingResponse = smallRankingDeferred.await()
            viewModel.smallRankingList.clear()
            smallRankingResponse.body()?.result?.let { viewModel.smallRankingList.addAll(it) }
        }
    }

    val context = LocalContext.current

    if (viewModel.smallRankingList.isEmpty()) {
        CircularProgressIndicator()
    } else {
        LazyColumn {
            item { Text(text = selectedCategoryName, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp)) }

            item {
                Text(
                    text = "ランキング",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }

            items(viewModel.smallRankingList) { recipe ->
                Card(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.recipeUrl))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {
                    Row {
                        AsyncImage(
                            model = recipe.foodImageUrl,
                            contentDescription = null,
                            imageLoader = ImageLoader(LocalContext.current),
                            modifier = Modifier
                                .weight(1f)
                                .size(100.dp)
                                .fillMaxWidth()
                        )

                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.weight(3f),
                        ) {
                            Text(text = "${recipe.rank}位", fontWeight = FontWeight.Bold)
                            Text(text = recipe.recipeTitle, fontWeight = FontWeight.Bold)
                            Text(text = recipe.recipePublishday)
                            Text(text = "by ${recipe.nickname}")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}