package com.example.recipegenerator

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipegenerator.ViewModel.RecipeUiState
import com.example.recipegenerator.ViewModel.RecipeViewModel
import com.example.recipegenerator.ViewModel.RecipeViewModelFactory
import com.example.recipegenerator.components.BottomNavigation
import com.example.recipegenerator.components.CategoryRanking
import com.example.recipegenerator.components.HistoryScreen
import com.example.recipegenerator.components.HomeNavigation
import com.example.recipegenerator.components.RecipeDetailScreen
import com.example.recipegenerator.components.RecipeGenerateApp
import com.example.recipegenerator.components.ShowFavoriteRecipesList
import com.example.recipegenerator.room.DatabaseRecipeRepository
import com.example.recipegenerator.room.RecipeDatabase
import com.example.recipegenerator.ui.theme.RecipeGeneratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeGeneratorTheme {
                // navControllerを作成
                val navController = rememberNavController()
                val app = LocalContext.current.applicationContext as Application
                val dao = RecipeDatabase.getDatabase(app).recipeDao()
                val recipeRepository = DatabaseRecipeRepository(dao)
                val recipeViewModel: RecipeViewModel =
                    viewModel(factory = RecipeViewModelFactory(recipeRepository))
                Scaffold(
                    topBar = {
                        HomeNavigation(navController = navController)
                    },
                    bottomBar = {
                        BottomNavigation(navController = navController)
                    }
                ) { innerpadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerpadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        // ナビゲーションホストの設定
                        NavHost(
                            navController = navController,
                            startDestination = "ranking",
                        ) {
                            composable(route = "ranking") {
                                RecipeGenerateApp(
                                    recipeUiState = recipeViewModel.recipeUiState,
                                    modifier = Modifier.fillMaxSize(),
                                    navController = navController,
                                    viewModel = recipeViewModel
                                )
                            }
                            composable(route = "categories") {
                                CategoryRanking()
                            }

                            composable(route = "favorites") {
                                ShowFavoriteRecipesList(
                                    recipeUiState = recipeViewModel.recipeUiState,
                                    modifier = Modifier.fillMaxSize(),
                                    navController = navController,
                                    viewModel = recipeViewModel
                                )
                            }
                            composable(route = "history") {
                                HistoryScreen(viewModel = recipeViewModel)
                            }
                            composable(route = "detail/{recipeIndex}") { backStackEntry ->
                                val index = backStackEntry.arguments?.getString("recipeIndex")
                                    ?.toIntOrNull()
                                val recipeList =
                                    (recipeViewModel.recipeUiState as? RecipeUiState.Success)?.results

                                if (index != null && recipeList != null && index in recipeList.indices) {
                                    RecipeDetailScreen(recipe = recipeList[index])
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

