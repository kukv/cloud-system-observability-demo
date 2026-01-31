plugins {
    java
    jacoco
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.springdoc.openapi)
    alias(libs.plugins.jig)
    alias(libs.plugins.spotless)
}

group = "jp.kukv"
version = "0.0.1"

dependencies {
    implementation(platform(libs.opentelemetry.bom))
    implementation(platform(libs.opentelemetry.instrumentation.bom))

    implementation(libs.bundles.spring.boot.starter.api)
    implementation(libs.bundles.spring.boot.starter.mybatis)

    implementation(libs.bundles.spring.boot.starter.opentelemetry)

    developmentOnly(libs.spring.boot.devtools)
    annotationProcessor(libs.spring.boot.configuration.processor)

    implementation(libs.commons.validator)

    testImplementation(libs.bundles.spring.boot.starter.test)
}

sourceSets {
    main {
        resources {
            srcDirs("src/main/java", "src/main/resources")
        }
    }
    test {
        resources {
            srcDirs("src/test/java")
            exclude("**/*.java")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
    }
}

spotless {
    java {
        target("src/**/*.java")
        googleJavaFormat()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

tasks {
    classes {
        mustRunAfter(clean)
    }

    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    test {
        useJUnitPlatform()
    }

    jacocoTestReport {
        reports {
            xml.required = true
        }
    }

    jigReports {
        dependsOn(clean, classes)
    }
}
