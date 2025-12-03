package com.example.services

import com.example.models.Ingredient
import com.example.repositories.IngredientRepository

class IngredientService(private val repository: IngredientRepository) {
    suspend fun getAllIngredients(): List<Ingredient> = repository.getAllIngredients()
}
