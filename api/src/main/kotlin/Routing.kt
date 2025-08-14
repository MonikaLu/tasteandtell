package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        staticResources("/content", "content")
        get("/") {
            call.respondText("Taste and Tell")
        }

        get("/test1") {
            val text = "<h1>Taste and Tell!</h1>"
            val contentType = ContentType.parse("text/html")
            call.respondText(text, contentType)
        }
    }
}
