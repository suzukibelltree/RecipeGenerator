package com.example.recipegenerator.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.recipegenerator.DataClass.Large
import com.example.recipegenerator.DataClass.Medium
import com.example.recipegenerator.DataClass.RankingData
import com.example.recipegenerator.DataClass.Small

class CategoryViewModel : ViewModel() {
    val largeList = mutableStateListOf<Large>() // 大カテゴリのリスト
    var largeRankingList = mutableStateListOf<RankingData>() // 大カテゴリのランキングリスト
    val mediumList = mutableStateListOf<Medium>() // 中カテゴリのリスト
    val mediumRankingList = mutableStateListOf<RankingData>() // 中カテゴリのランキングリスト
    val smallList = mutableStateListOf<Small>() // 小カテゴリのリスト
    val smallRankingList = mutableStateListOf<RankingData>() // 小カテゴリのランキングリスト
    val selectedCategoryName = mutableStateOf("") // 選択されたカテゴリ名
    val selectedLargeId = mutableStateOf("") // 選択された大カテゴリのID
    val selectedMediumId = mutableStateOf("") // 選択された中カテゴリのID
    val connectedId = mutableStateOf("") // 選択されたカテゴリの祖父-親ID
    val showMoreCategory = mutableStateOf(false) // カテゴリの表示数が増えるかどうか
    var isLargeCategoryLoaded = mutableStateOf(false) // 大カテゴリが読み込まれたかどうか
    var isMediumCategoryLoaded = mutableStateOf(false) // 中カテゴリが読み込まれたかどうか
    var isSmallCategoryLoaded = mutableStateOf(false) // 小カテゴリが読み込まれたかどうか
    var searchCategoryText = mutableStateOf("") // 検索されたカテゴリ名
    var matchLargeCategoryList = mutableStateListOf<Large>() // 検索された大カテゴリのリスト
    var matchMediumCategoryList = mutableStateListOf<Medium>() // 検索された中カテゴリのリスト
    var matchSmallCategoryList = mutableStateListOf<Small>() // 検索された小カテゴリのリスト

    //合致したカテゴリを返す関数
    fun getMatchCategoryList() {
        val searchCategory = searchCategoryText.value
        matchLargeCategoryList.clear()
        matchLargeCategoryList.addAll(largeList.filter { it.categoryName.contains(searchCategory) })

        matchMediumCategoryList.clear()
        matchMediumCategoryList.addAll(mediumList.filter { it.categoryName.contains(searchCategory) })

        matchSmallCategoryList.clear()
        matchSmallCategoryList.addAll(smallList.filter { it.categoryName.contains(searchCategory) })
    }

    //smallカテゴリのlargeカテゴリIDを取得する関数
    fun getGrandParentCategoryId(small: Small) {
        for(medium in mediumList) {
            if(medium.categoryId == small.parentCategoryId) {
                for(large in largeList) {
                    if(large.categoryId == medium.parentCategoryId) {
                        selectedLargeId.value = large.categoryId
                    }
                }
            }
        }
    }


}