package com.example.repositories

import com.example.db.suspendTransaction
import com.example.entities.IngredientDAO
import com.example.mappers.toIngredient
import com.example.models.Ingredient

object IngredientRepository {
   suspend fun allIngredients(): List<Ingredient> = suspendTransaction { IngredientDAO.all().map{
       it.toIngredient()
   }
   }
}