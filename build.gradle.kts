import java.time.Year

plugins {
    kotlin("multiplatform") version "2.4.10"
}

repositories { mavenCentral() }

val generatedSite = layout.buildDirectory.dir("generated/site")

kotlin {
    jvm()
    js {
        browser {
            commonWebpackConfig { outputFileName = "weldcore.js" }
        }
        binaries.executable()
    }
    sourceSets {
        commonTest.dependencies { implementation(kotlin("test")) }
        jsMain { resources.srcDir(generatedSite) }
    }
}

val jvmTarget = kotlin.targets.getByName("jvm") as org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget
val jvmMain = jvmTarget.compilations.getByName("main")

val generateSite by tasks.registering(JavaExec::class) {
    group = "build"
    description = "Generate static HTML from the Kotlin site content data classes."
    dependsOn("jvmMainClasses")
    classpath(jvmMain.output.allOutputs, jvmMain.runtimeDependencyFiles)
    mainClass.set("com.weldcore.GenerateSiteKt")
    args(generatedSite.get().asFile.absolutePath)
    inputs.files(fileTree("src/commonMain"), fileTree("src/jvmMain"))
    inputs.property("year", Year.now().value)
    outputs.dir(generatedSite)
}

tasks.named("jsProcessResources") { dependsOn(generateSite) }
