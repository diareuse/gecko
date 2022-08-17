package gecko.android.notification

import java.util.concurrent.atomic.AtomicInteger

object NotificationController {

    private val incomingCount = AtomicInteger(0)
    private val outgoingCount = AtomicInteger(0)
    private val dispatcher: NotificationDispatcher = NotificationDispatcherFactory.create()

    private val title
        get() = "Gecko! | → ${outgoingCount.get()} | ← ${incomingCount.get()}"

    fun incoming(text: String) {
        incomingCount.incrementAndGet()
        dispatcher.notify(title = title, text = text)
    }

    fun outgoing(text: String) {
        outgoingCount.incrementAndGet()
        dispatcher.notify(title = title, text = text)
    }

}