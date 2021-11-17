plugins {
    java
    id("com.epam.drill.agent.runner.app") version "0.2.2"
    id("com.google.cloud.tools.jib")
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
    jvmArgs = jvmArgs + "-Ddrill.http.hook.enabled=false"
    agentPath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\drill_agent.dll")
    runtimePath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\")
    additionalParams = mapOf("isCadence" to "true", "isAsyncApp" to "true")
}

val appMainClassName by extra("com.epam.Main")

val appJvmArgs = listOf(
    "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5007",
    "-Xms128m",
    "-XX:+UseG1GC",
    "-XX:MaxGCPauseMillis=100"
)


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

jib {
    from {
        image = "openjdk:8"
    }
    to {
        image = "drill4j/cadence-consumer"
        tags = setOf("0.1.0")
    }
    container {
        ports = listOf("5007")
        mainClass = appMainClassName
        jvmFlags = appJvmArgs + "-XX:-UseContainerSupport"
    }
}
