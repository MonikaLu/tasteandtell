package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val coverImageUrl: String?,
    val createdAt: String,
    val updatedAt: String?,
    val authorId: String,
val ingredients: List<Ingredient>,
    val instructions: List<Instruction>
    )
