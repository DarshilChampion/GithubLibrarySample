package com.darshilchampion.samplelibrary.Utilities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.WindowCompat
import com.darshilchampion.samplelibrary.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.Locale.getDefault

public class AppConstant {

    val TAG = "My AppConstant"
    lateinit var app_settingsSharedPreferences: SharedPreferences
    lateinit var app_settingsEditor: SharedPreferences.Editor


    fun setFirebaseEvent(apiName: String?, value: String?) {
        Log.d(TAG, "setFirebaseEventOfClick apiName = " + apiName)
        Log.d(TAG, "setFirebaseEventOfClick value = " + value)
        /*try {
            val firebaseAnalytics: FirebaseAnalytics
            firebaseAnalytics = FirebaseAnalytics.getInstance(MyApp().getAppContext())
            val params = Bundle()
            params.putString("value", value)
            params.putString("response_time", getCurrentDate())
            firebaseAnalytics.logEvent(apiName.toString(), params)
        } catch (e: Exception) {
            Log.d(TAG, "setFirebaseEvent catch e = " + e)
        }*/
    }

    fun setFirebaseEventOfClick(apiName: String?) {
        Log.d(TAG, "setFirebaseEventOfClick apiName = " + apiName)
        /*try {
            val firebaseAnalytics: FirebaseAnalytics
            firebaseAnalytics = FirebaseAnalytics.getInstance(MyApp().getAppContext())
            val params = Bundle()
            params.putString("value", "Click_" + apiName)
            params.putString("response_time", getCurrentDate())
            firebaseAnalytics.logEvent("Click_" + apiName, params)
        } catch (e: Exception) {
            Log.d(TAG, "setFirebaseEventOfClick catch e = " + e)
        }*/
    }


    val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")

    fun makeStringTitleCase(text: String?): String {
        if (text.isNullOrBlank()) return ""

        return text.trim()
            .split("\\s+".toRegex())
            .joinToString(" ") { word ->
                word.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase() else it.toString()
                }
            }
    }

    fun showKeyboard(view: View) {
        view.requestFocus()
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun getCurrentDate(): String {
        val dateFormat = "dd/MM/yyyy hh:mm:ss.SSS"
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        return formatter.format(calendar.getTime())
    }


    fun getCurrentDateCommonFun(): String {
        //23-FEB-2026
        val dateFormat = "dd-MMM-yyyy"
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        return formatter.format(calendar.getTime())
    }

    fun showDatePicker(
        context: Context,
        onDateSelected: (String) -> Unit
    ) {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->

                val selectedCalendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, selectedYear)
                    set(Calendar.MONTH, selectedMonth)
                    set(Calendar.DAY_OF_MONTH, selectedDay)
                }

                val format = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
                val formattedDate = format.format(selectedCalendar.time)

                onDateSelected(formattedDate)

            },
            year,
            month,
            day
        )

        //datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

        datePickerDialog.show()
    }

    fun convertDateFormat(inputDate: String): String {
        //23-FEB-2026 to 2026-02-23
        return try {
            val inputFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

            val date = inputFormat.parse(inputDate)
            outputFormat.format(date!!)
        }
        catch (ex: Exception) {
            Log.d(TAG, "convertDateFormat catch ex = $ex")
            ""
        }
    }

    fun convertDateFormatFrom_yyyyMMdd_to_ddMMMyyyy(inputDate: String): String {
        //2026-02-23 to 23-FEB-2026
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)

            val date = inputFormat.parse(inputDate)
            //Log.d(TAG, "convertDateFormatFrom_yyyyMMdd_to_ddMMMyyyy inputDate = $inputDate")
            //Log.d(TAG, "convertDateFormatFrom_yyyyMMdd_to_ddMMMyyyy date = $date")
            //Log.d(TAG, "convertDateFormatFrom_yyyyMMdd_to_ddMMMyyyy outputFormat.format(date!!) = ${outputFormat.format(date!!)}")
            outputFormat.format(date!!)
        }
        catch (ex: Exception) {
            Log.d(TAG, "convertDateFormat catch ex = $ex")
            ""
        }
    }


    fun getCommaValue(enterAmount: Double?): String {
        val formatter = DecimalFormat("##,##,##,###.##")
        return formatter.format(enterAmount)
    }

    fun formatAmountIn_K_L_Cr(amount: Double): String {

        fun format(value: Double): String {
            return if (value % 1.0 == 0.0) {
                value.toInt().toString()
            } else {
                String.format("%.1f", value)
            }
        }

        return when {
            amount >= 10000000 -> "₹${format(amount / 10000000.0)}Cr"
            amount >= 100000   -> "₹${format(amount / 100000.0)}L"
            amount >= 1000     -> "₹${format(amount / 1000.0)}K"
            else               -> "₹${format(amount)}"
        }
    }



    fun getPaymentModeStringConverter(str: String): String {
        return if (str.lowercase(getDefault()) == "online"){
            "Bank"
        }
        else if (str.lowercase(getDefault()) == "cash"){
            "Cash"
        }
        else {
            ""
        }
    }

    fun getInitials(name: String): String {
        val words = name.trim().split("\\s+".toRegex())

        return when {
            words.size >= 2 -> (words[0].first().toString() + words[1].first()).uppercase()
            words[0].length >= 2 -> words[0].take(2).uppercase()
            else -> words[0].uppercase()
        }
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPAN(pan: String): Boolean {
        val panRegex = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$".toRegex()
        return panRegex.matches(pan)
    }

    fun isValidMobile(mobile: String): Boolean {
        val mobileRegex = "^[6-9][0-9]{9}$".toRegex()
        return mobileRegex.matches(mobile)
    }

    fun isValidIFSC(ifsc: String): Boolean {
        val ifscPattern = Regex("^[A-Z]{4}0[A-Z0-9]{6}$")
        return ifscPattern.matches(ifsc.uppercase())
    }

    fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$"
        )
        return passwordRegex.matches(password)
    }

    fun vibrateButton() {
        //it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        val vibrator = MyApp().getAppContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(40, VibrationEffect.DEFAULT_AMPLITUDE))
    }

    fun logoutAndClearData(activity: Activity) {

        val app_settingsSharedPreferences = activity.getSharedPreferences(activity.getString(R.string.app_settings), MODE_PRIVATE)
        /*app_settingsSharedPreferences.edit {
            clear()
        }*/
        app_settingsSharedPreferences.edit().clear().commit() // use commit instead of apply

        /*Singleton.getInstance().user_id = ""
        Singleton.getInstance().user_name = ""
        Singleton.getInstance().user_mobileNumber = ""
        Singleton.getInstance().user_role = ""
        Singleton.getInstance().user_status = ""
        Singleton.getInstance().user_societies = ""
        Singleton.getInstance().deviceDetails = ""
        Singleton.getInstance().userToken = ""

        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)

        activity.finish()*/
    }

    fun setupStatusBar(activity: Activity) {
        val window = activity.window

        // 1️⃣ Enable edge-to-edge so status bar becomes transparent
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 2️⃣ Force BLUE status bar
        window.statusBarColor = activity.getColor(R.color.colorPrimary) // Color.parseColor("#0081E0")
        window.navigationBarColor =  activity.getColor(R.color.white) //Color.parseColor("#ffffff")

        // 3️⃣ Force WHITE icons
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.isAppearanceLightStatusBars = false    // false = white icons


        // 4️⃣ Handle system insets + keyboard + real status bar height

    }

    fun setupStatusBarGradient(activity: Activity) {
        val window = activity.window

        // 1️⃣ Enable edge-to-edge so status bar becomes transparent
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 2️⃣ Force BLUE status bar
        //window.statusBarColor = activity.getColor(R.color.colorPrimary) // Color.parseColor("#0081E0")
        window.navigationBarColor =  activity.getColor(R.color.white) //Color.parseColor("#ffffff")

        // 3️⃣ Force WHITE icons
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.isAppearanceLightStatusBars = true    // false = white icons


        // 4️⃣ Handle system insets + keyboard + real status bar height

    }


    private var progressToastLayout: View? = null
    fun showPleaseWaitToast(activity: Activity, message: String = "Please wait...") {
        activity.runOnUiThread {
            if (progressToastLayout != null) return@runOnUiThread

            try {
                val inflater = activity.layoutInflater
                val layout = inflater.inflate(R.layout.common_toast2, null)

                val toastLayout = layout.findViewById<CardView>(R.id.toastLayout)
                val toastProgressBar = layout.findViewById<ProgressBar>(R.id.toastProgressBar)
                val toastText = layout.findViewById<TextView>(R.id.toastText)

                toastText.text = message
                toastLayout.setCardBackgroundColor(
                    ColorStateList.valueOf(activity.getColor(R.color.pleaseWaitToast))
                )

                val rootView = activity.window.decorView as ViewGroup
                val marginTop = 60f.dpToPx(activity)
                val marginHorizontal = 24f.dpToPx(activity)
                val params = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.TOP
                    topMargin = marginTop.toInt()
                    leftMargin = marginHorizontal.toInt()
                    rightMargin = marginHorizontal.toInt()
                }
                layout.layoutParams = params

                layout.translationY = -layout.height.toFloat() - 200f
                layout.alpha = 0f

                rootView.addView(layout)
                progressToastLayout = layout

                // Disable touch
                //To turn Off-screen touch
                //activity.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

                layout.post {
                    layout.animate()
                        .alpha(1f)
                        .translationY(0f)
                        .setDuration(450)
                        .start()
                }

            } catch (ex: Exception) {
                Log.d(TAG, "showPleaseWaitToast exception: $ex")
            }
        }
    }


    private var progressToastParent: ViewGroup? = null
    fun showPleaseWaitToastNew(
        activity: Activity,
        message: String = "Please wait...",
        targetWindow: Window = activity.window
    ) {
        activity.runOnUiThread {
            if (progressToastLayout != null) return@runOnUiThread

            try {
                val layout = activity.layoutInflater.inflate(
                    R.layout.common_toast2,
                    null
                )

                val toastLayout = layout.findViewById<CardView>(R.id.toastLayout)
                val toastText = layout.findViewById<TextView>(R.id.toastText)

                toastText.text = message

                toastLayout.setCardBackgroundColor(
                    ColorStateList.valueOf(
                        activity.getColor(R.color.pleaseWaitToast)
                    )
                )

                val rootView = targetWindow.decorView as ViewGroup

                val marginTop = 40f.dpToPx(activity)
                val marginHorizontal = 24f.dpToPx(activity)

                val params = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.TOP
                    topMargin = marginTop.toInt()
                    leftMargin = marginHorizontal.toInt()
                    rightMargin = marginHorizontal.toInt()
                }

                layout.layoutParams = params
                layout.alpha = 0f

                rootView.addView(layout)
                layout.bringToFront()

                layout.elevation = 100f.dpToPx(activity)

                progressToastLayout = layout
                progressToastParent = rootView

                layout.post {
                    layout.translationY = -layout.height.toFloat() - 200f

                    layout.animate()
                        .alpha(1f)
                        .translationY(0f)
                        .setDuration(450)
                        .start()
                }

            } catch (ex: Exception) {
                Log.d(TAG, "showPleaseWaitToast exception: $ex")
            }
        }
    }

    fun hidePleaseWaitToast(activity: Activity) {
        activity.window.decorView.postDelayed({
            if (!activity.isFinishing && !activity.isDestroyed) {
                try {
                    progressToastLayout?.let { layout ->
                        val rootView = activity.window.decorView as ViewGroup
                        layout.animate()
                            .alpha(0f)
                            .translationY(-layout.height.toFloat() - 200f)
                            .setDuration(300)
                            .withEndAction {
                                rootView.removeView(layout)
                                progressToastLayout = null

                                //To turn On screen touch
                                //activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                            }
                            .start()
                    }
                } catch (ex: Exception) {
                    Log.d(TAG, "hidePleaseWaitToast exception: $ex")
                    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                }
            }
        }, 500)
    }


    /*fun hidePleaseWaitToast(activity: Activity) {
        activity.runOnUiThread {
            try {
                progressToastLayout?.let { layout ->
                    val rootView = activity.window.decorView as ViewGroup
                    layout.animate()
                        .alpha(0f)
                        .translationY(-layout.height.toFloat() - 200f)
                        .setDuration(300)
                        .withEndAction {
                            rootView.removeView(layout)
                            progressToastLayout = null

                            // Re-enable touch
                            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                        }
                        .start()
                }
            } catch (ex: Exception) {
                Log.d(TAG, "hidePleaseWaitToast exception: $ex")
            }
        }
    }*/

    fun showAnimatedToast(
        activity: Activity,
        message: String,
        toastIsOf: String
    ) {
        try {
            val inflater = activity.layoutInflater
            val layout = inflater.inflate(R.layout.common_toast, null)

            val toastLayout = layout.findViewById<CardView>(R.id.toastLayout)
            //val toastLine = layout.findViewById<CardView>(R.id.toastLine)
            val toastIcon = layout.findViewById<ImageView>(R.id.toastIcon)
            val toastText = layout.findViewById<TextView>(R.id.toastText)
            //(toastLayout.background as GradientDrawable).setColor(activity.getColor(R.color.white))

            //val toastIsOf1 = "warning"
            when (toastIsOf) {
                "success" -> {
                    toastIcon.setImageResource(R.drawable.ic_success)
                    toastText.text = message
                    toastLayout.setCardBackgroundColor(ColorStateList.valueOf(activity.getColor(R.color.toastSuccessBackground)))
                    //toastLine.setCardBackgroundColor(ColorStateList.valueOf(activity.getColor(R.color.paymentCredit)))
                }
                "warning" -> {
                    toastIcon.setImageResource(R.drawable.ic_warning)
                    toastText.text = message
                    toastLayout.setCardBackgroundColor(ColorStateList.valueOf(activity.getColor(R.color.toastWarningBackground)))
                    //toastLine.setCardBackgroundColor(ColorStateList.valueOf(activity.getColor(R.color.paymentCredit)))
                }
                "fail" -> {
                    toastIcon.setImageResource(R.drawable.ic_error)
                    toastText.text = message
                    toastLayout.setCardBackgroundColor(ColorStateList.valueOf(activity.getColor(R.color.toastFailureBackground)))
                    //toastLine.setCardBackgroundColor(ColorStateList.valueOf(activity.getColor(R.color.paymentDebit)))
                }
            }



            val isFadeAnimationOrSlideFromBottom = "top"// top / fadebottom / slideupdownfrombottom

            if (isFadeAnimationOrSlideFromBottom == "top") {
                val rootView = activity.window.decorView as ViewGroup

                val marginTop = 60f.dpToPx(activity)
                val marginHorizontal = 24f.dpToPx(activity)

                val params = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.TOP
                    topMargin = marginTop.toInt()
                    leftMargin = marginHorizontal.toInt()
                    rightMargin = marginHorizontal.toInt()
                }

                layout.layoutParams = params

                // Start above screen & fully transparent
                layout.translationY = -layout.height.toFloat() - 200f
                layout.alpha = 0f

                rootView.addView(layout)

                layout.post {

                    val endY = 0f  // natural position inside margin

                    // Fade-in + slide down
                    layout.animate()
                        .alpha(1f)                   // fade in
                        .translationY(endY)          // slide down
                        .setDuration(450)
                        .withEndAction {

                            layout.postDelayed({

                                // Fade-out + slide up
                                layout.animate()
                                    .alpha(0f)                       // fade out
                                    .translationY(-layout.height.toFloat() - 200f) // slide up
                                    .setDuration(450)
                                    .withEndAction {
                                        rootView.removeView(layout)
                                    }
                                    .start()

                            }, 1500)
                        }
                        .start()
                }
            }
            else if(isFadeAnimationOrSlideFromBottom == "fadebottom"){
                val rootView = activity.window.decorView as ViewGroup

                // Layout params for bottom placement
                val marginBottom = 100f.dpToPx(activity)
                val marginHorizontal = 24f.dpToPx(activity)

                val params = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.BOTTOM
                    leftMargin = marginHorizontal.toInt()
                    rightMargin = marginHorizontal.toInt()
                    bottomMargin = marginBottom.toInt()
                }

                layout.layoutParams = params

// Start fully transparent
                layout.alpha = 0f

                rootView.addView(layout)

// Fade-in
                layout.animate()
                    .alpha(1f)
                    .setDuration(400)
                    .withEndAction {

                        layout.postDelayed({

                            // Fade-out
                            layout.animate()
                                .alpha(0f)
                                .setDuration(400)
                                .withEndAction {
                                    rootView.removeView(layout)
                                }
                                .start()

                        }, 1500)
                    }
                    .start()


            }
            else if(isFadeAnimationOrSlideFromBottom == "slideupdownfrombottom"){
                val rootView = activity.window.decorView as ViewGroup

                // Set layout params with left/right margins before adding to root
                val marginBottom = 100f.dpToPx(activity)
                val marginHorizontal = 24f.dpToPx(activity)
                val params = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    leftMargin = marginHorizontal.toInt()
                    rightMargin = marginHorizontal.toInt()
                }
                layout.layoutParams = params

                // Start off-screen immediately
                layout.translationY = rootView.height.toFloat()

                // Add to root view after params and translationY are set
                rootView.addView(layout)

                layout.post {
                    val endY = rootView.height - layout.height - marginBottom

                    // Animate up
                    layout.animate()
                        .translationY(endY)
                        .setDuration(500)
                        .withEndAction {
                            layout.postDelayed({
                                // Animate down
                                layout.animate()
                                    .translationY(rootView.height.toFloat())
                                    .setDuration(500)
                                    .withEndAction {
                                        rootView.removeView(layout)
                                    }.start()
                            }, 2000)
                        }.start()
                }
            }
        }
        catch (ex: Exception){
            Log.d(TAG, "showAnimatedToast catch ex = $ex")
        }


        /*if(isFadeAnimationOrSlideFromBottom == "top"){
            val rootView = activity.window.decorView as ViewGroup

            val marginTop = 60f.dpToPx(activity)
            val marginHorizontal = 24f.dpToPx(activity)

            val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.TOP
                topMargin = marginTop.toInt()
                leftMargin = marginHorizontal.toInt()
                rightMargin = marginHorizontal.toInt()
            }

            layout.layoutParams = params

            // Start above the screen
            layout.translationY = -layout.height.toFloat() - 200f

            rootView.addView(layout)

            layout.post {

                // 👇 Important: end at Y = 0 (inside the topMargin space)
                val endY = 0f

                layout.animate()
                    .translationY(endY)
                    .setDuration(450)
                    .withEndAction {

                        layout.postDelayed({

                            // Slide up again
                            layout.animate()
                                .translationY(-layout.height.toFloat() - 200f)
                                .setDuration(450)
                                .withEndAction {
                                    rootView.removeView(layout)
                                }
                                .start()

                        }, 2000)
                    }
                    .start()
            }
        }*/

    }


    // Helper extension to convert dp to px
    fun Float.dpToPx(context: Context): Float {
        return this * context.resources.displayMetrics.density
    }
    // Extension for Int
    fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }


    lateinit var appUpdateManager: AppUpdateManager
    lateinit var installStateUpdatedListener: InstallStateUpdatedListener
    var IMMEDIATE: String = "IMMEDIATE"

    fun checkInAppUpdate(activity: Activity, mainLayout: View?, RC_APP_UPDATE: Int, UPDATE_MODE: String) {

        appUpdateManager = AppUpdateManagerFactory.create(activity)

        installStateUpdatedListener = installStateUpdateListener(activity, appUpdateManager, mainLayout)!!
        appUpdateManager.registerListener(installStateUpdatedListener)

        appUpdateManager.appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->

                // 🔥 FORCE ONLY IMMEDIATE UPDATE
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                ) {
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            AppUpdateType.IMMEDIATE,
                            activity,
                            RC_APP_UPDATE
                        )
                    } catch (e: SendIntentException) {
                        e.printStackTrace()
                    }
                }

                // 🔥 RESUME UPDATE if already in progress
                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        activity,
                        RC_APP_UPDATE
                    )
                }
            }
    }

    fun installStateUpdateListener(activity: Activity, appUpdateManager: AppUpdateManager, mainLayout: View?): InstallStateUpdatedListener? {
        installStateUpdatedListener = object : InstallStateUpdatedListener {
            override fun onStateUpdate(state: InstallState) {
                if (state.installStatus() == InstallStatus.DOWNLOADED) {
                    //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                    popupSnackbarForCompleteUpdate(mainLayout, appUpdateManager, activity)
                } else if (state.installStatus() == InstallStatus.INSTALLED) {
                    if (appUpdateManager != null) {
                        appUpdateManager.unregisterListener(installStateUpdatedListener)
                    }
                } else { Log.i("My InstallListner", "InstallStateUpdatedListener: state: " + state.installStatus())
                }
            }
        }
        return installStateUpdatedListener
    }

    fun popupSnackbarForCompleteUpdate(
        mainLayout: View?,
        appUpdateManager: AppUpdateManager?,
        activity: Activity
    ) {
        val rootView = activity.findViewById<View?>(android.R.id.content)

        if (rootView == null) {
            Log.d(TAG, "update Root view is null, cannot show Snackbar")
            return
        }

        val snackbar = Snackbar.make(rootView, "New update is ready!", Snackbar.LENGTH_INDEFINITE)

        //Snackbar snackbar = Snackbar.make(mainLayout, "New update is ready!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (appUpdateManager != null) {
                    appUpdateManager.completeUpdate()
                }
            }
        })

        snackbar.setActionTextColor(activity.getResources().getColor(R.color.white))
        snackbar.show()
    }

    fun onActivityResultForUpdate(
        activity: Activity,
        requestCode: Int,
        resultCode: Int,
        RC_APP_UPDATE: Int,
        mainLayout: View?
    ): Int {

        if (requestCode == RC_APP_UPDATE) {

            if (resultCode != Activity.RESULT_OK) {
                Log.d(TAG, "Update cancelled or failed")
                Toast.makeText(activity, "Update is required to continue", Toast.LENGTH_LONG).show()
                checkInAppUpdate(activity, mainLayout, RC_APP_UPDATE, IMMEDIATE)
                return 0
            }
        }
        return 1
    }

    fun showHideButtonLoading(mainView:View, contentView:View, loaderView:View, doWhatShowOrHide:String){
        if (doWhatShowOrHide == "show"){
            mainView.isEnabled = false
            mainView.isClickable = false
            contentView.visibility = View.GONE
            loaderView.visibility = View.VISIBLE
        }
        else{
            contentView.visibility = View.VISIBLE
            loaderView.visibility = View.GONE
            mainView.isEnabled = true
            mainView.isClickable = true
        }
    }






}