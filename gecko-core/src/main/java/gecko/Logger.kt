package gecko

import gecko.util.loadServices

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

    companion object : Logger {

        override fun log(message: String) {
            for (logger in loadServices<Logger>()) {
                logger.log(message)
            }
        }

    }

}