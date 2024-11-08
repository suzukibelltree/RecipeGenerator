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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipegenerator.DataClass.Large
import com.example.recipegenerator.ViewModel.CategoryViewModel
import com.example.recipegenerator.network.ApiClient
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.addAll

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
        NavHost(navController, startDestination = "large") {
            composable("large") {
                CategoryList(viewModel, innerPadding, navController)
            }
            composable("medium") {
                MediumCategoryList(viewModel.selectedParentId.value, viewModel, apiClient, coroutineScope, innerPadding, navController)
            }
            composable("small") {
                SmallCategoryList(viewModel.selectedParentId.value, viewModel, apiClient, coroutineScope, innerPadding)
            }
        }
    }
}