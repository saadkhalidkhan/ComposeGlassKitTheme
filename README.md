# ComposeGlassKit

Glassmorphism UI components for **Jetpack Compose** and **Material 3** — translucent surfaces, gradient borders, and configurable blur.

Maintained by [Saad Khan](https://github.com/saadkhalidkhan).

## Modules

| Module | Description |
|--------|-------------|
| `:glasskit` | Publishable Android library (`io.github.saadkhalidkhan:compose-glasskit`) |
| `:sample` | Sample app (`ComposeGlassKit Sample`) |

## Requirements

- Android API **26+**
- Jetpack Compose + Material 3
- Window backdrop blur for dialogs: API **31+** (graceful fallback on older devices)

## Installation

### Project dependency

```kotlin
// settings.gradle.kts
include(":glasskit")

// sample/build.gradle.kts
dependencies {
    implementation(project(":glasskit"))
}
```

### JitPack (after publishing to GitHub)

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}

// your-app/build.gradle.kts
dependencies {
    implementation("com.github.saadkhalidkhan:ComposeGlassKit:1.0.0")
}
```

Maven coordinates for local / Maven Central publish:

- **Group:** `io.github.saadkhalidkhan`
- **Artifact:** `compose-glasskit`
- **Version:** see `LIBRARY_VERSION` in `gradle.properties`

## Quick start

Wrap your screen (or app) with `GlassTheme` and use the components over a background (image, gradient, or video):

```kotlin
import io.github.saadkhalidkhan.composeglasskit.components.GlassCard
import io.github.saadkhalidkhan.composeglasskit.theme.GlassConfig
import io.github.saadkhalidkhan.composeglasskit.theme.GlassTheme

GlassTheme(
    config = GlassConfig(
        blurRadius = 16.dp,
        containerAlpha = 0.15f,
        borderAlpha = 0.25f,
    ),
) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Text("Hello, glass.", modifier = Modifier.padding(24.dp))
    }
}
```

## API overview

See [docs/API.md](docs/API.md) for parameters and component list.

| API | Role |
|-----|------|
| `GlassTheme` | Provides global `GlassConfig` via composition local |
| `GlassConfig` | Blur, alpha, border width/colors |
| `Modifier.glassEffect()` | Core glass surface modifier |
| `GlassCard` | Card container |
| `GlassButton` | Clickable glass button |
| `GlassNavBar` | Glass `NavigationBar` |
| `GlassDialog` | Modal with window blur on API 31+ |

## Blur strategy

ComposeGlassKit uses **layer blur** (`Modifier.blur`) for cards, buttons, and nav bars, plus **window blur** for dialogs on Android 12+. Read the full explanation, limitations, and layout tips in [docs/BLUR.md](docs/BLUR.md).

## Sample app

Run the `:sample` module — **ComposeGlassKit Sample** — to tweak blur/opacity live over Unsplash backgrounds.

```bash
./gradlew :sample:assembleDebug
```

## Publishing

```bash
./gradlew :glasskit:publishReleasePublicationToMavenLocal
```

Configure signing and Sonatype / Maven Central credentials for public release as needed.

## License

Add your license file before publishing (e.g. Apache 2.0).
