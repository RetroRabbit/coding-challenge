import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.4.21"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	id("org.springframework.boot") version "2.4.0"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("kapt") version kotlinVersion
}

group = "com.staffinghub.coding.challenges"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
	maven {
		url = uri("https://maven.pkg.github.com/liodali/KotlinMapster")
		credentials {
			username = "{USER_NAME}"
			password = "{TOKEN}"
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation ("com.hamza.dali:mapster-ktx:0.4.0")
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	testImplementation ("io.kotest:kotest-property:4.0.3")
	testImplementation ("org.mockito:mockito-core:3.+")
	testImplementation ("org.mockito:mockito-inline:3.11.2")
	kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
}

tasks {
	withType<KotlinCompile> {
		kotlinOptions {
			jvmTarget = "1.8"
			apiVersion = "1.4"
			languageVersion = "1.4"
			freeCompilerArgs = listOf("-Xjsr305=strict")
		}
	}
	withType<Test> {
		useJUnitPlatform()
		testLogging {
			events("passed", "skipped", "failed")
		}
	}
}
