plugins {
    id("application")
    id("java")
    id("org.openjfx.javafxplugin") version "0.0.13"
}

application {
    mainClass.set("agh.ics.oop.World")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.base", "javafx.fxml","javafx.graphics","javafx.media","javafx.swing", "javafx.web")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.3")
}

tasks.test {
    useJUnitPlatform()
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = JavaVersion.VERSION_17.toString()
    targetCompatibility = JavaVersion.VERSION_17.toString()
}
