package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.outlined.BrightnessAuto
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Role
import com.example.ui.theme.SchoolOSCardBorder
import com.example.ui.theme.SchoolOSDarkBg
import com.example.ui.theme.SchoolOSGreen
import com.example.ui.theme.SchoolOSHeaderBg
import com.example.ui.theme.SchoolOSMintLight
import com.example.ui.theme.SchoolOSNeonMint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolOSTopBar(
    currentRole: Role?,
    isDarkMode: Boolean?,
    isAdminLoggedIn: Boolean = false,
    onBack: (() -> Unit)?,
    onResetDemo: () -> Unit,
    onToggleTheme: () -> Unit,
    onOpenSafetyInfo: () -> Unit,
    onOpenAdmin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = SchoolOSHeaderBg,
        tonalElevation = 2.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    // Official Logo Icon: Green rounded badge with graduation cap
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(currentRole?.accentColor ?: SchoolOSGreen)
                            .border(1.dp, Color(0x33FFFFFF), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (currentRole != null) "${currentRole.label} Portal" else "SchoolOS AI",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 17.sp,
                            color = Color.White,
                            lineHeight = 19.sp
                        )
                        Text(
                            text = if (currentRole != null) "WORKSPACE · DEMO" else "LEARN WITH AI & CONFIDENCE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.5.sp,
                            letterSpacing = 1.1.sp,
                            color = currentRole?.accentColor ?: SchoolOSMintLight
                        )
                    }
                }
            },
            navigationIcon = {
                if (onBack != null) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("top_bar_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back",
                            tint = Color.White
                        )
                    }
                }
            },
            actions = {
                // Admin Portal Access Button
                IconButton(
                    onClick = onOpenAdmin,
                    modifier = Modifier.testTag("top_bar_admin_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.AdminPanelSettings,
                        contentDescription = if (isAdminLoggedIn) "Admin Control Center" else "Administrator Sign In",
                        tint = if (isAdminLoggedIn) SchoolOSNeonMint else Color(0xFF9BAEA4)
                    )
                }

                // Safety Info Button
                IconButton(
                    onClick = onOpenSafetyInfo,
                    modifier = Modifier.testTag("safety_info_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = "Demo Safety and Boundaries",
                        tint = SchoolOSNeonMint
                    )
                }

                // Theme Mode Toggle
                IconButton(
                    onClick = onToggleTheme,
                    modifier = Modifier.testTag("theme_toggle_button")
                ) {
                    val icon = when (isDarkMode) {
                        true -> Icons.Default.DarkMode
                        false -> Icons.Default.LightMode
                        null -> Icons.Outlined.BrightnessAuto
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = "Toggle display theme",
                        tint = Color(0xFF9BAEA4)
                    )
                }

                // Reset Demo Pill Button
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF0F2F21))
                        .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(8.dp))
                        .clickable(onClick = onResetDemo)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .testTag("reset_demo_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            tint = SchoolOSNeonMint,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Reset",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SchoolOSNeonMint
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = SchoolOSHeaderBg
            ),
            modifier = Modifier.testTag("top_bar")
        )
    }
}
