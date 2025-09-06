package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: String? = null,
    val title: String,
    val description: String? = null,
    val coverImageUrl: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val authorId: String,
    val ingredients: List<Ingredient>,
    val instructions: List<Instruction>
    )
