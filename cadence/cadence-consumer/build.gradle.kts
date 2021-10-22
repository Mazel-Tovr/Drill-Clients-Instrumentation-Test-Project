plugins {
    java
    id("com.epam.drill.agent.runner.app") version "0.2.2"
}

group = "com.epam"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://dl.bintray.com/drill/drill4j")
    maven(url = "https://drill4j.jfrog.io/artifactory/drill")
}

dependencies {
    implementation("com.uber.cadence:cadence-client:3.0.0")
    implementation("com.epam.drill:java-agent:0.8.0-10")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

drill {
    agentId = "Cadence-consumer"
    buildVersion = "0.1.0"
    groupId = "Cadence"
    adminHost = "localhost"
    adminPort = 8090
    logLevel = com.epam.drill.agent.runner.LogLevels.TRACE
    jvmArgs = setOf("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005")
    agentPath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\drill_agent.dll")
    runtimePath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\")
    additionalParams = mapOf("isAsyncApp" to "true")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
//rootProject.name = "com.epam.cadence-consumer"
