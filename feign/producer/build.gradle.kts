plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.cloud.tools.jib")
    id("com.epam.drill.agent.runner.app") version "0.2.0"
    application
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

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.squareup.okhttp3:okhttp:3.12.0")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

extra["springCloudVersion"] = "2020.0.4"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

drill {
    agentId = "feign-producer"
    groupId = "feign"
    buildVersion = "0.1.0"
    adminHost = "localhost"
    adminPort = 8090
    logLevel = com.epam.drill.agent.runner.LogLevels.TRACE
   // logFile = rootProject.buildDir.resolve("drill-log.log")
    agentPath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\drill_agent.dll")
    runtimePath = File("D:\\Github\\Drill\\java-agent\\build\\install\\mingwX64\\")
    jvmArgs = jvmArgs + "-Ddrill.http.hook.enabled=false"
    additionalParams = mapOf("isAsyncApp" to "true")
}

jib {
    from {
        image = "openjdk:8"
    }
    to {
        image = "drill4j/feign-produce"
        tags = setOf("0.1.0")
    }
    container {
        ports = listOf("8080", "5005")
        mainClass = appMainClassName
        jvmFlags = appJvmArgs + "-XX:-UseContainerSupport"
    }
}
