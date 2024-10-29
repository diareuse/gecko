package gecko.android.notification

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.checkSelfPermission

class NotificationDispatcherDefault(
    private val manager: NotificationManagerCompat,
    private val context: Context
) : NotificationDispatcher {

    private val notificationText = NotificationCompat.BigTextStyle()
        .setBigContentTitle("Gecko!")
        .bigText("")

    private val notification =
        NotificationCompat.Builder(context, NotificationChannelManager.getOrCreateChannel().id)
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

    @SuppressLint("MissingPermission")
    override fun notify(title: String, text: String) {
        notificationText.bigText(text).setBigContentTitle(title)
        notification.setStyle(notificationText)
        notification.setProgress(0, 0, false)
        notification.setContentText("Expand to view requests")
        if (hasPermission())
            manager.notify(1337, notification.build())
    }

    private fun hasPermission(permission: String = Manifest.permission.POST_NOTIFICATIONS) =
        checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

}