package com.example.recipegenerator

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipegenerator.ViewModel.RecipeViewModel
import com.example.recipegenerator.components.BottomNavigation
import com.example.recipegenerator.components.RecipeGenerateApp
import com.example.recipegenerator.ui.theme.RecipeGeneratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeGeneratorTheme {
                // navControllerを作成
                val navController = rememberNavController()

                Scaffold(
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
                            startDestination = "Home",
                        ) {
                            composable(route = "home") {
                                val recipeViewModel: RecipeViewModel = viewModel()
                                RecipeGenerateApp(
                                    recipeUiState = recipeViewModel.recipeUiState,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            composable(route = "favorite") {

                            }
                            composable(route = "history") {

                            }

                        }
                    }
                }


            }
        }
    }
}

