
package com.borun.applock

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.provider.Settings
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setPadding(28, 35, 28, 35)
        layout.setBackgroundColor(Color.rgb(18, 24, 40))

        val title = TextView(this)
        title.text = "🔐 BORUN APP LOCK"
        title.textSize = 27f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER

        val description = TextView(this)
        description.text = "Защита выбранных приложений"
        description.textSize = 18f
        description.setTextColor(Color.LTGRAY)
        description.gravity = Gravity.CENTER
        description.setPadding(0, 25, 0, 25)

        val appsButton = Button(this)
        appsButton.text = "Выбрать приложения"

        appsButton.setOnClickListener {
            startActivity(
                Intent(this, AppListActivity::class.java)
            )
        }

        val settingsButton = Button(this)
        settingsButton.text = "Настройки защиты"

        settingsButton.setOnClickListener {
            startActivity(
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            )
        }

        val status = TextView(this)
        status.text = "BORUN APP LOCK 1.0"
        status.textSize = 14f
        status.setTextColor(Color.LTGRAY)
        status.gravity = Gravity.CENTER
        status.setPadding(0, 30, 0, 0)

        layout.addView(title)
        layout.addView(description)
        layout.addView(appsButton)
        layout.addView(settingsButton)
        layout.addView(status)

        setContentView(layout)
    }
}
