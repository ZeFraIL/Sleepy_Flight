# 📱 Android Application Documentation (LEVEL 10/10)
________________________________________
🧾 General Information
**Project Name:**
Sleepy Flight
**Author(s):**
Zeev
**Date:**
July 2026
**Language:**
Java
**Development Environment:**
Android Studio
**Android Version (minSdk / targetSdk):**
28 / 35
________________________________________
🎯 Project Goal
• **What task does the application solve:** The app provides white noise for sleeping on flights, but only activates when safety conditions are met (Airplane Mode ON) and privacy/courtesy conditions are met (Headphones CONNECTED).
• **Why is this task important:** It ensures the user doesn't accidentally play noise out loud on a plane and encourages enabling Airplane Mode for safety and battery saving.
• **Target Audience:** Frequent flyers, travelers, and people who use white noise to sleep in noisy environments.
________________________________________
📌 Application Requirements
**Functional Requirements**
• Detect Airplane Mode status.
• Detect Headphone connection status.
• Play/Pause white noise based on these conditions and user toggle.
• Update UI to reflect current state.
**Non-functional Requirements**
• **Performance:** Minimal battery consumption while idling.
• **Usability:** Simple, one-screen interface.
• **Reliability:** Accurate detection of system state changes via BroadcastReceivers.
________________________________________
🧠 General Architecture
• **Chosen approach:**
– MVC (Model-View-Controller)
• **Why this was chosen:** The project is compact. A full MVVM would be overkill for a single-screen app with minimal data persistence.
• **Main system components:**
– `MainActivity` (View & Controller)
– `AirplaneReceiver` (Input Handler)
– `HeadphonesReceiver` (Input Handler)
– `MediaPlayer` (Resource/Model)
________________________________________
🧩 UML Diagram
[MainActivity] <--> [AirplaneReceiver]
[MainActivity] <--> [HeadphonesReceiver]
[MainActivity] --> [MediaPlayer]
[MainActivity] --> [UI Layout]
________________________________________
**Package Explanation:**
- Currently using a flat package structure `com.example.sleepyflight` as the project is small.
- For scaling, logic would be moved to a `service` or `domain` package, and UI to a `ui` package.
________________________________________
🧩 Detailed Class Description
📌 **Class: MainActivity**
**Role:**
The central hub of the application.
**Responsibility:**
Managing the UI, initializing the MediaPlayer, and coordinating logic between state changes.
**Main Methods:**
- `onCreate()` — Initializes UI components and registers receivers.
- `checkConditionsAndPlay()` — Validates if Airplane Mode is ON and Headphones are IN before playing.
- `updateUI()` — Refreshes the text labels on the screen.
- `onDestroy()` — Releases media resources.
**Interaction with other classes:**
Receives updates from `AirplaneReceiver` and `HeadphonesReceiver`.
________________________________________
📌 **Class: AirplaneReceiver**
**Role:**
System Event Listener.
**Why it's used:**
To automatically react when the user toggles Airplane Mode without needing the app to be actively polling.
________________________________________
📌 **Class: HeadphonesReceiver**
**Role:**
System Event Listener.
**Why it's used:**
To ensure audio only plays when headphones are plugged in, preventing public disturbance.
________________________________________
🔄 Application Workflow Diagram
1. User opens the app.
2. App checks current Airplane Mode and Headphone status.
3. User clicks "Play".
4. If (Airplane=ON AND Headphones=CONNECTED), sound starts.
5. If user toggles Airplane Mode OFF while playing, sound stops immediately.
________________________________________
🎨 UI/UX Analysis
• **Why the interface is made this way:** Focused on clarity. In a dark plane cabin, the user needs to see status immediately.
• **Principles used:**
– **Simplicity:** One button, two status lines.
– **Logic:** Color-coded or clear text feedback.
– **Accessibility:** Large text and buttons.
• **Improvements:** Adding a sleep timer or selection of different sounds.
________________________________________
⚙️ Threading
• **Used:**
– `MediaPlayer` internal threading (asynchronous preparation and playback).
• **Why:** Android's `MediaPlayer` handles the heavy lifting of audio decoding off the main thread.
• **Prevention:**
– **ANR:** Avoided by not performing blocking I/O on the main thread.
– **Memory leaks:** `onDestroy` ensures the `MediaPlayer` is released.
________________________________________
💾 Data Handling
• **Storage:** No persistent data storage currently (stateless).
• **Why:** The app's behavior is entirely dependent on real-time system states.
________________________________________
🌐 Networking
• **Status:** No network usage. This is a key feature as the app is designed for "Offline" flight mode.
________________________________________
🔐 Security (Basic level)
• **Sensitive data:** None.
• **Protection:** No permissions required other than standard system state listeners.
________________________________________
🧪 Testing
• **Tests:**
– **Manual Testing:** Verifying play/pause behavior by toggling hardware settings.
– **Unit Tests:** Can be added to test the logic of `checkConditionsAndPlay`.
________________________________________
🐞 Error Handling
• **Anticipated errors:** Media resource missing, system state permission issues.
• **Reaction:** Toast messages to inform the user.
________________________________________
⚡ Performance
• **Optimizations:** Using `BroadcastReceivers` instead of background services or polling.
• **Bottlenecks:** Minimal.
________________________________________
🚀 Extension Possibilities
• Add a volume slider.
• Multiple soundscapes (Rain, Ocean, Cabin noise).
• Material You (dynamic coloring) support.
