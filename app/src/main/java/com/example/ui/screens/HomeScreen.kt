package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Role
import com.example.ui.components.SafetyBanner
import com.example.ui.theme.RoleParentColor
import com.example.ui.theme.RoleStudentColor
import com.example.ui.theme.SchoolOSCardBg
import com.example.ui.theme.SchoolOSCardBorder
import com.example.ui.theme.SchoolOSDarkBg
import com.example.ui.theme.SchoolOSMintLight
import com.example.ui.theme.SchoolOSMutedText
import com.example.ui.theme.SchoolOSNeonMint
import com.example.ui.theme.SchoolOSPillBg

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onSelectRole: (Role) -> Unit,
    onOpenSafetyInfo: () -> Unit,
    onOpenAdmin: () -> Unit,
    liveConfig: com.example.model.AppLiveConfig = com.example.model.AppLiveConfig(),
    isAdminLoggedIn: Boolean = false,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(SchoolOSDarkBg)
            .padding(horizontal = 16.dp)
            .testTag("home_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- LIVE BROADCAST ALERT (Published Real-Time by Administrator) ---
        if (liveConfig.isAlertActive && liveConfig.broadcastAlert.isNotBlank()) {
            item {
                val isUrgent = liveConfig.alertUrgency.equals("Critical", ignoreCase = true)
                val alertBg = if (isUrgent) Color(0xFF330C0C) else Color(0xFF072417)
                val alertBorder = if (isUrgent) Color(0xFFFF5252) else SchoolOSNeonMint
                val badgeBg = if (isUrgent) Color(0xFF5A1515) else Color(0xFF0F3A26)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(alertBg)
                        .border(1.dp, alertBorder.copy(alpha = 0.6f), RoundedCornerShape(14.dp))
                        .padding(14.dp)
                        .testTag("home_broadcast_alert")
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Campaign,
                                    contentDescription = null,
                                    tint = if (isUrgent) Color(0xFFFF8A8A) else SchoolOSNeonMint,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "CAMPUS BROADCAST",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp,
                                    color = if (isUrgent) Color(0xFFFF8A8A) else SchoolOSNeonMint
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(badgeBg)
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = liveConfig.alertUrgency.uppercase(),
                                    fontSize = 9.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = liveConfig.broadcastAlert,
                            fontSize = 13.sp,
                            lineHeight = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // --- 1. HERO SECTION (Matched to Official SchoolOS AI Web) ---
        item {
            Spacer(modifier = Modifier.height(10.dp))

            // Eyebrow Capsule: Spark + LEARNING, TEACHING AND SCHOOL LIFE
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(SchoolOSPillBg)
                    .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .testTag("home_eyebrow")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = SchoolOSMintLight,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "LEARNING, TEACHING AND SCHOOL LIFE",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.1.sp,
                        color = SchoolOSMintLight
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Main Hero Headline (Dynamic Live Config with Neon Mint accent)
            val parts = liveConfig.heroTitle.split("\n", limit = 2)
            val headline = buildAnnotatedString {
                append(parts.firstOrNull() ?: "A brighter way to learn,")
                if (parts.size > 1) {
                    append("\n")
                    withStyle(SpanStyle(color = SchoolOSNeonMint)) {
                        append(parts[1])
                    }
                } else {
                    withStyle(SpanStyle(color = SchoolOSNeonMint)) {
                        append(" together.")
                    }
                }
            }
            Text(
                text = headline,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 38.sp,
                color = Color.White,
                modifier = Modifier.testTag("home_title")
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subtitle
            Text(
                text = liveConfig.heroSubtitle,
                fontSize = 13.5.sp,
                lineHeight = 19.sp,
                color = SchoolOSMutedText
            )

            Spacer(modifier = Modifier.height(16.dp))

            // CTA Button Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Primary CTA: Enter your portal ->
                Button(
                    onClick = { onSelectRole(Role.STUDENT) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SchoolOSNeonMint,
                        contentColor = Color(0xFF041C11)
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.testTag("hero_enter_portal_button")
                ) {
                    Text(
                        text = "Enter your portal",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                }

                // Secondary CTA: Safety Info
                OutlinedButton(
                    onClick = onOpenSafetyInfo,
                    shape = RoundedCornerShape(24.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(listOf(SchoolOSCardBorder, SchoolOSCardBorder))
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF0D2218),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.testTag("hero_safety_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = SchoolOSMintLight,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Safe Demo",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Feature Highlights tags
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = SchoolOSNeonMint,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Student & parent portals",
                        fontSize = 11.sp,
                        color = SchoolOSMutedText
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = SchoolOSNeonMint,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Fictional demo data",
                        fontSize = 11.sp,
                        color = SchoolOSMutedText
                    )
                }
            }
        }

        // --- HERO VISUAL CARD (Matching Official Web Hero Widget) ---
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF0F261B), Color(0xFF091A12))
                        )
                    )
                    .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(18.dp))
                    .padding(16.dp)
                    .testTag("hero_showcase_card")
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "YOUR SCHOOL, CONNECTED",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            color = SchoolOSMintLight
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SchoolOSPillBg)
                                .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "SchoolOS AI",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SchoolOSNeonMint
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Learn. Grow. Achieve.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "Every learner, one step at a time",
                        fontSize = 12.sp,
                        color = SchoolOSMutedText
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Floating preview pills
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF0A1D14))
                                .border(1.dp, Color(0xFF163826), RoundedCornerShape(10.dp))
                                .padding(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "💬", fontSize = 14.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(
                                        text = "Learn together",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Ask. Share. Understand.",
                                        fontSize = 9.5.sp,
                                        color = SchoolOSMutedText
                                    )
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF0A1D14))
                                .border(1.dp, Color(0xFF163826), RoundedCornerShape(10.dp))
                                .padding(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "🗓️", fontSize = 14.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(
                                        text = "Study tools",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Plans, focus and progress",
                                        fontSize = 9.5.sp,
                                        color = SchoolOSMutedText
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // --- 2. QUICK METRICS HIGHLIGHTS BAR (From Screenshot) ---
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF0A1D13))
                    .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(14.dp))
                    .padding(horizontal = 14.dp, vertical = 10.dp)
                    .testTag("highlights_metrics_bar")
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MetricChip(icon = "🎓", title = liveConfig.activeLearnersCount, desc = "Active Learners")
                    MetricChip(icon = "⚡", title = liveConfig.aiToolsCount, desc = "Smart Tools")
                    MetricChip(icon = "✨", title = liveConfig.attendanceRate, desc = "Attendance")
                    MetricChip(icon = "🛡️", title = if (isAdminLoggedIn) "Admin Live" else "Cloud Sync", desc = "Firestore 🟢")
                }
            }
        }

        // --- ALWAYS VISIBLE SAFETY BANNER ---
        item {
            SafetyBanner()
        }

        // --- 3. DEDICATED AI WORKSPACES (STUDENT & PARENT ONLY!) ---
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "SCHOOL & HOME WORKSPACES",
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.1.sp,
                color = SchoolOSMintLight
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "A useful home for every role",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Choose a portal below to explore its live feature catalog and try interactive sample tools.",
                fontSize = 12.5.sp,
                color = SchoolOSMutedText
            )
        }

        // CARD 01: STUDENT PORTAL (Matched to official screenshot #01)
        item {
            PortalNavigationCard(
                role = Role.STUDENT,
                portalNumber = "01",
                badgeText = "STUDENT WORKSPACE",
                accentColor = RoleStudentColor,
                title = "Student portal",
                description = "A personal space to learn, practise and stay organised.",
                highlights = "Online classes · Shared whiteboard · AI tutor",
                featureCount = 37,
                icon = Icons.Default.School,
                onClick = { onSelectRole(Role.STUDENT) }
            )
        }

        // CARD 02: PARENT PORTAL (Matched to official screenshot #02)
        item {
            PortalNavigationCard(
                role = Role.PARENT,
                portalNumber = "02",
                badgeText = "PARENT WORKSPACE",
                accentColor = RoleParentColor,
                title = "Parent portal",
                description = "Keep up with attendance, progress and school conversations.",
                highlights = "Learner progress · Diary · Messages",
                featureCount = 30,
                icon = Icons.Default.FamilyRestroom,
                onClick = { onSelectRole(Role.PARENT) }
            )
        }

        // --- 4. FEATURE PREVIEWS SECTION (Matching Official Web Screenshot) ---
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "SCHOOLOS FEATURES",
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.1.sp,
                color = SchoolOSMintLight
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Tools for the whole school community",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Explore what students and parents can access inside their connected portal.",
                fontSize = 12.5.sp,
                color = SchoolOSMutedText
            )
        }

        // Grid of 6 School Features (from screenshot)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FeatureShowcaseCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.AutoMirrored.Filled.MenuBook,
                        title = "Learning library",
                        description = "Teachers share class notes, PDFs, word & presentation companion files.",
                        actionText = "Explore student portal →",
                        onClick = { onSelectRole(Role.STUDENT) }
                    )
                    FeatureShowcaseCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.AutoAwesome,
                        title = "AI study assistant",
                        description = "Ask questions and receive step-by-step guidance from simulated AI.",
                        actionText = "Explore student portal →",
                        onClick = { onSelectRole(Role.STUDENT) }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FeatureShowcaseCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.DateRange,
                        title = "Personal timetable",
                        description = "Build a study plan around subjects, available time and upcoming exams.",
                        actionText = "Explore student portal →",
                        onClick = { onSelectRole(Role.STUDENT) }
                    )
                    FeatureShowcaseCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.ChatBubbleOutline,
                        title = "Ask your teacher",
                        description = "Send a private learning question and continue conversations safely.",
                        actionText = "Explore student portal →",
                        onClick = { onSelectRole(Role.STUDENT) }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FeatureShowcaseCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Campaign,
                        title = "School notices",
                        description = "Stay up to date with school-wide announcements, circulars and voice notes.",
                        actionText = "Explore parent portal →",
                        onClick = { onSelectRole(Role.PARENT) }
                    )
                    FeatureShowcaseCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.CheckCircle,
                        title = "Two dedicated portals",
                        description = "Separate dedicated workspaces for students, parents and family support.",
                        actionText = "Choose your portal →",
                        onClick = { onSelectRole(Role.STUDENT) }
                    )
                }
            }
        }

        // --- 5. "MORE THAN A NOTICE BOARD" SECTION (From Screenshot) ---
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFF091A11))
                    .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(18.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "BUILT FOR THE SCHOOL DAY",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        color = SchoolOSMintLight
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "More than a notice board",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Learning, communication and school routines connect in one place.",
                        fontSize = 12.sp,
                        color = SchoolOSMutedText
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FeatureChip(Icons.Default.CheckCircle, "Attendance tracking")
                        FeatureChip(Icons.Default.Assignment, "Assignments & tasks")
                        FeatureChip(Icons.Default.Quiz, "Quiz practice")
                        FeatureChip(Icons.Default.PlayCircleOutline, "Video lessons")
                        FeatureChip(Icons.Default.Mic, "Voice notices")
                        FeatureChip(Icons.Default.Timer, "Study timer")
                        FeatureChip(Icons.Default.DateRange, "Study planning")
                        FeatureChip(Icons.Default.ChatBubbleOutline, "Private messaging")
                        FeatureChip(Icons.Default.TrendingUp, "Progress insights")
                        FeatureChip(Icons.Default.HelpOutline, "Safe demo sandbox")
                    }
                }
            }
        }

        // --- 6. BOTTOM CALL TO ACTION CARD ---
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF0E291C), Color(0xFF143B28))
                        )
                    )
                    .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(16.dp))
                    .padding(18.dp)
                    .testTag("home_bottom_cta_card")
            ) {
                Column {
                    Text(
                        text = "READY TO TAKE A LOOK?",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.1.sp,
                        color = SchoolOSMintLight
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Find your school workspace",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Start exploring as a student or parent with instant access to sample features.",
                        fontSize = 12.sp,
                        color = SchoolOSMutedText
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { onSelectRole(Role.STUDENT) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = RoleStudentColor,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Student Portal", fontSize = 12.5.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = { onSelectRole(Role.PARENT) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = RoleParentColor,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Parent Portal", fontSize = 12.5.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
        }

        // --- 7. ADMINISTRATOR CLOUD ACCESS CARD ---
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF071911)),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, if (isAdminLoggedIn) SchoolOSNeonMint else SchoolOSCardBorder, RoundedCornerShape(16.dp))
                    .testTag("home_admin_card")
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
                                    .background(Color(0xFF0F2E20))
                                    .border(1.dp, SchoolOSNeonMint.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AdminPanelSettings,
                                    contentDescription = null,
                                    tint = SchoolOSNeonMint,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Administrator Control",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = if (isAdminLoggedIn) "Authenticated: aliafzal73216@gmail.com" else "Firebase Real-Time Ecosystem Manager",
                                    fontSize = 10.5.sp,
                                    color = if (isAdminLoggedIn) SchoolOSNeonMint else SchoolOSMintLight
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isAdminLoggedIn) Color(0xFF0A3320) else Color(0xFF10281D))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = if (isAdminLoggedIn) "LIVE CONNECTED" else "CLOUD AUTH",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isAdminLoggedIn) SchoolOSNeonMint else SchoolOSMintLight
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Sign in as Administrator (aliafzal73216@gmail.com) to edit broadcasts, customize portal highlights, adjust campus metrics, and publish changes in real time across all devices via Firestore.",
                        fontSize = 11.5.sp,
                        lineHeight = 16.sp,
                        color = Color(0xFFBED2C7)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = onOpenAdmin,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isAdminLoggedIn) SchoolOSNeonMint else Color(0xFF133626),
                            contentColor = if (isAdminLoggedIn) Color(0xFF04190E) else Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .testTag("home_open_admin_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AdminPanelSettings,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isAdminLoggedIn) "Open Admin Control Center (Live)" else "Admin Sign In & Control",
                                fontSize = 12.5.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MetricChip(
    icon: String,
    title: String,
    desc: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = icon, fontSize = 12.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = title,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Text(
            text = desc,
            fontSize = 9.sp,
            color = SchoolOSMutedText
        )
    }
}

@Composable
private fun PortalNavigationCard(
    role: Role,
    portalNumber: String,
    badgeText: String,
    accentColor: Color,
    title: String,
    description: String,
    highlights: String,
    featureCount: Int,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .testTag("role_card_${role.key}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = SchoolOSCardBg
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            // Top Bar: Glyph Tile + Tag + Number Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(accentColor.copy(alpha = 0.2f))
                            .border(1.dp, accentColor.copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = accentColor,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = badgeText,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        color = accentColor
                    )
                }

                Text(
                    text = portalNumber,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = SchoolOSMutedText.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Portal Title
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Description
            Text(
                text = description,
                fontSize = 12.5.sp,
                lineHeight = 17.sp,
                color = SchoolOSMutedText
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Highlights
            Text(
                text = highlights,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFC5D6CD)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Enter Button Pill
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF071B12))
                    .border(1.dp, accentColor.copy(alpha = 0.35f), RoundedCornerShape(10.dp))
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Enter as ${role.label.lowercase()}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FeatureShowcaseCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    description: String,
    actionText: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(SchoolOSCardBg)
            .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Column {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SchoolOSNeonMint,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 11.sp,
                lineHeight = 15.sp,
                color = SchoolOSMutedText
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = actionText,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = SchoolOSNeonMint
                )
            }
        }
    }
}

@Composable
private fun FeatureChip(icon: ImageVector, label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF0F261B))
            .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(8.dp))
            .padding(horizontal = 9.dp, vertical = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SchoolOSMintLight,
                modifier = Modifier.size(13.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                color = Color(0xFFE2E8E4)
            )
        }
    }
}
