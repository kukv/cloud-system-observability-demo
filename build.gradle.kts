plugins {
    alias(libs.plugins.spotless)
}

spotless {
    kotlinGradle {
        target("**/*.gradle.kts")
        ktlint()
    }
}
