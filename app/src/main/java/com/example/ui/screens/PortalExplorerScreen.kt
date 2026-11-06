package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DemoAction
import com.example.model.FeatureCategory
import com.example.model.FeatureInfo
import com.example.model.Role
import com.example.ui.components.FeatureInteractiveCard
import com.example.ui.components.RoleStatRow
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

@Composable
fun PortalExplorerScreen(
    currentRole: Role,
    selectedFeature: String,
    searchQuery: String,
    selectedCategory: FeatureCategory,
    feedbackMessage: String?,
    filteredFeatures: List<String>,
    featureInfo: FeatureInfo?,
    onSelectRole: (Role) -> Unit,
    onSelectFeature: (String) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSelectCategory: (FeatureCategory) -> Unit,
    onActionClick: (DemoAction) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Intercept system back press
    BackHandler {
        onBack()
    }

    // Strictly active portals: Student & Parent ONLY
    val allRoles = Role.activePortals

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(SchoolOSDarkBg)
            .padding(horizontal = 16.dp)
            .testTag("portal_explorer_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // --- 1. PORTAL SWITCHER BAR (Student & Parent ONLY) ---
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SWITCH WORKSPACE",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.1.sp,
                    color = SchoolOSMintLight
                )
                Text(
                    text = "Active: ${currentRole.label}",
                    fontSize = 10.sp,
                    color = currentRole.accentColor,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                allRoles.forEach { r ->
                    val isSelected = r == currentRole
                    val icon = if (r == Role.STUDENT) Icons.Default.School else Icons.Default.FamilyRestroom
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (isSelected) r.accentColor.copy(alpha = 0.22f)
                                else SchoolOSCardBg
                            )
                            .border(
                                width = if (isSelected) 1.5.dp else 1.dp,
                                color = if (isSelected) r.accentColor
                                else SchoolOSCardBorder,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { onSelectRole(r) }
                            .padding(horizontal = 12.dp, vertical = 9.dp)
                            .testTag("role_switcher_${r.key}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(r.accentColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "${r.label} Portal",
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else SchoolOSMutedText
                                )
                                Text(
                                    text = "${r.features.size} Features",
                                    fontSize = 9.5.sp,
                                    color = r.accentColor,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }

        // --- 2. COMMAND CENTER HEADER (Matched to official screenshot #fd2df772 / #20687c9b) ---
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("role_identity_banner"),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Top Tagline Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (currentRole == Role.STUDENT) "LEARN · PRACTISE · PROGRESS" else "STAY CLOSE TO EVERY STEP",
                            fontSize = 9.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = currentRole.accentColor,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "${currentRole.label} Portal",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(currentRole.accentColor.copy(alpha = 0.15f))
                            .border(1.dp, currentRole.accentColor.copy(alpha = 0.35f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 9.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${currentRole.features.size} Features",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = currentRole.accentColor
                        )
                    }
                }

                // Vibrant Mint Green Command Center Card (from official screenshot)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFF2EDB8B), Color(0xFF34D399), Color(0xFF4ADE80))
                            )
                        )
                        .padding(16.dp)
                        .testTag("command_center_banner")
                ) {
                    Column {
                        Text(
                            text = if (currentRole == Role.STUDENT) "STUDENT COMMAND CENTER" else "PARENT COMMAND CENTER",
                            fontSize = 9.5.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.1.sp,
                            color = Color(0xFF042F1A)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (currentRole == Role.STUDENT) "Welcome, Alex." else "Welcome, Morgan.",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF022615)
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "Your school workspace is live. 50+ smart educational tools and AI assistants ready.",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF064E3B),
                            lineHeight = 15.sp
                        )
                    }
                }

                // Role Stat Row
                RoleStatRow(stats = currentRole.stats)
            }
        }

        // --- 3. SAFETY MODE NOTICE BANNER ---
        item {
            SafetyBanner()
        }

        // --- 4. SEARCH & CATEGORY FILTERS ---
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SchoolOSCardBg)
                    .border(1.dp, SchoolOSCardBorder, RoundedCornerShape(16.dp))
                    .padding(14.dp)
                    .testTag("feature_filter_panel")
            ) {
                // Search Field
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_features_input"),
                    placeholder = {
                        Text(
                            text = "Search ${currentRole.label} features...",
                            fontSize = 13.sp,
                            color = SchoolOSMutedText
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = SchoolOSMintLight,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchQueryChange("") }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear search",
                                    tint = SchoolOSMutedText,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = currentRole.accentColor,
                        unfocusedBorderColor = SchoolOSCardBorder,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = currentRole.accentColor
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Category Chips Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    FeatureCategory.entries.forEach { cat ->
                        val isSelected = cat == selectedCategory
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectCategory(cat) },
                            label = {
                                Text(
                                    text = cat.title,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = currentRole.accentColor.copy(alpha = 0.2f),
                                selectedLabelColor = currentRole.accentColor,
                                containerColor = SchoolOSPillBg,
                                labelColor = SchoolOSMutedText
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = if (isSelected) currentRole.accentColor else SchoolOSCardBorder
                            ),
                            modifier = Modifier.testTag("category_chip_${cat.name}")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Filter Count & Feature Quick Selector
                Text(
                    text = "SHOWING ${filteredFeatures.size} OF ${currentRole.features.size} FEATURES",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = SchoolOSMutedText
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (filteredFeatures.isEmpty()) {
                    Text(
                        text = "No features match your search. Try adjusting the query.",
                        fontSize = 12.sp,
                        color = SchoolOSMutedText,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        contentPadding = PaddingValues(vertical = 2.dp),
                        modifier = Modifier.testTag("quick_feature_selector_row")
                    ) {
                        items(filteredFeatures) { feat ->
                            val isFeatSelected = feat == selectedFeature
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if (isFeatSelected) currentRole.accentColor
                                        else Color(0xFF0A1D13)
                                    )
                                    .border(
                                        1.dp,
                                        if (isFeatSelected) currentRole.accentColor else SchoolOSCardBorder,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable { onSelectFeature(feat) }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                                    .testTag("feature_chip_${feat.lowercase().replace(" ", "_")}"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = feat,
                                    fontSize = 11.sp,
                                    fontWeight = if (isFeatSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isFeatSelected) Color.White else Color(0xFFC5D6CD),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }

        // --- 5. ACTIVE FEATURE PREVIEW ---
        item {
            if (featureInfo != null) {
                FeatureInteractiveCard(
                    featureInfo = featureInfo,
                    role = currentRole,
                    feedbackMessage = feedbackMessage,
                    onActionClick = onActionClick,
                    onNavigateToFeature = onSelectFeature
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
