package com.faskan.todo

import io.quarkus.mongodb.panache.MongoEntity
import io.quarkus.mongodb.panache.kotlin.PanacheMongoEntity

@MongoEntity
data class Todo(val name: String, val description: String): PanacheMongoEntity()
