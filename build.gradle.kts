import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    id("com.google.protobuf") version "0.8.15"
    id("idea")
}

group = "com.yapoo.grpc"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")

    implementation("io.grpc:grpc-kotlin-stub:1.1.0")
    implementation("io.grpc:grpc-netty:1.39.0")
    implementation("io.grpc:grpc-protobuf:1.39.0")
    implementation("com.google.protobuf:protobuf-java:3.17.3")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

protobuf {
    protoc { artifact = "com.google.protobuf:protoc:3.17.3" }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.39.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.1.0:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}
