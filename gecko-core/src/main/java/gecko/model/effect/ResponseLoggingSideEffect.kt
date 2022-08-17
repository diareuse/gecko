package gecko.model.effect

import gecko.Logger
import gecko.model.Response
import gecko.model.ResponseDecorated

/**
 * Logs response as soon as it gets created.
 * Whenever is supplied with empty body, it give user feedback with `<empty-body>`.
 * */
internal class ResponseLoggingSideEffect(origin: Response) : ResponseDecorated(origin) {

    init {
        Logger.log("<- $code")
        Logger.log(body.decodeToString().ifBlank { "<empty-body>" })
    }

}

internal fun Response.withLogging(): Response {
    return ResponseLoggingSideEffect(this)
}