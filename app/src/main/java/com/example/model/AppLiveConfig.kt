package com.example.model

/**
 * Real-time published application configuration controlled by the administrator.
 * Synchronized across all app instances live via Cloud Firestore.
 */
data class AppLiveConfig(
    val heroTitle: String = "A brighter way to learn,\ntogether.",
    val heroSubtitle: String = "SchoolOS AI unites students and parents in one intelligent school ecosystem. Safe, supervised, and built for modern school life.",
    val broadcastAlert: String = "Term 2 AI Assessment & Progress Review window is now officially open across campus.",
    val isAlertActive: Boolean = true,
    val alertUrgency: String = "Important", // "Normal", "Important", "Critical"
    val activeLearnersCount: String = "1,420+",
    val aiToolsCount: String = "50+ Smart Tools",
    val attendanceRate: String = "96.4%",
    val systemStatus: String = "All Systems Operational (99.98% Uptime)",
    val studentNoticeTitle: String = "New AI Study Tutor & Notes Generator Active",
    val studentNoticeBody: String = "Students can now generate smart flashcards and study guides aligned with CBSE curriculum.",
    val parentNoticeTitle: String = "Term 2 Learning Progress & Attendance Published",
    val parentNoticeBody: String = "Parent portal now features direct teacher desk notes, attendance alerts, and AI parenting tips.",
    val voiceNoticeTitle: String = "Weekly learning update & exam schedule",
    val voiceNoticeSubtitle: String = "Audio summary by Principal for student and parent communities.",
    val allowAiStudyTutor: Boolean = true,
    val allowStoreAccess: Boolean = true,
    val allowParentRequests: Boolean = true,
    val lastPublishedBy: String = "aliafzal73216@gmail.com",
    val lastPublishedTime: String = "Live Cloud Synced",
    val version: Long = 1L
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "heroTitle" to heroTitle,
            "heroSubtitle" to heroSubtitle,
            "broadcastAlert" to broadcastAlert,
            "isAlertActive" to isAlertActive,
            "alertUrgency" to alertUrgency,
            "activeLearnersCount" to activeLearnersCount,
            "aiToolsCount" to aiToolsCount,
            "attendanceRate" to attendanceRate,
            "systemStatus" to systemStatus,
            "studentNoticeTitle" to studentNoticeTitle,
            "studentNoticeBody" to studentNoticeBody,
            "parentNoticeTitle" to parentNoticeTitle,
            "parentNoticeBody" to parentNoticeBody,
            "voiceNoticeTitle" to voiceNoticeTitle,
            "voiceNoticeSubtitle" to voiceNoticeSubtitle,
            "allowAiStudyTutor" to allowAiStudyTutor,
            "allowStoreAccess" to allowStoreAccess,
            "allowParentRequests" to allowParentRequests,
            "lastPublishedBy" to lastPublishedBy,
            "lastPublishedTime" to lastPublishedTime,
            "version" to version
        )
    }

    companion object {
        fun fromMap(data: Map<String, Any?>?): AppLiveConfig {
            if (data == null) return AppLiveConfig()
            val defaults = AppLiveConfig()
            return AppLiveConfig(
                heroTitle = data["heroTitle"] as? String ?: defaults.heroTitle,
                heroSubtitle = data["heroSubtitle"] as? String ?: defaults.heroSubtitle,
                broadcastAlert = data["broadcastAlert"] as? String ?: defaults.broadcastAlert,
                isAlertActive = (data["isAlertActive"] as? Boolean) ?: defaults.isAlertActive,
                alertUrgency = data["alertUrgency"] as? String ?: defaults.alertUrgency,
                activeLearnersCount = data["activeLearnersCount"] as? String ?: defaults.activeLearnersCount,
                aiToolsCount = data["aiToolsCount"] as? String ?: defaults.aiToolsCount,
                attendanceRate = data["attendanceRate"] as? String ?: defaults.attendanceRate,
                systemStatus = data["systemStatus"] as? String ?: defaults.systemStatus,
                studentNoticeTitle = data["studentNoticeTitle"] as? String ?: defaults.studentNoticeTitle,
                studentNoticeBody = data["studentNoticeBody"] as? String ?: defaults.studentNoticeBody,
                parentNoticeTitle = data["parentNoticeTitle"] as? String ?: defaults.parentNoticeTitle,
                parentNoticeBody = data["parentNoticeBody"] as? String ?: defaults.parentNoticeBody,
                voiceNoticeTitle = data["voiceNoticeTitle"] as? String ?: defaults.voiceNoticeTitle,
                voiceNoticeSubtitle = data["voiceNoticeSubtitle"] as? String ?: defaults.voiceNoticeSubtitle,
                allowAiStudyTutor = (data["allowAiStudyTutor"] as? Boolean) ?: defaults.allowAiStudyTutor,
                allowStoreAccess = (data["allowStoreAccess"] as? Boolean) ?: defaults.allowStoreAccess,
                allowParentRequests = (data["allowParentRequests"] as? Boolean) ?: defaults.allowParentRequests,
                lastPublishedBy = data["lastPublishedBy"] as? String ?: defaults.lastPublishedBy,
                lastPublishedTime = data["lastPublishedTime"] as? String ?: defaults.lastPublishedTime,
                version = (data["version"] as? Number)?.toLong() ?: defaults.version
            )
        }
    }
}
