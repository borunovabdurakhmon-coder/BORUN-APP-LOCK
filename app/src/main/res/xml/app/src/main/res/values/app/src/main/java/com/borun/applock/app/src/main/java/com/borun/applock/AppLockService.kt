package com.borun.applock

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.content.Intent

class AppLockService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType !=
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            return
        }

        val packageName = event.packageName?.toString() ?: return

        val preferences = getSharedPreferences(
            "BORUN_SETTINGS",
            MODE_PRIVATE
        )

        val lockedApps = preferences.getStringSet(
            "locked_apps",
            emptySet()
        ) ?: emptySet()

        if (lockedApps.contains(packageName)) {
            val intent = Intent(this, LockActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("package_name", packageName)
            startActivity(intent)
        }
    }

    override fun onInterrupt() {
        // Служба приостановлена Android.
    }
}
