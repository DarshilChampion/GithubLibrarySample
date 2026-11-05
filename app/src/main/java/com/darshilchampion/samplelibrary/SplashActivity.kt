package com.darshilchampion.samplelibrary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class SplashActivity : AppCompatActivity() {

    lateinit var context: Context

    val TAG = "My SplashActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install system splash screen (optional for Android 12+)
        val splashScreen = installSplashScreen()
        // Disable the animated icon
        splashScreen.setKeepOnScreenCondition { true } // just for delay
        super.onCreate(savedInstanceState)
        // Hide status bar
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        // Set the layout
        setContentView(R.layout.activity_splash)
        Log.d(TAG, "---------opened")
        context = this@SplashActivity

        // Delay 1 second and move to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 500)
    }
}
