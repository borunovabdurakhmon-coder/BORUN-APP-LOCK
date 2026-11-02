package com.borun.applock

import android.app.Activity
import android.os.Bundle
import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class AppListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = getSharedPreferences(
            "BORUN_SETTINGS",
            MODE_PRIVATE
        )

        val savedApps = preferences.getStringSet(
            "locked_apps",
            emptySet()
        )?.toMutableSet() ?: mutableSetOf()

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(20, 20, 20, 20)
        layout.setBackgroundColor(Color.rgb(18, 24, 40))

        val title = TextView(this)
        title.text = "Выбери приложения для защиты"
        title.textSize = 21f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER
        layout.addView(title)

        val list = ListView(this)
        val apps = packageManager.getInstalledApplications(0)
            .filter {
                packageManager.getLaunchIntentForPackage(it.packageName) != null &&
                it.packageName != packageName
            }
            .sortedBy {
                packageManager.getApplicationLabel(it).toString()
            }

        val adapter = object : ArrayAdapter<ApplicationInfo>(
            this,
            android.R.layout.simple_list_item_multiple_choice,
            android.R.id.text1,
            apps
        ) {
            override fun getView(
                position: Int,
                convertView: android.view.View?,
                parent: android.view.ViewGroup
            ): android.view.View {
                val view = super.getView(position, convertView, parent)
                val item = apps[position]
                (view as TextView).text =
                    packageManager.getApplicationLabel(item).toString()
                view.setTextColor(Color.WHITE)
                return view
            }
        }

        list.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        list.adapter = adapter

        apps.forEachIndexed { index, app ->
            list.setItemChecked(
                index,
                savedApps.contains(app.packageName)
            )
        }

        list.setOnItemClickListener { _, _, position, _ ->
            val packageName = apps[position].packageName

            if (list.isItemChecked(position)) {
                savedApps.add(packageName)
            } else {
                savedApps.remove(packageName)
            }
        }

        layout.addView(
            list,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val saveButton = Button(this)
        saveButton.text = "Сохранить выбор"

        saveButton.setOnClickListener {
            preferences.edit()
                .putStringSet("locked_apps", savedApps.toSet())
                .apply()

            Toast.makeText(
                this,
                "Выбор сохранён",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }

        layout.addView(saveButton)
        setContentView(layout)
    }
}
