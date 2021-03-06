plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "1.5.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32" //kotlin procura anotações deixa as classes e métodos abertos para herança e sobresctira
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.20"
}


version = "0.1"
group = "br.com.zup"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("br.com.zup.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    annotationProcessor("io.micronaut:micronaut-validation")
    implementation("io.micronaut.beanvalidation:micronaut-hibernate-validator")
    implementation("io.micronaut.xml:micronaut-jackson-xml")

    //JPA
    //implementation("io.micronaut.sql:micronaut-jdbc-tomcat")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    //////implementation("io.micronaut.data:micronaut-data-jdbc")
    //implementation("org.postgresql:42.2.18")
    implementation("org.mariadb.jdbc:mariadb-java-client")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    testAnnotationProcessor("io.micronaut:micronaut-inject-java")
    testImplementation("com.h2database:h2")
    testImplementation("org.mockito:mockito-core:3.8.0")

    //implementation("io.micronaut.test:micronaut-test-junit:5.3.2")
    //implementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")

   annotationProcessor("javax.persistence:javax.persistence-api:2.2")


}


application {
    mainClass.set("br.com.zup.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }


}
