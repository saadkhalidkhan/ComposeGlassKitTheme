# Blur strategy in ComposeGlassKit

Glassmorphism on Android usually combines **translucency**, **edge highlights**, and **blur**. This library uses two different blur paths.

## 1. Surface blur (`Modifier.glassEffect`)

Used by: `GlassCard`, `GlassButton`, `GlassNavBar`, and `GlassDialog` on API 26–30.

Implementation:

- `graphicsLayer { clip = true; shape = … }`
- `Modifier.blur(radius)` when `blurRadius > 0`
- Semi-transparent `background` + gradient `border`

### What it actually blurs

`Modifier.blur()` blurs **pixels drawn in that composable’s layer** (the glass panel and its children), not a live capture of siblings behind it in the same window.

You still get a convincing glass look when:

1. A **full-screen background** (image, gradient, video) sits **under** the glass in the tree.
2. **`containerAlpha`** is low enough that the background shows through the fill.
3. Blur softens content **on** the glass layer (icons, text) and adds frost when combined with transparency.

### What it does not do

- It does **not** sample and blur arbitrary composables behind a floating panel in a complex `LazyColumn` without a shared background.
- It is **not** identical to iOS `UIVisualEffectView` backdrop sampling.

For those cases, place decorative backgrounds behind glass in the layout, or use platform window blur (dialogs).

## 2. Window blur (`GlassDialog`, API 31+)

On Android 12+ (`Build.VERSION_CODES.S`), `GlassDialog` sets:

- `WindowManager.LayoutParams.FLAG_BLUR_BEHIND`
- `blurBehindRadius` derived from your `blurRadius` (scaled ×2, minimum 1)

The dialog panel uses **surface blur disabled** (`blurRadius = 0.dp` on the box) to avoid stacking two blurs.

This blurs **the activity window content behind the dialog**, which is the closest match to “real” backdrop blur in the framework today.

## Recommendations

| Scenario | Recommendation |
|----------|----------------|
| Hero UI over wallpaper | Full-screen background + `GlassCard` / `GlassButton` |
| Bottom nav over content | Same screen background under `Scaffold`; floating `GlassNavBar` |
| Modal | `GlassDialog` on API 31+; test on API 26–30 fallback |
| Lists with per-row glass | Row background or shared screen backdrop; avoid expecting row-to-row backdrop blur |
| Maximum blur | Increase `blurRadius` modestly; large values are expensive |

## Performance

Blur is GPU work. Prefer:

- `blurRadius` in the **8–24dp** range for lists
- Disabling blur (`0.dp`) on low-end devices if needed
- Window blur only on dialogs, not every list item

## Future improvements

Possible enhancements (not in v1.0.0):

- `RenderEffect` backdrop via offscreen capture (higher cost, more control)
- `GraphicsLayer` record-and-blur of content behind a bounded region
- User preference to disable blur for accessibility / battery
