package ru.anvarov.firebasepushes

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.anvarov.firebasepushes.Constants.BADGE_ON
import ru.anvarov.firebasepushes.Constants.MESSAGE
import ru.anvarov.firebasepushes.Constants.NOTIFICATION_CHANNEL_TITLE
import ru.anvarov.firebasepushes.Constants.NOTIFICATION_ID
import ru.anvarov.firebasepushes.Constants.PUSH_BODY
import ru.anvarov.firebasepushes.Constants.PUSH_TITLE
import ru.anvarov.firebasepushes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent?.action == MESSAGE) {
            val title = if (intent.hasExtra(PUSH_TITLE)) {
                intent.getStringExtra(PUSH_TITLE)
            } else {
                null
            }
            binding.tvTitle.text = title

            val body = if (intent.hasExtra(PUSH_BODY)) {
                intent.getStringExtra(PUSH_BODY)
            } else {
                null
            }
            binding.tvBody.text = body
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
    }

    // создание канала уведомлений
    private fun createNotificationChannel() {

        // запоминаем старый id для сравнения после замены в префах
        val oldChannelId = ConstantPref.channelId

        if (oldChannelId == NOTIFICATION_ID
            || !oldChannelId.contains(BADGE_ON)
        ) {
            val name = NOTIFICATION_CHANNEL_TITLE
            val description = "Описание канала уведомлений"
            val importance = NotificationManager.IMPORTANCE_HIGH
            ConstantPref.channelId = "$NOTIFICATION_ID ${(1000000..9999999).random()} $BADGE_ON"
            val channel = NotificationChannel(ConstantPref.channelId, name, importance)

            channel.let {
                it.description = description
                it.setShowBadge(true)
                it.enableLights(true)
                it.enableVibration(true)
            }
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)

            // удаляем старый канал
            deleteOldChannel(oldChannelId)
        }
    }

    // создание канала уведомлений
    private fun deleteOldChannel(channelId: String) {
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .deleteNotificationChannel(channelId)
    }
}