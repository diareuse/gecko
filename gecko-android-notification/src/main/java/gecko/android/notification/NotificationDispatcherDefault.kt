package gecko.android.notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.checkSelfPermission

class NotificationDispatcherDefault(
    private val manager: NotificationManagerCompat,
    private val context: Context
) : NotificationDispatcher {

    private val intent = Intent()
        .setComponent(ComponentName(context.packageName, "gecko.ui.GeckoActivity"))
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
        notification.setContentIntent(resolvePendingIntent())
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || hasPermission())
            manager.notify(1337, notification.build())
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasPermission(permission: String = Manifest.permission.POST_NOTIFICATIONS) =
        checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    private fun exists() = try {
        context.packageManager.queryIntentActivities(intent, 0).size > 0
    } catch (e: NameNotFoundException) {
        false
    }

    private fun resolvePendingIntent() = if (exists()) {
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    } else {
        null
    }

}