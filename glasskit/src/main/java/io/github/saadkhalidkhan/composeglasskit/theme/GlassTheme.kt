package io.github.saadkhalidkhan.composeglasskit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Global defaults for glass surfaces in this library.
 *
 * @param blurRadius Blur radius applied by [io.github.saadkhalidkhan.composeglasskit.modifiers.glassEffect].
 * @param containerAlpha Opacity of the translucent fill (0f–1f).
 * @param borderAlpha Opacity of the gradient border highlight (0f–1f).
 * @param borderWidth Width of the glass border stroke.
 * @param glassColor Base fill color. [Color.Unspecified] uses [androidx.compose.material3.MaterialTheme.colorScheme.surface].
 * @param borderColor Border gradient base color. [Color.Unspecified] uses outlineVariant from Material 3.
 */
@Immutable
data class GlassConfig(
    val blurRadius: Dp = 16.dp,
    val containerAlpha: Float = 0.15f,
    val borderAlpha: Float = 0.2f,
    val borderWidth: Dp = 1.dp,
    val glassColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
)

val LocalGlassConfig = staticCompositionLocalOf { GlassConfig() }

/** Access the active [GlassConfig] from a [GlassTheme] scope. */
object GlassTheme {
    val config: GlassConfig
        @Composable
        get() = LocalGlassConfig.current
}

/**
 * Provides [GlassConfig] to all glass components and [glassEffect] modifiers below this node.
 */
@Composable
fun GlassTheme(
    config: GlassConfig = GlassConfig(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalGlassConfig provides config) {
        content()
    }
}
