package com.example.model

data class DemoAction(
    val label: String,
    val feedback: String
)

data class FeatureInfo(
    val name: String,
    val category: FeatureCategory,
    val description: String,
    val sampleTitle: String,
    val sampleDetail: String,
    val sampleTags: List<String>,
    val actions: List<DemoAction>,
    val isSmartFeatureModule: Boolean = false
)

object FeatureCatalog {

    val admin15SmartModules = listOf(
        "SchoolOS Command Centre",
        "Exam Readiness",
        "Student Achievement Passport",
        "Adaptive Worksheet Studio",
        "School Knowledge Brain",
        "Smart Timetable Optimizer",
        "School Requests",
        "AI Lost-and-Found",
        "School Resource Manager",
        "Project Lab & Research Journal",
        "Emergency Communications",
        "Parent Communication Translator",
        "Study Recovery Planner",
        "Green Campus",
        "Trust & Security Centre"
    )

    fun getCategory(name: String): FeatureCategory {
        return when {
            name.contains("AI", ignoreCase = true) ||
            name in admin15SmartModules ||
            name in listOf("Smart Timetable Optimizer", "Adaptive Worksheet Studio", "School Knowledge Brain") ->
                FeatureCategory.AI_SMART

            name in listOf(
                "My Marks", "Child Marks", "Marks & Exams", "Exam Readiness",
                "Learning materials & Quizzes", "Learning games", "Homework & Assignments",
                "Homework & Curriculum", "Learning materials", "Upload resources",
                "Study Recovery Planner", "AI Study Tutor", "AI Study Notes & Flashcards",
                "AI Timetable", "Performance & progress", "Linked learners",
                "Class & Section", "Teaching assignments", "Project Lab & Research Journal"
            ) -> FeatureCategory.ACADEMICS

            name in listOf(
                "My Attendance", "Child Attendance", "Class Attendance", "Attendance Register",
                "Calendar", "Meetings", "Live classes", "Online classes",
                "School Resource Manager", "Excel Bulk Onboarding", "Staff & Teachers",
                "Students & Parents", "Regional schools", "Regional Command Centre",
                "School registrations", "All Schools", "School", "Study Clock & Goals"
            ) -> FeatureCategory.OPERATIONS

            name in listOf(
                "Emergency Communications", "Parent Communication Translator", "Messages",
                "Ask a teacher", "Questions", "Complaints", "Notices", "Mailbox", "Email Centre",
                "AI Lost-and-Found", "Forms", "Help requests"
            ) -> FeatureCategory.COMMUNICATION

            else -> FeatureCategory.ADMIN_SECURITY
        }
    }

    private val descriptions = mapOf(
        "Overview" to "A role-specific dashboard with daily priorities, announcements, and quick shortcuts.",
        "Exam Readiness" to "Review fictional assessment evidence and topic mastery indicators. Output is advisory and not a guaranteed score.",
        "Student Achievement Passport" to "Explore a student's verified co-curricular achievements and badge certification history.",
        "Adaptive Worksheet Studio" to "Preview dynamic differentiated practice sheets with stepped hints. AI generation is sample-only in this demo.",
        "School Knowledge Brain" to "Search authorized school circulars, syllabus guidelines, and curriculum policies with explicit source tags.",
        "Smart Timetable Optimizer" to "Review balanced room and faculty allocation suggestions designed to prevent scheduling collisions.",
        "School Requests" to "Track and simulate approval workflows for lab grants, field trips, facility access, and student leave.",
        "AI Lost-and-Found" to "Compare recovered campus items against lost reports using visual descriptions and match scores.",
        "School Resource Manager" to "Monitor laboratory kits, audio-visual projectors, sports equipment, and checkout schedules.",
        "Project Lab & Research Journal" to "Follow student inquiry milestones, experimental observations, and faculty mentorship logs.",
        "Emergency Communications" to "Compose priority campus safety bulletins and weather alerts for immediate in-app display.",
        "Parent Communication Translator" to "Draft multilingual family notifications with preserved dates, names, and action instructions.",
        "Study Recovery Planner" to "Explore restorative practice steps for topics where diagnostic quizzes revealed learning gaps.",
        "Green Campus" to "Log campus sustainability metrics: solar array generation, water conservation, and zero-waste initiatives.",
        "Trust & Security Centre" to "Inspect data boundaries, active multi-factor protections, and privacy safeguards across all portals.",
        "AI & Smart Features" to "Centrally inspect and navigate the 15 specialized smart feature modules across the platform.",
        "SchoolOS Command Centre" to "Access operational guidance and executive briefs drafted from authorized school context.",
        "AI Command Centre" to "Access role-tailored intelligence drafts and planning assistance for daily operations.",
        "Regional Command Centre" to "Inspect multi-school KPIs, regional enrollment distribution, and priority intervention metrics.",
        "AI Chat Assistant" to "Simulate safe interactive study questions with bounded syllabus assistance.",
        "AI Analytics" to "Examine personal academic progress trends and attendance correlation patterns.",
        "Calendar" to "Review academic terms, examination timetables, sports days, and public holidays.",
        "Forms" to "Fill out and preview student consent slips, activity registrations, and feedback forms.",
        "Meetings" to "Coordinate structured parent-teacher conferences and academic planning sessions.",
        "Portal analytics" to "Review engagement metrics, resource utilization, and assignment completion trends.",
        "Live classes" to "Inspect live classroom broadcast status and attention indicator tools with camera safeguards.",
        "Online classes" to "Access scheduled video instruction links and syllabus references for enrolled subjects.",
        "Marks & Exams" to "Enter and inspect class assessment marks, grade distributions, and moderation notes.",
        "My Marks" to "View published term grades, assignment feedback, and progress across all enrolled subjects.",
        "Child Marks" to "Follow your linked learner's term performance, subject scores, and teacher commendations.",
        "AI Quiz Generator" to "Draft multi-format assessment questions aligned to specified curriculum objectives.",
        "Learning materials & Quizzes" to "Access interactive practice quizzes and textbook companion resources.",
        "Learning games" to "Engage with curriculum-aligned math puzzles and science vocabulary reinforcement games.",
        "Learning materials" to "Browse digitized textbooks, lesson slides, and video lecture notes.",
        "Upload resources" to "Organize lesson plans, problem sets, and reading lists for student distribution.",
        "Class Attendance" to "Simulate daily attendance roll-call with automated absence notifications.",
        "My Attendance" to "Review personal attendance records, excused absences, and minimum threshold alerts.",
        "Child Attendance" to "Monitor your child's daily presence, late arrivals, and attendance percentages.",
        "Attendance Register" to "Audit platform-wide and section-level attendance rolls with date filters.",
        "Homework & Assignments" to "Track assigned problem sets, submission deadlines, and grading rubrics.",
        "Homework & Curriculum" to "Inspect assigned coursework, project requirements, and term syllabus maps.",
        "Performance & progress" to "Analyze academic growth trajectories, subject percentiles, and strength areas.",
        "AI Parenting & Study Tips" to "Access supportive home study routines and positive reinforcement guidance.",
        "Study Clock & Goals" to "Set focused study intervals and track daily personal learning milestones.",
        "Leaderboard" to "Celebrate class reading challenges, STEM projects, and collaborative badges.",
        "Ask a teacher" to "Submit private academic inquiries to subject instructors for clarification.",
        "Questions" to "Review and respond to pending learner doubts and homework questions.",
        "Messages" to "Follow two-way family communication with teachers and school administration.",
        "Complaints" to "Review confidential grievance tickets and administrative resolution steps.",
        "Notices" to "Read official school announcements, circulars, and event press releases.",
        "Mailbox" to "Access internal staff email folders and official correspondence threads.",
        "Email Centre" to "Compose targeted cohort announcements with template support.",
        "Excel Bulk Onboarding" to "Preview spreadsheet schema validation for student and teacher account creation.",
        "School registrations" to "Review onboarding petitions from prospective institutions with accreditation records.",
        "All Schools" to "Explore the directory of affiliated institutions and their campus profiles.",
        "Regional schools" to "View schools operating within the assigned regional supervisory district.",
        "Accounts" to "Simulate user credential governance, role assignments, and active account states.",
        "Staff & Teachers" to "Manage faculty directories, subject specializations, and departmental assignments.",
        "Students & Parents" to "Maintain student rosters and verified guardian linkage relationships.",
        "Class & Section" to "Configure grade levels, division sections, and classroom allocations.",
        "Teaching assignments" to "Assign lead teachers, subject faculties, and timetable blocks.",
        "Mobile app & branding" to "Configure school crests, visual palettes, and mobile client presentation.",
        "Website Content & CMS" to "Edit public campus landing pages, leadership bios, and news feeds.",
        "AI & API Management" to "Monitor AI provider configuration status and per-role inference rate policies.",
        "AI Website Manager" to "Review structured natural-language proposals for website copy and layout updates.",
        "Database connections" to "Inspect health parameters and backup verification status for the application data layer.",
        "Email & password audit" to "Review authentication event logs, credential rotation, and access attempts.",
        "Feature Control" to "Toggle visibility of experimental or role-specific feature modules across portals.",
        "Portal feature manager" to "Configure custom workflow pages and shortcut tiles for specific staff roles.",
        "Auto Admin Mode" to "Inspect automated platform diagnostic health checks and self-healing protocols.",
        "AI Alert Sound Manager" to "Preview and assign auditory alerts for live classroom attention signals.",
        "Security Centre" to "Inspect administrator multi-factor authentication, security factor enrollment, and login audit logs.",
        "Appearance" to "Customize system accent themes, typographic scale, and interface density.",
        "Account security" to "Configure session passkeys, authentication methods, and security question fallbacks.",
        "Profile & photo" to "Manage display credentials, bio details, and avatar imagery in sample mode.",
        "Portal Guide" to "Read comprehensive operational manuals and best-practice workflows for this portal role.",
        "Certificates & Letters" to "Preview printable achievement diplomas, transfer certificates, and bona fide letters.",
        "AI Study Tutor" to "Receive step-by-step reasoning and guided hints on complex practice problems.",
        "AI Study Notes & Flashcards" to "Generate organized study summaries and flip-card memory decks from lecture notes.",
        "AI Timetable" to "Draft customized personal revision schedules balanced against upcoming exam dates.",
        "Linked learners" to "Switch between linked siblings to inspect individual academic records and schedules.",
        "School" to "View school institutional credentials, affiliation IDs, and administrative contact records."
    )

    fun getInfo(featureName: String, role: Role): FeatureInfo {
        val category = getCategory(featureName)
        val description = descriptions[featureName] ?: "Explore the $featureName workflow in the ${role.label} portal. All actions are simulated locally."
        val isSmart = featureName in admin15SmartModules

        val (sampleTitle, sampleDetail, tags, actions) = when (featureName) {
            "Overview" -> Quad(
                "Welcome to the ${role.label} Portal",
                "You are viewing a demonstration workspace for ${role.label} (${role.context}). All statistics, messages, and records are sample data.",
                listOf("Interactive Tour", "Fictional Demo", role.label),
                listOf(
                    DemoAction("Open sample dashboard card", "Sample card opened. No account data was loaded."),
                    DemoAction("Explore this portal's first feature", "Select any feature from the navigation list to test its preview.")
                )
            )

            "AI & Smart Features" -> Quad(
                "Central Hub: 15 Smart Modules",
                "Centrally manages SchoolOS Command Centre, Exam Readiness, Achievement Passport, Adaptive Worksheets, Knowledge Brain, and 10 additional AI-assisted modules.",
                listOf("15 Modules", "Central Hub", "Platform AI"),
                listOf(
                    DemoAction("Audit all 15 modules", "All 15 smart modules verified active in demo mode."),
                    DemoAction("Check provider readiness", "Sample status: Demo provider configured (offline simulation).")
                )
            )

            "Exam Readiness" -> Quad(
                "Mid-Term Exam Preparedness Signal",
                "Based on 14 recent formative quizzes and homework checks: Mathematics: 91% (High), Science: 86% (Good), English: 84% (Steady). Overall preparedness: Ready.",
                listOf("Advisory Signal", "Not Official Score", "Formative Evidence"),
                listOf(
                    DemoAction("Simulate topic breakdown", "Displaying detailed breakdown for Calculus & Mechanics (sample view)."),
                    DemoAction("Refresh readiness estimate", "Readiness recalculated using local sample evidence. Advisory only.")
                )
            )

            "Student Achievement Passport" -> Quad(
                "Verified Milestone: Regional Science Fair 2026",
                "Awarded: Silver Honor in Renewable Energy Innovation. Verified by: Dr. S. Rao (Vice Principal). Digital Credential ID: PASSPORT-DEMO-8821.",
                listOf("Verified Evidence", "Human Approval", "Co-Curricular"),
                listOf(
                    DemoAction("Inspect credential evidence", "Evidence packet viewed: Project abstract & competition certificate (sample)."),
                    DemoAction("Simulate verification check", "Credential status confirmed: Approved by authorized school staff.")
                )
            )

            "Adaptive Worksheet Studio" -> Quad(
                "Draft: Quadratic Equations & Graphing (Tier B)",
                "Contains 6 progressive problems with graduated hint levels. Target completion time: 25 mins. Designed for adaptive pace without punitive scoring.",
                listOf("Adaptive Hints", "Differentiated", "Teacher Draft"),
                listOf(
                    DemoAction("Generate sample hint", "Sample hint: Try factoring out the common coefficient first before applying the formula."),
                    DemoAction("Save local draft", "Worksheet saved to temporary local demo storage.")
                )
            )

            "School Knowledge Brain" -> Quad(
                "Curriculum Notice Search: 'Laboratory Safety 2026'",
                "Source: Green Valley School Circular #CV-402 (Published Sept 12). Rule 4: Mandatory protective eyewear in Chemistry Lab 3.",
                listOf("Source-Cited", "Syllabus & Policy", "Zero Hallucination"),
                listOf(
                    DemoAction("Run sample search query", "Retrieved 3 matching paragraphs with explicit circular citations."),
                    DemoAction("Open citation source", "Opening Circular #CV-402 in preview mode.")
                )
            )

            "Smart Timetable Optimizer" -> Quad(
                "Proposed Schedule: Grade 10 - Section A",
                "Period 3: Physics Lab (Room 204). Period 4: Advanced Mathematics (Room 102). Collision check: Zero conflicts detected for lab equipment.",
                listOf("Constraint Check", "Zero Collision", "Optimal Balance"),
                listOf(
                    DemoAction("Run constraint audit", "No teacher or laboratory overlaps found in this sample proposal."),
                    DemoAction("Simulate slot swap", "Slot swapped with Period 5 locally. Demonstration only.")
                )
            )

            "School Requests" -> Quad(
                "Request #REQ-7741: STEM Field Trip to Observatory",
                "Applicant: Grade 9 Science Dept. Status: Pending Principal Endorsement. Budget Estimate: ₹12,500. Transport: School Bus Route #4.",
                listOf("Multi-Step Approval", "Audit Trail", "Departmental"),
                listOf(
                    DemoAction("Simulate approval", "Request marked approved in local demo state. No notification sent."),
                    DemoAction("Add review comment", "Comment added locally: 'Ensure parental consent slips are gathered.'")
                )
            )

            "AI Lost-and-Found" -> Quad(
                "Match Analysis: Stainless Water Flask",
                "Reported Lost: Blue insulated bottle with space stickers (Gym). Found Item: Insulated flask found near Basketball Court. Match Confidence: 94%.",
                listOf("Visual Match", "Human Confirmation", "Campus Care"),
                listOf(
                    DemoAction("Confirm matching item", "Item flagged for physical verification at Administration Desk."),
                    DemoAction("View item photos", "Displaying sample catalog image for verification.")
                )
            )

            "School Resource Manager" -> Quad(
                "Inventory: Chemistry Specimen Microscopes (Batch 4)",
                "Total Assets: 24 units. Checked Out: 18 units (Lab 2). Available: 6 units. Maintenance Status: All calibrated on Oct 20.",
                listOf("Asset Tracking", "Calibration Log", "Zero Hardware"),
                listOf(
                    DemoAction("Simulate item checkout", "1 unit checked out locally to Sample Teacher Meera."),
                    DemoAction("Run maintenance check", "All units reported in good operating condition (sample test).")
                )
            )

            "Project Lab & Research Journal" -> Quad(
                "Project: Smart School Garden Micro-Irrigation",
                "Lead: Class 8 STEM Cohort. Milestone: Soil Moisture Sensor Calibration. Progress: 75% complete. Mentor note: 'Great circuit diagram.'",
                listOf("Milestones", "Research Journal", "Collaborative"),
                listOf(
                    DemoAction("Add journal observation", "Observation saved: 'Sensor readings normalized at 35% moisture level.'"),
                    DemoAction("Mark milestone completed", "Milestone 3 marked complete in demo state.")
                )
            )

            "Emergency Communications" -> Quad(
                "Urgent Broadcast Draft: Heavy Rain Alert",
                "Notice: 'Classes will conclude at 1:30 PM today due to regional weather warning. School buses depart at 1:45 PM.' Mode: In-App Priority Banner.",
                listOf("High Priority", "In-App Notice Only", "No SMS/Email"),
                listOf(
                    DemoAction("Preview priority banner", "Simulating in-app emergency banner alert across all portals."),
                    DemoAction("Save alert draft", "Emergency draft stored locally. Delivery is disabled in demo mode.")
                )
            )

            "Parent Communication Translator" -> Quad(
                "Multilingual Message: Annual Sports Day Notice",
                "English: 'Please send sports uniform tomorrow.' Translated to Hindi: 'कृपया कल खेल की वर्दी भेजें।' Translated to Bengali: 'অনুগ্রহ করে আগামীকাল খেলার পোশাক পাঠান।'",
                listOf("Multilingual", "Sample Translations", "Family Support"),
                listOf(
                    DemoAction("Switch to Hindi preview", "Previewing sample Hindi translation. Notice dates and names preserved."),
                    DemoAction("Switch to Bengali preview", "Previewing sample Bengali translation for family circular.")
                )
            )

            "Study Recovery Planner" -> Quad(
                "Targeted Practice: Fraction Operations & Decimals",
                "Diagnostic Finding: 3 calculation slips on unlike denominators. Recommended Action: 3 micro-steps of 8 minutes each with visual number lines.",
                listOf("Non-Punitive", "Remedial Plan", "Diagnostic"),
                listOf(
                    DemoAction("Load 1st micro-step", "Step 1 loaded: Visualizing LCM on interactive fraction bars."),
                    DemoAction("Complete practice sample", "Sample step marked complete. Progress updated locally.")
                )
            )

            "Green Campus" -> Quad(
                "Campus Eco-Dashboard: Daily Metrics",
                "Rooftop Solar Generated: 148 kWh. Rainwater Harvested: 420 Liters. Organic Waste Composted: 35 kg. Fictional manual log.",
                listOf("Sustainability", "Eco-Metrics", "Manual Log"),
                listOf(
                    DemoAction("Log new water reading", "Recorded 25L conservation increase in local sample state."),
                    DemoAction("Generate weekly summary", "Weekly eco-efficiency report compiled for student assembly.")
                )
            )

            "Trust & Security Centre" -> Quad(
                "System Protection & Privacy Assurance",
                "Authentication Factors: Enrolled. Role Boundary Check: Active. Data Encryption: Localized. Network Isolation: Demo Sandbox.",
                listOf("Zero Trust", "Role Safeguards", "Audit Logging"),
                listOf(
                    DemoAction("Run integrity diagnostic", "Security boundary test passed: Zero external network transmissions."),
                    DemoAction("View demo audit events", "Showing 3 recent fictional authentication timestamps.")
                )
            )

            "My Attendance", "Child Attendance", "Class Attendance", "Attendance Register" -> Quad(
                "Attendance Record: Term 1 (96% Present)",
                "Present: 48 days. Excused Absences: 2 days. Unexcused: 0 days. Status: Excellent attendance record.",
                listOf("Roll Call", "Attendance", "Daily Log"),
                listOf(
                    DemoAction("Mark sample attendance: Present", "Attendance marked Present in local demo session."),
                    DemoAction("Filter by month", "Showing filtered attendance view for September-October.")
                )
            )

            "My Marks", "Child Marks", "Marks & Exams" -> Quad(
                "Assessment Summary: Mid-Term Examination",
                "Mathematics: 92/100 (A+). Physics: 88/100 (A). Chemistry: 90/100 (A+). Teacher Feedback: 'Demonstrates strong analytical problem solving.'",
                listOf("Grades", "Examinations", "Term Report"),
                listOf(
                    DemoAction("Open sample report card", "Report card opened in preview mode. All figures are fictional."),
                    DemoAction("Export sample transcript", "Sample transcript generated for local viewing only.")
                )
            )

            "AI Study Tutor", "AI Chat Assistant" -> Quad(
                "Interactive Concept Explanation",
                "Topic: Photosynthesis Light & Dark Reactions. Step 1: Chlorophyll absorbs light energy in the thylakoids, splitting water molecules into oxygen and hydrogen ions.",
                listOf("Step-by-Step", "Syllabus Guided", "Sample Output"),
                listOf(
                    DemoAction("Request step 2 explanation", "Step 2: ATP and NADPH synthesized in the light stage power the Calvin cycle in the stroma."),
                    DemoAction("Ask follow-up question", "Sample response: 'The dark reaction does not require darkness, only the ATP products.'")
                )
            )

            "AI Study Notes & Flashcards" -> Quad(
                "Study Flashcard #1 of 5: Newton's Laws",
                "Front: 'What is Newton's First Law of Motion?' Back: 'An object remains at rest or in uniform motion unless acted upon by a net external force (Inertia).'",
                listOf("Flashcards", "Quick Recall", "Revision Deck"),
                listOf(
                    DemoAction("Flip flashcard", "Card flipped: 'Inertia — mass is the measure of inertia.'"),
                    DemoAction("Next sample card", "Showing Flashcard #2: Newton's Second Law (F = ma).")
                )
            )

            "Learning materials & Quizzes", "AI Quiz Generator" -> Quad(
                "Sample Practice Quiz: Cell Biology (Question 1)",
                "Question: Which organelle is primarily responsible for ATP synthesis?\nA) Ribosome\nB) Mitochondria (Correct)\nC) Golgi Apparatus\nD) Nucleolus",
                listOf("Multiple Choice", "Instant Check", "Curriculum"),
                listOf(
                    DemoAction("Select Option B (Mitochondria)", "Correct! Mitochondria generate cellular ATP via aerobic respiration. Demo score +1."),
                    DemoAction("Select Option A (Ribosome)", "Incorrect in demo test. Ribosomes synthesize proteins.")
                )
            )

            "Learning games" -> Quad(
                "Math Quest: Number Puzzle Challenge",
                "Find the missing term: 2, 6, 12, 20, 30, [ ? ]. Rule: Difference increases by +2 each step (4, 6, 8, 10, 12).",
                listOf("Interactive Practice", "Logic Game", "Fun Learning"),
                listOf(
                    DemoAction("Submit Answer: 42", "Great thinking! 30 + 12 = 42. Challenge solved in demo mode."),
                    DemoAction("Try another puzzle", "Loading next sample pattern puzzle.")
                )
            )

            "Homework & Assignments", "Homework & Curriculum" -> Quad(
                "Assignment: Linear Equations Problem Set 4",
                "Due Date: Friday, 4:00 PM. Tasks: Complete Questions 1 to 10 on page 142. Submission Status: Ready for review.",
                listOf("Assignments", "Due Dates", "Classwork"),
                listOf(
                    DemoAction("Submit sample solution", "Sample assignment marked submitted locally. Not transmitted to school."),
                    DemoAction("View teacher rubric", "Rubric displayed: Accuracy (60%), Working Steps (40%).")
                )
            )

            "Study Clock & Goals" -> Quad(
                "Study Session Timer: 25-Min Pomodoro",
                "Current Goal: Science Chapter 4 Revision. Time Remaining: 18:45. Milestone: 2 intervals completed today.",
                listOf("Productivity", "Pomodoro Timer", "Personal Goal"),
                listOf(
                    DemoAction("Toggle study timer", "Timer toggled locally in demo mode."),
                    DemoAction("Mark goal achieved", "Daily study milestone marked achieved in demo session.")
                )
            )

            "Ask a teacher", "Questions", "Complaints", "Messages" -> Quad(
                "Inquiry Channel: Mathematics Help Desk",
                "Question: 'Could you explain why the discriminant b²-4ac tells us the number of real roots?' Status: Answered by Teacher Meera.",
                listOf("Academic Doubts", "Help Desk", "Safe Communication"),
                listOf(
                    DemoAction("Send follow-up question", "Follow-up question saved to local demo conversation."),
                    DemoAction("Mark resolved", "Inquiry marked resolved in local demo view.")
                )
            )

            "Notices", "Mailbox", "Email Centre" -> Quad(
                "Official Circular #2026-08: Annual Science Fair",
                "Notice: 'Green Valley School invites student project submissions for the Inter-School Science Colloquium on Nov 18. Register with your science faculty.'",
                listOf("Official Circular", "School Notice", "Announcements"),
                listOf(
                    DemoAction("Acknowledge circular", "Acknowledgment recorded locally in demo state."),
                    DemoAction("Share sample link", "Sample share link copied to clipboard.")
                )
            )

            else -> Quad(
                "Sample Preview: $featureName",
                "Demonstrating the $featureName feature for the ${role.label} workspace. Explore the role-specific capabilities using the controls below.",
                listOf("Feature Tour", role.label, "Local Simulation"),
                listOf(
                    DemoAction("Execute sample action", "Sample action executed. Everything remains local to this device."),
                    DemoAction("View sample configuration", "Displaying fictional configuration parameters.")
                )
            )
        }

        return FeatureInfo(
            name = featureName,
            category = category,
            description = description,
            sampleTitle = sampleTitle,
            sampleDetail = sampleDetail,
            sampleTags = tags,
            actions = actions,
            isSmartFeatureModule = isSmart
        )
    }

    private data class Quad(
        val title: String,
        val detail: String,
        val tags: List<String>,
        val actions: List<DemoAction>
    )
}
