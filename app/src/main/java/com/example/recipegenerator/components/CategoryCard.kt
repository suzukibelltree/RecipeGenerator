package com.example.recipegenerator.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipegenerator.DataClass.Large
import com.example.recipegenerator.DataClass.Medium
import com.example.recipegenerator.DataClass.Small
import com.example.recipegenerator.ViewModel.CategoryViewModel

@Composable
fun SmallCategoryCard(
    category: Small,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel,
    navController: NavController
) {
    Card(
        modifier = modifier
            .clickable {
                viewModel.selectedCategoryName.value = category.categoryName
                viewModel.connectedId.value = viewModel.selectedLargeId.value + "-" + category.parentCategoryId + "-" + category.categoryId
                navController.navigate("small")
                viewModel.showMoreCategory.value = false

            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = category.categoryName,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MediumCategoryCard(
    category: Medium,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel,
    navController: NavController
) {
    Card(
        modifier = modifier
            .clickable {
                viewModel.selectedCategoryName.value = category.categoryName
                viewModel.selectedMediumId.value = category.categoryId
                viewModel.connectedId.value = category.parentCategoryId + "-" + category.categoryId
                navController.navigate("medium")
                viewModel.showMoreCategory.value = false
                       },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = category.categoryName,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LargeCategoryCard(
    category: Large,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel,
    navController: NavController
    ) {
    Card(
        modifier = modifier
            .clickable {
                viewModel.selectedCategoryName.value = category.categoryName
                viewModel.selectedLargeId.value = category.categoryId
                navController.navigate("large")
                viewModel.showMoreCategory.value = false
                       },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = category.categoryName,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}