import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val ktorNettyVersion: String by project
val logbackVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.6.21"
}

group = "com.yasserakbbach.borutoserver"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorNettyVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}