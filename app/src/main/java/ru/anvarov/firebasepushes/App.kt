package ru.anvarov.firebasepushes

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().setDeliveryMetricsExportToBigQuery(true)
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            val token = it.result
            Log.d("Nero", "App token: $token")
        }
    }
}