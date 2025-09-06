package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Instruction(
    val id: String,
    val stepNumber: Int,
    val description: String,
    val imageUrl: String? = null,
)