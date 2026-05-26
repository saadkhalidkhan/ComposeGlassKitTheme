package com.saadkhan.composeglasskit.modifiers

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.saadkhan.composeglasskit.theme.GlassTheme

/**
 * Applies a glassmorphism surface with an animated entrance transition.
 *
 * The glass "forms" by animating blur radius from 0 to the target value, fading in
 * the container fill, and revealing the border gradient — creating the illusion of
 * the glass surface materializing.
 *
 * @param blurRadius Target blur radius after the animation completes.
 * @param containerColor Base fill color.
 * @param containerAlpha Target fill opacity after animation.
 * @param borderColor Border gradient base color.
 * @param borderAlpha Target border opacity after animation.
 * @param shape Clip and border shape.
 * @param borderWidth Border stroke width.
 * @param animationSpec The [AnimationSpec] controlling entrance timing. Defaults to a 600ms tween.
 */
fun Modifier.animatedGlassEffect(
    blurRadius: Dp = Dp.Unspecified,
    containerColor: Color? = null,
    containerAlpha: Float = Float.NaN,
    borderColor: Color? = null,
    borderAlpha: Float = Float.NaN,
    shape: Shape,
    borderWidth: Dp = Dp.Unspecified,
    animationSpec: AnimationSpec<Float> = tween(durationMillis = 600),
): Modifier = composed {
    val config = GlassTheme.config

    val targetBlurRadius = if (blurRadius != Dp.Unspecified) blurRadius else config.blurRadius
    val targetContainerAlpha = if (!containerAlpha.isNaN()) containerAlpha else config.containerAlpha
    val targetBorderAlpha = if (!borderAlpha.isNaN()) borderAlpha else config.borderAlpha
    val finalBorderWidth = if (borderWidth != Dp.Unspecified) borderWidth else config.borderWidth

    val baseContainerColor = containerColor
        ?: if (config.glassColor != Color.Unspecified) config.glassColor else MaterialTheme.colorScheme.surface

    val baseBorderColor = borderColor
        ?: if (config.borderColor != Color.Unspecified) config.borderColor else MaterialTheme.colorScheme.outlineVariant

    var animationStarted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { animationStarted = true }

    val progress by animateFloatAsState(
        targetValue = if (animationStarted) 1f else 0f,
        animationSpec = animationSpec,
        label = "glassEntrance",
    )

    val currentBlur = targetBlurRadius * progress
    val currentContainerAlpha = targetContainerAlpha * progress
    val currentBorderAlpha = targetBorderAlpha * progress

    this
        .graphicsLayer {
            clip = true
            this.shape = shape
            alpha = progress
        }
        .then(
            if (currentBlur > 0.dp) {
                Modifier.blur(currentBlur)
            } else {
                Modifier
            },
        )
        .background(
            color = baseContainerColor.copy(alpha = currentContainerAlpha),
            shape = shape,
        )
        .border(
            width = finalBorderWidth,
            brush = Brush.verticalGradient(
                colors = listOf(
                    baseBorderColor.copy(alpha = currentBorderAlpha),
                    baseBorderColor.copy(alpha = currentBorderAlpha * 0.4f),
                    baseBorderColor.copy(alpha = currentBorderAlpha * 0.1f),
                ),
            ),
            shape = shape,
        )
        .clip(shape)
}
