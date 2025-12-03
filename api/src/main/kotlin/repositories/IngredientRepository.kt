package com.example.repositories

import com.example.db.suspendTransaction
import com.example.models.Ingredient
import com.example.tables.IngredientDAO

object IngredientRepository {
    suspend fun getAllIngredients(): List<Ingredient> = suspendTransaction {
        IngredientDAO.all().map {
            it.toIngredient()
        }
    }
}