package com.example.recipegenerator.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipegenerator.DataClass.Large
import com.example.recipegenerator.ViewModel.CategoryViewModel
import com.example.recipegenerator.network.ApiClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.collections.addAll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryRanking() {
    val apiClient = ApiClient()
    val viewModel = CategoryViewModel()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) { // 画面が開かれたときに実行される
        coroutineScope.launch {
            kotlin.runCatching {
                apiClient.fetchRecipeCategory("large")  //大カテゴリ取得
            }
                .onSuccess { response ->
                    response.body()?.result?.let { viewModel.largeList.addAll(it.large) } // 大カテゴリのリストを状態変数に追加
                }
                .onFailure { e ->
                    Log.e("CategoryRanking", "API通信に失敗しました", e)
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
        when(viewModel.selectedCategory.value) {
            "large" -> { CategoryList(viewModel, innerPadding) }
        }
    }
}