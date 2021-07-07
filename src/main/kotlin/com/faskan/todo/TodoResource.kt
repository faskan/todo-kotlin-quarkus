package com.faskan.todo

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path

@Path("/api/todos")
class TodoResource(val todoRepository: TodoRepository) {

    @GET
    fun getAllTodos() : List<Todo>  {
        return todoRepository.findAll().list()
    }

    @POST
    fun saveTodo(todo: Todo) {
        todoRepository.persist(todo)
    }
}
