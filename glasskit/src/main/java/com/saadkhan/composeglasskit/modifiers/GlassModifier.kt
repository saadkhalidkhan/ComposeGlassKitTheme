package com.saadkhan.composeglasskit.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.saadkhan.composeglasskit.theme.GlassTheme

/**
 * Applies a glassmorphism surface: translucent fill, gradient border, and optional blur.
 *
 * ## Blur behavior
 *
 * This modifier uses [androidx.compose.ui.draw.blur], which blurs **content drawn on the same
 * composable layer** (the glass surface and its descendants), not a live snapshot of widgets
 * behind the glass in the layout tree. In practice you still get a frosted look when:
 *
 * - The glass sits over imagery or gradients (e.g. a full-screen background), and
 * - [containerAlpha] is low enough that underlying pixels show through the fill.
 *
 * For **true backdrop blur** of the window behind a dialog, use [com.saadkhan.composeglasskit.components.GlassDialog]
 * on Android 12+ (API 31), which enables `FLAG_BLUR_BEHIND` on the dialog window.
 *
 * See [docs/BLUR.md](https://github.com/saadkhalidkhan/ComposeGlassKit/blob/main/docs/BLUR.md)
 * in the repository for limitations and recommended layouts.
 *
 * @param blurRadius Blur radius. `0.dp` disables blur. Unspecified uses [GlassTheme.config].
 * @param containerColor Base fill color.
 * @param containerAlpha Fill opacity. NaN uses theme default.
 * @param borderColor Border gradient base color.
 * @param borderAlpha Border opacity. NaN uses theme default.
 * @param shape Clip and border shape (required).
 * @param borderWidth Border stroke width. Unspecified uses theme default.
 */
fun Modifier.glassEffect(
    blurRadius: Dp = Dp.Unspecified,
    containerColor: Color? = null,
    containerAlpha: Float = Float.NaN,
    borderColor: Color? = null,
    borderAlpha: Float = Float.NaN,
    shape: Shape,
    borderWidth: Dp = Dp.Unspecified,
): Modifier = composed {
    val config = GlassTheme.config

    val finalBlurRadius = if (blurRadius != Dp.Unspecified) blurRadius else config.blurRadius
    val finalContainerAlpha = if (!containerAlpha.isNaN()) containerAlpha else config.containerAlpha
    val finalBorderAlpha = if (!borderAlpha.isNaN()) borderAlpha else config.borderAlpha
    val finalBorderWidth = if (borderWidth != Dp.Unspecified) borderWidth else config.borderWidth

    val baseContainerColor = containerColor
        ?: if (config.glassColor != Color.Unspecified) config.glassColor else MaterialTheme.colorScheme.surface

    val baseBorderColor = borderColor
        ?: if (config.borderColor != Color.Unspecified) config.borderColor else MaterialTheme.colorScheme.outlineVariant

    this
        .graphicsLayer {
            clip = true
            this.shape = shape
        }
        .then(
            if (finalBlurRadius > 0.dp) {
                Modifier.blur(finalBlurRadius)
            } else {
                Modifier
            },
        )
        .background(
            color = baseContainerColor.copy(alpha = finalContainerAlpha),
            shape = shape,
        )
        .border(
            width = finalBorderWidth,
            brush = Brush.verticalGradient(
                colors = listOf(
                    baseBorderColor.copy(alpha = finalBorderAlpha),
                    baseBorderColor.copy(alpha = finalBorderAlpha * 0.4f),
                    baseBorderColor.copy(alpha = finalBorderAlpha * 0.1f),
                ),
            ),
            shape = shape,
        )
        .clip(shape)
}
