package com.example.services

import com.example.models.Recipe
import com.example.repositories.RecipeRepository
import java.time.LocalDateTime
import java.util.UUID

class RecipeService(private val repository: RecipeRepository) {

    fun getAllRecipes(): List<Recipe> = repository.allRecipes()

    fun getRecipeById(id: String?): Recipe? {
        if (id.isNullOrBlank()) {
            throw IllegalArgumentException("The recipe id cannot be null or empty")
        }
        return repository.recipeById(id)
    }

    fun createRecipe(recipe: Recipe): Recipe {
        val now = LocalDateTime.now().toString()
        val newRecipe = recipe.copy(
            id = UUID.randomUUID().toString(),
            createdAt = now,
            updatedAt = now,
        )
        repository.addRecipe(recipe)
        return newRecipe
    }

    fun deleteRecipe(id: String?): Boolean {
        if (id.isNullOrBlank()) {
            throw IllegalArgumentException("The recipe id cannot be null or empty")
        }
        return repository.deleteRecipe(id)
    }
}