package com.borun.applock

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class LockActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(20, 25, 40))
        }

        val title = TextView(this).apply {
            text = "BORUN APP LOCK"
            textSize = 26f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val message = TextView(this).apply {
            text = "Введите PIN-код"
            textSize = 18f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val pinInput = EditText(this).apply {
            hint = "PIN"
            inputType = 2
            textSize = 22f
            gravity = Gravity.CENTER
        }

        val unlockButton = Button(this).apply {
            text = "Разблокировать"
        }

        unlockButton.setOnClickListener {
            if (pinInput.text.toString() == "1234") {
                Toast.makeText(
                    this,
                    "Проверка PIN пройдена",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                pinInput.text.clear()
                Toast.makeText(
                    this,
                    "Неверный PIN",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        layout.addView(title)
        layout.addView(message)
        layout.addView(pinInput)
        layout.addView(unlockButton)

        setContentView(layout)
    }
}
