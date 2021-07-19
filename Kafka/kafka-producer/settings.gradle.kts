rootProject.name = "kafka-producer"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://oss.jfrog.org/artifactory/oss-release-local")
        maven(url = "https://drill4j.jfrog.io/artifactory/drill")
    }
}
