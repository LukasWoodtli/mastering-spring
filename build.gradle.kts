
plugins {
    java
    idea
}


group = "org.woodtli"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile("org.springframework:spring-core:5.1.9.RELEASE")
    compile("org.springframework:spring-context:5.1.9.RELEASE")
    compile("org.springframework:spring-beans:5.1.9.RELEASE")

    compile("log4j:log4j:1.2.16")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testCompile("org.junit.jupiter:junit-jupiter-params:5.2.0")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testCompile("org.mockito:mockito-core:2.+")
    testCompile("org.mockito:mockito-junit-jupiter:2.18.3")
    testCompile("org.hamcrest:hamcrest-library:2.1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
}
