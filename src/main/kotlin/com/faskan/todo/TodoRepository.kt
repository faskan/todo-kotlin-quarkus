package com.faskan.todo

import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TodoRepository : PanacheMongoRepository<Todo> {
}
