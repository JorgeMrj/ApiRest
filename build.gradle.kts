plugins {
    id("java")
}

group = "jorgemrj"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Lombok en desarrollo
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    //reactive
    implementation("io.reactivex.rxjava3:rxjava:3.0.13")
    //jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")

    //retroFit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")

    // JDBI para la base de datos
    implementation("org.jdbi:jdbi3-core:3.49.5") // Core
    implementation("org.jdbi:jdbi3-sqlobject:3.49.5") // SQL Object para DAO

    // H2 Database
    implementation("com.h2database:h2:2.3.232")

    // Logger
    implementation("ch.qos.logback:logback-classic:1.5.13")
    implementation("org.slf4j:slf4j-api:2.0.12")

    // Cache
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.2")

    testImplementation("org.mockito:mockito-junit-jupiter:5.19.0")
    testImplementation("org.mockito:mockito-core:5.19.0")

    // Lombok en test
    testCompileOnly("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
}

tasks.test {
    useJUnitPlatform()
}