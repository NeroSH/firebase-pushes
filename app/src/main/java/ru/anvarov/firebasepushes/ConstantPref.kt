package ru.anvarov.firebasepushes

import com.chibatching.kotpref.KotprefModel
import ru.anvarov.firebasepushes.Constants.NOTIFICATION_ID

object ConstantPref : KotprefModel() {
    var channelId by stringPref(NOTIFICATION_ID)
}