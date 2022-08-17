package gecko.android.notification

import androidx.core.app.NotificationManagerCompat
import gecko.android.notification.startup.GeckoStartup.Companion.context

object NotificationDispatcherFactory {

    fun create(): NotificationDispatcher {
        var dispatcher: NotificationDispatcher
        dispatcher = NotificationDispatcherDefault(NotificationManagerCompat.from(context), context)
        dispatcher = NotificationDispatcherBuffering(dispatcher)
        return dispatcher
    }

}