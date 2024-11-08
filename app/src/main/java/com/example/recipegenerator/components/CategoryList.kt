package com.example.recipegenerator.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipegenerator.ViewModel.CategoryViewModel

@Composable
fun CategoryList(
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

        if (viewModel.largeList.isNotEmpty()) {
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                Text(text = "料理")
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.largeList.subList(0, 12)) { large ->
                LargeCategoryCard(large, Modifier.padding(8.dp), viewModel, navController)
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                Text(text = "食材")
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.largeList.subList(12, 20)) { large ->
                LargeCategoryCard(large, Modifier.padding(8.dp), viewModel, navController)
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                Text(text = "目的・シーン")
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.largeList.subList(20, 27)) { large ->
                LargeCategoryCard(large, Modifier.padding(8.dp), viewModel, navController)
            }

            item {}

            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                Text(text = "各国料理")
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.largeList.subList(27, 35)) { large ->
                LargeCategoryCard(large, Modifier.padding(8.dp), viewModel, navController)
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                Text(text = "季節・イベント")
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.largeList.subList(35, 43)) { large ->
                LargeCategoryCard(large, Modifier.padding(8.dp), viewModel, navController)
            }
        } else {
            item { CircularProgressIndicator() }
        }
    }
}