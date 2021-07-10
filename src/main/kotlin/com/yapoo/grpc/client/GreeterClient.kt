package com.yapoo.grpc.client

import com.yapoo.grpc.greeter.GreeterGrpcKt
import com.yapoo.grpc.greeter.HelloRequest
import io.grpc.ManagedChannelBuilder
import java.util.concurrent.TimeUnit

suspend fun main() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    try {
        val stub = GreeterGrpcKt.GreeterCoroutineStub(channel)

        val name = "Kotlin"
        val request = HelloRequest.newBuilder().setName(name).build()
        val response = stub.hello(request)
        println(response)
    } finally {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}
