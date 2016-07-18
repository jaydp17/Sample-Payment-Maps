# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/jaydp/SDKs/android_sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepattributes JavascriptInterface
-keep public class com.instamojo.android.network.JavaScriptInterface
-keep public class * implements com.instamojo.android.network.JavaScriptInterface
-keepclassmembers class com.instamojo.android.network.JavaScriptInterface{
    <methods>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

# Keep source file names, line numbers for easier debugging
-keepattributes SourceFile,LineNumberTable

-keepattributes Signature
-dontwarn com.squareup.**
-dontwarn okio.**

# OkHttp
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

# apache http
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient

# Juspay rules
-keep class in.juspay.** {*;}
-dontwarn in.juspay.**

# support class
-keep class android.support.v4.** { *; }
-keep class android.support.v7.** { *; }