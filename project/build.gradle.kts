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
}

tasks.test {
    useJUnitPlatform()
}