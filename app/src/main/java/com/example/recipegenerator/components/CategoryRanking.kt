package com.example.recipegenerator.components

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipegenerator.ViewModel.CategoryViewModel
import com.example.recipegenerator.network.ApiClient
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
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

                delay(1000) // 1秒待つ

                val mediumDeferred = async { apiClient.fetchRecipeCategory("medium") }
                val mediumResponse = mediumDeferred.await()

                Log.d("CategoryRanking", "mediumResponse: $mediumResponse")
                mediumResponse.body()?.result?.medium?.let { viewModel.mediumList.addAll(it) }

                delay(1000) // 1秒待つ

                val smallDeferred = async { apiClient.fetchRecipeCategory("small") }
                val smallResponse = smallDeferred.await()

                smallResponse.body()?.result?.small?.let { viewModel.smallList.addAll(it) }
                viewModel.isLargeCategoryLoaded.value = true
            }
        }
    }

    NavHost(navController, startDestination = "category") {
        composable("category") {
            CategoryList(viewModel, navController)
        }
        composable("match") {
            MatchCategoryList(viewModel, navController)
        }
        composable("large") {
            LargeCategoryRanking(
                viewModel.selectedCategoryName.value,
                viewModel.selectedLargeId.value,
                viewModel,
                apiClient,
                coroutineScope,
                navController
            )
        }
        composable("medium") {
            MediumCategoryRanking(
                viewModel.selectedCategoryName.value,
                viewModel.selectedMediumId.value,
                viewModel.connectedId.value,
                viewModel,
                apiClient,
                coroutineScope,
                navController
            )
        }
        composable("small") {
            SmallCategoryRanking(
                viewModel.selectedCategoryName.value,
                viewModel.connectedId.value,
                viewModel,
                apiClient,
                coroutineScope
            )
        }
    }


}