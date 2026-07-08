# Class Description: MainActivity

## 1. General Information
*   **Class Name:** `MainActivity`
*   **Type:** Activity
*   **Purpose:** This is the main screen of the application. It is responsible for managing the user interface (UI), controlling the music player (MediaPlayer), and checking if the conditions to play the sound are met.
*   **Interaction:** It interacts with `AirplaneReceiver` and `HeadphonesReceiver` to get updates about the system state. It also controls the `MediaPlayer` to play white noise.

## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `mediaPlayer` | `MediaPlayer` | Plays the audio file (white noise). | In `onCreate`, `checkConditionsAndPlay`, and `onDestroy`. |
| `isAirplaneOn` | `boolean` | Stores whether Airplane Mode is active. | Updated by `AirplaneReceiver`, checked in `checkConditionsAndPlay`. |
| `isHeadphonesOn` | `boolean` | Stores whether headphones are plugged in. | Updated by `HeadphonesReceiver`, checked in `checkConditionsAndPlay`. |
| `playButton` | `Button` | The button the user clicks to start/stop the sound. | In `onCreate` to set a click listener. |
| `airplaneStatus` | `TextView` | Displays the text status of Airplane Mode. | In `updateUI`. |
| `headphonesStatus` | `TextView` | Displays the text status of the Headphones. | In `updateUI`. |

## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Return value:** `void`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `savedInstanceState` | `Bundle` | Contains saved data from a previous session (if any). |
*   **What does it do:**
    1.  Sets the visual layout using `setContentView`.
    2.  Finds and connects the UI elements (Button, TextViews) using `findViewById`.
    3.  Initializes the `MediaPlayer` with a specific sound resource (`R.raw.white_noise`).
    4.  Sets the music to loop infinitely.
    5.  Sets an "onClick" listener for the play button.
    6.  Registers "Receivers" to listen for system changes (Airplane Mode and Headset Plug).
    7.  Calls `updateUI` to show the initial state.
*   **When called:** Automatically by Android when the app starts.
*   **What is important to understand:** This is the entry point. If you forget to initialize something here, the app might crash later.

### Method name: `checkConditionsAndPlay`
*   **Type:** `public static`
*   **Return value:** `void`
*   **Parameters:** None
*   **What does it do:**
    1.  Checks if either `isAirplaneOn` or `isHeadphonesOn` is false.
    2.  If conditions are NOT met: It pauses the music if it's currently playing.
    3.  If conditions ARE met: It toggles the music (starts if paused, pauses if playing).
    4.  Calls `updateUI` to refresh the screen text.
*   **When called:** When the user clicks the play button, or when a Receiver detects a change in Airplane Mode or Headphones.
*   **What is important to understand:** Note that it is `static`, which allows the Receivers to call it directly. However, it currently pauses the music in both "else" branches if it's already playing, which acts as a toggle.

### Method name: `updateUI`
*   **Type:** `public static`
*   **Return value:** `void`
*   **Parameters:** None
*   **What does it do:** Updates the text on the screen to show "Active/Off" for Airplane Mode and "Connected/Disconnected" for Headphones.
*   **When called:** Whenever the state changes.
*   **What is important to understand:** This method must be called to keep the user informed.

### Method name: `onDestroy`
*   **Type:** `protected`
*   **Return value:** `void`
*   **Parameters:** None
*   **What does it do:** Releases the `MediaPlayer` resources back to the system.
*   **When called:** When the app is closed or destroyed.
*   **What is important to understand:** Crucial for preventing memory leaks. If we don't "release" the player, it might keep using battery/memory even after the app is closed.

## 4. Lifecycle
*   **onCreate():** Called when the Activity is created. This is where we setup the "stage" of our app.
*   **onDestroy():** Called before the Activity is destroyed. We use it to clean up resources like the `MediaPlayer`.

## 5. Interface Interaction (UI)
*   **Buttons:** `playButton` - Handles user interaction to toggle sound.
*   **TextViews:** `airplaneStatus`, `headphonesStatus` - Provide visual feedback.
*   **findViewById:** Used to link the XML layout elements to the Java variables.

## 6. Interaction with other components
*   **Receivers:** The class uses `registerReceiver` to listen for system-wide broadcasts.
*   **MediaPlayer:** A system component used to handle audio playback.

## 7. General logic of the class
The class acts as a coordinator. It waits for the user to want to play music, but it acts like a "gatekeeper" that only allows music to play if the phone is in Airplane Mode and headphones are connected.

**Use case:**
1. User opens app.
2. User plugs in headphones. `HeadphonesReceiver` tells `MainActivity` that `isHeadphonesOn` is true.
3. User clicks Play. Nothing happens because Airplane Mode is still Off.
4. User turns on Airplane Mode. `AirplaneReceiver` tells `MainActivity` to check conditions.
5. Music starts playing automatically.

## 8. Simplified explanation
**Explanation in simple words:**
Think of `MainActivity` as the **Captain of a Plane**. The Captain has a checklist before takeoff (playing music).
- One item on the checklist is "Airplane Mode".
- Another is "Headphones connected".
The Captain (MainActivity) won't let the engines start (MediaPlayer) until both boxes are checked. If you are flying and suddenly unplug your headphones, the Captain immediately stops the engines to make sure you don't disturb other passengers.

---
**Note for Improvement:** Using `public static` for UI elements and logic can lead to memory leaks or issues if the Activity is recreated (e.g., rotating the screen). It is better to use a proper communication pattern or a ViewModel.
