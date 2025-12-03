package com.example.mappers

import com.example.entities.IngredientDAO
import com.example.entities.IngredientTable
import com.example.models.Ingredient

fun IngredientDAO.toIngredient() = Ingredient(
    id = this.id.toString(),
    name = this.name,
    description = this.description,
)
