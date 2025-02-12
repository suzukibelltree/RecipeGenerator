package com.example.recipegenerator.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.recipegenerator.ViewModel.CategoryViewModel
import com.example.recipegenerator.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun LargeCategoryRanking(
    selectedCategoryName: String,
    parentCategoryId: String,
    viewModel: CategoryViewModel,
    apiClient: ApiClient,
    coroutineScope: CoroutineScope,
    innerPadding: PaddingValues,
    navController: NavController
) {
    LaunchedEffect(Unit, viewModel.isMediumCategoryLoaded) {
        coroutineScope.launch {
            val largeRankingDeferred = async { apiClient.fetchRecipeRanking(parentCategoryId) }
            val largeRankingResponse = largeRankingDeferred.await()
            viewModel.largeRankingList.clear()
            largeRankingResponse.body()?.result?.let { viewModel.largeRankingList.addAll(it) }
        }
    }

    val context = LocalContext.current

    // データが空の場合はローディングインジケーターを表示
    if (viewModel.mediumList.isEmpty() || viewModel.largeRankingList.isEmpty()) {
        CircularProgressIndicator()
    } else {
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item { Text(text = selectedCategoryName, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))}

            item { Text(text = "さらに絞る", fontSize = 18.sp, modifier = Modifier.padding(16.dp)) }

            val matchedMediumList = viewModel.mediumList.filter { it.parentCategoryId == parentCategoryId }
                .take(if (viewModel.showMoreCategory.value) Int.MAX_VALUE else 4)

            items(matchedMediumList.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    rowItems.forEachIndexed { index, medium ->
                        MediumCategoryCard(
                            medium,
                            Modifier
                                .fillMaxWidth(if (index == 0) 0.5f else 1f)
                                .padding(start = if (index == 0) 16.dp else 8.dp, end = if (index == 0) 8.dp else 16.dp),
                            viewModel,
                            navController
                        )
                    }
                }
            }

            item { OutlinedButton(
                onClick = { viewModel.showMoreCategory.value = !viewModel.showMoreCategory.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = if (viewModel.showMoreCategory.value) "閉じる" else "全て表示")
            }
            }

            item {
                Text(
                    text = "ランキング",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }

            items(viewModel.largeRankingList) { recipe ->
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