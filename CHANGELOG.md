# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2026-05-26

### Added

- `Modifier.animatedGlassEffect()` — entrance animation that transitions blur, alpha, and border from zero to target values
- `Modifier.glassShimmer()` — infinite diagonal light-streak overlay for ambient surface motion
- `Modifier.glassBorderGlow()` — pulsing border opacity animation for breathing edge highlight
- `GlassButton` press animation with configurable `pressScale` and `pressAlpha` parameters
- "Animate" tab in sample app showcasing all new animation modifiers
- "Config" tab in sample app separating visual editor controls from main content
- Combined effects demo showing all three modifiers stacked together

### Changed

- `GlassButton` now uses `graphicsLayer` for disabled state alpha (was `Modifier.alpha`)
- `GlassButton` uses `collectIsPressedAsState` for press detection
- Sample app restructured into tabbed navigation (Home, Animate, Config)

## [1.0.0] - 2026-05-17

### Added

- `:glasskit` Android library module with Maven publish configuration
- `GlassTheme`, `GlassConfig`, and `Modifier.glassEffect()`
- Components: `GlassCard`, `GlassButton`, `GlassNavBar`, `GlassDialog`
- `:sample` demo app with live blur/opacity controls and background gallery
- Material 3 integration and dynamic color support in sample
- Documentation: `docs/API.md`, `docs/BLUR.md`, `docs/USAGE.md`
- Unit tests for `GlassConfig` defaults
- GitHub Actions CI and release workflows
- Apache 2.0 license
- Maven Central release [`io.github.saadkhalidkhan:compose-glasskit:1.0.0`](https://central.sonatype.com/artifact/io.github.saadkhalidkhan/compose-glasskit)

[1.1.0]: https://github.com/saadkhalidkhan/ComposeGlassKitTheme/compare/v1.0.0...v1.1.0
[1.0.0]: https://github.com/saadkhalidkhan/ComposeGlassKitTheme/releases/tag/v1.0.0
