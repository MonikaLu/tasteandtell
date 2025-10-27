package com.example.routes

import com.example.models.Recipe
import com.example.repositories.RecipeRepository
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime
import java.util.*
import kotlinx.serialization.SerializationException

fun Route.recipeRoutes() {
    route("/recipes") {
        get {
            val recipes = RecipeRepository.allRecipes()
            call.respond(recipes)
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

        post {
            try {
                val request = call.receive<Recipe>()
                val newRecipe =
                    request.copy(
                        id = UUID.randomUUID().toString(),
                        createdAt = LocalDateTime.now().toString(),
                        updatedAt = LocalDateTime.now().toString(),
                    )
                RecipeRepository.addRecipe(newRecipe)
                call.respond(HttpStatusCode.Created, newRecipe)
            } catch (e: IllegalStateException) {
                println("Illegal Argument")
                e.printStackTrace()
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
            if (RecipeRepository.removeRecipe(recipeId)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
