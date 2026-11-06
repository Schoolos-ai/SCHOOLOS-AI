package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SchoolOSWarningBgDark
import com.example.ui.theme.SchoolOSWarningBgLight
import com.example.ui.theme.SchoolOSWarningBorderDark
import com.example.ui.theme.SchoolOSWarningBorderLight
import com.example.ui.theme.SchoolOSWarningTextDark
import com.example.ui.theme.SchoolOSWarningTextLight

@Composable
fun SafetyBanner(
    modifier: Modifier = Modifier,
    customText: String? = null
) {
    val isDark = isSystemInDarkTheme()
    val bg = if (isDark) SchoolOSWarningBgDark else SchoolOSWarningBgLight
    val border = if (isDark) SchoolOSWarningBorderDark else SchoolOSWarningBorderLight
    val text = if (isDark) SchoolOSWarningTextDark else SchoolOSWarningTextLight

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .border(1.dp, border, RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .testTag("safety_banner")
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Safety Information",
                tint = text,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = customText ?: "Sample mode: names, figures and activities are fictional. Nothing here is submitted to a school.",
                color = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp
            )
        }
    }
}
