package ru.anvarov.firebasepushes

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject
import ru.anvarov.firebasepushes.Constants.PUSH_BODY
import ru.anvarov.firebasepushes.Constants.PUSH_TAG
import ru.anvarov.firebasepushes.Constants.PUSH_TITLE

class PushService : FirebaseMessagingService() {
    companion object {
        private val TAG = "Nero"
    }
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val intentString = getStringFromIntent(message.toIntent())
        Log.d(TAG, "onMessageReceived =>\tbundle -> $intentString")

        if (message.data.isNotEmpty()) {
            try {
                val data = JSONObject(message.data as Map<*, *>)
                setMessageNotification(data)
            } catch (t: Throwable) {
                Log.e("Error", "Error parsing push - ${t.localizedMessage}")
            }
        }
    }

    // подготовка пуша с сообщением
    private fun setMessageNotification(dataJsonObject: JSONObject) {
        val pushTitle = dataJsonObject.getString(PUSH_TITLE)
        val pushBody = dataJsonObject.getString(PUSH_BODY)
        val pushTag = dataJsonObject.getString(PUSH_TAG)
        Log.w("Nero", """body: $dataJsonObject
            |pushTitle=$pushTitle
            |pushBody=$pushBody
            |pushTag=$pushTag
        """.trimMargin())

        val pendingIntent = this.createNotificationIntent(
            pushTitle,
            pushBody,
            pushTag,
        )

        val notification = NotificationCompat
            .Builder(this, ConstantPref.channelId)
            .setContentTitle(pushTitle)
            .setContentText(pushBody)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.mipmap.ic_launcher
                )
            )
            .setSmallIcon(R.drawable.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle().bigText(pushBody))
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).notify(pushTag.hashCode(), notification)
    }

    private fun createNotificationIntent(
        title: String,
        body: String,
        tag: String
    ): PendingIntent {
        val intent = this.intentForMainActivityWithClear(
            PUSH_TITLE to title,
            PUSH_BODY to body
        )
        intent.action = Constants.MESSAGE

        return PendingIntent.getActivity(
            /* context = */ this,
            /* requestCode = */ tag.hashCode(),
            /* intent = */ intent,
            /* flags = */ PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT
        )
    }

    private fun getStringFromIntent(intent: Intent): String {
        val extras = StringBuilder("\n{").apply {
            append(intent.extras?.keySet()
                ?.map {
                    if (it == "data") "\n\t\"$it\": ${intent.extras?.get(it)}"
                    else "\n\t\"$it\": \"${intent.extras?.get(it)}\""
                }
                ?.joinToString { it })
            append("\n}")
        }
        return extras.toString()
    }
}