# Mi-Freeform / Floating-Tab Open API (updated)

The freeform window interfaces are open for other apps to use. You can start a freeform window provided by this app in the following ways.

## 1. Open the app selection interface

The activity that shows the freeform app picker is:

`com.sunshine.freeform.ui.floating.FloatingActivity`

You can start this activity from another app to open the selection UI.

**Note:** This activity currently requires the keep-alive service of the freeform app to be running.

## 2. Directly open a freeform window via broadcast

You can also send a broadcast to start a freeform window for a specific activity. Examples:

### Method 1

```kotlin
val packageName: String = "com.sunshine.freeform"
val activityName: String = "com.sunshine.freeform.ui.main.MainActivity"
val userId: Int = 0
val intent = Intent("com.sunshine.freeform.start_freeform").apply {
    setPackage("com.sunshine.freeform")
    // Package name of the app to open in freeform
    putExtra("packageName", packageName)
    // Activity name to open. Note: the activity may need to be exported.
    putExtra("activityName", activityName)
    // Optional, defaults to -1. Use this for multi-user / app clone cases.
    putExtra("userId", userId)
    putExtra(Intent.EXTRA_INTENT, intent)
}
context.sendBroadcast(intent)
```

### Method 2

```kotlin
val packageName: String = "com.sunshine.freeform"
val activityName: String = "com.sunshine.freeform.ui.main.MainActivity"
val startIntent: Intent = Intent().setComponent(ComponentName(packageName, activityName))
val intent = Intent("com.sunshine.freeform.start_freeform").apply {
    setPackage("com.sunshine.freeform")
    putExtra(Intent.EXTRA_INTENT, startIntent)
}
context.sendBroadcast(intent)
```

