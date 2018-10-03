import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.dsl.Coroutines

group = "company.challenge"
version = "0.0.1"

val junitVersion = "5.3.1"

plugins {
    kotlin("jvm") version "1.2.71"
    application
    java
    jacoco
}


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
    "test"(Test::class) {
        useJUnitPlatform()
    }
    "build" {
        dependsOn(fatJar)
    }
}


dependencies {
    compile(kotlin("stdlib-jdk8"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testRuntime("org.junit.platform:junit-platform-console:1.2.0")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-core:2.18.3")
    testImplementation("org.mockito:mockito-junit-jupiter:2.18.3")

}

val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Implementation-Title"] = "Workflow Report"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "company.challenge.workflows.AppKt"
    }
    from(configurations.runtime.map { if (it.isDirectory) it else zipTree(it) })
    with(tasks["jar"] as CopySpec)
}