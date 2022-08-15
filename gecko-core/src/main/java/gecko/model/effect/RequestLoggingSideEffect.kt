package gecko.model.effect

import gecko.Logger
import gecko.model.Request
import gecko.model.RequestDecorated

internal class RequestLoggingSideEffect(
    origin: Request,
    logger: Logger = Logger
) : RequestDecorated(origin) {

    init {
        logger.log("-> $method | $url")
        logger.log(body.decodeToString().ifBlank { "<empty-body>" })
    }

}

internal fun Request.withLogging(logger: Logger = Logger): Request {
    return RequestLoggingSideEffect(this, logger)
}