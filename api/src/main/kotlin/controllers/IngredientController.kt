package com.example.controllers

import com.example.services.IngredientService
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ingredientRoutes(ingredientService: IngredientService) {
    route("/ingredients") {
        get {
            val ingredients = ingredientService.getAllIngredients()
            call.respond(ingredients)
        }
    }
}