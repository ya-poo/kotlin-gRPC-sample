package com.yapoo.grpc.server

import io.grpc.ServerBuilder

fun main() {
    val server = ServerBuilder
        .forPort(50051)
        .addService(GreeterService())
        .build()

    server.start()
    server.awaitTermination()
}
