package com.saadkhan.composeglasskit.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GlassConfigTest {

    @Test
    fun defaultConfig_usesDocumentedDefaults() {
        val config = GlassConfig()

        assertEquals(16.dp, config.blurRadius)
        assertEquals(0.15f, config.containerAlpha, 0.001f)
        assertEquals(0.2f, config.borderAlpha, 0.001f)
        assertEquals(1.dp, config.borderWidth)
        assertEquals(Color.Unspecified, config.glassColor)
        assertEquals(Color.Unspecified, config.borderColor)
    }

    @Test
    fun customConfig_overridesDefaults() {
        val config = GlassConfig(
            blurRadius = 24.dp,
            containerAlpha = 0.3f,
            borderAlpha = 0.5f,
            borderWidth = 2.dp,
            glassColor = Color.Red,
            borderColor = Color.Blue,
        )

        assertEquals(24.dp, config.blurRadius)
        assertEquals(0.3f, config.containerAlpha, 0.001f)
        assertEquals(0.5f, config.borderAlpha, 0.001f)
        assertEquals(2.dp, config.borderWidth)
        assertEquals(Color.Red, config.glassColor)
        assertEquals(Color.Blue, config.borderColor)
    }

    @Test
    fun defaultConfig_copy_preservesValues() {
        val copy = GlassConfig().copy(blurRadius = 8.dp)
        assertEquals(8.dp, copy.blurRadius)
        assertTrue(copy.containerAlpha > 0f)
    }
}
