package com.example.recipegenerator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipegenerator.ViewModel.CategoryViewModel

@Composable
fun MatchCategoryList(
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
            item(span = { GridItemSpan(maxCurrentLineSpan) }) { // 最大スパンを指定
                Text(text = "大カテゴリ一覧", fontSize = 18.sp)
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) { // 最大スパンを指定
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.matchLargeCategoryList) { large ->
                LargeCategoryCard(large, Modifier.padding(8.dp), viewModel, navController)
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) { // 最大スパンを指定
                Spacer(modifier = Modifier.height(16.dp))
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) { // 最大スパンを指定
                Text(text = "中カテゴリ一覧", fontSize = 18.sp)
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) { // 最大スパンを指定
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.matchMediumCategoryList) { medium ->
                MediumCategoryCard(medium, Modifier.padding(8.dp), viewModel, navController)
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) { // 最大スパンを指定
                Spacer(modifier = Modifier.height(16.dp))
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) { // 最大スパンを指定
                Text(text = "小カテゴリ一覧", fontSize = 18.sp)
            }

            item(span = { GridItemSpan(maxCurrentLineSpan) }) { // 最大スパンを指定
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(viewModel.matchSmallCategoryList) { small ->
                SmallCategoryCard(small, Modifier.padding(8.dp), viewModel, navController)
            }
        }
    }
}