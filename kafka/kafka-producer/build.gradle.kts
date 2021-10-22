plugins {
    java
    war
    id("org.springframework.boot")
    id("io.spring.dependency-management")
//    id("com.epam.drill.agent.runner.app") version "0.2.0"
    id("com.epam.drill.agent.runner.autotest") version "0.2.2"
}

group = "com.epam"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://dl.bintray.com/drill/drill4j")
    maven(url = "https://drill4j.jfrog.io/artifactory/drill")
}
val jUnitVersion = "5.6.2"

dependencies {
    implementation("org.apache.kafka:kafka-clients:1.0.0")
    implementation("org.apache.commons:commons-lang3:3.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")

}

//drill {
//    agentId = "Kafka-producer"
//    buildVersion = "0.1.0"
//    groupId = "Kafka"
//    adminHost = "localhost"
//    adminPort = 8090
//    logLevel = com.epam.drill.agent.runner.LogLevels.DEBUG
//    agentPath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\drill_agent.dll")
//    runtimePath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\")
//    additionalParams = mapOf("isKafka" to "true")
//}

//FOR TESTS
drill {
    adminHost = "localhost"
    agentId = "Kafka-consumer"
    adminPort = 8090
    logLevel = com.epam.drill.agent.runner.LogLevels.DEBUG
    agentPath = File("D:\\GitHub\\Drill\\autotest-agent\\build\\install\\mingwX64\\autoTestAgent.dll")
    runtimePath = File("D:\\GitHub\\Drill\\autotest-agent\\build\\install\\mingwX64")
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
