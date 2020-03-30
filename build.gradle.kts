import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.TestLoggerPlugin
import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
	id("org.springframework.boot") version "2.2.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	id ("com.adarshr.test-logger") version "2.0.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.rest-assured:rest-assured:4.2.0")
	testImplementation ( "io.rest-assured:kotlin-extensions:4.2.0")
	testImplementation ( "io.rest-assured:json-path:4.2.0")
	testImplementation ( "io.rest-assured:xml-path:4.2.0")

	testImplementation ("org.junit.jupiter:junit-jupiter-api:5.3.1")
	testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}


subprojects {

	apply {
		plugin("com.adarshr.test-logger")
	}

	plugins.withType<TestLoggerPlugin> {
		configure<TestLoggerExtension> {

			// pick a theme - mocha, standard, plain, mocha-parallel, standard-parallel or plain-parallel
			theme = ThemeType.MOCHA

			// set to false to disable detailed failure logs
			showExceptions = true

			// set to false to hide stack traces
			showStackTraces = true

			// set to true to remove any filtering applied to stack traces
			showFullStackTraces = false

			// set to false to hide exception causes
			showCauses = true

			// set threshold in milliseconds to highlight slow tests
			slowThreshold = 3000

			// displays a breakdown of passes, failures and skips along with total duration
			showSummary = true

			// set to true to see simple class names
			showSimpleNames = false

			// set to false to hide passed tests
			showPassed = true

			// set to false to hide skipped tests
			showSkipped = true

			// set to false to hide failed tests
			showFailed = true

			// enable to see standard out and error streams inline with the test results
			showStandardStreams = false

			// set to false to hide passed standard out and error streams
			showPassedStandardStreams = true

			// set to false to hide skipped standard out and error streams
			showSkippedStandardStreams = true

			// set to false to hide failed standard out and error streams
			showFailedStandardStreams = true
			
		}
	}
}
