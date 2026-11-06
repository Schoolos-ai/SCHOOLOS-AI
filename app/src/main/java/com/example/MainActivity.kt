package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.AboutSafetyDialog
import com.example.ui.components.SchoolOSTopBar
import com.example.ui.screens.AdminControlScreen
import com.example.ui.screens.AdminLoginDialog
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PortalExplorerScreen
import com.example.ui.theme.SchoolOSTheme
import com.example.viewmodel.ExplorerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ExplorerViewModel = viewModel()
            SchoolOSApp(viewModel = viewModel)
        }
    }
}

@Composable
fun SchoolOSApp(
    viewModel: ExplorerViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isSystemDark = isSystemInDarkTheme()
    val effectiveDarkTheme = uiState.isDarkMode ?: true

    SchoolOSTheme(darkTheme = effectiveDarkTheme) {
        // System Back navigation handler
        BackHandler(
            enabled = uiState.selectedRole != null ||
                uiState.showAboutSafetyDialog ||
                uiState.showAdminLoginDialog ||
                uiState.showAdminControlScreen
        ) {
            viewModel.handleBackNavigation()
        }

        Scaffold(
            topBar = {
                SchoolOSTopBar(
                    currentRole = if (uiState.showAdminControlScreen) null else uiState.selectedRole,
                    isDarkMode = uiState.isDarkMode,
                    isAdminLoggedIn = uiState.isAdminLoggedIn,
                    onBack = if (uiState.selectedRole != null || uiState.showAdminControlScreen) {
                        { viewModel.handleBackNavigation() }
                    } else null,
                    onResetDemo = { viewModel.resetDemo() },
                    onToggleTheme = { viewModel.toggleDarkMode() },
                    onOpenSafetyInfo = { viewModel.setShowAboutSafety(true) },
                    onOpenAdmin = { viewModel.openAdminLogin() }
                )
            },
            modifier = modifier
                .fillMaxSize()
                .testTag("app_root_scaffold")
        ) { innerPadding ->
            val contentModifier = Modifier.padding(innerPadding)

            when {
                uiState.showAdminControlScreen -> {
                    AdminControlScreen(
                        currentConfig = uiState.liveConfig,
                        isPublishing = uiState.isPublishing,
                        publishFeedback = uiState.publishFeedback,
                        onPublish = { viewModel.publishLiveConfig(it) },
                        onResetDefaults = { viewModel.resetLiveConfigToDefaults() },
                        onSignOut = { viewModel.signOutAdmin() },
                        onBack = { viewModel.closeAdminControl() },
                        modifier = contentModifier
                    )
                }
                uiState.selectedRole == null -> {
                    HomeScreen(
                        onSelectRole = { viewModel.selectRole(it) },
                        onOpenSafetyInfo = { viewModel.setShowAboutSafety(true) },
                        onOpenAdmin = { viewModel.openAdminLogin() },
                        liveConfig = uiState.liveConfig,
                        isAdminLoggedIn = uiState.isAdminLoggedIn,
                        modifier = contentModifier
                    )
                }
                else -> {
                    PortalExplorerScreen(
                        currentRole = uiState.selectedRole!!,
                        selectedFeature = uiState.selectedFeature,
                        searchQuery = uiState.searchQuery,
                        selectedCategory = uiState.selectedCategory,
                        feedbackMessage = uiState.feedbackMessage,
                        filteredFeatures = uiState.currentRoleFeatures,
                        featureInfo = uiState.currentFeatureInfo,
                        onSelectRole = { viewModel.selectRole(it) },
                        onSelectFeature = { viewModel.selectFeature(it) },
                        onSearchQueryChange = { viewModel.setSearchQuery(it) },
                        onSelectCategory = { viewModel.setCategory(it) },
                        onActionClick = { viewModel.triggerDemoAction(it) },
                        onBack = { viewModel.handleBackNavigation() },
                        modifier = contentModifier
                    )
                }
            }

            if (uiState.showAdminLoginDialog) {
                AdminLoginDialog(
                    onDismiss = { viewModel.closeAdminLogin() },
                    onSignIn = { email, pass -> viewModel.signInAdmin(email, pass) },
                    isLoading = uiState.adminAuthLoading,
                    errorMessage = uiState.adminAuthError
                )
            }

            if (uiState.showAboutSafetyDialog) {
                AboutSafetyDialog(
                    onDismiss = { viewModel.setShowAboutSafety(false) }
                )
            }
        }
    }
}
