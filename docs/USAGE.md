# Usage examples

Kotlin package: `com.saadkhan.composeglasskit`  
Maven coordinates: `io.github.saadkhalidkhan:compose-glasskit`

## Setup

### Maven Central

```kotlin
// settings.gradle.kts — ensure mavenCentral() is listed
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
```

```kotlin
// your-module/build.gradle.kts
dependencies {
    implementation("io.github.saadkhalidkhan:compose-glasskit:1.0.0")
}
```

See [README — Installation](../README.md#1-maven-central-recommended) for JitPack and other options.

### Local module (monorepo)

```kotlin
// settings.gradle.kts
include(":glasskit")

// your-module/build.gradle.kts
dependencies {
    implementation(project(":glasskit"))
}
```

Wrap content that uses glass components:

```kotlin
import com.saadkhan.composeglasskit.theme.GlassConfig
import com.saadkhan.composeglasskit.theme.GlassTheme

GlassTheme(
    config = GlassConfig(
        blurRadius = 16.dp,
        containerAlpha = 0.12f,
        borderAlpha = 0.25f,
    ),
) {
    // glass components here
}
```

Place a **full-screen background** (image, gradient, or video) **behind** glass UI for the best effect. See [BLUR.md](BLUR.md).

---

## GlassCard

```kotlin
import com.saadkhan.composeglasskit.components.GlassCard

GlassCard(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    shape = RoundedCornerShape(24.dp),
    containerAlpha = 0.15f,
    borderAlpha = 0.35f,
) {
    Column(Modifier.padding(20.dp)) {
        Text("Title", style = MaterialTheme.typography.titleLarge)
        Text("Subtitle", style = MaterialTheme.typography.bodyMedium)
    }
}
```

---

## GlassButton

```kotlin
import com.saadkhan.composeglasskit.components.GlassButton

GlassButton(
    onClick = { /* action */ },
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    containerColor = MaterialTheme.colorScheme.primary,
    containerAlpha = 0.35f,
) {
    Icon(Icons.Default.Favorite, contentDescription = null)
    Spacer(Modifier.width(8.dp))
    Text("Save")
}
```

---

## GlassNavBar

```kotlin
import com.saadkhan.composeglasskit.components.GlassNavBar

Scaffold(
    containerColor = Color.Transparent,
    bottomBar = {
        GlassNavBar(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(24.dp),
        ) {
            NavigationBarItem(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
            )
            // more items…
        }
    },
) { padding ->
    // screen content
}
```

---

## GlassDialog

```kotlin
import com.saadkhan.composeglasskit.components.GlassDialog

var showDialog by remember { mutableStateOf(false) }

if (showDialog) {
    GlassDialog(
        onDismissRequest = { showDialog = false },
        blurRadius = 30.dp,
        containerAlpha = 0.22f,
    ) {
        Column(
            Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Glass modal", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))
            GlassButton(onClick = { showDialog = false }) {
                Text("Close")
            }
        }
    }
}
```

On **API 31+**, the system blurs the activity behind the dialog. On older devices, surface blur is used instead.

---

## Custom modifier only

```kotlin
import com.saadkhan.composeglasskit.modifiers.glassEffect

Box(
    modifier = Modifier
        .size(200.dp)
        .glassEffect(
            shape = CircleShape,
            blurRadius = 20.dp,
            containerAlpha = 0.1f,
        ),
    contentAlignment = Alignment.Center,
) {
    Text("Custom glass")
}
```

---

## Per-component overrides

Any parameter on `GlassConfig` can be overridden per component:

```kotlin
GlassCard(
    blurRadius = 8.dp,
    containerAlpha = 0.2f,
    borderWidth = 2.dp,
) { /* … */ }
```

Unspecified values fall back to `GlassTheme.config`.
