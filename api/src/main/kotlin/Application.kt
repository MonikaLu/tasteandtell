package com.example


import com.example.controllers.recipeRoutes
import com.example.db.configureDatabases
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
    routing {
        staticResources("static", "static")
        recipeRoutes()
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
