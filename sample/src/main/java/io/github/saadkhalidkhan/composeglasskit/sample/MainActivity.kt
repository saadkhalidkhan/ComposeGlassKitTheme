package io.github.saadkhalidkhan.composeglasskit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Launch
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import io.github.saadkhalidkhan.composeglasskit.components.GlassButton
import io.github.saadkhalidkhan.composeglasskit.components.GlassCard
import io.github.saadkhalidkhan.composeglasskit.components.GlassDialog
import io.github.saadkhalidkhan.composeglasskit.components.GlassNavBar
import io.github.saadkhalidkhan.composeglasskit.sample.ui.theme.ComposeGlassKitTheme
import io.github.saadkhalidkhan.composeglasskit.theme.GlassConfig
import io.github.saadkhalidkhan.composeglasskit.theme.GlassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeGlassKitTheme {
                SampleAppContent()
            }
        }
    }
}

private val backgroundImages = listOf(
    "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?q=80&w=1964&auto=format&fit=crop",
    "https://images.unsplash.com/photo-1550684848-fac1c5b4e853?q=80&w=2070&auto=format&fit=crop",
    "https://images.unsplash.com/photo-1579546929518-9e396f3cc809?q=80&w=2070&auto=format&fit=crop",
    "https://images.unsplash.com/photo-1614850523296-d8c1af93d400?q=80&w=2070&auto=format&fit=crop"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleAppContent() {
    var blurRadius by remember { mutableFloatStateOf(16f) }
    var containerAlpha by remember { mutableFloatStateOf(0.12f) }
    var borderAlpha by remember { mutableFloatStateOf(0.25f) }
    var selectedImageIndex by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(0) }

    val glassConfig = GlassConfig(
        blurRadius = blurRadius.dp,
        containerAlpha = containerAlpha,
        borderAlpha = borderAlpha
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        AsyncImage(
            model = backgroundImages[selectedImageIndex],
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Overlay scrim to ensure text readability on light images
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )

        GlassTheme(config = glassConfig) {
            Scaffold(
                containerColor = Color.Transparent,
                bottomBar = {
                    GlassNavBar(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(24.dp) // Floating nav bar look
                    ) {
                        NavigationBarItem(
                            selected = selectedTab == 0,
                            onClick = { selectedTab = 0 },
                            icon = { Icon(Icons.Rounded.Home, contentDescription = "Home") },
                            label = { Text("Home") },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                                indicatorColor = Color.White.copy(alpha = 0.1f)
                            )
                        )
                        NavigationBarItem(
                            selected = selectedTab == 1,
                            onClick = { selectedTab = 1 },
                            icon = { Icon(Icons.Rounded.Dashboard, contentDescription = "Components") },
                            label = { Text("Showcase") },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                                indicatorColor = Color.White.copy(alpha = 0.1f)
                            )
                        )
                        NavigationBarItem(
                            selected = selectedTab == 2,
                            onClick = { selectedTab = 2 },
                            icon = { Icon(Icons.Rounded.Settings, contentDescription = "Settings") },
                            label = { Text("Config") },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                                indicatorColor = Color.White.copy(alpha = 0.1f)
                            )
                        )
                    }
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Title with Gradient
                    Text(
                        text = "ComposeGlassKit",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = (-1.5).sp,
                            brush = Brush.linearGradient(
                                colors = listOf(Color.White, Color.White.copy(alpha = 0.6f))
                            )
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Text(
                        "Beautiful, performant glassmorphism for Jetpack Compose.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    // Real-time Configuration Card
                    GlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        containerAlpha = 0.15f,
                        borderAlpha = 0.4f
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Rounded.Tune, null, tint = Color.White)
                                Spacer(Modifier.width(12.dp))
                                Text(
                                    "Visual Editor",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(Modifier.height(20.dp))
                            
                            ConfigSlider(
                                label = "Blur Intensity: ${blurRadius.toInt()}dp",
                                value = blurRadius,
                                onValueChange = { blurRadius = it },
                                valueRange = 0f..40f
                            )
                            
                            ConfigSlider(
                                label = "Glass Opacity: ${"%.2f".format(containerAlpha)}",
                                value = containerAlpha,
                                onValueChange = { containerAlpha = it },
                                valueRange = 0.05f..0.6f
                            )
                            
                            ConfigSlider(
                                label = "Edge Highlight: ${"%.2f".format(borderAlpha)}",
                                value = borderAlpha,
                                onValueChange = { borderAlpha = it },
                                valueRange = 0f..0.8f
                            )
                        }
                    }

                    // Background Gallery with horizontal scroll
                    Text(
                        "Scenery Presets", 
                        color = Color.White, 
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        backgroundImages.forEachIndexed { index, url ->
                            Card(
                                onClick = { selectedImageIndex = index },
                                modifier = Modifier
                                    .height(100.dp)
                                    .weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                border = if (selectedImageIndex == index) BorderStroke(3.dp, MaterialTheme.colorScheme.primary) else null,
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                            ) {
                                AsyncImage(
                                    model = url,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }

                    // Components Showcase
                    Text(
                        "Component Library", 
                        color = Color.White, 
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    
                    GlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Rounded.Layers, null, tint = Color.White)
                            }
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text("Dynamic Layering", style = MaterialTheme.typography.titleSmall, color = Color.White)
                                Text("Backdrop blur updates in real-time.", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.7f))
                            }
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        GlassButton(
                            onClick = { showDialog = true },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Icon(Icons.Rounded.Launch, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Open Modal")
                        }

                        GlassButton(
                            onClick = { },
                            modifier = Modifier.weight(1f),
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            containerAlpha = 0.3f,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Icon(Icons.Rounded.Favorite, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Save UI")
                        }
                    }

                    // Action Icons Row
                    GlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = CircleShape,
                        containerAlpha = 0.1f
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            listOf(Icons.Rounded.Share, Icons.Rounded.Download, Icons.Rounded.Edit, Icons.Rounded.MoreHoriz).forEach { icon ->
                                IconButton(
                                    onClick = { },
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color.White.copy(alpha = 0.05f), CircleShape)
                                ) {
                                    Icon(icon, null, tint = Color.White, modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                    
                    Spacer(Modifier.height(60.dp))
                }
            }

            if (showDialog) {
                GlassDialog(
                    onDismissRequest = { showDialog = false },
                    blurRadius = 30.dp,
                    containerAlpha = 0.25f
                ) {
                    Column(
                        modifier = Modifier.padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .background(Brush.sweepGradient(listOf(Color.Cyan, Color.Magenta, Color.Cyan)), CircleShape)
                                .padding(2.dp)
                                .background(Color.Black, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Rounded.AutoAwesome,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Text(
                            "Pro Glass Effects",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            "Optimized for high-performance rendering on Android 12+. Experience the future of Android UI.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        GlassButton(
                            onClick = { showDialog = false },
                            modifier = Modifier.fillMaxWidth(),
                            containerColor = MaterialTheme.colorScheme.primary,
                            containerAlpha = 0.5f,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Dismiss", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConfigSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>
) {
    Column {
        Text(label, color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.labelMedium)
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White,
                inactiveTrackColor = Color.White.copy(alpha = 0.3f)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeGlassKitTheme {
        SampleAppContent()
    }
}
