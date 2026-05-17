# ComposeGlassKit

Glassmorphism UI components for **Jetpack Compose** and **Material 3** — translucent surfaces, gradient borders, and configurable blur.

<p align="center">
  <a href="https://github.com/saadkhalidkhan/ComposeGlassKit/actions/workflows/ci.yml"><img src="https://img.shields.io/github/actions/workflow/status/saadkhalidkhan/ComposeGlassKit/ci.yml?branch=master&label=CI&logo=github" alt="CI status"/></a>
  <a href="https://jitpack.io/#saadkhalidkhan/ComposeGlassKit"><img src="https://jitpack.io/v/saadkhalidkhan/ComposeGlassKit.svg" alt="JitPack"/></a>
  <img src="https://img.shields.io/badge/version-1.0.0-blue" alt="Version"/>
  <a href="LICENSE"><img src="https://img.shields.io/github/license/saadkhalidkhan/ComposeGlassKit" alt="License"/></a>
  <a href="https://github.com/saadkhalidkhan/ComposeGlassKit/stargazers"><img src="https://img.shields.io/github/stars/saadkhalidkhan/ComposeGlassKit?style=social" alt="Stars"/></a>
</p>

<p align="center">
  <img src="https://img.shields.io/github/forks/saadkhalidkhan/ComposeGlassKit" alt="Forks"/>
  <img src="https://img.shields.io/github/issues/saadkhalidkhan/ComposeGlassKit" alt="Issues"/>
  <img src="https://img.shields.io/badge/API-26%2B-brightgreen?logo=android" alt="API 26+"/>
  <img src="https://img.shields.io/badge/Kotlin-2.2.10-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-BOM%202024.09-4285F4?logo=jetpackcompose&logoColor=white" alt="Compose"/>
  <img src="https://img.shields.io/badge/group-io.github.saadkhalidkhan-blue" alt="Maven group"/>
</p>

<p align="center">
  <video src="docs/media/glasskit.webm" width="720" autoplay loop muted playsinline>
    <a href="docs/media/glasskit.webm">Watch demo video</a>
  </video>
</p>

Maintained by **[Saad Khan](https://github.com/saadkhalidkhan)** · [Portfolio](https://github.com/saadkhalidkhan/Saad-Portfolio) · [Medium](https://medium.com/@saadkhan0799)

---

## Features

- **Glass components** — `GlassCard`, `GlassButton`, `GlassNavBar`, `GlassDialog`
- **Customizable effects** — blur radius, fill opacity, gradient borders
- **Material 3** — dynamic color, M3 shapes and color roles
- **Global theme** — `GlassTheme` + `GlassConfig` via composition locals
- **Dialog backdrop blur** — window blur on Android 12+ (API 31+)
- **Sample app** — live visual editor over photo backgrounds

## Table of contents

- [Preview](#preview)
- [Requirements](#requirements)
- [Installation](#installation)
- [Quick start](#quick-start)
- [Usage examples](#usage-examples)
- [Components](#components)
- [Blur strategy](#blur-strategy)
- [Sample app](#sample-app)
- [Modules](#modules)
- [Publishing](#publishing)
- [Documentation](#documentation)
- [Contributing](#contributing)
- [License](#license)

## Preview

Sample app with different background presets and live glass controls:

| Theme 1 | Theme 2 | Theme 3 |
|:-------:|:-------:|:-------:|
| ![Theme 1](docs/media/theme1.png) | ![Theme 2](docs/media/theme2.png) | ![Theme 3](docs/media/theme3.png) |

## Requirements

| Requirement | Version |
|-------------|---------|
| Android API | **26+** |
| Jetpack Compose | BOM **2024.09.00** (see `gradle/libs.versions.toml`) |
| Kotlin | **2.2.10** |
| Dialog window blur | **API 31+** (fallback on older devices) |

## Installation

### 1. Module dependency (monorepo / local)

```kotlin
// settings.gradle.kts
include(":glasskit")

// your-app/build.gradle.kts
dependencies {
    implementation(project(":glasskit"))
}
```

### 2. JitPack (GitHub release)

Add JitPack to your root `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
    implementation("com.github.saadkhalidkhan:ComposeGlassKit:1.0.0")
}
```

[![JitPack](https://img.shields.io/badge/JitPack-ComposeGlassKit-ff6b6b?logo=github)](https://jitpack.io/#saadkhalidkhan/ComposeGlassKit)

### 3. Maven coordinates

| | |
|---|---|
| **Group** | `io.github.saadkhalidkhan` |
| **Artifact** | `compose-glasskit` |
| **Version** | `1.0.0` (`LIBRARY_VERSION` in `gradle.properties`) |

```kotlin
implementation("io.github.saadkhalidkhan:compose-glasskit:1.0.0")
```

*Available on Maven Central after you publish; use JitPack until then.*

## Quick start

```kotlin
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.saadkhalidkhan.composeglasskit.components.GlassCard
import io.github.saadkhalidkhan.composeglasskit.theme.GlassConfig
import io.github.saadkhalidkhan.composeglasskit.theme.GlassTheme

@Composable
fun GlassHelloScreen() {
    // Use a full-screen background (image/gradient) behind this content.
    GlassTheme(
        config = GlassConfig(
            blurRadius = 16.dp,
            containerAlpha = 0.15f,
            borderAlpha = 0.25f,
        ),
    ) {
        GlassCard(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = "Hello, glass.",
                modifier = Modifier.padding(24.dp),
            )
        }
    }
}
```

## Usage examples

More recipes (buttons, nav bar, dialog, custom modifier):

**[docs/USAGE.md](docs/USAGE.md)**

```kotlin
// Glass button
GlassButton(onClick = { }, shape = RoundedCornerShape(16.dp)) {
    Text("Open")
}

// Glass dialog (API 31+ window blur)
GlassDialog(onDismissRequest = { showDialog = false }) {
    Text("Modal content", modifier = Modifier.padding(24.dp))
}
```

## Components

| Component | Description |
|-----------|-------------|
| [`GlassTheme`](glasskit/src/main/java/io/github/saadkhalidkhan/composeglasskit/theme/GlassTheme.kt) | Global `GlassConfig` provider |
| [`GlassConfig`](glasskit/src/main/java/io/github/saadkhalidkhan/composeglasskit/theme/GlassTheme.kt) | Blur, alpha, border defaults |
| [`Modifier.glassEffect()`](glasskit/src/main/java/io/github/saadkhalidkhan/composeglasskit/modifiers/GlassModifier.kt) | Core glass surface modifier |
| [`GlassCard`](glasskit/src/main/java/io/github/saadkhalidkhan/composeglasskit/components/GlassCard.kt) | Card container |
| [`GlassButton`](glasskit/src/main/java/io/github/saadkhalidkhan/composeglasskit/components/GlassButton.kt) | Clickable button |
| [`GlassNavBar`](glasskit/src/main/java/io/github/saadkhalidkhan/composeglasskit/components/GlassNavBar.kt) | Glass `NavigationBar` |
| [`GlassDialog`](glasskit/src/main/java/io/github/saadkhalidkhan/composeglasskit/components/GlassDialog.kt) | Modal with backdrop blur |

Full API reference: **[docs/API.md](docs/API.md)**

## Blur strategy

ComposeGlassKit uses **layer blur** for surfaces and **window blur** for dialogs on API 31+.

Read limitations and layout tips: **[docs/BLUR.md](docs/BLUR.md)**

## Sample app

Run **ComposeGlassKit Sample** (`:sample`) to tweak blur and opacity over Unsplash backgrounds:

```bash
./gradlew :sample:assembleDebug
./gradlew :sample:installDebug
```

## Modules

| Module | Description |
|--------|-------------|
| `:glasskit` | Publishable Android library |
| `:sample` | Interactive showcase app |

## Publishing

Local Maven:

```bash
./gradlew :glasskit:publishReleasePublicationToMavenLocal
```

GitHub release (tags `v*` trigger [release workflow](.github/workflows/release.yml) and attach APK/AAR artifacts).

## Documentation

| Doc | Contents |
|-----|----------|
| [docs/API.md](docs/API.md) | Parameters and components |
| [docs/USAGE.md](docs/USAGE.md) | Copy-paste examples |
| [docs/BLUR.md](docs/BLUR.md) | Blur behavior and performance |
| [docs/media/README.md](docs/media/README.md) | Screenshots & GIF guide |
| [CHANGELOG.md](CHANGELOG.md) | Version history |

## Contributing

1. Fork the repo and create a branch.
2. Run `./gradlew :glasskit:test :sample:assembleDebug` before opening a PR.
3. Follow existing Kotlin and Compose style.

Issues and PRs are welcome on [GitHub Issues](https://github.com/saadkhalidkhan/ComposeGlassKit/issues).

## License

```
Copyright 2026 Saad Khan

Licensed under the Apache License, Version 2.0
```

See [LICENSE](LICENSE) for the full text.

[![License](https://img.shields.io/github/license/saadkhalidkhan/ComposeGlassKit)](LICENSE)
