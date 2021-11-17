plugins {
    java
    application
    id("org.springframework.boot")
    id("io.spring.dependency-management")
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

val appMainClassName by extra("com.epam.Application")

val appJvmArgs = listOf(
    "-server",
    "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005",
    "-Djava.awt.headless=true",
    "-Xms128m",
    "-XX:+UseG1GC",
    "-XX:MaxGCPauseMillis=100"
)

application {
    mainClass.set(appMainClassName)
    applicationDefaultJvmArgs = appJvmArgs
}

dependencies {
    implementation("com.uber.cadence:cadence-client:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

drill {
    agentId = "Cadence-producer"
    buildVersion = "0.1.0"
    groupId = "Cadence"
    adminHost = "localhost"
    adminPort = 8090
    logLevel = com.epam.drill.agent.runner.LogLevels.TRACE
    agentPath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\drill_agent.dll")
    runtimePath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\")
    jvmArgs = jvmArgs + "-Ddrill.http.hook.enabled=false"
    additionalParams = mapOf("isCadence" to "true", "isAsyncApp" to "true")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

jib {
    from {
        image = "openjdk:8"
    }
    to {
        image = "drill4j/cadence-producer"
        tags = setOf("0.1.0")
    }
    container {
        ports = listOf("8080", "5005")
        mainClass = appMainClassName
        jvmFlags = appJvmArgs + "-XX:-UseContainerSupport"
    }
}
