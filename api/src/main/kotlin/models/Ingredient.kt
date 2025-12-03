package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val id: String,
    val name: String,
    val description: String? = null,
    val createdAt: String,
)