
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
    //compile("org.springframework:spring-di.context-support")
    //compile("log4j:log4j:1.2.16")

    testCompile("junit", "junit", "4.12")
    testCompile("org.springframework:spring-test:5.1.9.RELEASE")
    testCompile("org.mockito:mockito-core:1.10.19")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}