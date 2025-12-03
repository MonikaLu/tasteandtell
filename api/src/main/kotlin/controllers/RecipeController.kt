package com.example.controllers

import com.example.models.Recipe
import com.example.services.RecipeService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.SerializationException
import org.slf4j.LoggerFactory
import java.lang.IllegalArgumentException

private val logger = LoggerFactory.getLogger("RecipeController")

fun Route.recipeRoutes(recipeService: RecipeService) {
    route("/recipes") {
        get {
            val recipes = recipeService.getAllRecipes()
            call.respond(recipes)
        }
        get("/{id}") {
            try {
                val id = call.parameters["id"]
                val recipe = recipeService.getRecipeById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
                call.respond(recipe)
            } catch (e: IllegalArgumentException) {
                logger.warn("Invalid recipe ID: ${e.message}")
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
            }
        }

        post {
            try {
                val request = call.receive<Recipe>()
                val newRecipe = recipeService.createRecipe(request)
                call.respond(HttpStatusCode.Created, newRecipe)
            } catch (e: IllegalStateException) {
                logger.warn("Recipe validation failed: ${e.message}")
                call.respond(HttpStatusCode.BadRequest)
            } catch (e: SerializationException) {
                println("SERIALIZATION EXCEPTION")
                e.printStackTrace()

                call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete("/{id}") {
            val recipeId = call.parameters["id"]
            if (recipeId == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            if (recipeService.deleteRecipe(recipeId)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
