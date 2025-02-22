package com.example.recipegenerator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val url: String,
    val cost: String,
    val indication: String,
    val nickname: String,
    val recipeDescription: String,
    val recipeMaterial: List<String>,
    val registerDate: String,
    val makeCount: Int = 0
)