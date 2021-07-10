package com.yapoo.grpc.server

import com.yapoo.grpc.greeter.GreeterGrpcKt
import com.yapoo.grpc.greeter.HelloRequest
import com.yapoo.grpc.greeter.HelloResponse

class GreeterService : GreeterGrpcKt.GreeterCoroutineImplBase() {

    override suspend fun hello(
        request: HelloRequest
    ): HelloResponse = HelloResponse.newBuilder()
        .setText("Hello ${request.name}")
        .build()
}
