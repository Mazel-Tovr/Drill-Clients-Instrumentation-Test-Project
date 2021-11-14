rootProject.name = "Drill-Clients-Instrumentation-Test-Project"

pluginManagement {
    plugins {
        id("org.springframework.boot") version "2.5.2"
        id("io.spring.dependency-management") version "1.0.11.RELEASE"
        id("com.epam.drill.agent.runner.autotest") version "0.2.2"
        id("com.google.cloud.tools.jib") version "3.1.4"
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        maven(url = "https://repo.spring.io/milestone")
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://oss.jfrog.org/artifactory/oss-release-local")
        maven(url = "https://drill4j.jfrog.io/artifactory/drill")
    }
}

include(":cadence")
include(":cadence:cadence-consumer")
include(":cadence:cadence-producer")
include(":kafka")
include(":kafka:kafka-consumer")
include(":kafka:kafka-producer")
include(":feign")
include(":feign:consumer")
include(":feign:producer")
