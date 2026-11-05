package com.darshilchampion.gitsample

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog

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

    fun showBottomSheet(
        context: Context,
        title: String = "Welcome to ChampionSDK",
        message: String = "This bottom sheet is provided by ChampionSDK."
    ) {
        if (context !is Activity || context.isFinishing || context.isDestroyed) {
            return
        }

        val dialog = BottomSheetDialog(context)

        val container = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 32, 24, 24)
            setBackgroundColor(Color.WHITE)
        }

        val titleView = TextView(context).apply {
            text = title
            textSize = 22f
            setTextColor(Color.rgb(33, 33, 33))
            typeface = Typeface.DEFAULT_BOLD
        }

        val messageView = TextView(context).apply {
            text = message
            textSize = 16f
            setTextColor(Color.rgb(100, 100, 100))
            setPadding(0, 16, 0, 24)
        }

        val closeButton = Button(context).apply {
            text = "Got it"
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(81, 39, 179))
            setOnClickListener {
                dialog.dismiss()
            }
        }

        container.addView(
            titleView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        container.addView(
            messageView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        container.addView(
            closeButton,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        dialog.setContentView(container)
        dialog.show()
    }
}
