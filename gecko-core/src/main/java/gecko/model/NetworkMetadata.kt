package gecko.model

/**
 * Helper class wrapping both Request and Response to a singular data entry.
 * @see Request
 * @see Response
 * */
data class NetworkMetadata(
    val request: Request,
    val response: Response
)
