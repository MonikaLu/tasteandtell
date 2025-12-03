package com.example.entities
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

const val MAX_VARCHAR_LENGTH = 255

object IngredientTable : IntIdTable("ingredient") {
    val name = varchar("name", MAX_VARCHAR_LENGTH)
    val description = varchar("description", MAX_VARCHAR_LENGTH)
}

class IngredientDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<IngredientDAO>(IngredientTable)

    var name by IngredientTable.name
    var description by IngredientTable.description
}