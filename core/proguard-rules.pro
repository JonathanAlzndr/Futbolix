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
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-keep class com.example.futbolix.core.di.CoreModuleKt { *; }
-keep class com.example.futbolix.core.data.PlayerRepository { *; }
-keep class com.example.futbolix.core.domain.model.** { *; }
-keep interface com.example.futbolix.core.domain.repository.IPlayerRepository { *; }
-keep class com.example.futbolix.core.domain.usecase.PlayerInteractor { *; }
-keep interface com.example.futbolix.core.domain.usecase.PlayerUseCase { *; }
-keep interface com.example.futbolix.core.ui.PlayerAdapter$OnItemClickCallback { *; }
-keep class com.example.futbolix.core.ui.PlayerAdapter { *; }
-keep class com.example.futbolix.core.utils.Result$Error { *; }
-keep class com.example.futbolix.core.utils.Result$Loading { *; }
-keep class com.example.futbolix.core.utils.Result$Success { *; }
-keep class com.example.futbolix.core.utils.Result { *; }
-keep class com.example.futbolix.core.utils.DataMapper{ *; }
