allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://dl.bintray.com/drill/drill4j")
        maven(url = "https://drill4j.jfrog.io/artifactory/drill")
    }
}
