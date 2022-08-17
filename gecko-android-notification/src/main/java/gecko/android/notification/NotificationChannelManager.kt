package gecko.android.notification

import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import gecko.android.notification.startup.GeckoStartup.Companion.context

internal object NotificationChannelManager {

    private fun getChannel(): NotificationChannelCompat {
        val channelId = context.packageName + ".gecko"
        return NotificationChannelCompat.Builder(channelId, NotificationManagerCompat.IMPORTANCE_LOW)
            .setName("Gecko")
            .setDescription("Provides overview of requests and responses from intercepted API endpoints")
            .setShowBadge(false)
            .setVibrationEnabled(false)
            .build()
    }

    fun getOrCreateChannel(): NotificationChannelCompat {
        val manager = NotificationManagerCompat.from(context)
        val channels = manager.notificationChannelsCompat
        val channel = getChannel()
        if (channels.any { it.id == channel.id }) {
            return channel
        }

        manager.createNotificationChannel(channel)
        return channel
    }

}