package com.faskan.todo

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType

@QuarkusTest
class TodoResourceIT {

    @Container
    private val mongoDbContainer: MongoDBContainer = MongoDBContainer("mongo:4.2").withExposedPorts(27017)

    init {
        mongoDbContainer.start()
        System.setProperty("quarkus.mongodb.connection-string", mongoDbContainer.replicaSetUrl)
    }
    @Test
    fun shouldReturn200OK() {
        given().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .body(Todo("1", "Find", "Find the letter F"))
            .`when`().post("/api/todos").then().statusCode(200)

        val todo = given().`when`().get("/api/todos").then().statusCode(200).extract().`as`(Todo::class.java)
        Assertions.assertNotNull(todo.id)
        Assertions.assertEquals("Find", todo.name)
        Assertions.assertEquals("Find the letter F", todo.description)
    }
}
