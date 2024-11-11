package com.example.recipegenerator.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipegenerator.ViewModel.CategoryViewModel
import com.example.recipegenerator.network.ApiClient
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryRanking() {
    val apiClient = ApiClient()
    val viewModel = CategoryViewModel()
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    LaunchedEffect(Unit, viewModel.isLargeCategoryLoaded) { // 画面が開かれたときに実行される
        if (!viewModel.isLargeCategoryLoaded.value) {
            coroutineScope.launch {
                val largeDeferred = async { apiClient.fetchRecipeCategory("large") } // 大カテゴリ取得
                val largeResponse = largeDeferred.await()

                largeResponse.body()?.result?.large?.let { viewModel.largeList.addAll(it) }
                viewModel.isLargeCategoryLoaded.value = true
            }
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row{
                        Text(
                            text = "カテゴリ検索",
                            modifier = Modifier.padding(16.dp)
                        )
                        TextField(
                            value = "",
                            onValueChange = { /*TODO*/ },
                            label = { Text("カテゴリ名") },
                            placeholder = { Text("例)肉、カレー") }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "category") {
            composable("category") {
                CategoryList(viewModel, innerPadding, navController)
            }
            composable("large") {
                LargeCategoryRanking(viewModel.selectedCategoryName.value, viewModel.selectedLargeId.value, viewModel, apiClient, coroutineScope, innerPadding, navController)
            }
            composable("medium") {
                MediumCategoryRanking(viewModel.selectedCategoryName.value, viewModel.selectedMediumId.value, viewModel.connectedId.value, viewModel, apiClient, coroutineScope, innerPadding, navController)
            }
            composable("small") {
                SmallCategoryRanking(viewModel.selectedCategoryName.value, viewModel.connectedId.value, viewModel, apiClient, coroutineScope, innerPadding)
            }
        }
    }
}