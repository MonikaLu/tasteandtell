package com.example


import com.example.controllers.ingredientRoutes
import com.example.db.configureDatabases
import com.example.repositories.IngredientRepository
import com.example.services.IngredientService
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.configureRouting() {
    val ingredientRepository = IngredientRepository
    val ingredientService = IngredientService(ingredientRepository)

    routing {
        staticResources("static", "static")
//        recipeRoutes()
        ingredientRoutes(ingredientService)
    }
}


fun Application.module() {
    configureSerialization()
    configureRouting()
    configureDatabases()
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }

}
