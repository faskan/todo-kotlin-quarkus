package com.faskan.todo

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.MongoDBContainer

class MockMongoDatabase: QuarkusTestResourceLifecycleManager {

    private val mongoDbContainer: MongoDBContainer = MongoDBContainer("mongo:4.2").withExposedPorts(27017)

    override fun start(): MutableMap<String, String> {
        mongoDbContainer.start()
        return mutableMapOf(Pair("quarkus.mongodb.connection-string",mongoDbContainer.replicaSetUrl))
    }

    override fun stop() {
        mongoDbContainer.stop()
    }
}
