plugins {
    java
    id("com.epam.drill.agent.runner.app") version "0.2.0"
}

group = "com.emap"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/drill/drill4j")
    maven(url = "https://drill4j.jfrog.io/artifactory/drill")
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:2.7.0")
    implementation("org.apache.commons:commons-lang3:3.0")
    runtimeOnly("log4j:log4j:1.2.17")
    runtimeOnly("org.slf4j:slf4j-log4j12:1.7.6")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

drill {
    agentId = "Kafka-consumer"
    groupId = "Kafka"
    buildVersion = "0.1.0"
    adminHost = "localhost"
    adminPort = 8090
    logLevel = com.epam.drill.agent.runner.LogLevels.DEBUG
    agentPath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\drill_agent.dll")
    runtimePath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\")
    additionalParams = mapOf("isKafka" to "true")
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
