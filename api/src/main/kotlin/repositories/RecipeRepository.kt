package com.example.repositories

import com.example.models.Ingredient
import com.example.models.Instruction
import com.example.models.Recipe

object RecipeRepository {
    private val recipes = mutableListOf(
        Recipe(
            "1", "Grilled Cheese", "Buy a bread",
            createdAt = "2024-1-2",
            updatedAt = "2024-1-2",
            authorId = "1",
            coverImageUrl = "",
            ingredients = listOf(
                Ingredient(
                "1", "Bread", "testing", 1
            )
            ),
            instructions = listOf(
                Instruction(
                "1", 1, "test", "d"
            )
            )
        ),
        Recipe(
            "2", "Caesar Salad", "Buy a bread",
            createdAt = "2024-1-2",
            updatedAt = "2024-1-2",
            authorId = "1",
            coverImageUrl = "",
            ingredients = listOf(
                Ingredient(
                    "1", "Bread", "testing", 1
                )
            ),
            instructions = listOf(
                Instruction(
                    "1", 2, "test", "d"
                )
            )
        ),
    )

    fun allRecipes(): List<Recipe> = recipes

    fun recipeById(id: String?): Recipe? = recipes.find{ it.id == id }

    fun addRecipe(recipe: Recipe) {
        if (recipeById(recipe.id) != null) {
            throw IllegalStateException("Recipe with id ${recipe.id} already exists")
        }
        recipes.add(recipe)
    }

    fun removeRecipe(recipeId: String): Boolean {
        return recipes.removeIf {
            it.id == recipeId
        }
    }
}