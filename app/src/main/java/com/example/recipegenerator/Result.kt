package com.example.recipegenerator

import com.google.gson.annotations.SerializedName

//APIから取得したデータを格納するためのデータクラス
data class ApiData(
    @SerializedName("recipeTitle")
    val Title: String,
    @SerializedName("foodImageUrl")
    val foodimage: String,
    @SerializedName("recipeCost")
    val cost: String,
    @SerializedName("recipeIndication")
    val indication: String
)

//ResultDataのプロパティにお気に入り登録されたかを表す変数を追加したデータクラス(UI表示に関してはこちらを操作する)
data class Result(
    val apiData: ApiData,
    val isFavorite: Boolean = false
)

//ResultUIのリストを格納するためのデータクラス
data class ResultResponse(
    @SerializedName("result")
    val result: List<ApiData>
)