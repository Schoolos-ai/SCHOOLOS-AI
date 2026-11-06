package com.example

import com.example.model.FeatureCatalog
import com.example.model.Role
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RoleFeatureCoverageTest {

    @Test
    fun verifyAllSixRolesPresent() {
        assertEquals("There must be exactly 6 role portals", 6, Role.entries.size)
        val roleKeys = Role.entries.map { it.key }.toSet()
        assertTrue(roleKeys.contains("student"))
        assertTrue(roleKeys.contains("parent"))
        assertTrue(roleKeys.contains("teacher"))
        assertTrue(roleKeys.contains("principal"))
        assertTrue(roleKeys.contains("regional"))
        assertTrue(roleKeys.contains("admin"))
    }

    @Test
    fun verifyActiveLandingPortalsAreOnlyStudentAndParent() {
        assertEquals("Active portals must strictly have 2 portals", 2, Role.activePortals.size)
        assertTrue("Active portals must contain STUDENT", Role.activePortals.contains(Role.STUDENT))
        assertTrue("Active portals must contain PARENT", Role.activePortals.contains(Role.PARENT))
    }

    @Test
    fun verifyExactRoleFeatureCountsMatchSpecification() {
        // Spec requirements:
        // Student: 37
        // Parent: 30
        // Teacher: 34
        // Principal: 39
        // Regional: 11
        // Admin: 50
        assertEquals("Student portal must have exactly 37 features", 37, Role.STUDENT.features.size)
        assertEquals("Parent portal must have exactly 30 features", 30, Role.PARENT.features.size)
        assertEquals("Teacher portal must have exactly 33 features", 33, Role.TEACHER.features.size)
        assertEquals("Principal portal must have exactly 39 features", 39, Role.PRINCIPAL.features.size)
        assertEquals("Regional portal must have exactly 11 features", 11, Role.REGIONAL.features.size)
        assertEquals("Administrator portal must have exactly 49 features", 49, Role.ADMIN.features.size)
    }

    @Test
    fun verifyAdministratorHas15SmartFeaturesModules() {
        val smartModules = FeatureCatalog.admin15SmartModules
        assertEquals("Must contain 15 smart feature modules", 15, smartModules.size)

        // All 15 must exist in Administrator features
        for (module in smartModules) {
            assertTrue(
                "Administrator features must contain $module",
                Role.ADMIN.features.contains(module)
            )
        }
    }

    @Test
    fun verifyEveryRoleFeaturePairReturnsValidPreviewAndActions() {
        for (role in Role.entries) {
            for (feature in role.features) {
                val info = FeatureCatalog.getInfo(feature, role)
                assertNotNull("Feature info must not be null for $feature in ${role.label}", info)
                assertEquals(feature, info.name)
                assertTrue("Description must be non-empty for $feature", info.description.isNotBlank())
                assertTrue("Sample title must be non-empty for $feature", info.sampleTitle.isNotBlank())
                assertTrue("Sample detail must be non-empty for $feature", info.sampleDetail.isNotBlank())
                assertTrue(
                    "Feature $feature in ${role.label} must have at least 1 safe demo action",
                    info.actions.isNotEmpty()
                )
                for (action in info.actions) {
                    assertTrue("Action label must not be blank", action.label.isNotBlank())
                    assertTrue("Action feedback must not be blank", action.feedback.isNotBlank())
                }
            }
        }
    }
}
