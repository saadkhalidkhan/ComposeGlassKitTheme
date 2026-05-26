# ComposeGlassKit API

Package: `com.saadkhan.composeglasskit`

## Theme

### `GlassConfig`

| Property | Default | Description |
|----------|---------|-------------|
| `blurRadius` | `16.dp` | Blur applied by `glassEffect` |
| `containerAlpha` | `0.15f` | Fill opacity |
| `borderAlpha` | `0.2f` | Border highlight opacity |
| `borderWidth` | `1.dp` | Border stroke |
| `glassColor` | `Unspecified` | Fill color; uses M3 `surface` when unspecified |
| `borderColor` | `Unspecified` | Border color; uses M3 `outlineVariant` when unspecified |

### `GlassTheme(config) { }`

Provides `GlassConfig` to descendants. Read current values with `GlassTheme.config` inside composition.

## Modifier

### `Modifier.glassEffect(...)`

Required: `shape`. Optional overrides for blur, colors, alpha, and border width. Unspecified numeric values inherit from `GlassTheme`.

### `Modifier.animatedGlassEffect(...)`

Same parameters as `glassEffect` plus `animationSpec` (default `tween(600ms)`). Animates blur, alpha, and border from zero to target on first composition — the glass "forms" into view.

### `Modifier.glassShimmer(...)`

| Parameter | Default | Description |
|-----------|---------|-------------|
| `color` | `White @ 0.15f` | Shimmer highlight color |
| `durationMillis` | `2000` | Full sweep cycle duration |
| `shimmerWidth` | `0.4f` | Band width as fraction of composable width |
| `angle` | `20f` | Diagonal angle in degrees |

Draws an infinite diagonal light-streak overlay. Apply after `glassEffect` or `animatedGlassEffect`.

### `Modifier.glassBorderGlow(...)`

| Parameter | Default | Description |
|-----------|---------|-------------|
| `shape` | (required) | Must match the glass surface shape |
| `borderColor` | theme default | Base gradient color |
| `borderWidth` | theme default | Stroke width |
| `minAlpha` | `0.1f` | Minimum pulse opacity |
| `maxAlpha` | `0.5f` | Maximum pulse opacity |
| `durationMillis` | `2000` | Full pulse cycle (min → max → min) |

Adds a breathing border glow that oscillates independently of the glass fill.

## Components

All components accept the same glass parameters as `glassEffect` unless noted.

### `GlassCard`

`Box` with glass styling. Parameters: `modifier`, `shape` (default `MaterialTheme.shapes.large`), glass overrides, `content`.

### `GlassButton`

`onClick`, `enabled`, `shape` (default `CircleShape`), `contentPadding`, `pressScale` (default `0.96f`), `pressAlpha` (default `0.85f`), `content` row scope. Includes animated scale and alpha feedback on press.

### `GlassNavBar`

Material 3 `NavigationBar` with transparent container and glass modifier. Content: `NavigationBarItem` composables.

### `GlassDialog`

`onDismissRequest`, `properties`, glass overrides. Uses window `blurBehindRadius` on API 31+; surface blur on older APIs.

## Previews

Each component includes a `@Preview` in the library module for Android Studio preview.
