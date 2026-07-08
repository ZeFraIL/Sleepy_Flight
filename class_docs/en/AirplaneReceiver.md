# Class Description: AirplaneReceiver

## 1. General Information
*   **Class Name:** `AirplaneReceiver`
*   **Type:** BroadcastReceiver
*   **Purpose:** This class "listens" for changes in the device's Airplane Mode status. It acts as an automatic observer that wakes up when the system sends a signal.
*   **Interaction:** It communicates with `MainActivity` by updating its static variables and triggering its logic.

## 2. Variables (Class Fields)
This class does not have its own persistent variables. It uses local variables within its methods.

## 3. Class Methods

### Method name: `onReceive`
*   **Type:** `public`
*   **Return value:** `void`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `context` | `Context` | The environment of the application. |
    | `intent` | `Intent` | The message sent by the system containing details about the event. |
*   **What does it do:**
    1.  Checks if the received message (Intent) is indeed about "Airplane Mode Changed".
    2.  Extracts the "state" (True for ON, False for OFF) from the intent.
    3.  Updates the `MainActivity.isAirplaneOn` variable.
    4.  Shows a "Toast" (a small temporary popup message) to inform the user.
    5.  Calls `MainActivity.checkConditionsAndPlay()` to react immediately to the change.
*   **When called:** Automatically by the Android system whenever the user toggles the Airplane Mode switch in the settings.
*   **What is important to understand:** `BroadcastReceiver`s are transient. They exist only for the duration of the `onReceive` method. They should not perform long-running tasks.

## 4. Lifecycle
This class is not an Activity, so it doesn't have a standard lifecycle. It only has the `onReceive` event.

## 5. Interface Interaction (UI)
*   **Toast:** Uses `Toast.makeText` to show a brief message on the screen. It doesn't have a permanent UI layout.

## 6. Interaction with other components
*   **MainActivity:** Directly modifies the static fields of `MainActivity`.
*   **System Intent:** Listens for `Intent.ACTION_AIRPLANE_MODE_CHANGED`.

## 7. General logic of the class
The class is like a **Sensor**. It does nothing until it feels a change. When the change happens, it reports back to the main office (`MainActivity`) so it can decide what to do next.

## 8. Simplified explanation
**Explanation in simple words:**
Imagine `AirplaneReceiver` as a **Security Guard** standing by the Airplane Mode switch. 
The Guard doesn't control the plane, but as soon as he sees someone flip the switch, he picks up his walkie-talkie and shouts to the Captain (`MainActivity`): "Sir, the mode has changed! Check if we should still be playing music!"
The Guard also holds up a small sign (Toast) for the user to see that he noticed the change.
