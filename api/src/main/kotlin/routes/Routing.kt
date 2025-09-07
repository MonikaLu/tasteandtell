package com.example.routes

import com.example.models.Ingredient
import com.example.models.Instruction
import com.example.models.Recipe
import com.example.repositories.RecipeRepository
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.SerializationException
import java.time.LocalDateTime
import java.util.*

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

            post {
                try {
                    val request = call.receive<Recipe>()
                    val newRecipe = Recipe(
                        id = UUID.randomUUID().toString(),
                        title = request.title,
                        description = request.description,
                        coverImageUrl = request.coverImageUrl,
                        createdAt = LocalDateTime.now().toString(),
                        updatedAt = LocalDateTime.now().toString(),
                        authorId = request.authorId,
                        ingredients = request.ingredients.map {
                            Ingredient(
                                id = it.id,
                                name = it.name,
                                description = it.description,
                                amount = it.amount,
                            )
                        },
                        instructions = request.instructions.map{
                            Instruction(
                                id = it.id,
                                stepNumber = it.stepNumber,
                                description = it.description,
                                imageUrl = it.imageUrl,
                            )
                        }
                    )
                    RecipeRepository.addRecipe(newRecipe)
                    call.respond(HttpStatusCode.Created, newRecipe)
                } catch (e: IllegalStateException) {
                    println("Illegal Argument")
                     e.printStackTrace()

                    call.respond(HttpStatusCode.BadRequest)
                } catch (e: SerializationException) {
                    println("SERIALIZATIONEXCEPTION")
                     e.printStackTrace()

                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete  ("/{id}") {
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
