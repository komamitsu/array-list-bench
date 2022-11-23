plugins {
    java
    application
    id("me.champeau.jmh") version "0.6.7"
}

group = "org.komamitsu"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.msgpack:jackson-dataformat-msgpack:0.9.1")
    implementation("org.komamitsu:fluency-core:2.7.0")
    implementation("org.komamitsu:fluency-fluentd:2.7.0")
//    implementation("org.komamitsu:fluency-fluentd-ext:2.7.0")
    jmhAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.36")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

application {
    mainClass.set("org.komamitsu.unixdomainsockettest.Main")
}