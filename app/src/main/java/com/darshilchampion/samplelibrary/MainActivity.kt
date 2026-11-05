package com.darshilchampion.samplelibrary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.darshilchampion.samplelibrary.databinding.ActivityMainBinding
import java.io.File
import com.darshilchampion.gitsample.ChampionSDK

class MainActivity : AppCompatActivity() {

    lateinit var context: Context
    lateinit var app_settingsSharedPreferences: SharedPreferences
    lateinit var app_settingsEditor: SharedPreferences.Editor
    //var appConstant = AppConstant()

    var user_id = ""

    val TAG = "My MainActivity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge (required on Android 15)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "---------opened")

        context = this@MainActivity
        app_settingsSharedPreferences = getSharedPreferences(getString(R.string.app_settings), MODE_PRIVATE)
        app_settingsEditor = app_settingsSharedPreferences.edit()
        //viewDialog = ViewDialog(context)

        deleteCache(this)



        val championSDK = ChampionSDK
        // Initialize SDK
        championSDK.initialize(this)

        // Show custom message
        championSDK.showMessage(
            this,
            "Hello from ChampionSDK!"
        )

        championSDK.showBottomSheet(
            this,
            title = "Champion SDK",
            message = "Your SDK is successfully integrated!"
        )





        // New Modern BackPressed Handler
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        })

    }




    fun deleteCache(context: Context) {
        try {
            val dir = context.getCacheDir()
            deleteDir(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory()) {
            val children = dir.list()
            for (i in children!!.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
            return dir.delete()
        } else if (dir != null && dir.isFile()) {
            return dir.delete()
        } else {
            return false
        }
    }



}