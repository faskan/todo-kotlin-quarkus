package com.faskan.todo

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.common.mapper.TypeRef
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType

@QuarkusTestResource(MockMongoDatabase::class)
@QuarkusTest
class TodoResourceIT {

    @Test
    fun shouldReturn200OK() {
        given().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .body(Todo("Find", "Find the letter F"))
            .`when`().post("/api/todos").then().statusCode(204)

        //https://github.com/quarkusio/quarkus/issues/6742
        val response = given().`when`().get("/api/todos").then().statusCode(200)
            .extract().body().asString()
        JSONAssert.assertEquals("""
            [{ "name" : "Find", "description" : "Find the letter F"}]
        """.trimIndent(), response, JSONCompareMode.LENIENT)

    }
}
