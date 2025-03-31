import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "2.1.0"
    id("com.gradleup.shadow") version "8.3.5"
}

group = "space.hugster"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()

    listOf(
        "papermc-repo" to "https://repo.papermc.io/repository/maven-public/",
        "jitpack.io" to "https://jitpack.io",
    ).forEach { (name, url) ->
        maven {
            this.name = name
            this.url = uri(url)
        }
    }
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    compileOnly("io.github.revxrsal:lamp.common:4.0.0-beta.19")
    compileOnly("io.github.revxrsal:lamp.bukkit:4.0.0-beta.19")

    compileOnly("com.github.NEZNAMY:TAB-API:5.0.7")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinJvmCompile> {
    compilerOptions {
        javaParameters = true
    }
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("")

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

kotlin {
    jvmToolchain(21)
}