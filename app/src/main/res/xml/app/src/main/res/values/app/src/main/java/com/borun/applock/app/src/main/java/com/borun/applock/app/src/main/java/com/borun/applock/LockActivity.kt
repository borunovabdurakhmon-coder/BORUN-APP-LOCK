package com.borun.applock

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.WindowManager
import android.widget.*
import android.text.InputType

class LockActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setPadding(30, 30, 30, 30)
        layout.setBackgroundColor(Color.rgb(18, 24, 40))

        val title = TextView(this)
        title.text = "🔒 BORUN APP LOCK"
        title.textSize = 27f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER

        val message = TextView(this)
        message.text = "Введите PIN-код для продолжения"
        message.textSize = 18f
        message.setTextColor(Color.WHITE)
        message.gravity = Gravity.CENTER
        message.setPadding(0, 25, 0, 20)

        val pinInput = EditText(this)
        pinInput.hint = "PIN-код"
        pinInput.inputType =
            InputType.TYPE_CLASS_NUMBER or
            InputType.TYPE_NUMBER_VARIATION_PASSWORD
        pinInput.setTextColor(Color.WHITE)
        pinInput.setHintTextColor(Color.LTGRAY)
        pinInput.gravity = Gravity.CENTER

        val button = Button(this)
        button.text = "Разблокировать"

        val status = TextView(this)
        status.setTextColor(Color.RED)
        status.gravity = Gravity.CENTER

        button.setOnClickListener {
            val preferences = getSharedPreferences(
                "BORUN_SETTINGS",
                MODE_PRIVATE
            )

            val savedPin = preferences.getString("pin", "1234")

            if (pinInput.text.toString() == savedPin) {
                finish()
            } else {
                status.text = "Неверный PIN-код"
                pinInput.text.clear()
            }
        }

        layout.addView(title)
        layout.addView(message)
        layout.addView(pinInput)
        layout.addView(button)
        layout.addView(status)

        setContentView(layout)
    }

    override fun onBackPressed() {
        // Не закрываем экран кнопкой «Назад».
    }
}
