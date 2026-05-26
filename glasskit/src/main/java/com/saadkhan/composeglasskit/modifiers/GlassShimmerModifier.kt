package com.saadkhan.composeglasskit.modifiers

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Draws a diagonal light-streak shimmer that sweeps across the composable surface.
 *
 * Apply this **after** [glassEffect] or [animatedGlassEffect] to overlay a moving
 * highlight on the glass surface. The shimmer is purely additive — it doesn't alter
 * existing content or background.
 *
 * @param color The highlight color. A translucent white works best for glass surfaces.
 * @param durationMillis Time in milliseconds for one full sweep cycle.
 * @param shimmerWidth Proportion of the composable width that the highlight band occupies (0f–1f).
 * @param angle Diagonal angle in degrees (0 = horizontal, 90 = vertical). Default is 20 degrees.
 */
fun Modifier.glassShimmer(
    color: Color = Color.White.copy(alpha = 0.15f),
    durationMillis: Int = 2000,
    shimmerWidth: Float = 0.4f,
    angle: Float = 20f,
): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "glassShimmer")

    val progress by transition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmerProgress",
    )

    drawWithContent {
        drawContent()

        val width = size.width
        val height = size.height

        val angleRad = Math.toRadians(angle.toDouble()).toFloat()
        val dx = kotlin.math.cos(angleRad)
        val dy = kotlin.math.sin(angleRad)

        val centerX = width * progress
        val centerY = height * progress * (dy / dx).coerceIn(-1f, 1f)
        val bandHalf = width * shimmerWidth * 0.5f

        val startX = centerX - bandHalf * dx
        val startY = centerY - bandHalf * dy
        val endX = centerX + bandHalf * dx
        val endY = centerY + bandHalf * dy

        val shimmerBrush = Brush.linearGradient(
            colors = listOf(
                Color.Transparent,
                color,
                color.copy(alpha = color.alpha * 0.6f),
                Color.Transparent,
            ),
            start = Offset(startX, startY),
            end = Offset(endX, endY),
        )

        drawRect(brush = shimmerBrush)
    }
}
