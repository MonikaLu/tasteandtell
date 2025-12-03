package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class RecipeIngredient(
    val recipeId: String,
    val ingredientId: String,
    val amount: Double,
    val measurement: Measurement
)
