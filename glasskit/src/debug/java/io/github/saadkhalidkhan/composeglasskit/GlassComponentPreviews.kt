package io.github.saadkhalidkhan.composeglasskit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.saadkhalidkhan.composeglasskit.components.GlassButton
import io.github.saadkhalidkhan.composeglasskit.components.GlassCard
import io.github.saadkhalidkhan.composeglasskit.components.GlassNavBar
import io.github.saadkhalidkhan.composeglasskit.modifiers.glassEffect

@Preview(showBackground = true, backgroundColor = 0xFF6200EE)
@Composable
private fun GlassCardPreview() {
    MaterialTheme {
        GlassCard(modifier = Modifier.padding(16.dp)) {
            Text("Glass Card", modifier = Modifier.padding(16.dp), color = Color.White)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF03DAC6)
@Composable
private fun GlassButtonPreview() {
    MaterialTheme {
        GlassButton(onClick = {}) {
            Text("Glass Button")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFBB86FC)
@Composable
private fun GlassNavBarPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            GlassNavBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Profile") },
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("Settings") },
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF6200EE)
@Composable
private fun GlassDialogSurfacePreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .glassEffect(
                    blurRadius = 16.dp,
                    shape = MaterialTheme.shapes.extraLarge,
                )
                .padding(24.dp),
        ) {
            Text("Glass Dialog", color = Color.White)
        }
    }
}
