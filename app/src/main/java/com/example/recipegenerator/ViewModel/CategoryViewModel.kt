package com.example.recipegenerator.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipegenerator.DataClass.Large
import com.example.recipegenerator.DataClass.Medium
import com.example.recipegenerator.DataClass.Small
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel : ViewModel() {
    val largeList = mutableStateListOf<Large>() // 大カテゴリのリスト
    val mediumList = mutableStateListOf<Medium>() // 中カテゴリのリスト
    val smallList = mutableStateListOf<Small>() // 小カテゴリのリスト
    val selectedParentId = mutableStateOf("") // 選択されたカテゴリの親ID
    val showMoreCategory = mutableStateOf(false) // カテゴリの表示数が増えるかどうか
    var isLargeCategoryLoaded = mutableStateOf(false) // 大カテゴリが読み込まれたかどうか
    var isMediumCategoryLoaded = mutableStateOf(false) // 中カテゴリが読み込まれたかどうか
    var isSmallCategoryLoaded = mutableStateOf(false) // 小カテゴリが読み込まれたかどうか

}