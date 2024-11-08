package com.example.recipegenerator.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipegenerator.ViewModel.CategoryViewModel
import com.example.recipegenerator.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun MediumCategoryList(
    parentCategoryId: String,
    viewModel: CategoryViewModel,
    apiClient: ApiClient,
    coroutineScope: CoroutineScope,
    innerPadding: PaddingValues,
    navController: NavController
) {
    LaunchedEffect(Unit, viewModel.isMediumCategoryLoaded) {
        if (!viewModel.isMediumCategoryLoaded.value) {
            coroutineScope.launch {
                val mediumDeferred = async { apiClient.fetchRecipeCategory("medium") }
                val mediumResponse = mediumDeferred.await()

                mediumResponse.body()?.result?.medium?.let { viewModel.mediumList.addAll(it) }
                viewModel.isMediumCategoryLoaded.value = true
            }
        }
    }

    // データが空の場合はローディングインジケーターを表示
    if (viewModel.mediumList.isEmpty()) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (viewModel.showMoreCategory.value) {
                items(viewModel.mediumList) { medium ->
                    if (medium.parentCategoryId == parentCategoryId) {
                        MediumCategoryCard(medium, Modifier.padding(8.dp), viewModel, navController)
                    }
                }
            } else {
                val filteredMediumList = viewModel.mediumList.filter { it.parentCategoryId == parentCategoryId }.take(4)
                items(filteredMediumList) { medium ->
                    if (medium.parentCategoryId == parentCategoryId) {
                        MediumCategoryCard(medium, Modifier.padding(8.dp), viewModel, navController)
                    }
                }
            }

            item { TextButton(
                onClick = { viewModel.showMoreCategory.value = !viewModel.showMoreCategory.value}
            ) {
                Text(if (viewModel.showMoreCategory.value) "縮小" else "全て表示")
            } }
        }
    }
}