package com.example.recipegenerator.DataClass

//レシピのランキングを扱うデータクラス
data class RankingRecipeData(
    val result : List<RankingData>
)

data class RankingData(
    val foodImageUrl: String,
    val mediumImageUrl: String,
    val nickname: String,
    val pickup: Int,
    val rank: String,
    val recipeCost: String,
    val recipeDescription: String,
    val recipeId: Int,
    val recipeIndication: String,
    val recipeMaterial: List<String>,
    val recipePublishday: String,
    val recipeTitle: String,
    val recipeUrl: String,
    val shop: Int,
    val smallImageUrl: String
)