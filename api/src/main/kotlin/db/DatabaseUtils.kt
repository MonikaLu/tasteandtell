package com.example.db

import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

private const val databaseUrl = "jdbc:postgresql://localhost:5432/baobook"
private const val user = "bao"
private const val password = "book"

fun configureDatabases() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/baobook",
        user = "bao",
        password = "book"
    )
    val flyway = Flyway.configure().dataSource(databaseUrl, user, password).load()
    flyway.migrate()
}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)