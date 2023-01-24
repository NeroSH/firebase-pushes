package ru.anvarov.firebasepushes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

// получить MainActivity Intent с параметрами
inline fun Context.intentForMainActivityWithClear(vararg params: Pair<String, Any?>): Intent {
    val intent = Intent().setClassName(
        this,
        BuildConfig.APPLICATION_ID + "MainActivity"
    )
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

    if (params.isNotEmpty()) {
        intent.setParamsExtra(params)
    }

    return intent
}


// установка параметров в extra
fun Intent.setParamsExtra(params: Array<out Pair<String, Any?>>) {
    params.forEach {
        when (val value = it.second) {
            null -> this.putExtra(it.first, null as Serializable?)
            is Int -> this.putExtra(it.first, value)
            is Long -> this.putExtra(it.first, value)
            is CharSequence -> this.putExtra(it.first, value)
            is String -> this.putExtra(it.first, value)
            is Float -> this.putExtra(it.first, value)
            is Double -> this.putExtra(it.first, value)
            is Char -> this.putExtra(it.first, value)
            is Short -> this.putExtra(it.first, value)
            is Boolean -> this.putExtra(it.first, value)
            is Serializable -> this.putExtra(it.first, value)
            is Bundle -> this.putExtra(it.first, value)
            is Parcelable -> this.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> this.putExtra(it.first, value)
                value.isArrayOf<String>() -> this.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> this.putExtra(it.first, value)
            }
            is IntArray -> this.putExtra(it.first, value)
            is LongArray -> this.putExtra(it.first, value)
            is FloatArray -> this.putExtra(it.first, value)
            is DoubleArray -> this.putExtra(it.first, value)
            is CharArray -> this.putExtra(it.first, value)
            is ShortArray -> this.putExtra(it.first, value)
            is BooleanArray -> this.putExtra(it.first, value)
        }

        return@forEach
    }
}

// установка параметров в bundle
fun Bundle.setParamsExtra(params: Array<out Pair<String, Any?>>) {
    params.forEach {
        when (val value = it.second) {
            is Int -> this.putInt(it.first, value)
            is Long -> this.putLong(it.first, value)
            is CharSequence -> this.putCharSequence(it.first, value)
            is String -> this.putString(it.first, value)
            is Float -> this.putFloat(it.first, value)
            is Double -> this.putDouble(it.first, value)
            is Char -> this.putChar(it.first, value)
            is Short -> this.putShort(it.first, value)
            is Boolean -> this.putBoolean(it.first, value)
            is Serializable -> this.putSerializable(it.first, value)
            is Parcelable -> this.putParcelable(it.first, value)
            is IntArray -> this.putIntArray(it.first, value)
            is LongArray -> this.putLongArray(it.first, value)
            is FloatArray -> this.putFloatArray(it.first, value)
            is DoubleArray -> this.putDoubleArray(it.first, value)
            is CharArray -> this.putCharArray(it.first, value)
            is ShortArray -> this.putShortArray(it.first, value)
            is BooleanArray -> this.putBooleanArray(it.first, value)
        }

        return@forEach
    }
}
