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
import com.example.recipegenerator.DataClass.Large
import com.example.recipegenerator.DataClass.Medium
import com.example.recipegenerator.DataClass.Small

@Composable
fun SmallCategoryCard(category: Small) {
    Card {
        Text(text = category.categoryName)
    }
}

@Composable
fun MediumCategoryCard(category: Medium) {
    Card {
        Text(text = category.categoryName)
    }
}

@Composable
fun LargeCategoryCard(category: Large,modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clickable { /*TODO*/ },
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