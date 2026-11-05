# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.shockwave.**#pdf

-keep class com.pnsol.sdk.newland.listener.** { *; }
-keep class com.pnsol.sdk.auth.** {*;}
-keep class com.pnsol.sdk.payment.PaymentInitialization {*;}
-keep class com.pnsol.sdk.payment.PaymentProcessThread {*;}
-keep class com.pnsol.sdk.payment.PaymentModeThread {*;}
-keep class com.pnsol.sdk.payment.** {*;}
-keep class com.pnsol.sdk.qpos.listener.QPOSListener {*;}
-keep class com.pnsol.sdk.payment.MiuraUpdates {*;}
-keep class com.pnsol.sdk.interfaces.** {*;}
-keep class com.pnsol.sdk.enums.** {*;}
-keep class com.pnsol.sdk.miura.emv.tlv.ISOUtil {*;}
-keep class com.pnsol.sdk.miura.response.ResponseManager {*;}
-keep class com.pnsol.sdk.miura.messages.Message {*;}
-keep class com.pnsol.sdk.usb.USBClass{*;}
-keep class com.dspread.xpos.BLE.** { *; }
-keep class com.pnsol.sdk.util.** { *; }
-keep class com.newland.mpos.payswiff.**{*;}
-keep class org.codehaus.jackson.** { *; }
-keep class com.pnsol.sdk.n910.** { *; }
-keep class com.pnsol.sdk.n3.** { *; }
-keep class android.a.a.** { *; }
-keep class android.misc.** { *; }
-keep class android.newland.** { *; }
-keep class android.psam.** { *; }
-keep class com.a.a.** { *; }
-keep class com.newland.** { *; }
-keep class com.nlscan.android.scan.** { *; }



-keep class org.xmlpull.v1** { *;}
-dontwarn org.xmlpull.v1.**

-dontwarn org.w3c.dom.**
-dontwarn org.joda.time.**
-dontwarn org.shaded.apache.**
-dontwarn org.ietf.jgss.**
-dontwarn com.firebase.**
-dontnote com.firebase.client.core.GaePlatform

-keepattributes Signature
-keepattributes *Annotation*
-keepattributes InnerClasses,EnclosingMethod

-keep class com.firebase** { *; }

-keepnames class com.fasterxml.jackson** { *; }
-keepnames class javax.servlet** { *; }
-keepnames class org.ietf.jgss** { *; }

-keep class com.loopj.android** { *; }
-keep interface com.loopj.android** { *; }
-dontobfuscate
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable


# rxjava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}

# support design
-dontwarn android.support.design.**
-keep class android.support.design** { *; }
-keep interface android.support.design** { *; }
-keep public class android.support.design.R$* { *; }


# retrofit
-dontwarn okio.**
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp** { *; }
-keep interface com.squareup.okhttp** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

#your package path where your gson models are stored
-keep class com.abc.model** { *; }

# Keep these for GSON and Jackson
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keep class com.google.gson** { *; }

# Crashlitycs 2.+
-keep class com.crashlytics** { *; }
-keep class com.crashlytics.android**
-keepattributes SourceFile, LineNumberTable, *Annotation*
# If you are using custom exceptions, add this line so that custom exception types are skipped during obfuscation:
-keep public class * extends java.lang.Exception
# For Fabric to properly de-obfuscate your crash reports, you need to remove this line from your ProGuard config:
# -printmapping mapping.txt

# Picasso
-dontwarn com.squareup.okhttp.**

# OkHttp3
-keep class okhttp3** { *; }
-keep interface okhttp3** { *; }
-dontwarn okhttp3.**

# Needed for Parcelable/SafeParcelable Creators to not get stripped
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}