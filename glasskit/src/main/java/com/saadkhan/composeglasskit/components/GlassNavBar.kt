package com.saadkhan.composeglasskit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.saadkhan.composeglasskit.theme.GlassTheme

/**
 * A [NavigationBar] with a glassmorphism container. Pass [NavigationBarItem] children as content.
 *
 * Unlike applying [glassEffect] directly, this component separates the blurred glass
 * background from the content layer so that icons and labels remain crisp and readable.
 */
@Composable
fun GlassNavBar(
    modifier: Modifier = Modifier,
    containerColor: Color? = null,
    containerAlpha: Float = Float.NaN,
    borderColor: Color? = null,
    borderAlpha: Float = Float.NaN,
    borderWidth: Dp = Dp.Unspecified,
    shape: Shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    blurRadius: Dp = Dp.Unspecified,
    contentColor: Color = Color.White,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit,
) {
    val config = GlassTheme.config

    val finalBlurRadius = if (blurRadius != Dp.Unspecified) blurRadius else config.blurRadius
    val finalContainerAlpha = if (!containerAlpha.isNaN()) containerAlpha else config.containerAlpha
    val finalBorderAlpha = if (!borderAlpha.isNaN()) borderAlpha else config.borderAlpha
    val finalBorderWidth = if (borderWidth != Dp.Unspecified) borderWidth else config.borderWidth

    val baseContainerColor = containerColor
        ?: if (config.glassColor != Color.Unspecified) config.glassColor else MaterialTheme.colorScheme.surface

    val baseBorderColor = borderColor
        ?: if (config.borderColor != Color.Unspecified) config.borderColor else MaterialTheme.colorScheme.outlineVariant

    Box(modifier = modifier.clip(shape)) {
        // Glass background layer — blurred separately so content stays sharp
        Box(
            modifier = Modifier
                .matchParentSize()
                .then(
                    if (finalBlurRadius > 0.dp) Modifier.blur(finalBlurRadius) else Modifier
                )
                .background(
                    color = baseContainerColor.copy(alpha = finalContainerAlpha),
                    shape = shape,
                )
        )

        // Border drawn on top, unblurred
        Box(
            modifier = Modifier
                .matchParentSize()
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
        )

        // Content layer — no blur applied
        NavigationBar(
            containerColor = Color.Transparent,
            contentColor = contentColor,
            windowInsets = windowInsets,
            content = content,
        )
    }
}
