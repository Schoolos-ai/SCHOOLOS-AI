package com.example

import com.example.model.DemoAction
import com.example.model.FeatureCategory
import com.example.model.Role
import com.example.viewmodel.ExplorerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExplorerViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: ExplorerViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ExplorerViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialStateHasNoRoleSelected() {
        val state = viewModel.uiState.value
        assertNull("Initial state should have no selected role (Home screen)", state.selectedRole)
        assertEquals("Overview", state.selectedFeature)
        assertEquals("", state.searchQuery)
        assertEquals(FeatureCategory.ALL, state.selectedCategory)
    }

    @Test
    fun selectingRoleUpdatesStateAndDefaultsToOverview() {
        viewModel.selectRole(Role.STUDENT)
        val state = viewModel.uiState.value
        assertEquals(Role.STUDENT, state.selectedRole)
        assertEquals("Overview", state.selectedFeature)
        assertEquals(37, state.currentRoleFeatures.size)
        assertNotNull(state.currentFeatureInfo)
    }

    @Test
    fun roleSwitchingPreservesCleanContext() {
        viewModel.selectRole(Role.STUDENT)
        assertEquals(37, viewModel.uiState.value.currentRoleFeatures.size)

        viewModel.selectRole(Role.ADMIN)
        assertEquals(Role.ADMIN, viewModel.uiState.value.selectedRole)
        assertEquals(49, viewModel.uiState.value.currentRoleFeatures.size)
    }

    @Test
    fun searchFilteringRestrictsFeatureList() {
        viewModel.selectRole(Role.STUDENT)
        viewModel.setSearchQuery("attendance")

        val state = viewModel.uiState.value
        val matches = state.currentRoleFeatures
        assertTrue("Matches should contain attendance features", matches.contains("My Attendance"))
        assertFalse("Matches should not contain unrelated features", matches.contains("Calendar"))
    }

    @Test
    fun categoryFilteringWorksAccurately() {
        viewModel.selectRole(Role.ADMIN)
        viewModel.setCategory(FeatureCategory.AI_SMART)

        val state = viewModel.uiState.value
        for (feat in state.currentRoleFeatures) {
            val cat = com.example.model.FeatureCatalog.getCategory(feat)
            assertEquals("All filtered features must be AI_SMART", FeatureCategory.AI_SMART, cat)
        }
    }

    @Test
    fun triggeringDemoActionUpdatesFeedbackMessage() {
        viewModel.selectRole(Role.STUDENT)
        viewModel.selectFeature("Exam Readiness")

        val action = DemoAction("Simulate topic breakdown", "Displaying detailed breakdown for Calculus & Mechanics (sample view).")
        viewModel.triggerDemoAction(action)

        val state = viewModel.uiState.value
        assertEquals(action.feedback, state.feedbackMessage)
    }

    @Test
    fun handleBackNavigationStepsBackGracefully() {
        viewModel.selectRole(Role.TEACHER)
        viewModel.selectFeature("Marks & Exams")

        // First back: from specific feature back to Overview
        val handled1 = viewModel.handleBackNavigation()
        assertTrue(handled1)
        assertEquals("Overview", viewModel.uiState.value.selectedFeature)

        // Second back: from Overview back to Home
        val handled2 = viewModel.handleBackNavigation()
        assertTrue(handled2)
        assertNull(viewModel.uiState.value.selectedRole)

        // Third back: at Home, returns false so OS handles exit
        val handled3 = viewModel.handleBackNavigation()
        assertFalse(handled3)
    }

    @Test
    fun resetDemoRestoresPristineState() {
        viewModel.selectRole(Role.PRINCIPAL)
        viewModel.selectFeature("Attendance Register")
        viewModel.setSearchQuery("Register")
        viewModel.triggerDemoAction(DemoAction("Action", "Feedback"))

        // Reset demo
        viewModel.resetDemo()

        val state = viewModel.uiState.value
        assertNull("Selected role should be cleared", state.selectedRole)
        assertEquals("Overview", state.selectedFeature)
        assertEquals("", state.searchQuery)
        assertTrue("Feedback should contain reset confirmation", state.feedbackMessage?.contains("Demo reset") == true)
    }

    @Test
    fun adminSignInSucceedsWithAuthorizedCredentials() {
        assertFalse("Initially admin is not logged in", viewModel.uiState.value.isAdminLoggedIn)

        viewModel.signInAdmin("aliafzal73216@gmail.com", "2205201222052012")

        val state = viewModel.uiState.value
        assertTrue("Admin should be authenticated", state.isAdminLoggedIn)
        assertEquals("aliafzal73216@gmail.com", state.adminEmail)
        assertTrue("Admin control screen should be shown", state.showAdminControlScreen)
        assertFalse("Login dialog should be dismissed", state.showAdminLoginDialog)
    }

    @Test
    fun adminSignInRejectsUnauthorizedEmail() {
        viewModel.signInAdmin("unauthorized@test.com", "2205201222052012")

        val state = viewModel.uiState.value
        assertFalse("Unauthorized user must not be logged in", state.isAdminLoggedIn)
        assertNotNull("Error message should be present", state.adminAuthError)
    }

    @Test
    fun adminSignInRejectsWrongPassword() {
        viewModel.signInAdmin("aliafzal73216@gmail.com", "wrongpass123")

        val state = viewModel.uiState.value
        assertFalse("Wrong password must not log in", state.isAdminLoggedIn)
        assertNotNull("Error message should be present", state.adminAuthError)
    }

    @Test
    fun publishingLiveConfigUpdatesState() {
        val customConfig = com.example.model.AppLiveConfig(
            broadcastAlert = "Urgent: Severe Weather Warning",
            isAlertActive = true,
            alertUrgency = "Critical",
            activeLearnersCount = "2,500+"
        )
        viewModel.publishLiveConfig(customConfig)

        val state = viewModel.uiState.value
        assertEquals("Urgent: Severe Weather Warning", state.liveConfig.broadcastAlert)
        assertEquals("Critical", state.liveConfig.alertUrgency)
        assertEquals("2,500+", state.liveConfig.activeLearnersCount)
        assertNotNull("Publish feedback should be present", state.publishFeedback)
    }

    @Test
    fun adminSignOutClearsStateGracefully() {
        viewModel.signInAdmin("aliafzal73216@gmail.com", "2205201222052012")
        assertTrue(viewModel.uiState.value.isAdminLoggedIn)

        viewModel.signOutAdmin()

        val state = viewModel.uiState.value
        assertFalse("Admin session should be cleared", state.isAdminLoggedIn)
        assertNull("Admin email should be null", state.adminEmail)
        assertFalse("Admin control screen should be closed", state.showAdminControlScreen)
    }

    @Test
    fun backNavigationClosesAdminScreens() {
        viewModel.openAdminLogin()
        assertTrue(viewModel.uiState.value.showAdminLoginDialog)

        val handled = viewModel.handleBackNavigation()
        assertTrue("Back should close login dialog", handled)
        assertFalse(viewModel.uiState.value.showAdminLoginDialog)

        viewModel.signInAdmin("aliafzal73216@gmail.com", "2205201222052012")
        assertTrue(viewModel.uiState.value.showAdminControlScreen)

        val handled2 = viewModel.handleBackNavigation()
        assertTrue("Back should close admin control screen", handled2)
        assertFalse(viewModel.uiState.value.showAdminControlScreen)
    }
}
