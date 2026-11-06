package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppLiveConfig
import com.example.ui.theme.RoleAdminColor
import com.example.ui.theme.SchoolOSCardBg
import com.example.ui.theme.SchoolOSCardBorder
import com.example.ui.theme.SchoolOSDarkBg
import com.example.ui.theme.SchoolOSMintLight
import com.example.ui.theme.SchoolOSMutedText
import com.example.ui.theme.SchoolOSNeonMint
import com.example.ui.theme.SchoolOSPillBg

@Composable
fun AdminControlScreen(
    currentConfig: AppLiveConfig,
    isPublishing: Boolean,
    publishFeedback: String?,
    onPublish: (AppLiveConfig) -> Unit,
    onResetDefaults: () -> Unit,
    onSignOut: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var heroTitle by remember(currentConfig) { mutableStateOf(currentConfig.heroTitle) }
    var heroSubtitle by remember(currentConfig) { mutableStateOf(currentConfig.heroSubtitle) }
    var broadcastAlert by remember(currentConfig) { mutableStateOf(currentConfig.broadcastAlert) }
    var isAlertActive by remember(currentConfig) { mutableStateOf(currentConfig.isAlertActive) }
    var alertUrgency by remember(currentConfig) { mutableStateOf(currentConfig.alertUrgency) }
    var activeLearnersCount by remember(currentConfig) { mutableStateOf(currentConfig.activeLearnersCount) }
    var aiToolsCount by remember(currentConfig) { mutableStateOf(currentConfig.aiToolsCount) }
    var attendanceRate by remember(currentConfig) { mutableStateOf(currentConfig.attendanceRate) }
    var systemStatus by remember(currentConfig) { mutableStateOf(currentConfig.systemStatus) }
    var studentNoticeTitle by remember(currentConfig) { mutableStateOf(currentConfig.studentNoticeTitle) }
    var studentNoticeBody by remember(currentConfig) { mutableStateOf(currentConfig.studentNoticeBody) }
    var parentNoticeTitle by remember(currentConfig) { mutableStateOf(currentConfig.parentNoticeTitle) }
    var parentNoticeBody by remember(currentConfig) { mutableStateOf(currentConfig.parentNoticeBody) }
    var voiceNoticeTitle by remember(currentConfig) { mutableStateOf(currentConfig.voiceNoticeTitle) }
    var voiceNoticeSubtitle by remember(currentConfig) { mutableStateOf(currentConfig.voiceNoticeSubtitle) }
    var allowAiStudyTutor by remember(currentConfig) { mutableStateOf(currentConfig.allowAiStudyTutor) }
    var allowStoreAccess by remember(currentConfig) { mutableStateOf(currentConfig.allowStoreAccess) }
    var allowParentRequests by remember(currentConfig) { mutableStateOf(currentConfig.allowParentRequests) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(SchoolOSDarkBg)
            .padding(horizontal = 16.dp)
            .testTag("admin_control_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- 1. TOP HEADER ROW ---
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("admin_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to public tour",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text(
                            text = "Admin Control Center",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "SchoolOS Cloud Controller",
                            fontSize = 11.5.sp,
                            color = SchoolOSNeonMint
                        )
                    }
                }

                OutlinedButton(
                    onClick = onSignOut,
                    modifier = Modifier.testTag("admin_signout_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = null,
                        tint = RoleAdminColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign Out",
                        fontSize = 11.5.sp,
                        color = RoleAdminColor
                    )
                }
            }
        }

        // --- 2. LIVE FIRESTORE CLOUD STATUS CARD ---
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF092015)),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, SchoolOSNeonMint.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                    .testTag("admin_status_card")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(SchoolOSNeonMint)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "FIRESTORE REAL-TIME SYNC: ACTIVE",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                color = SchoolOSNeonMint
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.CloudDone,
                            contentDescription = null,
                            tint = SchoolOSNeonMint,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Authenticated Admin: aliafzal73216@gmail.com",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Every publish action writes live to Firestore document 'app_config/live_settings'. Connected clients update automatically via real-time snapshot listeners.",
                        fontSize = 11.5.sp,
                        lineHeight = 16.sp,
                        color = Color(0xFFC4DACE)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Last Published: ${currentConfig.lastPublishedTime}",
                            fontSize = 11.sp,
                            color = SchoolOSMintLight
                        )
                    }
                }
            }
        }

        // --- 3. FEEDBACK MESSAGE BANNER ---
        if (!publishFeedback.isNullOrBlank()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF0A3320))
                        .border(1.dp, SchoolOSNeonMint, RoundedCornerShape(12.dp))
                        .padding(14.dp)
                        .testTag("admin_publish_feedback")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = SchoolOSNeonMint,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = publishFeedback,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        // --- 4. SECTION: CAMPUS BROADCAST ALERT ---
        item {
            AdminSectionCard(
                title = "Campus Broadcast Alert",
                icon = Icons.Default.Campaign,
                badgeText = if (isAlertActive) "ACTIVE" else "DISABLED"
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Broadcast Alert Across Entire App",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Text(
                            text = "Displays banner prominently on Student & Parent screens",
                            fontSize = 11.sp,
                            color = SchoolOSMutedText
                        )
                    }
                    Switch(
                        checked = isAlertActive,
                        onCheckedChange = { isAlertActive = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = SchoolOSNeonMint,
                            checkedTrackColor = Color(0xFF0C3D26)
                        ),
                        modifier = Modifier.testTag("alert_active_switch")
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Alert Urgency Level:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Normal", "Important", "Critical").forEach { level ->
                        FilterChip(
                            selected = alertUrgency == level,
                            onClick = { alertUrgency = level },
                            label = { Text(level) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = if (level == "Critical") Color(0xFF5E1313) else Color(0xFF0F3824),
                                selectedLabelColor = if (level == "Critical") Color(0xFFFF8B8B) else SchoolOSNeonMint,
                                containerColor = SchoolOSDarkBg,
                                labelColor = SchoolOSMutedText
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                AdminTextField(
                    label = "Broadcast Message Text",
                    value = broadcastAlert,
                    onValueChange = { broadcastAlert = it },
                    tag = "admin_broadcast_text"
                )
            }
        }

        // --- 5. SECTION: HERO HEADLINE & SUBTITLE ---
        item {
            AdminSectionCard(
                title = "Landing Hero Section",
                icon = Icons.Default.AutoAwesome
            ) {
                AdminTextField(
                    label = "Hero Main Headline",
                    value = heroTitle,
                    onValueChange = { heroTitle = it },
                    tag = "admin_hero_title"
                )
                Spacer(modifier = Modifier.height(12.dp))
                AdminTextField(
                    label = "Hero Subtitle Description",
                    value = heroSubtitle,
                    onValueChange = { heroSubtitle = it },
                    tag = "admin_hero_subtitle"
                )
            }
        }

        // --- 6. SECTION: CAMPUS METRICS & SYSTEM STATUS ---
        item {
            AdminSectionCard(
                title = "Live Campus Metrics & System Health",
                icon = Icons.Default.Speed
            ) {
                AdminTextField(
                    label = "Active Learners Count",
                    value = activeLearnersCount,
                    onValueChange = { activeLearnersCount = it },
                    tag = "admin_metric_learners"
                )
                Spacer(modifier = Modifier.height(10.dp))
                AdminTextField(
                    label = "AI Smart Tools Metric",
                    value = aiToolsCount,
                    onValueChange = { aiToolsCount = it },
                    tag = "admin_metric_tools"
                )
                Spacer(modifier = Modifier.height(10.dp))
                AdminTextField(
                    label = "Campus Attendance Rate",
                    value = attendanceRate,
                    onValueChange = { attendanceRate = it },
                    tag = "admin_metric_attendance"
                )
                Spacer(modifier = Modifier.height(10.dp))
                AdminTextField(
                    label = "System Operational Status",
                    value = systemStatus,
                    onValueChange = { systemStatus = it },
                    tag = "admin_system_status"
                )
            }
        }

        // --- 7. SECTION: NOTICES & CIRCULARS ---
        item {
            AdminSectionCard(
                title = "School Notice Board & Audio Broadcast",
                icon = Icons.Default.NotificationsActive
            ) {
                AdminTextField(
                    label = "Parent Portal Notice Headline",
                    value = parentNoticeTitle,
                    onValueChange = { parentNoticeTitle = it },
                    tag = "admin_parent_notice_title"
                )
                Spacer(modifier = Modifier.height(10.dp))
                AdminTextField(
                    label = "Parent Portal Notice Body",
                    value = parentNoticeBody,
                    onValueChange = { parentNoticeBody = it },
                    tag = "admin_parent_notice_body"
                )
                Spacer(modifier = Modifier.height(12.dp))
                AdminTextField(
                    label = "Student Portal Highlight Title",
                    value = studentNoticeTitle,
                    onValueChange = { studentNoticeTitle = it },
                    tag = "admin_student_notice_title"
                )
                Spacer(modifier = Modifier.height(10.dp))
                AdminTextField(
                    label = "Student Portal Highlight Body",
                    value = studentNoticeBody,
                    onValueChange = { studentNoticeBody = it },
                    tag = "admin_student_notice_body"
                )
                Spacer(modifier = Modifier.height(12.dp))
                AdminTextField(
                    label = "Voice Notice Topic",
                    value = voiceNoticeTitle,
                    onValueChange = { voiceNoticeTitle = it },
                    tag = "admin_voice_title"
                )
            }
        }

        // --- 8. SECTION: FEATURE TOGGLES ---
        item {
            AdminSectionCard(
                title = "Ecosystem Feature Access",
                icon = Icons.Default.Tune
            ) {
                FeatureToggleRow(
                    label = "AI Study Tutor & Flashcards",
                    subtext = "Allow students access to AI syllabus tutor",
                    checked = allowAiStudyTutor,
                    onCheckedChange = { allowAiStudyTutor = it }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FeatureToggleRow(
                    label = "Parent Direct Requests & Complaints",
                    subtext = "Enable confidential parent ticket submissions",
                    checked = allowParentRequests,
                    onCheckedChange = { allowParentRequests = it }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FeatureToggleRow(
                    label = "School Store & Uniform Ordering",
                    subtext = "Allow digital catalog and ordering",
                    checked = allowStoreAccess,
                    onCheckedChange = { allowStoreAccess = it }
                )
            }
        }

        // --- 9. PUBLISH ACTIONS ---
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SchoolOSCardBg),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(16.dp))
                    .padding(vertical = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = {
                            val newConfig = currentConfig.copy(
                                heroTitle = heroTitle,
                                heroSubtitle = heroSubtitle,
                                broadcastAlert = broadcastAlert,
                                isAlertActive = isAlertActive,
                                alertUrgency = alertUrgency,
                                activeLearnersCount = activeLearnersCount,
                                aiToolsCount = aiToolsCount,
                                attendanceRate = attendanceRate,
                                systemStatus = systemStatus,
                                studentNoticeTitle = studentNoticeTitle,
                                studentNoticeBody = studentNoticeBody,
                                parentNoticeTitle = parentNoticeTitle,
                                parentNoticeBody = parentNoticeBody,
                                voiceNoticeTitle = voiceNoticeTitle,
                                voiceNoticeSubtitle = voiceNoticeSubtitle,
                                allowAiStudyTutor = allowAiStudyTutor,
                                allowStoreAccess = allowStoreAccess,
                                allowParentRequests = allowParentRequests
                            )
                            onPublish(newConfig)
                        },
                        enabled = !isPublishing,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SchoolOSNeonMint,
                            contentColor = Color(0xFF04190E)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("admin_publish_button")
                    ) {
                        if (isPublishing) {
                            CircularProgressIndicator(
                                color = Color(0xFF04190E),
                                modifier = Modifier.size(22.dp),
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Publishing to Firestore Cloud...",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.CloudUpload,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Publish Changes Live (Sync to All Devices)",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = onResetDefaults,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("admin_reset_defaults_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            tint = SchoolOSMintLight,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Reset All Settings to Defaults",
                            fontSize = 12.5.sp,
                            color = SchoolOSMintLight
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
private fun AdminSectionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    badgeText: String? = null,
    content: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SchoolOSCardBg),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF0F2E20)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = SchoolOSNeonMint,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = title,
                        fontSize = 14.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                if (badgeText != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF0E3823))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = badgeText,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SchoolOSNeonMint
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            content()
        }
    }
}

@Composable
private fun AdminTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    tag: String
) {
    Column {
        Text(
            text = label,
            fontSize = 11.5.sp,
            fontWeight = FontWeight.SemiBold,
            color = SchoolOSMintLight
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = SchoolOSNeonMint,
                unfocusedBorderColor = SchoolOSCardBorder,
                focusedContainerColor = SchoolOSDarkBg,
                unfocusedContainerColor = SchoolOSDarkBg
            ),
            modifier = Modifier
                .fillMaxWidth()
                .testTag(tag)
        )
    }
}

@Composable
private fun FeatureToggleRow(
    label: String,
    subtext: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(
                text = subtext,
                fontSize = 11.sp,
                color = SchoolOSMutedText
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = SchoolOSNeonMint,
                checkedTrackColor = Color(0xFF0C3D26)
            )
        )
    }
}
