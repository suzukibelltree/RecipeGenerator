package com.example.recipegenerator.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipegenerator.ViewModel.CategoryViewModel

@Composable
fun MatchCategoryList(
    viewModel: CategoryViewModel,
    innerPadding: PaddingValues,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        item { Text(text = "大カテゴリ一覧", fontSize = 18.sp) }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        items(viewModel.matchLargeCategoryList) { large ->
            LargeCategoryCard(large, Modifier.padding(8.dp), viewModel, navController)
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { Text(text = "中カテゴリ一覧", fontSize = 18.sp) }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        items(viewModel.matchMediumCategoryList) { medium ->
            MediumCategoryCard(medium, Modifier.padding(8.dp), viewModel, navController)
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { Text(text = "小カテゴリ一覧", fontSize = 18.sp) }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        items(viewModel.matchSmallCategoryList) { small ->
            SmallCategoryCard(small, Modifier.padding(8.dp), viewModel, navController)
        }
    }
}