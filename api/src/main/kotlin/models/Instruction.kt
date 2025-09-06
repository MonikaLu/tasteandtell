package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Instruction(
    val stepNumber: Int,
    val description: String,
    val imageUrl: String?,
)