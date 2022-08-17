package gecko.android.notification

class NotificationDispatcherBuffering(
    private val origin: NotificationDispatcher
) : NotificationDispatcher {

    private val lines = mutableListOf<String>()

    override fun notify(title: String, text: String) {
        lines.add(0, text)
        while (lines.size > 10) {
            lines.removeAt(lines.size - 1)
        }
        origin.notify(title, lines.joinToString(separator = "\n"))
    }

}