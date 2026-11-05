package com.darshilchampion.gitsample

import android.content.Context
import android.widget.Toast

object ChampionSDK {

    fun initialize(context: Context) {
        Toast.makeText(
            context.applicationContext,
            "ChampionSDK initialized!",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun showMessage(context: Context, message: String) {
        Toast.makeText(
            context.applicationContext,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}
