plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.brigid.idp"
version = "0.0.1-SNAPSHOT"
description = "IdP Amaterasu project"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter:3.5.7")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.7")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:3.5.7")
	implementation("org.springframework.boot:spring-boot-starter-web:3.5.7")
    implementation("org.springframework.security:spring-security-crypto:7.0.0")
    implementation("redis.clients:jedis:7.1.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.20.1")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.20.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.jsonwebtoken:jjwt-api:0.13.0")

    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")
	compileOnly("org.projectlombok:lombok:1.18.42")
	runtimeOnly("org.postgresql:postgresql:42.7.8")

	annotationProcessor("org.projectlombok:lombok:1.18.42")

	testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.7")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.2.21")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.1.0-M1")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
