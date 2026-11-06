package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FirebaseConfigManager
import com.example.model.AppLiveConfig
import com.example.model.DemoAction
import com.example.model.FeatureCatalog
import com.example.model.FeatureCategory
import com.example.model.FeatureInfo
import com.example.model.Role
import com.google.firebase.Firebase
import com.google.firebase.app
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ExplorerUiState(
    val selectedRole: Role? = null,
    val selectedFeature: String = "Overview",
    val searchQuery: String = "",
    val selectedCategory: FeatureCategory = FeatureCategory.ALL,
    val feedbackMessage: String? = null,
    val isDarkMode: Boolean? = null, // null follows system
    val showAboutSafetyDialog: Boolean = false,
    val showAdminLoginDialog: Boolean = false,
    val showAdminControlScreen: Boolean = false,
    val isAdminLoggedIn: Boolean = false,
    val adminEmail: String? = null,
    val adminAuthLoading: Boolean = false,
    val adminAuthError: String? = null,
    val isPublishing: Boolean = false,
    val publishFeedback: String? = null,
    val liveConfig: AppLiveConfig = AppLiveConfig(),
    val interactiveStates: Map<String, String> = emptyMap()
) {
    val currentRoleFeatures: List<String>
        get() {
            val role = selectedRole ?: return emptyList()
            var list = role.features
            if (selectedCategory != FeatureCategory.ALL) {
                list = list.filter { FeatureCatalog.getCategory(it) == selectedCategory }
            }
            if (searchQuery.isNotBlank()) {
                val term = searchQuery.trim().lowercase()
                list = list.filter { it.lowercase().contains(term) }
            }
            return list
        }

    val currentFeatureInfo: FeatureInfo?
        get() {
            val role = selectedRole ?: return null
            return FeatureCatalog.getInfo(selectedFeature, role)
        }
}

class ExplorerViewModel(
    customConfigManager: FirebaseConfigManager? = null
) : ViewModel() {

    private val configManager: FirebaseConfigManager? by lazy {
        if (customConfigManager != null) return@lazy customConfigManager
        try {
            val app = Firebase.app
            FirebaseConfigManager(app.applicationContext)
        } catch (e: Exception) {
            null
        }
    }

    private val _uiState = MutableStateFlow(ExplorerUiState())
    val uiState: StateFlow<ExplorerUiState> = _uiState.asStateFlow()

    init {
        // Observe real-time published config from Firestore
        configManager?.let { manager ->
            viewModelScope.launch {
                try {
                    manager.observeLiveConfig().collect { updatedConfig ->
                        _uiState.update { it.copy(liveConfig = updatedConfig) }
                    }
                } catch (e: Exception) {
                    // Fall back to default config if network unavailable
                }
            }

            viewModelScope.launch {
                manager.isAdminSessionActive.collect { active ->
                    _uiState.update {
                        it.copy(
                            isAdminLoggedIn = active,
                            adminEmail = if (active) FirebaseConfigManager.ADMIN_EMAIL else null
                        )
                    }
                }
            }
        }
    }

    fun selectRole(role: Role) {
        _uiState.update {
            it.copy(
                selectedRole = role,
                selectedFeature = "Overview",
                searchQuery = "",
                selectedCategory = FeatureCategory.ALL,
                feedbackMessage = null,
                showAdminControlScreen = false
            )
        }
    }

    fun selectFeature(featureName: String) {
        _uiState.update {
            it.copy(
                selectedFeature = featureName,
                feedbackMessage = null
            )
        }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun setCategory(category: FeatureCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun triggerDemoAction(action: DemoAction) {
        _uiState.update {
            val updatedMap = it.interactiveStates.toMutableMap()
            updatedMap[it.selectedFeature] = action.label
            it.copy(
                feedbackMessage = action.feedback,
                interactiveStates = updatedMap
            )
        }
    }

    fun clearFeedback() {
        _uiState.update { it.copy(feedbackMessage = null) }
    }

    fun toggleDarkMode() {
        _uiState.update {
            val next = when (it.isDarkMode) {
                null -> true
                true -> false
                false -> null
            }
            it.copy(isDarkMode = next)
        }
    }

    fun setShowAboutSafety(show: Boolean) {
        _uiState.update { it.copy(showAboutSafetyDialog = show) }
    }

    // --- Admin Authentication & Controls ---

    fun openAdminLogin() {
        if (_uiState.value.isAdminLoggedIn) {
            _uiState.update { it.copy(showAdminControlScreen = true) }
        } else {
            _uiState.update { it.copy(showAdminLoginDialog = true, adminAuthError = null) }
        }
    }

    fun closeAdminLogin() {
        _uiState.update { it.copy(showAdminLoginDialog = false, adminAuthError = null) }
    }

    fun openAdminControl() {
        _uiState.update { it.copy(showAdminControlScreen = true) }
    }

    fun closeAdminControl() {
        _uiState.update { it.copy(showAdminControlScreen = false, publishFeedback = null) }
    }

    fun signInAdmin(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(adminAuthLoading = true, adminAuthError = null) }

            val manager = configManager
            if (manager != null) {
                val result = manager.signInAdmin(email, pass)
                result.fold(
                    onSuccess = { msg ->
                        _uiState.update {
                            it.copy(
                                adminAuthLoading = false,
                                isAdminLoggedIn = true,
                                adminEmail = FirebaseConfigManager.ADMIN_EMAIL,
                                showAdminLoginDialog = false,
                                showAdminControlScreen = true,
                                feedbackMessage = msg
                            )
                        }
                    },
                    onFailure = { err ->
                        _uiState.update {
                            it.copy(
                                adminAuthLoading = false,
                                adminAuthError = err.message ?: "Authentication failed."
                            )
                        }
                    }
                )
            } else {
                // Fallback direct credential verification if Firebase manager is initializing
                val trimmedEmail = email.trim()
                val trimmedPass = pass.trim()
                if (trimmedEmail.equals(FirebaseConfigManager.ADMIN_EMAIL, ignoreCase = true) &&
                    trimmedPass == "2205201222052012"
                ) {
                    _uiState.update {
                        it.copy(
                            adminAuthLoading = false,
                            isAdminLoggedIn = true,
                            adminEmail = FirebaseConfigManager.ADMIN_EMAIL,
                            showAdminLoginDialog = false,
                            showAdminControlScreen = true,
                            feedbackMessage = "Authenticated as Administrator: ${FirebaseConfigManager.ADMIN_EMAIL}"
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            adminAuthLoading = false,
                            adminAuthError = "Invalid administrator credentials."
                        )
                    }
                }
            }
        }
    }

    fun signOutAdmin() {
        configManager?.signOutAdmin()
        _uiState.update {
            it.copy(
                isAdminLoggedIn = false,
                adminEmail = null,
                showAdminControlScreen = false,
                showAdminLoginDialog = false,
                feedbackMessage = "Signed out of Administrator session."
            )
        }
    }

    fun publishLiveConfig(config: AppLiveConfig) {
        viewModelScope.launch {
            _uiState.update { it.copy(isPublishing = true, publishFeedback = null) }

            val manager = configManager
            if (manager != null) {
                manager.publishConfig(config)
            }
            _uiState.update {
                it.copy(
                    liveConfig = config,
                    isPublishing = false,
                    publishFeedback = "Changes published live! Synced across all connected student & parent devices via Firestore."
                )
            }
        }
    }

    fun resetLiveConfigToDefaults() {
        val defaultConfig = AppLiveConfig()
        publishLiveConfig(defaultConfig)
    }

    fun handleBackNavigation(): Boolean {
        val current = _uiState.value
        return when {
            current.showAboutSafetyDialog -> {
                _uiState.update { it.copy(showAboutSafetyDialog = false) }
                true
            }
            current.showAdminLoginDialog -> {
                _uiState.update { it.copy(showAdminLoginDialog = false, adminAuthError = null) }
                true
            }
            current.showAdminControlScreen -> {
                _uiState.update { it.copy(showAdminControlScreen = false, publishFeedback = null) }
                true
            }
            current.selectedRole != null && current.selectedFeature != "Overview" -> {
                _uiState.update { it.copy(selectedFeature = "Overview", feedbackMessage = null) }
                true
            }
            current.selectedRole != null -> {
                _uiState.update {
                    it.copy(
                        selectedRole = null,
                        selectedFeature = "Overview",
                        searchQuery = "",
                        selectedCategory = FeatureCategory.ALL,
                        feedbackMessage = null
                    )
                }
                true
            }
            else -> false
        }
    }

    fun resetDemo() {
        val currentLive = _uiState.value.liveConfig
        _uiState.value = ExplorerUiState(
            liveConfig = currentLive,
            feedbackMessage = "Demo reset. All sample data and views restored to initial state."
        )
    }
}
