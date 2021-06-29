import org.jetbrains.kotlin.builtins.StandardNames.FqNames.annotation

//para conseguir desesrializar o obj
buildscript {
    dependencies {
        classpath ("org.jetbrains.kotlin:kotlin-noarg:1.5.20")
    }
}


plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "2.0.1"

    //plugin para deixar as classes abertas
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32"

    //plugin para jpa

    id("org.jetbrains.kotlin.plugin.jpa") version "1.4.32"

}

//definir o caminhos das classes que serãoabertas
allOpen{
    annotation("io.micronault.http.annotation.Controller")
}

version = "0.1"
group = "br.com.zup"

val kotlinVersion= project.properties["kotlinVersion"]
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
    kapt("io.micronaut.data:micronaut-data-processor")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.beanvalidation:micronaut-hibernate-validator")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut.xml:micronaut-jackson-xml")
    annotationProcessor("io.micronaut:micronaut-validation")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.3.0")

    //jackson
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")


    //jpa
    implementation("mysql:mysql-connector-java:8.0.15")
    compile("io.micronaut.sql:micronaut-jdbc-hikari")



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