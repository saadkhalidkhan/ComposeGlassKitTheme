package com.saadkhan.composeglasskit.components

import android.os.Build
import android.view.WindowManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.saadkhan.composeglasskit.modifiers.glassEffect
import com.saadkhan.composeglasskit.theme.GlassTheme

/**
 * A dialog with a glassmorphism panel.
 *
 * ## Blur behavior
 *
 * - **API 31+**: Sets window `FLAG_BLUR_BEHIND` so the activity behind the dialog is blurred.
 *   Surface blur on the dialog box is disabled in this case to avoid double-blur.
 * - **API 26–30**: Falls back to [glassEffect] surface blur on the dialog content only.
 */
@Composable
fun GlassDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    blurRadius: Dp = Dp.Unspecified,
    containerColor: Color? = null,
    containerAlpha: Float = Float.NaN,
    borderColor: Color? = null,
    borderAlpha: Float = Float.NaN,
    borderWidth: Dp = Dp.Unspecified,
    properties: DialogProperties = DialogProperties(),
    content: @Composable BoxScope.() -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        val view = LocalView.current
        val isAtLeastS = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        val config = GlassTheme.config
        val finalBlurRadius = if (blurRadius != Dp.Unspecified) blurRadius else config.blurRadius

        SideEffect {
            val window = (view.parent as? DialogWindowProvider)?.window
            if (isAtLeastS) {
                window?.let {
                    it.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
                    it.attributes.blurBehindRadius =
                        (finalBlurRadius.value * 2).toInt().coerceAtLeast(1)
                }
            }
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp)
                .glassEffect(
                    blurRadius = if (isAtLeastS) 0.dp else finalBlurRadius,
                    containerColor = containerColor,
                    containerAlpha = containerAlpha,
                    borderColor = borderColor,
                    borderAlpha = borderAlpha,
                    shape = shape,
                    borderWidth = borderWidth,
                )
                .padding(24.dp),
            content = content,
        )
    }
}
