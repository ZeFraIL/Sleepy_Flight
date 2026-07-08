# Class Description: HeadphonesReceiver

## 1. General Information
*   **Class Name:** `HeadphonesReceiver`
*   **Type:** BroadcastReceiver
*   **Purpose:** This class detects when headphones are plugged in or unplugged from the device.
*   **Interaction:** It updates the status in `MainActivity` and tells it to re-evaluate whether music should be playing.

## 2. Variables (Class Fields)
This class does not have its own persistent variables.

## 3. Class Methods

### Method name: `onReceive`
*   **Type:** `public`
*   **Return value:** `void`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `context` | `Context` | The environment of the application. |
    | `intent` | `Intent` | The message containing the headset plug state. |
*   **What does it do:**
    1.  Listens for `ACTION_HEADSET_PLUG`.
    2.  Extracts the `state` integer:
        - `0` means unplugged.
        - `1` means plugged in.
    3.  If unplugged: sets `MainActivity.isHeadphonesOn` to `false`.
    4.  If plugged in: sets `MainActivity.isHeadphonesOn` to `true`.
    5.  Shows a `Toast` notification.
    6.  Calls `MainActivity.checkConditionsAndPlay()` to update the sound and UI.
*   **When called:** Automatically by the system when a physical or Bluetooth headset state change is detected.
*   **What is important to understand:** The system uses an integer `0/1` instead of a boolean for headphones because some devices might report other states (like "with microphone" vs "without microphone"), but here we only care about the basic connection.

## 4. Lifecycle
Only the `onReceive` method is relevant.

## 5. Interface Interaction (UI)
*   **Toast:** Shows "Headphones Disconnected" or "Headphones Connected".

## 6. Interaction with other components
*   **MainActivity:** Calls static methods and updates static variables.
*   **System Broadcast:** Listens for `Intent.ACTION_HEADSET_PLUG`.

## 7. General logic of the class
Like the `AirplaneReceiver`, this is another **Sensor**. It monitors the headphone jack. Its only job is to update the state and alert the main activity.

## 8. Simplified explanation
**Explanation in simple words:**
Think of `HeadphonesReceiver` as a **Doorman** at a club. The Doorman's only job is to check if you are wearing the right "shoes" (Headphones). 
If you take them off, he immediately calls the DJ (`MainActivity`) and says: "Stop the music! No shoes, no service!" 
If you put them on, he tells the DJ: "Okay, they are ready to dance if the other conditions are met."
