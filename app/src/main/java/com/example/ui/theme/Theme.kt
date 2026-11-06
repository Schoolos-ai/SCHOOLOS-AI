package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = SchoolOSNeonMint,
    onPrimary = Color(0xFF041C11),
    primaryContainer = SchoolOSPillBg,
    onPrimaryContainer = SchoolOSNeonMint,
    secondary = SchoolOSMintLight,
    onSecondary = Color(0xFF041C11),
    background = SchoolOSDarkBg,
    onBackground = SchoolOSLightText,
    surface = SchoolOSCardBg,
    onSurface = SchoolOSLightText,
    surfaceVariant = SchoolOSPillBg,
    onSurfaceVariant = SchoolOSMutedText,
    outline = SchoolOSCardBorder
)

private val LightColorScheme = lightColorScheme(
    primary = SchoolOSGreen,
    onPrimary = Color.White,
    primaryContainer = SchoolOSGreenSoft,
    onPrimaryContainer = SchoolOSGreenDark,
    secondary = SchoolOSGreenDark,
    onSecondary = Color.White,
    background = SchoolOSBackgroundLight,
    onBackground = SchoolOSInkLight,
    surface = SchoolOSSurfaceLight,
    onSurface = SchoolOSInkLight,
    surfaceVariant = SchoolOSGreenSoft,
    onSurfaceVariant = SchoolOSMutedLight,
    outline = SchoolOSLineLight
)

@Composable
fun SchoolOSTheme(
    darkTheme: Boolean = true, // Default to official website dark aesthetic
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
