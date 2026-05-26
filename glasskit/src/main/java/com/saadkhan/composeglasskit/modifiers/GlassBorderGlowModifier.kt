package com.saadkhan.composeglasskit.modifiers

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.saadkhan.composeglasskit.theme.GlassTheme

/**
 * Applies an animated pulsing glow to the glass border.
 *
 * The border gradient alpha oscillates between [minAlpha] and [maxAlpha], creating an
 * ambient "breathing" light effect on the glass edge. Pair with [glassEffect] for the
 * full glass appearance — this modifier only draws the animated border.
 *
 * @param shape Border shape. Should match the shape used for [glassEffect].
 * @param borderColor Base color for the gradient border.
 * @param borderWidth Border stroke width.
 * @param minAlpha Minimum opacity during the pulse cycle.
 * @param maxAlpha Maximum opacity during the pulse cycle.
 * @param durationMillis Full pulse cycle duration (min to max and back).
 */
fun Modifier.glassBorderGlow(
    shape: Shape,
    borderColor: Color? = null,
    borderWidth: Dp = Dp.Unspecified,
    minAlpha: Float = 0.1f,
    maxAlpha: Float = 0.5f,
    durationMillis: Int = 2000,
): Modifier = composed {
    val config = GlassTheme.config
    val finalBorderWidth = if (borderWidth != Dp.Unspecified) borderWidth else config.borderWidth

    val baseBorderColor = borderColor
        ?: if (config.borderColor != Color.Unspecified) config.borderColor else MaterialTheme.colorScheme.outlineVariant

    val transition = rememberInfiniteTransition(label = "glassBorderGlow")

    val alpha by transition.animateFloat(
        initialValue = minAlpha,
        targetValue = maxAlpha,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis / 2),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "borderGlowAlpha",
    )

    border(
        width = finalBorderWidth,
        brush = Brush.verticalGradient(
            colors = listOf(
                baseBorderColor.copy(alpha = alpha),
                baseBorderColor.copy(alpha = alpha * 0.5f),
                baseBorderColor.copy(alpha = alpha * 0.15f),
            ),
        ),
        shape = shape,
    )
}
