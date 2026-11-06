# SchoolOS AI Explorer

A standalone, installable Android product-tour application built with Kotlin and Jetpack Compose. Modeled directly on the official SchoolOS AI website design, it serves as the entry point for exploring the Student and Parent AI portals with realistic, fictional sample data and safe local demo interactions.

---

## 🛡️ Product and Safety Boundaries

1. **100% Fictional Data**: All names, statistics, classes, attendance records, marks, and circulars are simulated sample data.
2. **No Credentials / No Sign-In**: Open directly into the interactive tour. No real school credentials, passwords, OTPs, or biometric factors are requested.
3. **No External Network Calls**: Runs entirely offline on device. No analytics, tracking, cloud sync, or external API transmissions.
4. **No Sensitive Android Permissions**: Zero camera, microphone, contacts, location, storage, or notification permissions required.
5. **Local-Only Interactions**: All filters, sample form submissions, quiz choices, and workflow toggles are ephemeral and can be cleared instantly via the **Reset demo** action.

---

## 📦 Package & Application Information

- **App Name**: `SchoolOS AI Explorer`
- **Application ID**: `com.schoolos.explorer`
- **Minimum SDK**: Android 7.0 (API 24)
- **Target SDK**: Android 16 (API 36)
- **UI Framework**: 100% Jetpack Compose with Material Design 3

---

## 🏛️ Role Portals & Feature Coverage

Every role and feature specified in the product specification is fully represented with navigable, interactive sample previews:

| Role Portal | Features | Key Capabilities Covered |
| :--- | :---: | :--- |
| **Administrator** | **49** | Central 15 Smart Modules Hub, Trust & Security, Auto Admin Mode, CMS, Accounts, Governance |
| **Regional Office** | **11** | Regional Oversight, Regional Command Centre, Cross-School Analytics, Calendar |
| **Principal** | **39** | School Leadership, Attendance Registers, Staff & Learner Rosters, Teaching Assignments, Exams |
| **Teacher** | **33** | Classroom Management, Attendance, Marks & Exams, AI Quiz Generator, Resource Sharing, Notices |
| **Student** | **37** | Study Tutor, Flashcards, Timetable, Learning Games, My Marks, Attendance, Study Clock & Goals |
| **Parent** | **30** | Linked Learners, Performance & Progress, Child Attendance & Marks, Parenting Tips, Messages |

### 🤖 The 15 Administrator AI & Smart Features Modules
All 15 modules are centrally accessible from the Administrator portal under **AI & Smart Features**:
1. SchoolOS Command Centre
2. AI Exam Readiness Predictor
3. Student Achievement Passport
4. Adaptive Worksheet Studio
5. School Knowledge Brain
6. Smart Timetable Optimizer
7. School Request and Approval Workflow
8. AI Lost-and-Found
9. Smart School Resource Manager
10. Project Lab and Research Journal
11. Emergency Communication Mode
12. Parent Communication Translator
13. Intelligent Study Recovery Planner
14. Green Campus Intelligence
15. SchoolOS Trust and Security Centre

---

## 🛠️ Build & Test Commands

### Run Unit & Robolectric Tests:
```bash
gradle :app:testDebugUnitTest
```

### Build Debug APK:
```bash
gradle :app:assembleDebug
```

---

## 📱 Navigation & Features
- **Back Navigation**: Supports Android system back gestures (`BackHandler`) to step back from a feature to the role overview, and from the role overview to the home portal picker.
- **Theme Support**: In-app theme toggle supporting System Default, Dark Mode, and Light Mode.
- **Reset Demo**: Tapping **Reset** clears search queries, resets category filters, and returns to initial state.
