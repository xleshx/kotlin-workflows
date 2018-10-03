import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    kotlin("jvm") version "1.2.71"
    application
    java
    jacoco
}

group = "company.challenge"
version = "0.0.1"

val junitVersion = "5.3.1"

application {
    group = "company.challenge"
    version = "0.0.1"
    applicationName = "workflows-report"
    mainClassName = "company.challenge.workflows.AppKt"
}

repositories {
    jcenter()
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

tasks {
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }
}
dependencies {
    implementation(kotlin("stdlib", "1.2.71"))



    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
//    testRuntime("org.junit.platform:junit-platform-console:1.2.0")

//    testRuntime("org.junit.platform:junit-platform-launcher:1.3.1")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-core:2.18.3")
    testImplementation("org.mockito:mockito-junit-jupiter:2.18.3")

}