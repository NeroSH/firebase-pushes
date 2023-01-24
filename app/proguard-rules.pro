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

# BEGIN ReactiveNetwork
-dontwarn com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
-dontwarn io.reactivex.functions.Function
-dontwarn rx.internal.util.**
-dontwarn sun.misc.Unsafe
# END ReactiveNetwork

# BEGIN Retrofit2
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy INSTANCE.
-keepattributes Exceptions
# END Retrofit2

# BEGIN PersistentCookieJar
-dontwarn com.franmontiel.persistentcookiejar.**
-keep class com.franmontiel.persistentcookiejar.**

-keep class com.getcompass.globalsearch.** { *; }
-keepnames class com.getcompass.globalsearch.** { *; }

# one of the three files returns an error
-keep class com.getcompass.videocall.janus.** { *; }
-keepnames class com.getcompass.videocall.janus.** { *; }

-keep class com.facebook.** { *; }
-keepnames class com.facebook.** { *; }
-keep class org.webrtc.** { *; }
-keepnames class org.webrtc.** { *; }

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# END PersistentCookieJar

# BEGIN Android-FilePicker
# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
# GlideWebpDecoder
-keep public class com.bumptech.glide.integration.webp.WebpImage { *; }
-keep public class com.bumptech.glide.integration.webp.WebpFrame { *; }
-keep public class com.bumptech.glide.integration.webp.WebpBitmapFactory { *; }
# support-v7-appcompat
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}
# support-design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
# END Android-FilePicker

-keep class com.chibatching.kotpref.** { *; }
-keep class com.getcompass.core.preferences.** { *; }

# BEGIN ReactiveNetwork
-dontwarn com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
-dontwarn io.reactivex.functions.Function
-dontwarn rx.internal.util.**
-dontwarn sun.misc.Unsafe
# END ReactiveNetwork

-keep class com.facebook.yoga.** { *; }
-keep class * extends com.chibatching.kotpref.KotprefModel
-keep class com.getcompass.network.** { *; }
-keep class com.getcompass.core.api.** { *; }
-keep class com.getcompass.core.db.entities.** { *; }
-keep class com.getcompass.core.videocalls.** { *; }

-keep interface kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader
-keep class kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoaderImpl
-keep class kotlin.reflect.jvm.internal.impl.load.java.FieldOverridabilityCondition
-keep class kotlin.reflect.jvm.internal.impl.load.java.ErasedOverridabilityCondition
-keep class kotlin.reflect.jvm.internal.impl.load.java.JavaIncompatibilityRulesOverridabilityCondition

-if class **$Companion extends **
-keep class <2>
-if class **$Companion implements **
-keep class <2>

-keep class com.getcompass.android.ui.screen.core.** { *; }
-keep class * implements com.getcompass.android.ui.screen.core.ScreenFragment

-keep class kotlin.Metadata { *; }

-keep class com.getcompass.messages.MessagesInputFragment
-keep class com.getcompass.thread.inputfragment.ThreadInputFragment
-keep class com.getcompass.proposal.inputfragment.ProposalInputFragment
-keep class com.getcompass.quoterepost.inputfragment.QuoteAndRepostInputFragment
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

-keep class com.getcompass.employeecard.** { *; }

-keep class com.getcompass.calendar.LocalUtils
-keep class com.getcompass.common.utils.** { *; }
#-keep class com.getcompass.calendar.CalendarGenerator
#-keep class com.getcompass.android.CalendarUtils

#-keep class com.facebook.litho.utils.** { *; }

-dontwarn kotlin.**

# RxJava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-ignorewarnings
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.huawei.hianalytics.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

-optimizationpasses 5
# this seems to be enough

# remove indirection
-allowaccessmodification

# move everything into the root package
-repackageclasses ""

# remove source file names
-renamesourcefileattribute ""

# keep Parcelable Creators due to crappy interface design
-keepclassmembers class * implements android.os.Parcelable {
  public static final ** CREATOR;
}

# suppress warnings
-dontwarn android.support.**

# remove runtime assertions, they are enforced in compile-time by Kotlin compiler
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
  public static void checkExpressionValueIsNotNull(java.lang.Object, java.lang.String);
  public static void checkFieldIsNotNull(java.lang.Object, java.lang.String);
  public static void checkFieldIsNotNull(java.lang.Object, java.lang.String, java.lang.String);
  public static void checkNotNull(java.lang.Object);
  public static void checkNotNull(java.lang.Object, java.lang.String);
  public static void checkNotNullExpressionValue(java.lang.Object, java.lang.String);
  public static void checkNotNullParameter(java.lang.Object, java.lang.String);
  public static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
  public static void checkReturnedValueIsNotNull(java.lang.Object, java.lang.String);
  public static void checkReturnedValueIsNotNull(java.lang.Object, java.lang.String, java.lang.String);
}