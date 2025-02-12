package com.example.recipegenerator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipegenerator.ViewModel.CategoryViewModel

@Composable
fun CategoryList(
    viewModel: CategoryViewModel,
    navController: NavController
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(start = 16.dp, end = 16.dp),
        ) {
            Text(
                text = "カテゴリ検索",
                modifier = Modifier.padding(16.dp)
            )
            TextField(
                value = viewModel.searchCategoryText.value,
                onValueChange = { viewModel.searchCategoryText.value = it },
                label = { Text("カテゴリ名") },
                placeholder = { Text("例)肉、カレー") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (viewModel.isLargeCategoryLoaded.value) {
                            keyboardController?.hide()
                            viewModel.getMatchCategoryList()
                            navController.navigate("match")
                        }
                    }
                )
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
}