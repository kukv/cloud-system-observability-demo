plugins {
    java
    jacoco
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.jig)
    alias(libs.plugins.spotless)
}

group = "jp.kukv"
version = "0.0.1"

dependencies {
    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.restclient)
    annotationProcessor(libs.spring.boot.configuration.processor)
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
