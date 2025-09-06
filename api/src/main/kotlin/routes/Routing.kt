package com.example.routes

import com.example.repositories.RecipeRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.recipeRoutes() {
        route("/recipes") {
            get{
                val recipes =       RecipeRepository.allRecipes()
                call.respond(
                    recipes
                )
            }
            get("/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val recipe = RecipeRepository.recipeById(id)
                if (recipe == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(recipe)
            }
        }
}
