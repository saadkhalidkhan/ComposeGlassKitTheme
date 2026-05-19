import java.util.Properties

/**
 * Vanniktech reads [mavenCentralUsername] / signing keys via [org.gradle.api.provider.ProviderFactory.gradleProperty],
 * which only sees gradle.properties (or ORG_GRADLE_PROJECT_* / -Dorg.gradle.project.*), not Android's local.properties.
 */
private val publishPropertyKeys = listOf(
    "mavenCentralUsername",
    "mavenCentralPassword",
    "signing.keyId",
    "signing.password",
    "signing.secretKeyRingFile",
)

private fun loadPublishPropertiesFromLocal(): Properties {
    val properties = Properties()
    val localPropertiesFile = File(settingsDir, "local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { properties.load(it) }
    }
    return properties
}

/**
 * Merges publish/signing keys from local.properties into the user Gradle properties file
 * (~/.gradle/gradle.properties), which [gradleProperty] resolves for all projects.
 */
private fun syncPublishSecretsToUserGradleProperties() {
    val localProperties = loadPublishPropertiesFromLocal()
    val hasPublishSecrets = publishPropertyKeys.any { key ->
        localProperties.getProperty(key)?.trim()?.isNotEmpty() == true
    }
    if (!hasPublishSecrets) return

    val userGradleDir = File(System.getProperty("user.home"), ".gradle")
    userGradleDir.mkdirs()
    val userGradlePropertiesFile = File(userGradleDir, "gradle.properties")
    val userGradleProperties = Properties()
    if (userGradlePropertiesFile.exists()) {
        userGradlePropertiesFile.inputStream().use { userGradleProperties.load(it) }
    }

    publishPropertyKeys.forEach { key ->
        localProperties.getProperty(key)?.trim()?.takeIf { it.isNotEmpty() }?.let { value ->
            userGradleProperties.setProperty(key, value)
        }
    }

    userGradlePropertiesFile.outputStream().use { output ->
        userGradleProperties.store(
            output,
            "Includes publish/signing secrets synced from ComposeGlassKit local.properties",
        )
    }
}

syncPublishSecretsToUserGradleProperties()

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "ComposeGlassKit"
include(":sample", ":glasskit")
