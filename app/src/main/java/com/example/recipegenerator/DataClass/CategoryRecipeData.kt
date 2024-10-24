package com.example.recipegenerator.DataClass

//カテゴリ一覧を扱うデータクラス
data class Result(
    val result: ResultData
)

data class ResultData(
    val large: List<Large>,
    val medium: List<Medium>,
    val small: List<Small>
)

data class Large(
    val categoryId : String,
    val categoryName : String,
    val categoryUrl : String,
)

data class Medium(
    val categoryId : String,
    val categoryName : String,
    val categoryUrl : String,
    val parentCategoryId : String,
)

data class Small(
    val categoryId : String,
    val categoryName : String,
    val categoryUrl : String,
    val parentCategoryId : String,
)