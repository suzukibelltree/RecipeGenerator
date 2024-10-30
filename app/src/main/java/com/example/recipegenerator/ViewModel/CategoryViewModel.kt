package com.example.recipegenerator.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.recipegenerator.DataClass.Large

class CategoryViewModel : ViewModel() {
    val largeList = mutableStateListOf<Large>() // 大カテゴリのリスト
    val selectedCategory = mutableStateOf("large") // カテゴリ表示の制御
}