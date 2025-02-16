package com.example.recipegenerator

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//APIから取得したデータを格納するためのデータクラス
data class ApiData(
    // レシピのタイトル
    @SerializedName("recipeTitle")
    val Title: String,
    // レシピのURL
    @SerializedName("recipeUrl")
    val url: String,
    // レシピの画像URL
    @SerializedName("foodImageUrl")
    val foodimage: String,
    // レシピの費用
    @SerializedName("recipeCost")
    val cost: String,
    // レシピの調理時間
    @SerializedName("recipeIndication")
    val indication: String,
    // 投稿者のニックネーム
    val nickname: String,
    // レシピの説明コメント
    val recipeDescription: String,
    // レシピの材料(リスト)
    val recipeMaterial: List<String>,
)

//ResultDataのプロパティにお気に入り登録されたかを表す変数を追加したデータクラス(UI表示に関してはこちらを操作する)
data class Result(
    val apiData: ApiData,
    var isFavorite: Boolean = false
)

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val Title: String,
    val url: String,
    val Stringmage: String,
    val cost: String,
    val indication: String,
    val nickname: String,
    val recipeDescription: String,
    val recipeMaterial: List<String>,
    val registerDate: String,
    val makeCount: Int = 0
)

//ResultUIのリストを格納するためのデータクラス
data class ResultResponse(
    @SerializedName("result")
    val result: List<ApiData>
)