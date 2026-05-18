package com.saadkhan.composeglasskit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.saadkhan.composeglasskit.modifiers.glassEffect

/**
 * A card-shaped container with a glassmorphism surface.
 *
 * @param content Content inside the card.
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
    blurRadius: Dp = Dp.Unspecified,
    containerColor: Color? = null,
    containerAlpha: Float = Float.NaN,
    borderColor: Color? = null,
    borderAlpha: Float = Float.NaN,
    borderWidth: Dp = Dp.Unspecified,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier.glassEffect(
            blurRadius = blurRadius,
            containerColor = containerColor,
            containerAlpha = containerAlpha,
            borderColor = borderColor,
            borderAlpha = borderAlpha,
            shape = shape,
            borderWidth = borderWidth,
        ),
        content = content,
    )
}
