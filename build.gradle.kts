plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0") // Jackson databind
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.0") // Jackson Kotlin module
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.0")


    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.0")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}

tasks.test {
    useJUnitPlatform()
}