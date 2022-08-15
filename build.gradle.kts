import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

    implementation("io.ktor:ktor-server-core:2.0.3")
    implementation("io.ktor:ktor-server-netty:2.0.3")
    implementation("ch.qos.logback:logback-classic:1.2.11")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}