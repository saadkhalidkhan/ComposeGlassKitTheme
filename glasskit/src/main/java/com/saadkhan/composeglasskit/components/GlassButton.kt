package com.saadkhan.composeglasskit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.saadkhan.composeglasskit.modifiers.glassEffect

/**
 * A clickable button with a glassmorphism surface.
 */
@Composable
fun GlassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    blurRadius: Dp = Dp.Unspecified,
    containerColor: Color? = null,
    containerAlpha: Float = Float.NaN,
    borderColor: Color? = null,
    borderAlpha: Float = Float.NaN,
    borderWidth: Dp = Dp.Unspecified,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .alpha(if (enabled) 1f else 0.5f)
            .glassEffect(
                blurRadius = blurRadius,
                containerColor = containerColor,
                containerAlpha = containerAlpha,
                borderColor = borderColor,
                borderAlpha = borderAlpha,
                shape = shape,
                borderWidth = borderWidth,
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides if (enabled) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            },
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    content = content,
                )
            }
        }
    }
}
