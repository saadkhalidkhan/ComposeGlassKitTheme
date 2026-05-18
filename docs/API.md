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

## Components

All components accept the same glass parameters as `glassEffect` unless noted.

### `GlassCard`

`Box` with glass styling. Parameters: `modifier`, `shape` (default `MaterialTheme.shapes.large`), glass overrides, `content`.

### `GlassButton`

`onClick`, `enabled`, `shape` (default `CircleShape`), `contentPadding`, `content` row scope.

### `GlassNavBar`

Material 3 `NavigationBar` with transparent container and glass modifier. Content: `NavigationBarItem` composables.

### `GlassDialog`

`onDismissRequest`, `properties`, glass overrides. Uses window `blurBehindRadius` on API 31+; surface blur on older APIs.

## Previews

Each component includes a `@Preview` in the library module for Android Studio preview.
