package gecko.android.notification

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationDispatcherDefault(
    private val manager: NotificationManagerCompat,
    context: Context
) : NotificationDispatcher {

    private val notificationText = NotificationCompat.BigTextStyle()
        .setBigContentTitle("Gecko!")
        .bigText("")

    private val notification = NotificationCompat.Builder(context, NotificationChannelManager.getOrCreateChannel().id)
        .setContentTitle("Gecko!")
        .setContentText("Awaiting network requests…")
        .setStyle(notificationText)
        .setOnlyAlertOnce(true)
        .setProgress(0, 0, true)
        .setAutoCancel(false)
        .setOngoing(true)
        .setSmallIcon(R.drawable.ic_stat_gecko)

    init {
        notify("Gecko!", "Awaiting network requests…")
    }

    override fun notify(title: String, text: String) {
        notificationText.bigText(text).setBigContentTitle(title)
        notification.setStyle(notificationText)
        notification.setProgress(0, 0, false)
        notification.setContentText("Expand to view requests")
        manager.notify(1337, notification.build())
    }

}