package com.example.recipegenerator.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipegenerator.ViewModel.CategoryViewModel
import com.example.recipegenerator.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun SmallCategoryList(
    parentCategoryId: String,
    viewModel: CategoryViewModel,
    apiClient: ApiClient,
    coroutineScope: CoroutineScope,
    innerPadding: PaddingValues
    ) {
    LaunchedEffect(Unit, viewModel.isSmallCategoryLoaded) {
        if (!viewModel.isSmallCategoryLoaded.value) {
            coroutineScope.launch {
                val smallDeferred = async { apiClient.fetchRecipeCategory("small") }
                val smallResponse = smallDeferred.await()

                smallResponse.body()?.result?.small?.let { viewModel.smallList.addAll(it) }
                viewModel.isSmallCategoryLoaded.value = true
            }
        }
    }

    if (viewModel.smallList.isEmpty()) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(viewModel.smallList) { small ->
                if (small.parentCategoryId == parentCategoryId) {
                    SmallCategoryCard(small, Modifier.padding(8.dp), viewModel)
                }
            }
        }
    }
}