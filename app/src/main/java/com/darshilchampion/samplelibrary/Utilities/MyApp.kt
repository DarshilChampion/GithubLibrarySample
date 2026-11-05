package com.darshilchampion.samplelibrary.Utilities

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import com.darshilchampion.samplelibrary.R

class MyApp : Application() {

    val TAG = "My MyApp"
    companion object {
        lateinit var instance: MyApp
            private set
    }

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Initialize SharedPreferences
        sharedPrefs = getSharedPreferences(getString(R.string.app_settings), Context.MODE_PRIVATE)

        


        // Add default parameters
        /*if (Singleton.getInstance().getSelectedLanguage() == "hindi") {
            newBodyBuilder.addEncoded("language", "hi_IN")
        } else {
            newBodyBuilder.addEncoded("language", "en_IN")
        }*/
        createChannel()

    }

    private fun createChannel() {

        val channel = NotificationChannel(
            "call_channel",
            "Incoming Calls",
            NotificationManager.IMPORTANCE_HIGH
        )

        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    // Save user preference
    fun setDarkMode(enabled: Boolean) {
        Log.d(TAG, "setDarkMode enabled = $enabled")
        sharedPrefs.edit { putBoolean(getString(R.string.isDarkMode), enabled) }
        applyTheme(enabled)
    }

    // Get saved preference, default is light (false)
    fun isDarkModeEnabled(): Boolean {
        Log.d(TAG, "isDarkModeEnabled = ${sharedPrefs.getBoolean(getString(R.string.isDarkMode), false)}")
        return sharedPrefs.getBoolean(getString(R.string.isDarkMode), false)
    }

    // Apply theme
    private fun applyTheme(darkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (darkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun getAppContext(): Context {
        return instance.applicationContext
    }
}

