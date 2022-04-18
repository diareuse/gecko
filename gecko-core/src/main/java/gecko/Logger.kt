package gecko

/**
 * Wraps system logging.
 * */
fun interface Logger {

    /**
     * Requests underlying implementation to log provided [message]. It does not rely, but expects
     * that the messages are logged in the same chronological order as provided to the underlying
     * implementation.
     * */
    fun log(message: String)

}