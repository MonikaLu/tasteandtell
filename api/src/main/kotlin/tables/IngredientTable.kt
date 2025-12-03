package com.example.tables

import com.example.models.Ingredient
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.util.*

const val MAX_VARCHAR_LENGTH = 255

object IngredientTable : UUIDTable("ingredients") {
    val name = varchar("name", MAX_VARCHAR_LENGTH)
    val description = varchar("description", MAX_VARCHAR_LENGTH)
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
}

class IngredientDAO(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<IngredientDAO>(IngredientTable)

    var name by IngredientTable.name
    var description by IngredientTable.description
    var createdAt by IngredientTable.createdAt

    fun toIngredient() = Ingredient(
        id = this.id.toString(),
        name = this.name,
        description = this.description,
        createdAt = this.createdAt.toString()
    )
}

