package com.example


import com.example.routes.recipeRoutes
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
    configureRouting()
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }

}
