package com.saadkhan.composeglasskit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.saadkhan.composeglasskit.modifiers.glassEffect

/**
 * A [NavigationBar] with a glassmorphism container. Pass [NavigationBarItem] children as content.
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
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier.glassEffect(
            blurRadius = blurRadius,
            containerColor = containerColor,
            containerAlpha = containerAlpha,
            borderColor = borderColor,
            borderAlpha = borderAlpha,
            shape = shape,
            borderWidth = borderWidth,
        ),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface,
        windowInsets = windowInsets,
        content = content,
    )
}
