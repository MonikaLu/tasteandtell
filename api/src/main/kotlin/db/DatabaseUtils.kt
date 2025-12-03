package com.example.db

import io.ktor.server.application.Application
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun Application.configureDatabases() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/baobook",
        user = "bao",
        password = "book"
    )
}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
newSuspendedTransaction(Dispatchers.IO, statement = block)